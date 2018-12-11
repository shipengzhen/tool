package com.coder.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.coder.config.Config;

public class ColumnUtil {
	
	public static List getColumnList(String tableName)throws Exception{
		List list = new ArrayList();
		//try{
			String sql = "";
			String db_type = Config.ReaderConfigInfo("DB.db_type");
			if(db_type != null && db_type.equals("mysql")){
				//sql = "show columns from " + tableName;
				sql = "select * from "+ tableName;
			}else if(db_type != null && db_type.equals("oracle")){
				sql = "select * from "+ tableName +" where rownum=1";
			}
			
			Connection con = Conn.getConnection();
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			
			if(db_type != null && db_type.equals("mysql")){
				int isHavePk = 0;
				/*
				while(rs.next()){
					String c_name = rs.getString("Field");
					String c_type = rs.getString("Type");
					String c_leng = DataUtil.getColumnLength(c_type);
					boolean c_ispk = false;
					if(rs.getString("Key").equals("PRI")){
						c_ispk = true;
						isHavePk++;
					}
					//
					DataColunm col = new DataColunm();
					col.setColumnName(c_name);
					col.setColunmType(c_type);
					col.setPrimaryKey(c_ispk);
					col.setColumnLength(c_leng);
					col.setCol_javaType("mysql");
					list.add(col);
				}
					*/
				ResultSetMetaData metadata = rs.getMetaData();
				String pk = getColumnPrimaryKeys(tableName);
				for(int i=1;i<=metadata.getColumnCount();i++){
					String c_name = metadata.getColumnName(i);
					String c_type = metadata.getColumnTypeName(i);
					int c_lengs = metadata.getColumnDisplaySize(i);
					String c_leng = "";
					if(c_lengs > 0) {
						c_leng = String.valueOf(c_lengs);
					}
					String java_dataType = metadata.getColumnClassName(i);
					if(c_type != null && c_type.equals("BLOB")){
						//java_dataType = "java.lang.String";
						//c_leng = "";
						throw new Exception("错误信息: " + tableName + "表中存在BLOB数据类型,当前版本不支持BLOB类型映射!");
					}else if(c_type != null && c_type.equals("LONGBLOB")){
						//java_dataType = "java.lang.String";
						//c_leng = "";
						throw new Exception("错误信息: " + tableName + "表中存在LONGBLOB数据类型,当前版本不支持LONGBLOB类型映射!");
					}
					
					//System.out.println(c_leng+":ooooooooooooooooooo>  "+metadata.getColumnLabel(i)+" <ooooooooooooooooo:" + c_name);
					boolean c_ispk = false;
					
					if(c_name.equals(pk)){
						c_ispk = true;
						isHavePk++;
					}
					//
					DataColunm col = new DataColunm();
					col.setColumnName(c_name);
					col.setColunmType(c_type);
					col.setPrimaryKey(c_ispk);
					col.setColumnLength(c_leng);
					col.setCol_javaType(java_dataType);
					list.add(col);
                }
				if(isHavePk == 0)throw new Exception("错误信息: 表:" + tableName + "没有设置主键,工具不能生成实体类!");
			}else if(db_type != null && db_type.equals("oracle")){
				String pk = getColumnPrimaryKeys(tableName.toUpperCase());
				ResultSetMetaData metadata = rs.getMetaData();
				int isHavePk = 0;
				for(int i=1;i<=metadata.getColumnCount();i++){
					String c_name = metadata.getColumnName(i);
					String c_type = metadata.getColumnTypeName(i);
					int c_leng = metadata.getColumnDisplaySize(i);
					String java_dataType = metadata.getColumnClassName(i);
					if(java_dataType != null && java_dataType.equals("oracle.sql.CLOB")){
						//java_dataType = "java.lang.String";
						throw new Exception("错误信息: " + tableName + "表中存在CLOB数据类型,当前版本不支持CLOB类型映射!");
					}else if(java_dataType != null && java_dataType.equals("oracle.sql.BLOB")){
						//java_dataType = "java.lang.String";
						throw new Exception("错误信息: " + tableName + "表中存在BLOB数据类型,当前版本不支持BLOB类型映射!");
					}
					boolean c_ispk = false;
					if(c_name.equals(pk)){
						c_ispk = true;
						isHavePk++;
					}
					DataColunm col = new DataColunm();
					col.setColumnName(c_name);
					col.setColunmType(c_type.toLowerCase());
					col.setPrimaryKey(c_ispk);
					col.setColumnLength(String.valueOf(c_leng));
					col.setCol_javaType(java_dataType);
					list.add(col);
                }
				if(isHavePk == 0)throw new Exception("错误信息: 表:" + tableName + "没有设置主键,工具不能生成实体类!");
			}
		//}catch(Exception e){
		//	e.printStackTrace();
		//}
		
		return list;
	}
	
	public static String getColumnPrimaryKeys(String tableName){
		String p_name = "";
		try{
			Connection con = Conn.getConnection();
			DatabaseMetaData dbmd =  con.getMetaData();
			ResultSet rs = dbmd.getPrimaryKeys(null,null,tableName);
			
			while(rs.next()){
				
				p_name = rs.getString(4);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return p_name;
	}
}
