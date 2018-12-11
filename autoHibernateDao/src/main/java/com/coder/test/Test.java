package com.coder.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coder.config.Config;
import com.coder.db.ColumnUtil;
import com.coder.db.DataColunm;
import com.coder.db.DataUtil;
import com.coder.db.TableUtil;
import com.coder.template.FreeMakerUtil;
import com.coder.tool.StringUtil;
import com.coder.vo.ClassInfo;
import com.coder.vo.Po;

public class Test {
	public static void main(String[] args) {
		
		try{
			List aa = TableUtil.getTableList();
			List xmlfile = new ArrayList();
			String defaultKey = Config.ReaderConfigInfo("DB.default_key");
			String key_set = Config.ReaderConfigInfo("DB.pk_set");
			if(defaultKey==null || defaultKey.equals(""))defaultKey="uuid.hex";
			List keySetList = StringUtil.getCustomConfigPk(key_set);
			
			for(int i = 0 ; i < aa.size() ; i++){
				String table = aa.get(i).toString();
				List bb = ColumnUtil.getColumnList(table);
				List polist = new ArrayList();
				List tylist = new ArrayList();//datatype list
				List filist = new ArrayList();//fieldName list
				for(int p = 0 ; p < bb.size() ; p++){
					DataColunm col = (DataColunm)bb.get(p);
				    String columnName = col.getColumnName();
				    String dataType = col.getColunmType();
				    String len = col.getColumnLength();
				    String javatype = col.getCol_javaType();
				    if(javatype != null && javatype.equals("mysql")){
				    	javatype = DataUtil.FormatMySqlDataType(dataType);
				    }
				    
					Po o = new Po();
				    o.setIsPk(""+col.isPrimaryKey());
				    o.setFname(StringUtil.FormatDataToObjectNameFirstLowerCase(columnName));
				    o.setFieldSet(StringUtil.FormatDataToObjectNameFirstUpperCase(columnName));
				    o.setFtype(javatype);
				    o.setColumnName(columnName);
				    o.setSql_type(dataType);
				    o.setKey_set(defaultKey);
				    for(int pks = 0 ; keySetList != null && pks < keySetList.size() ; pks++)
				    {
				    	String[][] tab = (String[][])keySetList.get(pks);
				    	if(table.toLowerCase().equals(tab[0][0])){
				    		o.setKey_set(tab[0][1]);
				    	}
				    }
				    //计算字段长度
				    if(len != null && !len.equals("")){
				    	if(len.indexOf(",") != -1){
				    		String ar[] = len.split(",");
				    		o.setColumnLength(ar[0]);
				    		o.setColumnLengthIn(ar[1]);
				    	}else{
				    		o.setColumnLength(len);
				    	}
				    }
				    polist.add(o);
				    //
				}
				// test write vo.java
				String vopath = System.getProperty("user.dir")+"\\src\\main\\java\\"+Config.ReaderConfigInfo("PROJECT.packagepath").replace(".", "\\");
				System.out.println(vopath);
				String packagepath = Config.ReaderConfigInfo("PROJECT.packagepath");
				System.out.println(packagepath);
				String dataBase = Config.ReaderConfigInfo("DB.db_database");
				String fileName = StringUtil.FormatDataToObjectNameFirstUpperCase(table);
				
				ClassInfo po = new ClassInfo();
				po.setClassName(fileName);
				po.setTableName(table);
				po.setPagckdir(packagepath);
				po.setParentClassName("ValueObject");
				po.setDataBase(dataBase);
				
				//----------------create vo dao daoimpl bhm.xml 
				Map root = new HashMap();
	        	root.put("objs", po);
	        	root.put("polist", polist);
	        	System.out.println("正在生成...  " + vopath + "/" + fileName + ".java 文件");
	        	FreeMakerUtil.WriteFileToPath("vo.ftl", vopath,vopath+ "/" + fileName + ".java", root);//create vo
	        	System.out.println("正在生成...  " + vopath + "/" + fileName + ".hbm.xml 文件");
	        	FreeMakerUtil.WriteFileToPath("hbx.ftl", vopath,vopath+ "/" + fileName + ".hbm.xml", root);
	        	System.out.println("正在生成...  " + vopath + "/" + fileName + "Dao.java 文件");
	        	FreeMakerUtil.WriteFileToPath("dao.ftl", vopath,vopath+ "/" + fileName + "Dao.java", root);//create dao
	        	System.out.println("正在生成...  " + vopath + "/" + fileName + "DaoImpl.java 文件");
	        	FreeMakerUtil.WriteFileToPath("impl.ftl", vopath+"/impl",vopath+ "/impl/" + fileName + "DaoImpl.java", root); //create daoimpl
	        	//----------------end -------------------------------
            	//end
                //transaction.ftl
	        	String pa = packagepath.replace('.','/') + "/" + fileName + ".hbm.xml";;
	        	po.setHbmFile(pa);
	        	xmlfile.add(po);
	        	 
			}	
			//只生成一次
			//-----------------init application.xml-----------------------
        	String driver = Config.ReaderConfigInfo("DB.db_classname");
        	String url = Config.ReaderConfigInfo("DB.db_conn");
        	String user = Config.ReaderConfigInfo("DB.db_user");
        	String pass = Config.ReaderConfigInfo("DB.db_passwd");
        	String configoutpath = Config.ReaderConfigInfo("PROJECT.configfilepath");
            Map config_root = new HashMap();
            config_root.put("driver", driver);
            config_root.put("url", url);
            config_root.put("user", user);
            config_root.put("pass", pass);
           
            config_root.put("xmlfile", xmlfile);
            FreeMakerUtil.WriteFileToPath("application.ftl", configoutpath,configoutpath+ "/" + "application" + ".xml", config_root);//create application config
        	//-----------------end init aplication.xml--------------------
            
            //-----------------init transaction.xml
            FreeMakerUtil.WriteFileToPath("transaction.ftl", configoutpath,configoutpath+ "/" + "transaction" + ".xml", config_root);//create application config
            //-----------------init transaction.xml end
            
            //-----------------init dao_context---------------
            FreeMakerUtil.WriteFileToPath("dao_context.ftl", configoutpath,configoutpath+ "/" + "dao_context" + ".xml", config_root);//create application config
            
            //------------------create CommonContextUtil
            String vopath = Config.ReaderConfigInfo("PROJECT.vooutpath");
            String packages =  Config.ReaderConfigInfo("PROJECT.packagepath");
            config_root.put("packagepath", packages);
            
            FreeMakerUtil.WriteFileToPath("CommonContextUtil.ftl", vopath,vopath+ "/" + "CommonContextUtil" + ".java", config_root);//create application config
            
            //-------------------create DaoCollection
            
            FreeMakerUtil.WriteFileToPath("DaoCollection.ftl", vopath,vopath+ "/" + "DaoCollection" + ".java", config_root);//create application config
            
            //------------------create ValueObject
            FreeMakerUtil.WriteFileToPath("ValueObject.ftl", vopath,vopath+ "/" + "ValueObject" + ".java", config_root);//create application config
            
            System.out.println("db文件,生成成功!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
