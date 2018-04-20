package com.bdqn.spz.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

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
				colnames[i] = firstSmall(rsmd.getColumnName(i + 1));
				colTypes[i] = rsmd.getColumnTypeName(i + 1);
				if (url.equals("jdbc:mysql:")) {
					sql = "select column_comment from information_schema.columns "
							+ "where table_name=? and column_name=?";
					Object[] objects = { tableName, colnames[i] };
					resultSet = baseDao.getResultSet(sql, objects);
					while (resultSet.next()) {
						comments[i] = resultSet.getString("column_comment");
					}
				}
				if (colTypes[i].equalsIgnoreCase("datetime")) {
					f_util = true;
				}
				if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")) {
					f_sql = true;
				}
				if (colTypes[i].equalsIgnoreCase("date") || colTypes[i].equalsIgnoreCase("timestamp")) {
					f_util = true;
				}
				if (colTypes[i].equalsIgnoreCase("blob")) {
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
				String outputPath = directory.getAbsolutePath() + "/src/" + this.packageOutPath.replace(".", "/") + "/"
						+ baseDao.firstBig(tableName) + ".java";
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
		if (sqlType.equalsIgnoreCase("bit")) {
			return "boolean";
		} else if (sqlType.equalsIgnoreCase("tinyint")) {
			return "byte";
		} else if (sqlType.equalsIgnoreCase("smallint")) {
			return "short";
		} else if (sqlType.equalsIgnoreCase("int")) {
			return "Integer";
		} else if (sqlType.equalsIgnoreCase("bigint")) {
			return "long";
		} else if (sqlType.equalsIgnoreCase("float")) {
			return "float";
		} else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
				|| sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
				|| sqlType.equalsIgnoreCase("smallmoney")) {
			return "double";
		} else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
				|| sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
				|| sqlType.equalsIgnoreCase("text")) {
			return "String";
		} else if (sqlType.equalsIgnoreCase("datetime")) {
			return "Date";
		} else if (sqlType.equalsIgnoreCase("image")) {
			return "Blod";
		} else if (sqlType.equalsIgnoreCase("binary_double")) {
			return "double";
		} else if (sqlType.equalsIgnoreCase("binary_float")) {
			return "float";
		} else if (sqlType.equalsIgnoreCase("blob")) {
			return "byte[]";
		} else if (sqlType.equalsIgnoreCase("blob")) {
			return "byte[]";
		} else if (sqlType.equalsIgnoreCase("char") || sqlType.equalsIgnoreCase("nvarchar2")
				|| sqlType.equalsIgnoreCase("varchar2")) {
			return "String";
		} else if (sqlType.equalsIgnoreCase("date") || sqlType.equalsIgnoreCase("timestamp")
				|| sqlType.equalsIgnoreCase("timestamp with local time zone")
				|| sqlType.equalsIgnoreCase("timestamp with time zone")) {
			return "Date";
		} else if (sqlType.equalsIgnoreCase("number")) {
			return "Long";
		}
		return null;
	}
}
