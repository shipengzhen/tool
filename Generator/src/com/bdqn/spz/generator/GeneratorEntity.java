package com.bdqn.spz.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

import javax.sound.midi.SysexMessage;

/**
 * 生成实体类
 * 
 * @author 施鹏振
 *
 */
public class GeneratorEntity {

    private String packageOutPath = ConfigManager.getInstance().getValue("packageOutPath");// 指定实体生成所在包的路径

    private String authorName = ConfigManager.getInstance().getValue("authorName");// 作者名字  

    protected String tableName;// 表名 

    private String[] colnames;//  列名数组

    private String[] colTypes;// 列名类型数组

    private String[] comments;// 注释数据

    private int[] colSizes;// 列名大小数组

    private boolean f_util = false;//  是否需要导入包java.util.*

    private boolean f_sql = false; //  是否需要导入包java.sql.*  

    private String url = null;

    private BaseDao baseDao = new BaseDao();

    private ResultSet resultSet = null;

    /**
     * 将字符串首字母变成小写
     * 
     * @param string
     * @return String
     */
    private String firstSmall(String string) {
        return string.substring(0, 1).toLowerCase() + string.substring(1);
    }

    /**
     * @功能描述:使用substring()方法截取0-pos之间的字符串+pos之后的字符串，相当于将要把要删除的字符串删除
     * @参数说明：@param s
     * @参数说明：@param pos
     * @参数说明：@return
     * @作者： shipengzhen
     * @创建时间：2018年4月26日 上午11:00:06
     */
    public String removeCharAt(String s, int pos) {

        return s.substring(0, pos) + s.substring(pos + 1);
    }

