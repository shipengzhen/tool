package com.coder.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.coder.config.Config;

public class TableUtil {
      
	//
	public static List getTableList(){
		List list = new ArrayList();
		try{
			
			String sql = "";
			String db_type = Config.ReaderConfigInfo("DB.db_type");
			String database = Config.ReaderConfigInfo("DB.db_database");
			if(db_type != null && db_type.equals("mysql")){
				sql = "show tables from " + database;
			}else if(db_type != null && db_type.equals("oracle")){
				sql = "select * from user_tables";
			}
			Connection con = Conn.getConnection();
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
				list.add(rs.getString(1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
}
