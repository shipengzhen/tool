package com.bdqn.spz.tools.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {

	public void getDate() throws ParseException{
		Date date=new Date();//获取当前时间
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String string2=simpleDateFormat.format(date);//Date转String
		date=simpleDateFormat.parse("1997-04-19");//String转Date
	}
}
