package com.bdqn.spz.tools.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件的工具类-单例模式
 * @author 施鹏振
 *
 */
public class ConfigManager {

	private static Properties properties;

	//配置文件路径
	private static String configFile = "database.properties";

	public static ConfigManager getInstance() {
		return InnerConfigManager.configManager;
	}

	public static ConfigManager getInstance(String configFile) {
		ConfigManager.configFile =configFile;
		return InnerConfigManager.configManager;
	}
	
	static class InnerConfigManager {
		static ConfigManager configManager = new ConfigManager();
	}
	
	// 私有构造器-读取数据库配置文件
	private ConfigManager() {
		try {
			properties = new Properties();
			InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream(configFile);
			properties.load(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 传入key获取值
	 * 
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		return properties.getProperty(key);
	}
}
