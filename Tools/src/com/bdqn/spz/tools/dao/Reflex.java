package com.bdqn.spz.tools.dao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射
 * @author 施鹏�?
 *
 */
public class Reflex {
	public void getReflex(Object object){
		//反射
		//第一种方�?:
		//Class.forName("类的路径");
		//第二种方式：  
		//java中每个类型都有class属�??.  
		//�?.class;
		//第三种方式：  
		//java语言中任何一个java对象都有getClass 方法
		//对象.getClass();
		Class<? extends Object> c=object.getClass();
//		�?
		System.out.println("�?"+c);
//		getDeclaredMethods(),获取�?有的方法,返回 Method对象的一个数�?
		for (Method method : c.getDeclaredMethods()) {
			System.out.println("类的�?有方�?"+method);
			System.out.println("类的�?有方法名"+method.getName());
//			getReturnType(),获得方法的放回类�?,返回Class对象
			System.out.println("返回数据类型"+method.getReturnType());
			System.out.println("返回数据类型�?"+method.getReturnType().getName());
//			getParameterTypes(),获得方法的传入参数类�?,返回Class对象的数�?
			for (Class<?> class1: method.getParameterTypes()) {
				System.out.println("传入参数类型"+class1);
				System.out.println("传入参数类型�?"+class1.getName());
			}
		}
		try {
//			getDeclaredMethod("方法�?",方法传入参数的数据类�?),返回 Method对象
			System.out.println("指定类的某个方法"+c.getDeclaredMethod("setGradeName",String.class));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
//		getDeclaredConstructors()获取�?有的构�?�方�?,返回 Constructor对象的一个数�?
		for (Constructor<?> constructor : c.getDeclaredConstructors()) {
			System.out.println("�?有构�?"+constructor);
		}
		try {
//			getDeclaredConstructor("方法传入参数的数据类�?"),返回 �?个Constructor对象
			System.out.println("指定类的某个构�?�方�?"+c.getDeclaredConstructor(int.class,String.class));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
//		getSuperclass(), 返回表示�? Class �?表示的实体（类�?�接口�?�基本类型或 void）的超类�? Class
		System.out.println("返回此类的父�?"+c.getSuperclass());
//		getInterfaces(),确定此对象所表示的类或接口实现的接口(数组)
		for (Object object2 :c.getInterfaces()) {
			System.out.println("返回此类的接�?"+object2);
		}
//		getDeclaredFields(),返回 Field 对象的一个数组，这些对象反映�? Class 对象�?表示的类或接口所声明的所有字�?
		for (Field field : c.getDeclaredFields()) {
			System.out.println("类的�?有字�?"+field);
			System.err.println(field.getName());
			System.out.println("类的�?有字段名"+field.getName());//获取字段�?
		}
		try {
//			c.getDeclaredField("字段�?"),返回�?�? Field 对象，该对象反映�? Class 对象�?表示的类或接口的指定已声明字�?
			System.out.println("类的某个指定的字�?"+c.getDeclaredField("gradeId"));
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
//		getFields()，返回一个包含某�? Field 对象的数组，这些对象反映�? Class 对象�?表示的类或接口的�?有可访问公共字段
		for (Field field : c.getFields()) {
			System.out.println("类的共有字段"+field);
		}
//		getField("字段�?"),返回�?�? Field 对象，它反映�? Class 对象�?表示的类或接口的指定公共成员字段
		try {
			System.out.println("指定累的某个字段"+c.getField("gradeId"));
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		System.out.println("类的�?"+c.getPackage());
		System.out.println("类的包名"+c.getName());//�? String 的形式返回此 Class 对象�?表示的实体（类�?�接口�?�数组类、基本类型或 void）名�?
	}
}