    /**
     * 构造函数  
     */
    public void getEntity() {
        // 查要生成实体类的表  
        String sql = null;
        url = ConfigManager.getInstance().getValue("url").substring(0, 11);
        if (url.equals("jdbc:oracle")) {
            sql = "select * from \"" + tableName + "\"";
        } else if (url.equals("jdbc:mysql:")) {
            sql = "select * from `" + tableName + "`";
        } else {
            sql = "select * from " + tableName;
        }
        try {
            ResultSetMetaData rsmd = baseDao.getPreparedStatement(sql).getMetaData();
            int size = rsmd.getColumnCount();// 统计列
            colnames = new String[size];
            colTypes = new String[size];
            colSizes = new int[size];
            comments = new String[size];
            for (int i = 0; i < size; i++) {
                String colname = firstSmall(rsmd.getColumnName(i + 1));
                for (int j = 0; j < colname.length(); j++) {
                    if ('#'==colname.charAt(j)) {
                        colname=removeCharAt(colname,j);
                    }
                }
                colnames[i] = colname;
                colTypes[i] = rsmd.getColumnTypeName(i + 1);
                if (url.equals("jdbc:mysql:")) {
                    sql = "select column_comment from information_schema.columns "
                            + "where table_name=? and column_name=?";
                    Object[] objects = { tableName, colnames[i] };
                    resultSet = baseDao.getResultSet(sql, objects);
                    while (resultSet.next()) {
                        String column_comment = resultSet.getString("column_comment");
                        comments[i] = column_comment;
                    }
                }

                if ("datetime".equalsIgnoreCase(colTypes[i])) {
                    f_util = true;
                }
                if ("image".equalsIgnoreCase(colTypes[i]) || "text".equalsIgnoreCase(colTypes[i])) {
                    f_sql = true;
                }
                if ("date".equalsIgnoreCase(colTypes[i]) || "timestamp".equalsIgnoreCase(colTypes[i])) {
                    f_util = true;
                }
                if ("blob".equalsIgnoreCase(colTypes[i])) {
                    f_sql = true;
                }
                colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
            }
            String content = parse(colnames, colTypes, colSizes);
            try {
                File directory = new File("");
                // String path = this.getClass().getResource("").getPath();
                // System.out.println("绝对路径："+directory.getAbsolutePath());
                // System.out.println("相对路径："+directory.getCanonicalPath());  
                // String outputPath = directory.getAbsolutePath()+ "/src/"+path.substring(path.lastIndexOf("/com/", path.length()), path.length()) + initcap(tableName) + ".java";
                
                String outputPath = directory.getAbsolutePath() + "\\src\\" + this.packageOutPath.replace(".", "\\");
                directory=new File(outputPath);
                if (!directory.exists()) {
                    directory.mkdir();
                }
                outputPath=outputPath+"\\"+baseDao.firstBig(tableName) + ".java";
                System.err.println(outputPath);
                FileWriter fw = new FileWriter(outputPath);
                PrintWriter pw = new PrintWriter(fw);
                pw.println(content);
                pw.flush();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baseDao.closeConnection();
        }
    }

    /**
     * 生成实体类主体代码
     * 
     * @param colnames
     * @param colTypes
     * @param colSizes
     * @return String
     */
    private String parse(String[] colnames, String[] colTypes, int[] colSizes) {
        StringBuffer sb = new StringBuffer();
        try {
            sb.append("package " + this.packageOutPath + ";\n\n");
            // 判断是否导入工具包  
            if (f_util) {
                sb.append("import java.util.Date;\n\n");
                f_util = false;
            }
            if (f_sql) {
                sb.append("import java.sql.*;\n\n");
                f_sql = false;
            }
            // 注释部分  
            sb.append("/**\n");
            sb.append(" * " + tableName);
            if (url.equals("jdbc:mysql:")) {
                String sql = "select table_comment from information_schema.tables where table_name=?";
                resultSet = baseDao.getResultSet(sql, tableName);
                while (resultSet.next()) {
                    if (!resultSet.getString("table_comment").equalsIgnoreCase("")) {
                        sb.append("(" + resultSet.getString("table_comment") + ")");
                    }
                }
            }
            sb.append("实体类\n");
            sb.append(" * " + new Date() + " " + this.authorName + "\n");
            sb.append(" */ \n");
            // 实体部分  
            sb.append("public class " + baseDao.firstBig(tableName) + "{\n");
            processAllAttrs(sb);// 属性  
            processAllMethod(sb);// get set方法
            sb.append("}\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baseDao.closeConnection();
        }
        return sb.toString();
    }

    /**
     * 生成所有字段
     * 
     * @param sb
     */
    private void processAllAttrs(StringBuffer sb) {
        for (int i = 0; i < colnames.length; i++) {
            // 字段注释
            if (url.equals("jdbc:mysql:")) {
                if (!comments[i].equals("")) {
                    sb.append("\n\t//" + comments[i]);
                }
            }
            sb.append("\n\tprivate " + sqlType2JavaType(colTypes[i]) + " " + colnames[i] + ";\n");
        }
    }

    /**
     * 生成所有get、set方法
     * 
     * @param sb
     */
    private void processAllMethod(StringBuffer sb) {
        for (int i = 0; i < colnames.length; i++) {
            if (url.equals("jdbc:mysql:")) {
                if (!comments[i].equalsIgnoreCase("")) {
                    sb.append("\n\t/**\n\t * " + comments[i] + "\n\t * @param " + colnames[i] + "\n\t */");
                }
            }
            sb.append("\n\tpublic void set" + baseDao.firstBig(colnames[i]) + "(" + sqlType2JavaType(colTypes[i]) + " "
                    + colnames[i] + "){\n");
            sb.append("\t\tthis." + colnames[i] + "=" + colnames[i] + ";\n");
            sb.append("\t}\n");
            if (url.equals("jdbc:mysql:")) {
                if (!comments[i].equalsIgnoreCase("")) {
                    sb.append("\n\t/**\n\t * " + comments[i] + "\n\t * @return " + sqlType2JavaType(colTypes[i]) + " "
                            + colnames[i] + "\n\t */");
                }
            }
            sb.append("\n\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + baseDao.firstBig(colnames[i]) + "(){\n");
            sb.append("\t\treturn " + colnames[i] + ";\n");
            sb.append("\t}\n");
        }
    }

    /**
     * 获取字段数据类型
     * 
     * @param sqlType
     * @return
     */
    private String sqlType2JavaType(String sqlType) {
        if ("bit".equalsIgnoreCase(sqlType)) {
            return "boolean";
        } else if ("tinyint".equalsIgnoreCase(sqlType)) {
            return "byte";
        } else if ("smallint".equalsIgnoreCase(sqlType)) {
            return "short";
        } else if ("int".equalsIgnoreCase(sqlType)) {
            return "Integer";
        } else if ("bigint".equalsIgnoreCase(sqlType)) {
            return "long";
        } else if ("float".equalsIgnoreCase(sqlType)) {
            return "float";
        } else if ("decimal".equalsIgnoreCase(sqlType) || "numeric".equalsIgnoreCase(sqlType)
                || "real".equalsIgnoreCase(sqlType) || "money".equalsIgnoreCase(sqlType)
                || "smallmoney".equalsIgnoreCase(sqlType)) {
            return "double";
        } else if ("varchar".equalsIgnoreCase(sqlType) || "char".equalsIgnoreCase(sqlType)
                || "nvarchar".equalsIgnoreCase(sqlType) || "nchar".equalsIgnoreCase(sqlType)
                || "text".equalsIgnoreCase(sqlType)) {
            return "String";
        } else if ("datetime".equalsIgnoreCase(sqlType)) {
            return "Date";
        } else if ("image".equalsIgnoreCase(sqlType)) {
            return "Blod";
        } else if ("binary_double".equalsIgnoreCase(sqlType)) {
            return "double";
        } else if ("binary_float".equalsIgnoreCase(sqlType)) {
            return "float";
        } else if ("blob".equalsIgnoreCase(sqlType)) {
            return "byte[]";
        } else if ("clob".equalsIgnoreCase(sqlType)) {
            return "byte[]";
        } else if ("char".equalsIgnoreCase(sqlType) || "nvarchar2".equalsIgnoreCase(sqlType)
                || "varchar2".equalsIgnoreCase(sqlType)) {
            return "String";
        } else if ("date".equalsIgnoreCase(sqlType) || "timestamp".equalsIgnoreCase(sqlType)
                || "timestamp with local time zone".equalsIgnoreCase(sqlType)
                || "timestamp with time zone".equalsIgnoreCase(sqlType)) {
            return "Date";
        } else if ("number".equalsIgnoreCase(sqlType)) {
            return "Long";
        }
        return "String";
    }
}
