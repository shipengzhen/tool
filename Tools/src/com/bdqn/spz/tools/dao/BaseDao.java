package com.bdqn.spz.tools.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.lang.Class;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * @author 施鹏振
 *
 */
public class BaseDao {

	/**
	 * 将字符串首字母转成大写
	 * 
	 * @param string
	 * @return
	 */
	public String firstBig(String string) {
		return string.substring(0, 1).toUpperCase() + string.substring(1);
	}

	// private String DRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	// private String
	// URL="jdbc:sqlserver://localhost:1433;DataBaseName=Membersinfo";
	// private String USER="spz";
	// private String PASSWORD="shipengzhen1997";

	/**
	 * 创建Connection对象
	 */
	private Connection connection;

	/**
	 * 创建PreparedStatement对象
	 */
	private PreparedStatement preparedStatement;

	/**
	 * 创建ResultSet对象
	 */
	private ResultSet resultSet;

	/**
	 * 创建CallableStatement对象
	 */
	private CallableStatement callableStatement;

	/**
	 * 是否自动提交事务
	 */
	private boolean commit = true;

	/**
	 * 解决与数据库字段不一致
	 */
	private Map<String, String> map;

	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * 读取连接信息
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection Connection() throws ClassNotFoundException, SQLException {
		// Properties properties=new Properties();
		// String configFile="database.properties";//配置文件路径
		// //加载配置文件到输入流
		// InputStream
		// is=BaseDao.class.getClassLoader().getResourceAsStream(configFile);
		// //Reader is=new
		// FileReader("F:\\Learning\\Java\\SSM\\1.初始MyBatis\\BaseDao\\src\\database.properties");
		// //从输入流中读取属性列�?
		// properties.load(is);
		// Class.forName(properties.getProperty("driver"));
		// connection=DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("user"),properties.getProperty("password"));
		Class.forName(ConfigManager.getInstance().getValue("driver"));
		connection = DriverManager.getConnection(ConfigManager.getInstance("database.properties").getValue("url"),
				ConfigManager.getInstance().getValue("user"), ConfigManager.getInstance().getValue("password"));
		return connection;
	}

