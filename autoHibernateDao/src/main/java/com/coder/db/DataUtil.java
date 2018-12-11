package com.coder.db;

import java.util.ArrayList;
import java.util.List;

public class DataUtil {
    /**
     * 其它常用数据类型可以慢慢补在下面的函数中
     * */
	public static String FormatMySqlDataType(String dataType)throws Exception{
		
		String r = "";
		if(dataType.indexOf("bigint") != -1){
			r = "java.lang.Long";
		}else if(dataType.indexOf("varchar") != -1){
			r = "java.lang.String";
		}else if(dataType.indexOf("date") != -1){
			r = "java.util.Date";
		}else if(dataType.indexOf("int") != -1){
			r = "java.lang.Integer";
		}else if(dataType.indexOf("double") != -1){
			r = "java.lang.Double";
		}else if(dataType.indexOf("decimal") != -1){
			r = "java.lang.Double";
		}else{
			r = "java.lang.String";
		}
		//
		return r;
	}
	
	public static String getColumnLength(String dataType)throws Exception{
		   String r = "";
		   if(dataType.indexOf("(") != -1){
			   r = dataType.substring(dataType.indexOf("(")+1, dataType.indexOf(")"));	   
		   }else{
			   if(dataType.equals("date")){
				   r = "10";
			   }else if(dataType.equals("datetime")){
				   r = "19";
			   }else if(dataType.equals("double")){
				   r = "22,0";
			   }else if(dataType.equals("blob")){
				   r = "3000";
			   }
		   }
		   return r;
		}
	public static List FormatListMySqlDataType(List list)throws Exception{
		List rlist = new ArrayList();
		
		for(int i = 0 ; list != null && i < list.size() ; i++){
			String r[][] = new String[1][2];
			String dataType = (String)list.get(i);
			r[0][0] = dataType;
			r[0][1] = FormatMySqlDataType(dataType);
			rlist.add(r);
		}
	    return rlist;
	}
	/**
	 * 
	 * 
	 * 
	 */
public static String FormatOracleDataType(String dataType)throws Exception{
		
	    if(dataType!=null && !dataType.equals(""))dataType.toLowerCase();
		String r = "";
		if(dataType.equals("number")){
			r = "java.lang.Long";
		}else if(dataType.equals("float")){
			r = "java.lang.Float";
		}else if(dataType.equals("number")){
			r = "java.util.Date";
		}else if(dataType.equals("number")){
			r = "java.lang.Integer";
		}else if(dataType.equals("number")){
			r = "java.lang.Double";
		}else if(dataType.equals("number")){
			r = "java.lang.Double";
		}else{
			r = "java.lang.String";
		}
		//
		return r;
	}
	
	
}
