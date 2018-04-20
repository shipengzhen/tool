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

		// ��̬����InvokeTest���ʵ��
		// Class<?> classType = Test.class;
		// Object invokeTest = classType.newInstance();
		//
		// //��̬����InvokeTest���add(int num1, int num2)���������ΪaddMethod��Method����
		// Method addMethod = classType.getMethod("hh", new Class[]{int.class});
		// System.out.println(addMethod);
		// //��̬�����Method����invokeί�ж�̬�����InvokeTest����ִ�ж�Ӧ�βε�add����
		// Object result = addMethod.invoke(invokeTest, new Object[]{1});
	}

	public Object hh(int a, int b) {
		System.out.println(a + b);
		return 0;
	}

	/**
	 * <h1>����insert��䣬����Model����ָ��������ָ��ӳ��</h1><br/>
	 * ���磺sqlInsert(user,null,map)<br/>
	 * <br/>
	 * �������map���Զ���߼�����<br>
	 * map.put("drop","sa");��������sa���Բ���ӳ��<br>
	 * map.put("sex","default");������sex�ֶ���default<br>
	 * map.put("columnName","propName");�����������������һ�µ����
	 * 
	 * @param obj
	 *            Ҫӳ��Ķ���
	 * @param tableName
	 *            ��������null����obj������
	 * @param map
	 *            ���Զ���߼�����
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

		Field[] fs = c.getDeclaredFields();// �����������
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

		// ����map��drop���Զ���ӳ��
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