	/**
	 * 创建Connection连接
	 * 
	 * @return Connection
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		if (connection == null) {
			connection = Connection();
		} else if (connection.isClosed() == true) {
			connection = Connection();
		}
		connection.setAutoCommit(commit);
		return connection;
	}

	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * 关闭
	 * 
	 * @throws SQLException
	 */
	public void closeConnection(CallableStatement callableStatement, ResultSet resultSet,
			PreparedStatement preparedStatement, Connection connection) {
		try {
			if (callableStatement != null && callableStatement.isClosed() == false) {
				callableStatement.close();
			}
			if (resultSet != null && resultSet.isClosed() == false) {
				resultSet.close();
			}
			if (preparedStatement != null && preparedStatement.isClosed() == false) {
				preparedStatement.close();
			}
			if (connection != null && connection.isClosed() == false) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭
	 * 
	 * @throws SQLException
	 */
	public void closeConnection() {
		try {
			if (callableStatement != null && callableStatement.isClosed() == false) {
				callableStatement.close();
			}
			if (resultSet != null && resultSet.isClosed() == false) {
				resultSet.close();
			}
			if (preparedStatement != null && preparedStatement.isClosed() == false) {
				preparedStatement.close();
			}
			if (connection != null && connection.isClosed() == false) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * 是否自动提交事务,默认true自动提交
	 * 
	 * @param b
	 * @return
	 */
	public boolean setCommit(boolean b) {
		commit = b;
		return commit;
	};

	/**
	 * 提交事务
	 * 
	 * @throws SQLException
	 */
	public void commit() throws SQLException {
		if (connection != null) {
			if (connection.getAutoCommit() == false) {
				connection.commit();
			}
		}
	}

	/**
	 * 回滚事务
	 * 
	 * @throws SQLException
	 */
	public void rollback() throws SQLException {
		if (connection != null) {
			if (connection.getAutoCommit() == false) {
				connection.rollback();
			}
		}
	}

	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * 无参数获取PreparedStatement
	 * 
	 * @param sql
	 * @return PreparedStatement
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public PreparedStatement getPreparedStatement(String sql) throws ClassNotFoundException, SQLException {
		connection = getConnection();
		preparedStatement = connection.prepareStatement(sql);
		return preparedStatement;
	}

	/**
	 * 单参数获取PreparedStatement
	 * 
	 * @param sql
	 * @param object
	 * @return PreparedStatement
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public PreparedStatement getPreparedStatement(String sql, Object object)
			throws ClassNotFoundException, SQLException {
		connection = getConnection();
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setObject(1, object);
		return preparedStatement;
	}

	/**
	 * 多参数获取PreparedStatement
	 * 
	 * @param sql
	 * @param objects
	 * @return PreparedStatement
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public PreparedStatement getPreparedStatement(String sql, Object[] objects)
			throws ClassNotFoundException, SQLException {
		connection = getConnection();
		preparedStatement = connection.prepareStatement(sql);
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				preparedStatement.setObject(i + 1, objects[i]);
			}
		}
		return preparedStatement;
	}
	// ---------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * 单参数增删改
	 * 
	 * @param sql
	 * @param object
	 * @return int
	 */
	public int exUpdate(String sql, Object object) {
		int count = 0;
		try {
			preparedStatement = getPreparedStatement(sql, object);
			count = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return count;
	}

	/**
	 * 多参数增删改
	 * 
	 * @param sql
	 * @param objects
	 * @return int
	 */
	public int exUpdate(String sql, Object[] objects) {
		int count = 0;
		try {
			preparedStatement = getPreparedStatement(sql, objects);
			count = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return count;
	}

	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * 无条件查询返回ResultSet�?
	 * 
	 * @param sql
	 * @return ResultSet
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ResultSet getResultSet(String sql) throws ClassNotFoundException, SQLException {
		preparedStatement = getPreparedStatement(sql);
		resultSet = preparedStatement.executeQuery();
		return resultSet;
	}

	/**
	 * 单条件查询返回ResultSet�?
	 * 
	 * @param sql
	 * @param object
	 * @return ResultSet
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ResultSet getResultSet(String sql, Object object) throws ClassNotFoundException, SQLException {
		preparedStatement = getPreparedStatement(sql, object);
		resultSet = preparedStatement.executeQuery();
		return resultSet;
	}

	/**
	 * 多条件查询返回表
	 * 
	 * @param sql
	 * @param objects
	 * @return ResultSet
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ResultSet getResultSet(String sql, Object[] objects) throws ClassNotFoundException, SQLException {
		preparedStatement = getPreparedStatement(sql, objects);
		resultSet = preparedStatement.executeQuery();
		return resultSet;
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * 无条件查询返回一行一�?(受影响行�?)
	 * 
	 * @param sql
	 * @param objects
	 * @return Object
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Object getSingle(String sql) throws ClassNotFoundException, SQLException {
		Object obj = null;
		resultSet = getResultSet(sql);
		if (resultSet.next()) {
			obj = resultSet.getObject(1);
		}
		return obj;
	}

	/**
	 * 单条件查询返回一行一�?(受影响行�?)
	 * 
	 * @param sql
	 * @param object
	 * @return Object
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Object getSingle(String sql, Object object) throws ClassNotFoundException, SQLException {
		Object obj = null;
		resultSet = getResultSet(sql, object);
		if (resultSet.next()) {
			obj = resultSet.getObject(1);
		}
		return obj;
	}

	/**
	 * 多条件查询返回一行一�?(受影响行�?)
	 * 
	 * @param sql
	 * @param objects
	 * @return Object
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Object getSingle(String sql, Object[] objects) throws ClassNotFoundException, SQLException {
		Object obj = null;
		resultSet = getResultSet(sql, objects);
		if (resultSet.next()) {
			obj = resultSet.getObject(1);
		}
		return obj;
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * 无条件查询返回多行一列
	 * @param sql
	 * @param objects
	 * @return List<Object>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<Object> getList(String sql) throws ClassNotFoundException, SQLException {
		List<Object> objs=new ArrayList<Object>();
		resultSet = getResultSet(sql);
		while (resultSet.next()) {
			Object obj = resultSet.getObject(1);
			objs.add(obj);
		}
		return objs;
	}

	/**
	 * 单条件查询返回多行一列
	 * @param sql
	 * @param object
	 * @return List<Object>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<Object> getList(String sql, Object object) throws ClassNotFoundException, SQLException {
		List<Object> objs=new ArrayList<Object>();
		resultSet = getResultSet(sql, object);
		while (resultSet.next()) {
			Object obj = resultSet.getObject(1);
			objs.add(obj);
		}
		return objs;
	}

	/**
	 * 多条件查询返回多行一列
	 * @param sql
	 * @param objects
	 * @return List<Object>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Object> getList(String sql, Object[] objects) throws ClassNotFoundException, SQLException {
		List<Object> objs=new ArrayList<Object>();
		resultSet = getResultSet(sql, objects);
		while (resultSet.next()) {
			Object obj = resultSet.getObject(1);
			objs.add(obj);
		}
		return objs;
	}
	
	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * 给Map赋�?�用于解决实体类字段与数据库字段不一�?
	 * 
	 * @param map
	 * @return Map<String, String>
	 */
	public Map<String, String> setMap(Map<String, String> map) {
		if (this.map == null) {
			this.map = map;
		}
		return this.map;
	}

	/**
	 * 自动映射给对象赋�?
	 * 
	 * @param colCount
	 * @param rsmd
	 * @param class1
	 * @param t
	 * @param rs
	 * @return
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public Object setObject(int colCount, ResultSetMetaData rsmd, Class<?> class1, Object t, ResultSet rs)
			throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			InstantiationException {
		for (int i = 0; i < colCount; i++) {
			String columnName = rsmd.getColumnName(i + 1);// 数据库字段名
			String methodName = "set" + firstBig(columnName);// 获取ResultSet中的字段�?,拼接成方法名
			for (Method method : class1.getMethods()) {
				if (method.getName().substring(0, 3).equalsIgnoreCase("set")) {
					Class<?> parameterType = method.getParameterTypes()[0];
					String parameterTypeName = parameterType.getName();
					if (methodName.equalsIgnoreCase(method.getName())) {
						if (parameterTypeName.equalsIgnoreCase("int")) {
							method.invoke(t, rs.getInt(columnName));
						} else if (parameterTypeName.equalsIgnoreCase("double")) {
							method.invoke(t, rs.getDate(columnName));
						} else if (parameterTypeName.equalsIgnoreCase("float")) {
							method.invoke(t, rs.getFloat(columnName));
						} else if (parameterTypeName.equalsIgnoreCase("long")) {
							method.invoke(t, rs.getLong(columnName));
						} else if (parameterTypeName.equalsIgnoreCase("short")) {
							method.invoke(t, rs.getShort(columnName));
						} else if (parameterTypeName.equalsIgnoreCase("boolean")) {
							method.invoke(t, rs.getBoolean(columnName));
						} else {
							method.invoke(t, rs.getObject(columnName));
						}
					} else {
						if (parameterTypeName.equalsIgnoreCase("int") || parameterTypeName.equalsIgnoreCase("double")
								|| parameterTypeName.equalsIgnoreCase("float") || parameterTypeName.equalsIgnoreCase("long")
								|| parameterTypeName.equalsIgnoreCase("short") || parameterTypeName.equalsIgnoreCase("boolean")) {
						} else if (parameterType.getPackage().equals(class1.getPackage())) {
							method.invoke(t, setObject(colCount, rsmd, parameterType, parameterType.newInstance(), rs));
						}
					}
				}
			}
		}
		return t;
	}

	/**
	 * 通过Map给对象赋�?
	 * 
	 * @param colCount
	 * @param rsmd
	 * @param class1
	 * @param t
	 * @param rs
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public void setObjectMap(int colCount, ResultSetMetaData rsmd, Class<?> class1, Object t, ResultSet rs)
			throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			InstantiationException {
		for (int i = 0; i < colCount; i++) {
			String columnName = rsmd.getColumnName(i + 1);
			for (String key : map.keySet()) {// 获取Map的key
				String methodName = "set" + firstBig(key);// 拼接成实体类中的set方法
				String value = map.get(key);
				for (Method method : class1.getMethods()) {
					if (method.getName().substring(0, 3).equalsIgnoreCase("set")) {
						Class<?> parameterType = method.getParameterTypes()[0];
						String parameterTypeName = parameterType.getName();
						if (method.getName().equalsIgnoreCase(methodName)) {
							if (columnName.equalsIgnoreCase(value)) {
								if (parameterTypeName.equalsIgnoreCase("int")) {
									method.invoke(t, rs.getInt(columnName));
								} else if (parameterTypeName.equalsIgnoreCase("double")) {
									method.invoke(t, rs.getDate(columnName));
								} else if (parameterTypeName.equalsIgnoreCase("float")) {
									method.invoke(t, rs.getFloat(columnName));
								} else if (parameterTypeName.equalsIgnoreCase("long")) {
									method.invoke(t, rs.getLong(columnName));
								} else if (parameterTypeName.equalsIgnoreCase("short")) {
									method.invoke(t, rs.getShort(columnName));
								} else if (parameterTypeName.equalsIgnoreCase("boolean")) {
									method.invoke(t, rs.getBoolean(columnName));
								} else {
									method.invoke(t, rs.getObject(columnName));
								}
							} else {
								if (parameterTypeName.equalsIgnoreCase("int") || parameterTypeName.equalsIgnoreCase("double")
										|| parameterTypeName.equalsIgnoreCase("float") || parameterTypeName.equalsIgnoreCase("long")
										|| parameterTypeName.equalsIgnoreCase("short") || parameterTypeName.equalsIgnoreCase("boolean")) {
								} else if (parameterType.getPackage().equals(class1.getPackage())) {
									method.invoke(t,
											setObject(colCount, rsmd, parameterType, parameterType.newInstance(), rs));
								}
							}
						}
					}
				}
			}
		}
	}
	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * 返回对象(数据库字段与实体类字段不�?�?)
	 * 
	 * @param class1
	 * @param rs
	 * @param maps
	 * @return Object
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public <T> T setObject(Class<T> class1, ResultSet rs, Map<String, String> map) throws SQLException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		T t = null;
		ResultSetMetaData rsmd = rs.getMetaData();
		int colCount = rsmd.getColumnCount();// 返回ResultSet中的列数
		while (rs.next()) {
			t = class1.newInstance();// 创建对象
			setObjectMap(colCount, rsmd, class1, t, rs);
		}
		return t;
	}

	/**
	 * 通过ResultSet返回对象
	 * 
	 * @param <T>
	 * 
	 * @param class1
	 * @param rs
	 * @return Object
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public <T> T getObject(Class<T> class1, ResultSet rs) throws SQLException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		T t = null;
		if (map == null) {
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();// 返回ResultSet中的列数
			while (rs.next()) {
				t = class1.newInstance();// 创建对象
				setObject(colCount, rsmd, class1, t, rs);
			}
		} else {
			t = setObject(class1, rs, map);
		}
		return t;

	}

	/**
	 * 返回对象集合(数据库字段与实体类字段不�?�?)
	 * 
	 * @param <T>
	 * 
	 * @param class1
	 * @param rs
	 * @return List<Object>
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public <T> List<T> setList(Class<T> class1, ResultSet rs) throws SQLException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<T> ts = new ArrayList<T>();
		T t = null;
		ResultSetMetaData rsmd = rs.getMetaData();
		int colCount = rsmd.getColumnCount();// 返回ResultSet中的列数
		while (rs.next()) {
			t = class1.newInstance();// 创建对象
			setObjectMap(colCount, rsmd, class1, t, rs);
			ts.add(t);
		}
		return ts;
	}

	/**
	 * 通过ResultSet返回对象集合
	 * 
	 * @param <T>
	 * 
	 * @param class1
	 * @param rs
	 * @return List<Object>
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public <T> List<T> getList(Class<T> class1, ResultSet rs) throws SQLException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<T> ts = new ArrayList<T>();
		if (map == null) {
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();// 返回ResultSet中的列数
			while (rs.next()) {
				T t = class1.newInstance();// 创建对象
				setObject(colCount, rsmd, class1, t, rs);
				ts.add(t);
			}
		} else {
			ts = setList(class1, rs);
		}
		return ts;
	}
	// --------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * 无条件查询返回对�?
	 * 
	 * @param <T>
	 * @param class1
	 * @param sql
	 * @return Object
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public <T> T getObject(Class<T> class1, String sql) throws ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		resultSet = getResultSet(sql);
		return getObject(class1, resultSet);
	}

	/**
	 * 单条件查询返回对�?
	 * 
	 * @param <T>
	 * @param class1
	 * @param sql
	 * @param obj
	 * @return Object
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public <T> T getObject(Class<T> class1, String sql, Object object) throws ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		resultSet = getResultSet(sql, object);
		return getObject(class1, resultSet);
	}

	/**
	 * 多条件查询返回对�?
	 * 
	 * @param class1
	 * @param sql
	 * @param objects
	 * @return Object
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public <T> T getObject(Class<T> class1, String sql, Object[] objects) throws ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		resultSet = getResultSet(sql, objects);
		return getObject(class1, resultSet);
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * 无条件查询返回对象集�?
	 * 
	 * @param class1
	 * @param sql
	 * @return List<Object>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public <T> List<T> getList(Class<T> class1, String sql) throws ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		resultSet = getResultSet(sql);
		return getList(class1, resultSet);
	}

	/**
	 * 单条件查询返回对象集�?
	 * 
	 * @param <T>
	 * @param class1
	 * @param sql
	 * @param obj
	 * @return List<Object>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public <T> List<T> getList(Class<T> class1, String sql, Object object) throws ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		resultSet = getResultSet(sql, object);
		return getList(class1, resultSet);
	}

	/**
	 * 多条件查询返回对象集�?
	 * 
	 * @param <T>
	 * 
	 * @param class1
	 * @param sql
	 * @param objects
	 * @return List<Object>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public <T> List<T> getList(Class<T> class1, String sql, Object[] objects)
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		resultSet = getResultSet(sql, objects);
		return getList(class1, resultSet);
	}

	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	// --------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * 返回CallableStatement对象
	 * 
	 * @param sql
	 * @param objects
	 * @return CallableStatement
	 */
	public CallableStatement getcallableStatement(String sql, Object[] objects) {
		try {
			connection = getConnection();
			// 调用存储过程
			callableStatement = connection.prepareCall(sql);
			// 给参数赋�?
			if (objects != null) {
				for (int i = 0; i < objects.length; i++) {
					callableStatement.setObject(i + 1, objects[i]);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return callableStatement;
	}

	/**
	 * 调用存储过程
	 * 
	 * @param sql
	 * @param objects
	 * @param outParamPos
	 * @param sqlType
	 * @return Object
	 */
	public Object excuteQuery(String sql, Object[] objects, int outParamPos, int sqlType) {
		try {
			callableStatement = getcallableStatement(sql, objects);
			// 注册输出参数
			callableStatement.registerOutParameter(outParamPos, sqlType);
			// 执行
			callableStatement.execute();
			// 返回输出参数
			return callableStatement.getObject(outParamPos);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(callableStatement, resultSet, preparedStatement, connection);
		}
		return null;
	}
}
