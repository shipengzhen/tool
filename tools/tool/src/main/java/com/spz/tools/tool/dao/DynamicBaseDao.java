package com.spz.tools.tool.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


import java.io.IOException;
import java.io.InputStream;
import java.lang.Class;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



public class DynamicBaseDao {
	
	
//	private String DRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	private String URL="jdbc:sqlserver://localhost:1433;DataBaseName=Membersinfo";
//	private String USER="spz";
//	private String PASSWORD="shipengzhen1997";
	
	/**
	 * 创建Connection对象
	 */
	protected Connection connection;
	/**
	 * 创建PreparedStatement对象
	 */
	protected PreparedStatement preparedStatement;
	/**
	 * 创建ResultSet对象
	 */
	protected ResultSet resultSet;
	/**
	 * 创建CallableStatement对象
	 */
	private CallableStatement callableStatement;
	
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * 创建Connection连接
	 * @return Connection
	 */
	protected Connection getConnection(){
		try {
			Properties properties=new Properties();
			String configFile="database.properties";//配置文件路径
			//加载配置文件到输入流
			InputStream is=DynamicBaseDao.class.getClassLoader().getResourceAsStream(configFile);
			//Reader is=new FileReader("F:\\Learning\\Java\\SSM\\1.初始MyBatis\\BaseDao\\src\\database.properties");
			//从输入流中读取属性列�?
			properties.load(is);
			Class.forName(properties.getProperty("driver"));
			connection=DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("user"),properties.getProperty("password"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * 关闭
	 */
	protected void closeConnection(){
		try {
			if (callableStatement!=null) {
				callableStatement.close();
			}
			if(resultSet!=null){
				resultSet.close();
			}
			if(preparedStatement!=null){
				preparedStatement.close();
			}
			if(connection!=null){
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	/**
	 * 添加对条信息
	 * @param table
	 * @param string
	 * @return
	 */
	public String InsertSQL(String table,String[] strings){
		StringBuffer stringBuffer=new StringBuffer("insert into "+table+" values(");
		if (strings!=null) {
			for (String s : strings) {
				stringBuffer.append(s+",");
			}
		}
		stringBuffer.delete(stringBuffer.length()-1,stringBuffer.length());
		stringBuffer.append(")");
		System.out.println(stringBuffer);
		return stringBuffer.toString();
	}
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * 获取PreparedStatement
	 * @return PreparedStatement
	 */
	protected PreparedStatement getPreparedStatement(String sql,Object[] objects){
		try {
			connection=getConnection();
			preparedStatement=connection.prepareStatement(sql);
			if (objects!=null) {
				for (int i = 0; i < objects.length; i++) {
					preparedStatement.setObject(i+1,objects[i]);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return preparedStatement;
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * 增删�?
	 * @return int
	 */
	protected int exUpdate(String sql,Object[] objects){
		try {
			preparedStatement=getPreparedStatement(sql,objects);
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return 0;
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
	
	/**
	 * 无条件查询返回ResultSet�?
	 * @param sql
	 * @return ResultSet
	 */
	protected ResultSet getResultSet(String sql){
		try {
			connection=getConnection();
			preparedStatement=connection.prepareStatement(sql);
			resultSet= preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	/**
	 * 无条件查询返回一行一�?(受影响行�?)
	 * @param sql
	 * @param objects
	 * @return Object
	 */
	protected Object executeQuerySingle(String sql){
		try {
			resultSet=getResultSet(sql);
			if (resultSet.next()) {
				return resultSet.getObject(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return null;
	}
	
	/**
	 * 无条件查询返回对�?
	 * @param class1
	 * @param sql
	 * @return Object
	 */
	protected Object getObject(Class<? extends Object> class1,String sql) {
		Object object=null;
		try {
			resultSet=getResultSet(sql);
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int colCount=rsmd.getColumnCount();//返回ResultSet中的列数
			List<String> colNameList=new ArrayList<String>();
			for(int i=0;i<colCount;i++){
				colNameList.add(rsmd.getColumnName(i+1));//获取指定列名�?
			}
			while (resultSet.next()) {
				object = class1.newInstance();//创建对象
				for(int i=0;i<colCount;i++){
					for (Method method: class1.getDeclaredMethods()) {//遍历实体类中的方�?
						String methodName="set"+colNameList.get(i);//获取ResultSet中的字段�?,拼接成方法名
						if (methodName.equals(method.getName())) {
							if (method.getParameterTypes()[0].getName().equals("int")) {
								method.invoke(object,resultSet.getInt(colNameList.get(i)));//获取ResultSet中�??
							}else if (method.getParameterTypes()[0].getName().equals("double")) {
								method.invoke(object,resultSet.getDate(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("float")) {
								method.invoke(object,resultSet.getFloat(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("java.lang.String")) {
								method.invoke(object,resultSet.getString(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("java.sql.Date")) {
								method.invoke(object,resultSet.getDate(colNameList.get(i)));
							}else {
								method.invoke(object,resultSet.getObject(colNameList.get(i)));
							}
						}
					}
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return object;
	}
	
	/**
	 * 无条件查询返回对象集�?
	 * @param class1
	 * @param sql
	 * @return List<Object>
	 */
	protected List<Object> getList(Class<? extends Object> class1,String sql) {
		List<Object> objects=new ArrayList<Object>();
		try {
			resultSet=getResultSet(sql);
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int colCount=rsmd.getColumnCount();//返回ResultSet中的列数
			List<String> colNameList=new ArrayList<String>();
			for(int i=0;i<colCount;i++){
				colNameList.add(rsmd.getColumnName(i+1));//获取指定列名�?
			}
			while (resultSet.next()) {
				Object object = class1.newInstance();//创建对象
				for(int i=0;i<colCount;i++){
					for (Method method: class1.getDeclaredMethods()) {//遍历实体类中的方�?
						String methodName="set"+colNameList.get(i);//获取ResultSet中的字段�?,拼接成方法名
						if (methodName.equals(method.getName())) {
							if (method.getParameterTypes()[0].getName().equals("int")) {
								method.invoke(object,resultSet.getInt(colNameList.get(i)));//获取ResultSet中�??
							}else if (method.getParameterTypes()[0].getName().equals("double")) {
								method.invoke(object,resultSet.getDate(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("float")) {
								method.invoke(object,resultSet.getFloat(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("java.lang.String")) {
								method.invoke(object,resultSet.getString(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("java.sql.Date")) {
								method.invoke(object,resultSet.getDate(colNameList.get(i)));
							}else {
								method.invoke(object,resultSet.getObject(colNameList.get(i)));
							}
						}
					}
				}
				objects.add(object);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return objects;
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * 单条件Object查询返回�?
	 * @param sql
	 * @param obj
	 * @return ResultSet
	 */
	protected ResultSet getResultSet(String sql,Object obj){
		try {
			connection=getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setObject(1,obj);;
			return preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	/**
	 * 单条件Object查询返回�?行一�?(受影响行�?)
	 * @param sql
	 * @param obj
	 * @return Object
	 */
	protected Object executeQuerySingle(String sql,Object obj){
		try {
			resultSet=getResultSet(sql,obj);
			if (resultSet.next()) {
				return resultSet.getObject(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return null;
	}
	
	/**
	 * 单条件Object查询返回对象
	 * @param class1
	 * @param sql
	 * @param obj
	 * @return Object
	 */
	protected Object getObject(Class<? extends Object> class1,String sql,Object obj) {
		Object object=null;
		try {
			resultSet=getResultSet(sql,obj);
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int colCount=rsmd.getColumnCount();//返回ResultSet中的列数
			List<String> colNameList=new ArrayList<String>();
			for(int i=0;i<colCount;i++){
				colNameList.add(rsmd.getColumnName(i+1));//获取指定列名�?
			}
			while (resultSet.next()) {
				object = class1.newInstance();//创建对象
				for(int i=0;i<colCount;i++){
					for (Method method: class1.getDeclaredMethods()) {//遍历实体类中的方�?
						String methodName="set"+colNameList.get(i);//获取ResultSet中的字段�?,拼接成方法名
						if (methodName.equals(method.getName())) {
							if (method.getParameterTypes()[0].getName().equals("int")) {
								method.invoke(object,resultSet.getInt(colNameList.get(i)));//获取ResultSet中�??
							}else if (method.getParameterTypes()[0].getName().equals("double")) {
								method.invoke(object,resultSet.getDate(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("float")) {
								method.invoke(object,resultSet.getFloat(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("java.lang.String")) {
								method.invoke(object,resultSet.getString(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("java.sql.Date")) {
								method.invoke(object,resultSet.getDate(colNameList.get(i)));
							}else {
								method.invoke(object,resultSet.getObject(colNameList.get(i)));
							}
						}
					}
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return object;
	}
	
	/**
	 * 单条件Object查询返回对象集合
	 * @param class1
	 * @param sql
	 * @param obj
	 * @return List<Object>
	 */
	protected List<Object> getList(Class<? extends Object> class1,String sql,Object obj) {
		List<Object> objects=new ArrayList<Object>();
		try {
			resultSet=getResultSet(sql,obj);
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int colCount=rsmd.getColumnCount();//返回ResultSet中的列数
			List<String> colNameList=new ArrayList<String>();
			for(int i=0;i<colCount;i++){
				colNameList.add(rsmd.getColumnName(i+1));//获取指定列名�?
			}
			while (resultSet.next()) {
				Object object = class1.newInstance();//创建对象
				for(int i=0;i<colCount;i++){
					for (Method method: class1.getDeclaredMethods()) {//遍历实体类中的方�?
						String methodName="set"+colNameList.get(i);//获取ResultSet中的字段�?,拼接成方法名
						if (methodName.equals(method.getName())) {
							if (method.getParameterTypes()[0].getName().equals("int")) {
								method.invoke(object,resultSet.getInt(colNameList.get(i)));//获取ResultSet中�??
							}else if (method.getParameterTypes()[0].getName().equals("double")) {
								method.invoke(object,resultSet.getDate(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("float")) {
								method.invoke(object,resultSet.getFloat(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("java.lang.String")) {
								method.invoke(object,resultSet.getString(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("java.sql.Date")) {
								method.invoke(object,resultSet.getDate(colNameList.get(i)));
							}else {
								method.invoke(object,resultSet.getObject(colNameList.get(i)));
							}
						}
					}
				}
				objects.add(object);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return objects;
	}	
	
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * 单条件int查询返回�?
	 * @param sql
	 * @param i
	 * @return ResultSet
	 */
	protected ResultSet getResultSet(String sql,int ii){
		try {
			connection=getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,ii);
			resultSet=preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	/**
	 * 单条件int查询返回�?行一�?(受影响行�?)
	 * @param sql
	 * @param i
	 * @return Object
	 */
	protected Object executeQuerySingle(String sql,int i){
		try {
			resultSet=getResultSet(sql,i);
			if (resultSet.next()) {
				return resultSet.getObject(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return null;
	}
	
	/**
	 * 单条件int查询返回对象
	 * @param class1
	 * @param sql
	 * @param ii
	 * @return Object
	 */
	protected Object getObject(Class<? extends Object> class1,String sql,int ii) {
		Object object=null;
		try {
			resultSet=getResultSet(sql,ii);
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int colCount=rsmd.getColumnCount();//返回ResultSet中的列数
			List<String> colNameList=new ArrayList<String>();
			for(int i=0;i<colCount;i++){
				colNameList.add(rsmd.getColumnName(i+1));//获取指定列名�?
			}
			while (resultSet.next()) {
				object = class1.newInstance();//创建对象
				for(int i=0;i<colCount;i++){
					for (Method method: class1.getDeclaredMethods()) {//遍历实体类中的方�?
						String methodName="set"+colNameList.get(i);//获取ResultSet中的字段�?,拼接成方法名
						if (methodName.equals(method.getName())) {
							if (method.getParameterTypes()[0].getName().equals("int")) {
								method.invoke(object,resultSet.getInt(colNameList.get(i)));//获取ResultSet中�??
							}else if (method.getParameterTypes()[0].getName().equals("double")) {
								method.invoke(object,resultSet.getDate(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("float")) {
								method.invoke(object,resultSet.getFloat(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("java.lang.String")) {
								method.invoke(object,resultSet.getString(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("java.sql.Date")) {
								method.invoke(object,resultSet.getDate(colNameList.get(i)));
							}else {
								method.invoke(object,resultSet.getObject(colNameList.get(i)));
							}
						}
					}
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return object;
	}
	
	/**
	 * 单条件int查询返回对象集合
	 * @param class1
	 * @param sql
	 * @param ii
	 * @return List<Object>
	 */
	protected List<Object> getList(Class<? extends Object> class1,String sql,int ii) {
		List<Object> objects=new ArrayList<Object>();
		try {
			resultSet=getResultSet(sql,ii);
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int colCount=rsmd.getColumnCount();//返回ResultSet中的列数
			List<String> colNameList=new ArrayList<String>();
			for(int i=0;i<colCount;i++){
				colNameList.add(rsmd.getColumnName(i+1));//获取指定列名�?
			}
			while (resultSet.next()) {
				Object object = class1.newInstance();//创建对象
				for(int i=0;i<colCount;i++){
					for (Method method: class1.getDeclaredMethods()) {//遍历实体类中的方�?
						String methodName="set"+colNameList.get(i);//获取ResultSet中的字段�?,拼接成方法名
						if (methodName.equals(method.getName())) {
							if (method.getParameterTypes()[0].getName().equals("int")) {
								method.invoke(object,resultSet.getInt(colNameList.get(i)));//获取ResultSet中�??
							}else if (method.getParameterTypes()[0].getName().equals("double")) {
								method.invoke(object,resultSet.getDate(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("float")) {
								method.invoke(object,resultSet.getFloat(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("java.lang.String")) {
								method.invoke(object,resultSet.getString(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("java.sql.Date")) {
								method.invoke(object,resultSet.getDate(colNameList.get(i)));
							}else {
								method.invoke(object,resultSet.getObject(colNameList.get(i)));
							}
						}
					}
				}
				objects.add(object);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return objects;
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	
	/**
	 * 单条件Stirng查询返回�?
	 * @param sql
	 * @param string
	 * @return ResultSet
	 */
	protected ResultSet getResultSet(String sql,String string){
		try {
			connection=getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1,string);
			return preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	/**
	 * 单条件String查询返回�?行一�?(受影响行�?)
	 * @param sql
	 * @param objects
	 * @return Object
	 */
	protected Object executeQuerySingle(String sql,String string){
		try {
			resultSet=getResultSet(sql,string);
			if (resultSet.next()) {
				return resultSet.getObject(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return null;
	}
	
	/**
	 * 单条件String查询返回对象
	 * @param class1
	 * @param sql
	 * @param string
	 * @return Object
	 */
	protected Object getObject(Class<? extends Object> class1,String sql,String string) {
		Object object=null;
		try {
			resultSet=getResultSet(sql,string);
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int colCount=rsmd.getColumnCount();//返回ResultSet中的列数
			List<String> colNameList=new ArrayList<String>();
			for(int i=0;i<colCount;i++){
				colNameList.add(rsmd.getColumnName(i+1));//获取指定列名�?
			}
			while (resultSet.next()) {
				object = class1.newInstance();//创建对象
				for(int i=0;i<colCount;i++){
					for (Method method: class1.getDeclaredMethods()) {//遍历实体类中的方�?
						String methodName="set"+colNameList.get(i);//获取ResultSet中的字段�?,拼接成方法名
						if (methodName.equals(method.getName())) {
							if (method.getParameterTypes()[0].getName().equals("int")) {
								method.invoke(object,resultSet.getInt(colNameList.get(i)));//获取ResultSet中�??
							}else if (method.getParameterTypes()[0].getName().equals("double")) {
								method.invoke(object,resultSet.getDate(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("float")) {
								method.invoke(object,resultSet.getFloat(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("java.lang.String")) {
								method.invoke(object,resultSet.getString(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("java.sql.Date")) {
								method.invoke(object,resultSet.getDate(colNameList.get(i)));
							}else {
								method.invoke(object,resultSet.getObject(colNameList.get(i)));
							}
						}
					}
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return object;
	}
	
	/**
	 * 单条件String查询返回对象集合
	 * @param class1
	 * @param sql
	 * @param string
	 * @return List<Object>
	 */
	protected List<Object> getList(Class<? extends Object> class1,String sql,String string) {
		List<Object> objects=new ArrayList<Object>();
		try {
			resultSet=getResultSet(sql,string);
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int colCount=rsmd.getColumnCount();//返回ResultSet中的列数
			List<String> colNameList=new ArrayList<String>();
			for(int i=0;i<colCount;i++){
				colNameList.add(rsmd.getColumnName(i+1));//获取指定列名�?
			}
			while (resultSet.next()) {
				Object object = class1.newInstance();//创建对象
				for(int i=0;i<colCount;i++){
					for (Method method: class1.getDeclaredMethods()) {//遍历实体类中的方�?
						String methodName="set"+colNameList.get(i);//获取ResultSet中的字段�?,拼接成方法名
						if (methodName.equals(method.getName())) {
							if (method.getParameterTypes()[0].getName().equals("int")) {
								method.invoke(object,resultSet.getInt(colNameList.get(i)));//获取ResultSet中�??
							}else if (method.getParameterTypes()[0].getName().equals("double")) {
								method.invoke(object,resultSet.getDate(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("float")) {
								method.invoke(object,resultSet.getFloat(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("java.lang.String")) {
								method.invoke(object,resultSet.getString(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("java.sql.Date")) {
								method.invoke(object,resultSet.getDate(colNameList.get(i)));
							}else {
								method.invoke(object,resultSet.getObject(colNameList.get(i)));
							}
						}
					}
				}
				objects.add(object);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return objects;
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * 多条件查询返回表
	 * @param sql
	 * @param objects
	 * @return ResultSet
	 */
	protected ResultSet getResultSet(String sql,Object[] objs){
		try {
			preparedStatement=getPreparedStatement(sql,objs);
			return preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	/**
	 * 多条件查询返回一行一�?(受影响行�?)
	 * @param sql
	 * @param objects
	 * @return Object
	 */
	protected Object executeQuerySingle(String sql,Object[] objs){
		try {
			resultSet=getResultSet(sql, objs);
			if (resultSet.next()) {
				return resultSet.getObject(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return null;
	}
	
	/**
	 * 多条件查询返回对�?
	 * @param class1
	 * @param sql
	 * @param objs
	 * @return Object
	 */
	protected Object getObject(Class<? extends Object> class1,String sql,Object[] objs) {
		Object object=null;
		try {
			resultSet=getResultSet(sql, objs);
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int colCount=rsmd.getColumnCount();//返回ResultSet中的列数
			List<String> colNameList=new ArrayList<String>();
			for(int i=0;i<colCount;i++){
				colNameList.add(rsmd.getColumnName(i+1));//获取指定列名�?
			}
			while (resultSet.next()) {
				object = class1.newInstance();//创建对象
				for(int i=0;i<colCount;i++){
					for (Method method: class1.getDeclaredMethods()) {//遍历实体类中的方�?
						String methodName="set"+colNameList.get(i);//获取ResultSet中的字段�?,拼接成方法名
						if (methodName.equals(method.getName())) {
							if (method.getParameterTypes()[0].getName().equals("int")) {
								method.invoke(object,resultSet.getInt(colNameList.get(i)));//获取ResultSet中�??
							}else if (method.getParameterTypes()[0].getName().equals("double")) {
								method.invoke(object,resultSet.getDate(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("float")) {
								method.invoke(object,resultSet.getFloat(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("java.lang.String")) {
								method.invoke(object,resultSet.getString(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("java.sql.Date")) {
								method.invoke(object,resultSet.getDate(colNameList.get(i)));
							}else {
								method.invoke(object,resultSet.getObject(colNameList.get(i)));
							}
						}
					}
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return object;
	}
	
	/**
	 * 多条件查询返回对象集�?
	 * @param class1
	 * @param sql
	 * @param objs
	 * @return List<Object>
	 */
	protected List<Object> getList(Class<? extends Object> class1,String sql,Object[] objs) {
		List<Object> objects=new ArrayList<Object>();
		try {
			resultSet=getResultSet(sql, objs);
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int colCount=rsmd.getColumnCount();//返回ResultSet中的列数
			List<String> colNameList=new ArrayList<String>();
			for(int i=0;i<colCount;i++){
				colNameList.add(rsmd.getColumnName(i+1));//获取指定列名�?
			}
			while (resultSet.next()) {
				Object object = class1.newInstance();//创建对象
				for(int i=0;i<colCount;i++){
					for (Method method: class1.getDeclaredMethods()) {//遍历实体类中的方�?
						String methodName="set"+colNameList.get(i);//获取ResultSet中的字段�?,拼接成方法名
						if (methodName.equals(method.getName())) {
							if (method.getParameterTypes()[0].getName().equals("int")) {
								method.invoke(object,resultSet.getInt(colNameList.get(i)));//获取ResultSet中�??
							}else if (method.getParameterTypes()[0].getName().equals("double")) {
								method.invoke(object,resultSet.getDate(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("float")) {
								method.invoke(object,resultSet.getFloat(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("java.lang.String")) {
								method.invoke(object,resultSet.getString(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("java.sql.Date")) {
								method.invoke(object,resultSet.getDate(colNameList.get(i)));
							}else {
								method.invoke(object,resultSet.getObject(colNameList.get(i)));
							}
						}
					}
				}
				objects.add(object);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return objects;
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * 遍历ResultSet并赋值到MapList
	 * @param rs
	 * @return List<Map<String,Object>>
	 */
	protected  List<Map<String,Object>> resultSetToMapList(ResultSet rs){
		List<Map<String,Object>> results=new ArrayList<Map<String,Object>>();
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount=rsmd.getColumnCount();//返回ResultSet中的列数
			List<String> colNameList=new ArrayList<String>();
			for(int i=0;i<colCount;i++){
				colNameList.add(rsmd.getColumnName(i+1));//获取指定列名�?
			} 
			while(rs.next()){
				Map<String, Object> map=new HashMap<String, Object>();
				for(int i=0;i<colCount;i++){
					String key=colNameList.get(i);
					Object value=rs.getObject(colNameList.get(i));
					map.put(key, value);
				}
				results.add(map);
			}
//			String[] colNames=new String[colCount];
//			for(int i=0;i<colCount;i++){
//				colNames[i]=rsmd.getColumnName(i+1);//获取指定列名称下标从1�?�?
//			} 
//			while(rs.next()){
//				for(int i=0;i<colCount;i++){
//					Map map=new HashMap<String, Object>();
//					String key=colNames[i];
//					Object value=rs.getString(colNames[i]);
//					map.put(key, value);
//					results.add(map);
//				}
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
			return results;
	}
	
	/**
	 * 返回对象
	 * @param class1
	 * @param rs
	 * @return Object
	 */
	protected Object getObjectMap(Class<? extends Object> class1,ResultSet rs) {
		Object object=null;
		List<Map<String,Object>> maps=resultSetToMapList(rs);
		try {
			object = class1.newInstance();//创建对象
			for (Map<String, Object> map : maps) {//遍历List<Map<String,Object>>
				for (String  field :map.keySet()) {//获取字段�?
					String methodName="set"+field;//拼接成方法名
					for (Method method: class1.getDeclaredMethods()) {//遍历实体类中的方�?
						if(methodName.equals(method.getName())){
							if (method.getParameterTypes()[0].getName().equals("int")) {
								method.invoke(object,Integer.parseInt(map.get(field).toString()));
							}else if (method.getParameterTypes()[0].getName().equals("double")) {
								method.invoke(object,Double.parseDouble(map.get(field).toString()));
							}else if (method.getParameterTypes()[0].getName().equals("float")) {
								method.invoke(object,Float.parseFloat(map.get(field).toString()));
							}else if (method.getParameterTypes()[0].getName().equals("java.lang.String")) {
								method.invoke(object,(String)map.get(field));
							}else if (method.getParameterTypes()[0].getName().equals("java.sql.Date")) {
								method.invoke(object,(Date)map.get(field));
//							}else if (method.getParameterTypes()[0].getName().equals(class1.getName())) {
//								method.invoke(object,(class1)map.get(field));
							}else {
								method.invoke(object,map.get(field));
							}
						}
					}
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return object;
	}
	
	/**
	 * 返回对象
	 * @param class1
	 * @param rs
	 * @return Object
	 */
	protected Object getObject(Class<? extends Object> class1,ResultSet rs) {
		Object object=null;
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount=rsmd.getColumnCount();//返回ResultSet中的列数
			List<String> colNameList=new ArrayList<String>();
			for(int i=0;i<colCount;i++){
				colNameList.add(rsmd.getColumnName(i+1));//获取指定列名�?
			}
			while (rs.next()) {
				object = class1.newInstance();//创建对象
				for(int i=0;i<colCount;i++){
					for (Method method: class1.getDeclaredMethods()) {//遍历实体类中的方�?
						String methodName="set"+colNameList.get(i);//获取ResultSet中的字段�?,拼接成方法名
						if (methodName.equals(method.getName())) {
							if (method.getParameterTypes()[0].getName().equals("int")) {
								method.invoke(object,rs.getInt(colNameList.get(i)));//获取ResultSet中�??
							}else if (method.getParameterTypes()[0].getName().equals("double")) {
								method.invoke(object,rs.getDate(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("float")) {
								method.invoke(object,rs.getFloat(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("java.lang.String")) {
								method.invoke(object,rs.getString(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("java.sql.Date")) {
								method.invoke(object,rs.getDate(colNameList.get(i)));
							}else {
								method.invoke(object,rs.getObject(colNameList.get(i)));
							}
						}
					}
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return object;
	}
	
	
	/**
	 * 返回对象集合
	 * @param class1
	 * @param rs
	 * @return List<Object>
	 */
	protected List<Object> getListMap(Class<? extends Object> class1,ResultSet rs){
		List<Object> objects=new ArrayList<Object>();
		List<Map<String,Object>> maps=resultSetToMapList(rs);
		try {
			for (Map<String, Object> map : maps) {//遍历List<Map<String,Object>>
				Object object = class1.newInstance();//创建对象
				for (String  field :map.keySet()) {//获取字段�?
					String methodName="set"+field;//拼接成方法名
					for (Method method: class1.getDeclaredMethods()) {//遍历实体类中的方�?
						if(methodName.equals(method.getName())){
							if (method.getParameterTypes()[0].getName().equals("int")) {
								method.invoke(object,Integer.parseInt(map.get(field).toString()));
							}else if (method.getParameterTypes()[0].getName().equals("double")) {
								method.invoke(object,Double.parseDouble(map.get(field).toString()));
							}else if (method.getParameterTypes()[0].getName().equals("float")) {
								method.invoke(object,Float.parseFloat(map.get(field).toString()));
							}else if (method.getParameterTypes()[0].getName().equals("java.lang.String")) {
								method.invoke(object,(String)map.get(field));
							}else if (method.getParameterTypes()[0].getName().equals("java.sql.Date")) {
								method.invoke(object,(Date)map.get(field));
//							}else if (method.getParameterTypes()[0].getName().equals(class1.getName())) {
//								method.invoke(object,(class1)map.get(field));
							}else {
								method.invoke(object,map.get(field));
							}
						}
					}
				}
				objects.add(object);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return objects;
	}
	
	/**
	 * 返回对象集合
	 * @param class1
	 * @param rs
	 * @return List<Object>
	 */
	protected List<Object> getList(Class<? extends Object> class1,ResultSet rs) {
		List<Object> objects=new ArrayList<Object>();
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount=rsmd.getColumnCount();//返回ResultSet中的列数
			List<String> colNameList=new ArrayList<String>();
			for(int i=0;i<colCount;i++){
				colNameList.add(rsmd.getColumnName(i+1));//获取指定列名�?
			}
			while (rs.next()) {
				Object object = class1.newInstance();//创建对象
				for(int i=0;i<colCount;i++){
					for (Method method: class1.getDeclaredMethods()) {//遍历实体类中的方�?
						String methodName="set"+colNameList.get(i);//获取ResultSet中的字段�?,拼接成方法名
						if (methodName.equals(method.getName())) {
							if (method.getParameterTypes()[0].getName().equals("int")) {
								method.invoke(object,rs.getInt(colNameList.get(i)));//获取ResultSet中�??
							}else if (method.getParameterTypes()[0].getName().equals("double")) {
								method.invoke(object,rs.getDate(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("float")) {
								method.invoke(object,rs.getFloat(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("java.lang.String")) {
								method.invoke(object,rs.getString(colNameList.get(i)));
							}else if (method.getParameterTypes()[0].getName().equals("java.sql.Date")) {
								method.invoke(object,rs.getDate(colNameList.get(i)));
							}else {
								method.invoke(object,rs.getObject(colNameList.get(i)));
							}
						}
					}
				}
				objects.add(object);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return objects;
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * 返回CallableStatement对象
	 * @param sql
	 * @param objects
	 * @return CallableStatement
	 */
	protected CallableStatement getcallableStatement(String sql,Object[] objects){
		try {
			connection=getConnection();
			//调用存储过程
			callableStatement=connection.prepareCall(sql);
			//给参数赋�?
			if (objects!=null) {
				for (int i = 0; i < objects.length; i++) {
					callableStatement.setObject(i+1,objects[i]);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return callableStatement;
	}
	
	/**
	 * 调用存储过程
	 * @param sql
	 * @param objects
	 * @param outParamPos
	 * @param sqlType
	 * @return Object
	 */
	protected Object excuteQuery(String sql,Object[] objects,int outParamPos,int sqlType){
		try {
			callableStatement=getcallableStatement(sql,objects);
			//注册输出参数
			callableStatement.registerOutParameter(outParamPos,sqlType);
			//执行
			callableStatement.execute();
			//返回输出参数
			return callableStatement.getObject(outParamPos);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return null;
	}
}
