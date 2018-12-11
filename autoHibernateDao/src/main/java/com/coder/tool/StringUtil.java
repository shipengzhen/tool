package com.coder.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringUtil {
    //约定表名字段名统一命名规则  ： 表名，字段名 只能用小写字母和下划线组成,可以出现多个下划线分隔字母例如：music_siner_info
	
	public static String FormatStr(String  str){
		return str==null?"":str;
	}
	
	/**
	 * format TableName to ObjectName  不能逆向转换
	 * java类名  set后面的名称
	 * */
	public static String FormatDataToObjectNameFirstUpperCase(String name)throws Exception{
		String r = "";
	    
		if("".equals(FormatStr(name)))throw new Exception("FormatDataToObjectName 异常:参数不能为null");
		if(name.length() == 1) throw new Exception("FormatDataToObjectName 异常:参数长度必须大于1位");
		name = name.toLowerCase();
		if(name.indexOf("_") != -1){
			String t1 = "";
			String t2 = "";
			do{
				t1 = name.substring(0, name.indexOf("_"));
				t1 = FormatFristtoCase(t1);
				
				t2 = name.substring(name.indexOf("_") + 1,name.length());
				t2 = FormatFristtoCase(t2);
				
				r = t1 + t2;
				name = r;
			}while(r.indexOf("_") != -1);
		}else{
			r = FormatFristtoCase(name);
		}
		return r;
	}
	/**
	 * format TableName to ObjectName  不能逆向转换
	 * 属性名称//按照hibernate mapping规则
	 * */
	public static String FormatDataToObjectNameFirstLowerCase(String name)throws Exception{
		String r = "";
		
		if("".equals(FormatStr(name)))throw new Exception("FormatDataToObjectName 异常:参数不能为null");
		name = name.toLowerCase();
		if(name.length() == 1) throw new Exception("FormatDataToObjectName 异常:参数长度必须大于1位");

		if(name.indexOf("_") != -1){
			String t1 = "";
			String t2 = "";
			do{
				t1 = name.substring(0, name.indexOf("_"));
				//t1 = FormatFristtoCase(t1);
				if(!t1.equals("")&&t1.length()==1){
					t1 = t1.toUpperCase();
				}
				t2 = name.substring(name.indexOf("_") + 1,name.length());
				if(!t2.equals("") && t2.length() > 1){
				   t2 = FormatFristtoCase(t2);
				}else{
				   t2 = t2.toUpperCase();
				}
				r = t1 + t2;
				name = r;
			}while(r.indexOf("_") != -1);
		}else{
			r = FormatFristtoCaseXX(name);
		}
		return r;
	}
	/*
	//format OjbectName to TableName
	public static String FormatObjectToDataName(String name)throws Exception{
        String r = "";
	    
		if("".equals(FormatStr(name)))throw new Exception("FormatObjectToDataName 异常:参数不能为null");
		if(name.length() == 1) throw new Exception("FormatObjectToDataName 异常:参数长度必须大于1位");
		
		
		return "";
	}
	*/
	//转换一个单词的首字母位大写
	public static String FormatFristtoCase(String name)throws Exception{
		//System.out.println(name);
		if("".equals(FormatStr(name)))throw new Exception("FormatFristtoCase 异常:参数不能为null");
		//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>:::" + name);
		//if(name.length() == 1) throw new Exception(name+ "FormatFristtoCase 异常:参数长度必须大于1位");
		return name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
	}
	public static String FormatFristtoCaseXX(String name)throws Exception{
		//System.out.println(name);
		if("".equals(FormatStr(name)))throw new Exception("FormatFristtoCase 异常:参数不能为null");
		if(name.length() == 1) throw new Exception(name + "FormatFristtoCase 异常:参数长度必须大于1位");
		return name.substring(0, 1).toLowerCase() + name.substring(1, name.length());
	}
	public static List FormatListFiledName(List lists)throws Exception{
		List list = new ArrayList();
		for(int i = 0 ; lists != null && i < lists.size() ; i++)
		{
			String mname = (String)lists.get(i);
			list.add(FormatDataToObjectNameFirstLowerCase(mname));
		}
		return list;
	}
	public static Map FormatMapFiledName(List lists)throws Exception{
		Map map = new HashMap();
		for(int i = 0 ; lists != null && i < lists.size() ; i++)
		{
			String mname = (String)lists.get(i);
			
			String hname = FormatDataToObjectNameFirstLowerCase(mname);
			map.put(mname, hname);
		}
		return map;
	}
	public static void main(String args[]){
		try{
			/*ORACLE 属性
			String sst = FormatDataToObjectNameFirstLowerCase("LINK_PIC");
			String sst1 = FormatDataToObjectNameFirstLowerCase("AAAA_DDDD");
			String sst2 = FormatDataToObjectNameFirstLowerCase("AAA_BBB_CC_DDDD");
			String sst3 = FormatDataToObjectNameFirstLowerCase("V_FIELD");
			String sst4 = FormatDataToObjectNameFirstLowerCase("D_E_F_G");
			*/
			
			String sst =  FormatDataToObjectNameFirstUpperCase("LINK_PIC");
			String sst1 = FormatDataToObjectNameFirstUpperCase("AAAA_DDDD");
			String sst2 = FormatDataToObjectNameFirstUpperCase("AAA_BBB_CC_DDDD");
			String sst3 = FormatDataToObjectNameFirstUpperCase("V_FIELD");
			String sst4 = FormatDataToObjectNameFirstUpperCase("D_E_F_G");
			
			System.out.println(sst);
			System.out.println(sst2);
			System.out.println(sst3);
			System.out.println(sst4);
			System.out.println(sst1);
		}catch(Exception e){
			
		}
	}
	/**
	 * 得到自定义表的主键生成算法集合
	 * */
	public static List getCustomConfigPk(String key_set)throws Exception{
		List keySetList = new ArrayList();
		if(key_set != null && !key_set.equals("")){
			String[] keys = key_set.split(",");
			for(int i  = 0 ; i < keys.length ; i++){
				String[][] keysinfo = new String[1][2];
				String info = keys[i];
				if(info.indexOf("/") == -1)throw new Exception("配置文件参数错误,请检查config.xml文件中pk_set项的数据定义格式");
				String arrayinfo[] = info.split("/");
				keysinfo[0][0] = arrayinfo[0];
				keysinfo[0][1] = arrayinfo[1];
				keySetList.add(keysinfo);
			}
		}
		return keySetList;
	}
}