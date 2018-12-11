package com.coder.db;

import java.sql.Connection;
import java.sql.DriverManager;

import com.coder.config.Config;

public class Conn {
    
	private static Connection con = null;
	
	public  static Connection getConnection(){
		try{
		     String db_url = Config.ReaderConfigInfo("DB.db_conn");
		     String db_classname = Config.ReaderConfigInfo("DB.db_classname");
		     String db_user = Config.ReaderConfigInfo("DB.db_user");
		     String db_passwd = Config.ReaderConfigInfo("DB.db_passwd");
		     Class.forName(db_classname);   
	         con=DriverManager.getConnection(db_url, db_user, db_passwd);   
		     
		}catch(Exception e){
			e.printStackTrace();
		}
		return con;
	}
	
	
}
