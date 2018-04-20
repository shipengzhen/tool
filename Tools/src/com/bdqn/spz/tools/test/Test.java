package com.bdqn.spz.tools.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.bdqn.spz.tools.dao.DaoHelper;

public class Test {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// Reflex reflex=new Reflex();
		// Grade grade=new Grade();
		// reflex.getReflex(grade);
		// Analyze analyze=new Analyze();
		// analyze.getAnalyze();
		// DynamicBaseDao dynamicBaseDao=new DynamicBaseDao();
		// String[] strings={"1","2","3"};
		// dynamicBaseDao.InsertSQL("ss",strings);
		// reflex.getReflex(Student.class);

		// for (Field field : Student.class.getDeclaredFields()) {
		// System.out.println(field.getGenericType());
		// }

		DaoHelper daoHelper = new DaoHelper();
		daoHelper.getHelper(Test.class, "hh", new Object[] { 1, 1 });

		// 动态构造InvokeTest类的实例
		// Class<?> classType = Test.class;
		// Object invokeTest = classType.newInstance();
		//
		// //动态构造InvokeTest类的add(int num1, int num2)方法，标记为addMethod的Method对象
		// Method addMethod = classType.getMethod("hh", new Class[]{int.class});
		// System.out.println(addMethod);
		// //动态构造的Method对象invoke委托动态构造的InvokeTest对象，执行对应形参的add方法
		// Object result = addMethod.invoke(invokeTest, new Object[]{1});
	}

	public Object hh(int a, int b) {
		System.out.println(a + b);
		return 0;
	}

	/**
	 * <h1>生成insert语句，根据Model对象，指定表名，指定映射</h1><br/>
	 * 例如：sqlInsert(user,null,map)<br/>
	 * <br/>
	 * 你可以用map做自定义高级控制<br>
	 * map.put("drop","sa");将代表丢弃sa属性不做映射<br>
	 * map.put("sex","default");将代表sex字段用default<br>
	 * map.put("columnName","propName");解决列名和属性名不一致的情况
	 * 
	 * @param obj
	 *            要映射的对象
	 * @param tableName
	 *            表名，填null将用obj的类名
	 * @param map
	 *            做自定义高级控制
	 * @return
	 */
	public String sqlInsert(Object obj, String tableName, Map<String, String> map) {
		Class<?> c = obj.getClass();
		if (tableName == null)
			tableName = c.getSimpleName();
		if (map == null)
			map = new HashMap<String, String>();

		StringBuilder sbk = new StringBuilder();// set...
		StringBuilder sbv = new StringBuilder();// where...

		Field[] fs = c.getDeclaredFields();// 获得属性数组
		for (Field f : fs) {
			String propName = f.getName();
			if (map.containsValue(propName) == false) {
				try {
					f.setAccessible(true);
					sbk.append(f.getName() + ",");// K
					sbv.append("'" + f.get(obj) + "',");// V
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		// 遍历map，drop、自定义映射
		for (String k : map.keySet()) {
			try {
				if (k.indexOf("drop") == 0) {

				} else {
					sbk.append(k + ",");// K
					if (map.get(k).indexOf("default") == 0) {
						sbv.append("default,");
					} else {
						Field f = c.getDeclaredField(map.get(k));
						f.setAccessible(true);
						sbv.append("'" + f.get(obj) + "',");// V
					}
				}
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		String strk = sbk.substring(0, sbk.length() - 1);
		String strv = sbv.substring(0, sbv.length() - 1);

		return "insert into " + tableName + "(" + strk + ") \nvalue(" + strv + ")";
	}

}
