package com.spz.tools.tool.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ����
 * @author ʩ����
 *
 */
public class Analyze {
	public void getAnalyze(){
		try {
			Properties properties=new Properties();
			String configFile="database.properties";//�����ļ�·��
			//���������ļ���������
			InputStream is=BaseDao.class.getClassLoader().getResourceAsStream(configFile);
			//Reader is=new FileReader("F:\\Learning\\Java\\SSM\\1.��ʼMyBatis\\BaseDao\\src\\database.properties");
			//���������ж�ȡ�����б�
			properties.load(is);
			System.out.println(properties.getProperty("url"));
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
}
