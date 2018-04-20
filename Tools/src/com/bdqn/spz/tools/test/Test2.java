package com.bdqn.spz.tools.test;

import java.lang.reflect.Method;

import com.bdqn.spz.tools.entity.Grade;

public class Test2 {

	public static void main(String[] args) throws ClassNotFoundException {
		Class<?> c=Grade.class;
		for (Method method : c.getMethods()) {
//			System.out.println(method.getName());
//			System.out.println(method.getName().substring(0, 3));
			if (method.getName().substring(0, 3).equals("set")) {
				System.out.println(method.getName());
				if (method.getParameterTypes()[0].getName().equals("int")) {
					System.out.println(method.getParameterTypes()[0].getName());
				} else if (method.getParameterTypes()[0].getName().equals("double")) {
					System.out.println(method.getParameterTypes()[0].getName());
				} else if (method.getParameterTypes()[0].getName().equals("float")) {
					System.out.println(method.getParameterTypes()[0].getName());
				} else if (method.getParameterTypes()[0].getName().equals("java.util.List")) {
					
					System.out.println(method.getParameterTypes()[0].getName());
					String string=method.getParameters()[0].getParameterizedType().getTypeName();
					int a=string.indexOf('<');
					int b=string.indexOf('>');
					string=string.substring(a+1, b);
					System.out.println(string);
					System.out.println(Class.forName(string));
				}
				System.out.println("-----------------------------------------------------------");
			}
		}
	}
}
