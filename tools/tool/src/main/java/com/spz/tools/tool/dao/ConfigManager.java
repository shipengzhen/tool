package com.spz.tools.tool.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ��ȡ�����ļ��Ĺ�����-����ģʽ
 * @author ʩ����
 *
 */
public class ConfigManager {

	private static Properties properties;

	//�����ļ�·��
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
	
	// ˽�й�����-��ȡ���ݿ������ļ�
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
	 * ����key��ȡֵ
	 * 
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		return properties.getProperty(key);
	}
}
