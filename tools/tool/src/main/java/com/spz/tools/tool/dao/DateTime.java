package com.spz.tools.tool.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {

	public void getDate() throws ParseException{
		Date date=new Date();//��ȡ��ǰʱ��
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String string2=simpleDateFormat.format(date);//DateתString
		date=simpleDateFormat.parse("1997-04-19");//StringתDate
	}
}
