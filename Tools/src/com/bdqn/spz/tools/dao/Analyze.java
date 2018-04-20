package com.bdqn.spz.tools.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 解析
 * @author 施鹏振
 *
 */
public class Analyze {
	public void getAnalyze(){
		try {
			Properties properties=new Properties();
			String configFile="database.properties";//配置文件路径
			//加载配置文件到输入流
			InputStream is=BaseDao.class.getClassLoader().getResourceAsStream(configFile);
			//Reader is=new FileReader("F:\\Learning\\Java\\SSM\\1.初始MyBatis\\BaseDao\\src\\database.properties");
			//从输入流中读取属性列表
			properties.load(is);
			System.out.println(properties.getProperty("url"));
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
}
