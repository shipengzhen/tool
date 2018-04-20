package com.bdqn.spz.tools.dao;

/**
 * 读取*.properties配置文件配合ConfigManager(单列)使用
 * @author 施鹏振
 *
 */
public class Proper {

	private static String configFile = "spz.properties";
	
	
	
	/**
	 * 一个文件获取一个值
	 * @param configFile
	 * @param key
	 * @return String
	 */
	public static String getProperties(String configFile,String key){
		return ConfigManager.getInstance(configFile).getValue(key);
	}
	
	/**
	 * 两个文件获取一个值
	 * @param configFile
	 * @param key1
	 * @param key2
	 * @return String
	 */
	public static String getString(String configFile,String key1,String key2){
		configFile=getProperties(configFile,key1);
		return ConfigManager.getInstance(configFile).getValue(key2);
	}
	
	/**
	 * 两个文件获取一个值
	 * @param key1
	 * @param key2
	 * @return String
	 */
	public static String getString(String key1,String key2){
		configFile=getProperties(configFile,key1);
		return ConfigManager.getInstance(configFile).getValue(key2);
	}
}
