package com.spz.tools.tool.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

/**
 * 
 * һ��BaseDao�������һ�����ݿ�ػ�<br />
 * һ�λػ��ڼ䣬����������һ��Connection����
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
	
	
	//��������ַ�����֡�����
	//private static String driver="com.mysql.jdbc.Driver";
	//private static String url="jdbc:mysql://localhost:3306/yixiao?useUnicode=true&characterEncoding=utf-8";
	//private static String name="root";
	//private static String pwd="root";
	
	//��������
	private Connection conn=null;
	
	
	/**
	 * ���췽����trueΪ�Զ�����Ĭ��false
	 */
	public SqlHelper(){
		setAutoCommit(false);
	}
	public SqlHelper(boolean b){
		setAutoCommit(b);
	}
	
	/**
	 * ����������Ϣ����һ��Connection����
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
	 * ���Connection���Ӷ���<br/>
	 * һ��������㲻��Ҫ���ô˷���
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
	 * �ͷŴ�Connection��ռ�õ���Դ<br/>
	 * ����������ٴ�ʹ�û�����䴴��һ��������
	 * ������������ӽ����Զ�����
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
	
	/* * * * * * * * * * ������� * * * * * * * * * * * * * * * * * */
	
	/**
	 * �����Ƿ��Զ��ύ����trueΪ�ǣ�Ĭ��false
	 */
	public void setAutoCommit(boolean b){
		try {
			getConn().setAutoCommit(b);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	/**
	 * �ύ����
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
	 * �ع�����
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

	/* * * * * * * * * * �������� * * * * * * * * * * * * * * * * * */
	
	/**
	 * ��ɾ�ģ�������Ӱ������
	 * @param sql sql���
	 * @param cans �����б�
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
	 * ��ɾ�ĵ����أ�������Ӱ�����������Ҿ����Ƿ������ύ����
	 * @param sql
	 * @param cans
	 * @param b ��дtrue������ִ����sql���ύ����
	 * @return
	 */
	public int getUpdate(String sql,List<?>cans,boolean b){
		Integer ret=getUpdate(sql, cans);
		commit();
		return ret;
	}
	/**
	 * ��ѯ������ResultSet
	 * @param sql
	 * @param cans ָ��sql����еĲ���
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
	 * �ۺϲ�ѯ�����ص�һ�е�һ��ֵ
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

	/* * * * * * * * * * �����ӳ�� * * * * * * * * * * * * * * * * * */
	
	/**
	 * ��ResultSet--ӳ��Ϊ--List< Map< String,Object> >
	 * <br/><br/>��������ڲ�дʵ���������¿��ٿ��������Ǹ���ѡ��
	 * <br/>�������ط����ĵڶ���������дtrue���Զ���ӡ���ϣ��������
	 */
	public List<Map<String,Object>> getListMap(ResultSet rs){
		//1����������
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			ResultSetMetaData md = rs.getMetaData();//2����ȡ��ͷ
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
	 * ��ResultSet--ӳ��Ϊ--List< Map< String,Object> >
	 * <br/><br/>��������ڲ�дʵ���������¿��ٿ��������Ǹ���ѡ��
	 * <br/>�������ط����ĵڶ���������дtrue���Զ���ӡ���ϣ��������
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
	 * <h1>��ResultSetӳ��ΪList< ? > </h1><br/>
	 * <h1>&nbsp;&nbsp;&nbsp;&nbsp;���磺getList(rs,User.class,null)</h1><br/>
	 * ������е��ֶ���ʵ��������Բ�һ�£���Map�����Զ���ӳ��<br/>
	 * Map< String,String >��K�����ݿ�������V��ʵ����������<br/>
	 * @param rs  Ҫӳ���ResultSet����
	 * @param c	  Ҫӳ������ͣ�һ��дXxx.class
	 * @param map  �Զ����ӳ��Map
	 * @return
	 */
	public <T> List<T> getList(ResultSet rs,Class<T> c,Map<String,String> map){
		List<T>he=new ArrayList<T>();//1����������
		try {
			while(rs.next()){
				T obj=c.newInstance();//ʵ����T
				Field[] fs = c.getDeclaredFields();//��ȡ������������
				//entity���Ժͱ��ֶε�ӳ��
				for (Field f : fs) {
					setObjectProp(rs,obj,f.getName(),f.getName());
				}
				//�������Զ����ӳ�䣬����Kӳ�䵽V
				if(map!=null){
					for (String k : map.keySet()) {
						setObjectProp(rs,obj,k,map.get(k));
						
					}
				}
				he.add(obj);//��ӵ�����
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
	/** ��ָ��ResultSet����ӳ�䵽ָ���������������ָ��ӳ���ϵ*/
	private void setObjectProp(ResultSet rs,Object obj,String k,String v){
		try {
			
			//�жϣ�����Ǵ�����
			if(v.indexOf('.')==-1){
				Field f = obj.getClass().getDeclaredField(v);//�������
				f.setAccessible(true);
				Object rsValue=this.getRSValue(rs,k,f.getType());
				f.set(obj,rsValue);
			}else{
				int dian=v.indexOf('.');
				String zuo=v.substring(0,dian);
				String you=v.substring(dian+1,v.length());
				
				//�������
				
				Field propF=obj.getClass().getDeclaredField(zuo);//���Ե�Field
				Class<?>propC=propF.getType();//�����Ե�����
				Object propObj=propF.get(obj);//������
				if(propObj==null){
					propObj=propC.newInstance();
				}
				setObjectProp(rs,propObj,k,you);		
				
			}
			
		} catch (NoSuchFieldException e) {
			e.printStackTrace();//�Ҳ�������
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			//e.printStackTrace();//���ؿ�ֵ��ֵ���������ͽ���������
		} catch (IllegalAccessException e) {
			e.printStackTrace();//���Ը�ֵ����
		} catch (InstantiationException e) {
			e.printStackTrace();//�������ʹ���������ܴ������޹������
		}
	}
	
	
	
	/**
	 * ��[ResultSet]�л�ȡ[ָ������]��ֵ������ָ��[��������]
	 */
	private Object getRSValue(ResultSet rs,String cName,Class<?> c){
		String type=c.getName();//��ȡ������Ϣ
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
				//���ͻ�ƣ����Ǹ�������
				try {
					Object propObj=c.newInstance();//ʵ����
					Field[] fs = c.getDeclaredFields();//��ȡ������������
					for (Field f : fs) {
						setObjectProp(rs,propObj,f.getName(),f.getName());
					}
					return propObj;
				} catch (InstantiationException e) {
					//e.printStackTrace();ʵ�����ӿڿ����������쳣
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
	        }
		} catch (SQLException e) {
			//e.printStackTrace();//�޴����������쳣
		}
		return null;
	}
	
	
	
	/* * * * * * * * * * �洢���� * * * * * * * * * * * * * * * * * * */
	
	/**
	 * ָ��sql�Ͳ���������һ��CallableStatement�洢���̶���
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
	 * ���ô洢����--��ɾ�ģ�������Ӱ������������ָ����Щ���������������<br/><br/>
	 * &nbsp;&nbsp;����int[]Ϊ{1,3}������Object[]���±�1��3�Ĳ���Ϊ�������<br/>
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
	 * ���ô洢����--��ѯ������ResultSet������ָ����Щ���������������<br/><br/>
	 * &nbsp;&nbsp;����int[]Ϊ{1,3}������Object[]���±�1��3�Ĳ���Ϊ�������<br/>
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
	 * ���ô洢����--�ۺϲ�ѯ�����ص��е��У�����ָ����Щ���������������<br/><br/>
	 * &nbsp;&nbsp;����int[]Ϊ{1,3}������Object[]���±�1��3�Ĳ���Ϊ�������<br/>
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
	
	/* * * * * * * * * * һЩ�ܱ߷��� * * * * * * * * * * * * * * * * * */
	
	/**
	 * <h1>������װһ��Map����</h1>
	 * ���磺loadMap("userId","id","password","pwd")<br/>
	 * ���õ�һ��{userId=id, password=pwd}
	 */
	public Map<String, String> doMap(String... kvs){
		Map<String, String>map=new HashMap<String, String>();
		for (int i=1;i<kvs.length;i+=2) {
			map.put(kvs[i-1], kvs[i]);
		}
		return map;
	}
	
	
	
	/**
	 * ������װһ��List< Object >
	 * <br/>�ҳ���������û����������һ��������������ȷʵ��������Ĵ����������
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