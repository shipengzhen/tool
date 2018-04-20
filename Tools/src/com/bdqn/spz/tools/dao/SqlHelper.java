package com.bdqn.spz.tools.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

/**
 * 
 * 一个BaseDao对象代表一次数据库回话<br />
 * 一次回话期间，所有请求共用一个Connection对象
 * 
 * @author kong
 */
public class SqlHelper {
	
	public static class SQL{
		static Properties prop;
		public static String getProp(String key){
			if(prop==null){
				try {
					InputStream is=SQL.class.getClassLoader().getResourceAsStream("database.properties");
					prop=new Properties();
					prop.load(is);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return prop.getProperty(key);
		}
	}
	
	
	//驱动、地址、名字、密码
	//private static String driver="com.mysql.jdbc.Driver";
	//private static String url="jdbc:mysql://localhost:3306/yixiao?useUnicode=true&characterEncoding=utf-8";
	//private static String name="root";
	//private static String pwd="root";
	
	//声明连接
	private Connection conn=null;
	
	
	/**
	 * 构造方法，true为自动事务，默认false
	 */
	public SqlHelper(){
		setAutoCommit(false);
	}
	public SqlHelper(boolean b){
		setAutoCommit(b);
	}
	
	/**
	 * 根据配置信息加载一个Connection链接
	 */
	private Connection loadConn(){
		try {
			Class.forName(SQL.getProp("driver"));
			return DriverManager.getConnection(SQL.getProp("url"),SQL.getProp("user"),SQL.getProp("password"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获得Connection链接对象<br/>
	 * 一般情况下你不需要调用此方法
	 */
	public Connection getConn(){
		if(conn==null){
			conn=loadConn();
		} else {
			try {
				if(conn.isClosed()==true){
					conn=loadConn();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	/**
	 * 释放此Connection所占用的资源<br/>
	 * 并且如果你再次使用会逼迫其创建一个新连接
	 * 并且这个新连接将是自动事务
	 */
	public void closeConn(){
		try {
			if(conn!=null){
				if(conn.isClosed()==false){
					conn.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* * * * * * * * * * 事务相关 * * * * * * * * * * * * * * * * * */
	
	/**
	 * 设置是否自动提交事务，true为是，默认false
	 */
	public void setAutoCommit(boolean b){
		try {
			getConn().setAutoCommit(b);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	/**
	 * 提交事务
	 */
	public void commit(){
		try {
			if(getConn().getAutoCommit()==false){
				getConn().commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 回滚事务
	 */
	public void rollback(){
		try {
			if(getConn().getAutoCommit()==false){
				getConn().rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* * * * * * * * * * 基本方法 * * * * * * * * * * * * * * * * * */
	
	/**
	 * 增删改，返回受影响行数
	 * @param sql sql语句
	 * @param cans 参数列表
	 * @return
	 */
	public int getUpdate(String sql,List<?>cans){
		try {
			PreparedStatement pst=getConn().prepareStatement(sql);
			if(cans!=null){
				for (int i = 0; i < cans.size(); i++) {
					pst.setObject(i+1,cans.get(i));
				}
			}
			return pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	/**
	 * 增删改的重载，返回受影响行数，并且决定是否连带提交事务
	 * @param sql
	 * @param cans
	 * @param b 填写true，会在执行完sql后提交事务
	 * @return
	 */
	public int getUpdate(String sql,List<?>cans,boolean b){
		Integer ret=getUpdate(sql, cans);
		commit();
		return ret;
	}
	/**
	 * 查询，返回ResultSet
	 * @param sql
	 * @param cans 指定sql语句中的参数
	 * @return
	 */
	public ResultSet getQuery(String sql,List<?>cans){
		try {
			PreparedStatement pst=getConn().prepareStatement(sql);
			if(cans!=null){
				for (int i = 0; i < cans.size(); i++) {
					pst.setObject(i+1, cans.get(i));
				}
			}
			return pst.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 聚合查询，返回第一行第一列值
	 * @param sql
	 * @param cans
	 * @return
	 */
	public Object getScala(String sql,List<?>cans){
		try {
			ResultSet rs=getQuery(sql,cans);
			if(rs.next())
				return rs.getObject(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/* * * * * * * * * * 结果集映射 * * * * * * * * * * * * * * * * * */
	
	/**
	 * 将ResultSet--映射为--List< Map< String,Object> >
	 * <br/><br/>如果你想在不写实体类的情况下快速开发，这是个好选择
	 * <br/>并且重载方法的第二个参数填写true将自动打印集合，便与调试
	 */
	public List<Map<String,Object>> getListMap(ResultSet rs){
		//1、声明集合
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			ResultSetMetaData md = rs.getMetaData();//2、获取表头
			while (rs.next()) {
				Map<String,Object> rowData = new HashMap<String,Object>();
		        for (int i = 1; i <= md.getColumnCount(); i++){
		        	rowData.put(md.getColumnName(i), rs.getObject(i));
		        }
		        list.add(rowData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return list;
	}
	/**
	 * 将ResultSet--映射为--List< Map< String,Object> >
	 * <br/><br/>如果你想在不写实体类的情况下快速开发，这是个好选择
	 * <br/>并且重载方法的第二个参数填写true将自动打印集合，便与调试
	 */
	public List<Map<String,Object>> getListMap(ResultSet rs,boolean b){
		List<Map<String,Object>> list= getListMap(rs);
		if(b==true){
			for (Map<String, Object> map : list) {
				System.out.println(map.toString());
			}
		}
        return list;
	}
	
	/**
	 * <h1>将ResultSet映射为List< ? > </h1><br/>
	 * <h1>&nbsp;&nbsp;&nbsp;&nbsp;例如：getList(rs,User.class,null)</h1><br/>
	 * 如果表中的字段与实体类的属性不一致，用Map来做自定义映射<br/>
	 * Map< String,String >的K是数据库列名，V是实体类属性名<br/>
	 * @param rs  要映射的ResultSet对象
	 * @param c	  要映射的类型，一般写Xxx.class
	 * @param map  自定义的映射Map
	 * @return
	 */
	public <T> List<T> getList(ResultSet rs,Class<T> c,Map<String,String> map){
		List<T>he=new ArrayList<T>();//1、声明集合
		try {
			while(rs.next()){
				T obj=c.newInstance();//实例化T
				Field[] fs = c.getDeclaredFields();//获取此类所有属性
				//entity属性和表字段的映射
				for (Field f : fs) {
					setObjectProp(rs,obj,f.getName(),f.getName());
				}
				//调用者自定义的映射，遍历K映射到V
				if(map!=null){
					for (String k : map.keySet()) {
						setObjectProp(rs,obj,k,map.get(k));
						
					}
				}
				he.add(obj);//添加到集合
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return he;
	}
	/** 将指定ResultSet对象映射到指定对象属性里，并且指定映射关系*/
	private void setObjectProp(ResultSet rs,Object obj,String k,String v){
		try {
			
			//判断，如果是纯属性
			if(v.indexOf('.')==-1){
				Field f = obj.getClass().getDeclaredField(v);//获得属性
				f.setAccessible(true);
				Object rsValue=this.getRSValue(rs,k,f.getType());
				f.set(obj,rsValue);
			}else{
				int dian=v.indexOf('.');
				String zuo=v.substring(0,dian);
				String you=v.substring(dian+1,v.length());
				
				//获得属性
				
				Field propF=obj.getClass().getDeclaredField(zuo);//属性的Field
				Class<?>propC=propF.getType();//该属性的类型
				Object propObj=propF.get(obj);//该属性
				if(propObj==null){
					propObj=propC.newInstance();
				}
				setObjectProp(rs,propObj,k,you);		
				
			}
			
		} catch (NoSuchFieldException e) {
			e.printStackTrace();//找不到属性
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			//e.printStackTrace();//返回空值赋值给基本类型将引发错误
		} catch (IllegalAccessException e) {
			e.printStackTrace();//属性赋值错误
		} catch (InstantiationException e) {
			e.printStackTrace();//复杂类型创建对象可能触发的无构造错误
		}
	}
	
	
	
	/**
	 * 从[ResultSet]中获取[指定列名]的值，并且指定[数据类型]
	 */
	private Object getRSValue(ResultSet rs,String cName,Class<?> c){
		String type=c.getName();//获取类型信息
		try {
			if (type.equalsIgnoreCase("java.lang.String")) {
				return rs.getString(cName);
			} else if (type.equalsIgnoreCase("int")||type.equalsIgnoreCase("java.lang.Integer")) {
	        	return rs.getInt(cName);
	        } else if (type.equalsIgnoreCase("long")||type.equalsIgnoreCase("java.lang.Long")) {
				return rs.getLong(cName);
	        } else if (type.equalsIgnoreCase("short")||type.equalsIgnoreCase("java.lang.Short")) {
	        	return rs.getShort(cName);
	        } else if (type.equalsIgnoreCase("float")||type.equalsIgnoreCase("java.lang.Float")) {
	        	return rs.getFloat(cName);
	        } else if (type.equalsIgnoreCase("double")||type.equalsIgnoreCase("java.lang.Double")) {
	        	return rs.getDouble(cName);
	        } else if (type.equalsIgnoreCase("boolean")||type.equalsIgnoreCase("java.lang.Boolean")) {
	        	return rs.getBoolean(cName);
	        } else if (type.equals("java.sql.Date")||type.equals("java.util.Date")) {
	        	return rs.getDate(cName);
			} else{
				//层层突破，定是复杂类型
				try {
					Object propObj=c.newInstance();//实例化
					Field[] fs = c.getDeclaredFields();//获取此类所有属性
					for (Field f : fs) {
						setObjectProp(rs,propObj,f.getName(),f.getName());
					}
					return propObj;
				} catch (InstantiationException e) {
					//e.printStackTrace();实例化接口可能遇到的异常
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
	        }
		} catch (SQLException e) {
			//e.printStackTrace();//无此列名引发异常
		}
		return null;
	}
	
	
	
	/* * * * * * * * * * 存储过程 * * * * * * * * * * * * * * * * * * */
	
	/**
	 * 指定sql和参数，加载一个CallableStatement存储过程对象
	 */
	private CallableStatement loadCall(String sql,Object[]cans){
		try {
			CallableStatement call=getConn().prepareCall(sql);
			if(cans!=null){
				for (int i = 0; i < cans.length; i++) {
					call.setObject(i+1, cans[i]);
				}
			}
			return call;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 调用存储过程--增删改，返回受影响行数，并且指定哪些参数的是输出参数<br/><br/>
	 * &nbsp;&nbsp;例如int[]为{1,3}将代表Object[]中下标1和3的参数为输出参数<br/>
	 */
	public int callUpdate(String sql,Object[]cans,int[]outs){
		try {
			CallableStatement call=loadCall(sql, cans);
			int res=call.executeUpdate();
			if(outs!=null){
				for (int i = 0; i < outs.length; i++) {
					call.registerOutParameter(outs[i]+1,Types.JAVA_OBJECT);
					cans[outs[i]]=call.getObject(outs[i]+1);
				}
			}
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	/**
	 * 调用存储过程--查询，返回ResultSet，并且指定哪些参数的是输出参数<br/><br/>
	 * &nbsp;&nbsp;例如int[]为{1,3}将代表Object[]中下标1和3的参数为输出参数<br/>
	 */
	public ResultSet callQuery(String sql,Object[]cans,int[]outs){
		try {
			CallableStatement call=loadCall(sql, cans);
			ResultSet rs=call.executeQuery();
			if(outs!=null){
				for (int i = 0; i < outs.length; i++) {
					call.registerOutParameter(outs[i]+1,Types.JAVA_OBJECT);
					cans[outs[i]]=call.getObject(outs[i]+1);
				}
			}
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 调用存储过程--聚合查询，返回单行单列，并且指定哪些参数的是输出参数<br/><br/>
	 * &nbsp;&nbsp;例如int[]为{1,3}将代表Object[]中下标1和3的参数为输出参数<br/>
	 */
	public Object callScala(String sql,Object[]cans,int[]outs){
		try {
			ResultSet rs=callQuery(sql, cans, outs);
			if(rs.next())
				return rs.getObject(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/* * * * * * * * * * 一些周边方法 * * * * * * * * * * * * * * * * * */
	
	/**
	 * <h1>快速组装一个Map集合</h1>
	 * 例如：loadMap("userId","id","password","pwd")<br/>
	 * 将得到一个{userId=id, password=pwd}
	 */
	public Map<String, String> doMap(String... kvs){
		Map<String, String>map=new HashMap<String, String>();
		for (int i=1;i<kvs.length;i+=2) {
			map.put(kvs[i-1], kvs[i]);
		}
		return map;
	}
	
	
	
	/**
	 * 快速组装一个List< Object >
	 * <br/>我承认这是最没技术含量的一个函数，但是它确实可以让你的代码更加优雅
	 */
	public List<Object> doOA(Object... cans){
		List<Object>list=new ArrayList<Object>();
		if(cans!=null){
			for(int i=0;i<cans.length;i++){
				list.add(cans[i]);
			}
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
}