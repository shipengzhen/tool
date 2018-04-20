package com.bdqn.spz.tools.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;


/**
 * 
 * @author 施鹏振
 *
 */
public class DaoHelper {
	
	//调取某个方法
	public void getHelper(Class<?> class1,String methodName,Object[] objects) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		Class<?>[] classes=null;
		System.out.println(class1.getName());
		Object object = class1.newInstance();
		for (Method method : class1.getMethods()) {
			if (method.getName().equals(methodName)) {
				System.out.println(method.getReturnType().getName());
			}
		}
		//Method method = class1.getMethod(methodName,classes);
		//Object object2= method.invoke(object,objects);
	}
	
	public <T> T getObject(Class<?> class1,String methodName,Object[] objects) throws InstantiationException, IllegalAccessException{
		String sql=Proper.getString(class1.getName(),methodName);
		Class<?>[] classes=null;
		Object object = class1.newInstance();
		for (Method method : class1.getMethods()) {
			if (method.getName().equals(methodName)) {
				if (method.getParameterCount()>0) {
					classes=method.getParameterTypes();
				}
			}
		}
		return null;
	}
	
}
