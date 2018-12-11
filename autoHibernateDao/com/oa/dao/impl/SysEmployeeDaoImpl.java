package com.oa.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.sql.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.oa.dao.*;

public class SysEmployeeDaoImpl extends HibernateDaoSupport implements SysEmployeeDao{
       private static final Log log = LogFactory.getLog(SysEmployeeDaoImpl.class);
       /**
	    * Constructer of this class ;
	    * ���ߣ�zyf
	    * ��дʱ�䣺2010.1.16
	    * ģ�����ͣ�DAOImplģ�壬1.0��
	    */

    public void insert(SysEmployee vo) throws Exception{
        try {
			getHibernateTemplate().save(vo);
			log.debug("save successful");
		}catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
    
    }
    public void modify(SysEmployee vo) throws Exception{
        try {
			getHibernateTemplate().saveOrUpdate(vo);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
    }
    public void remove(SysEmployee vo) throws Exception{
        try {
			getHibernateTemplate().delete(vo);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
    }
    /**
      * ʵ�ַ�ҳ�Ĳ�ѯ,ͨ��SQL���
      *
      */
    public List queryForPage(final String hql,final int pageIndex,final int pageSize,final boolean is)throws Exception { 
		List list = getHibernateTemplate().executeFind(new HibernateCallback() { 
			public Object doInHibernate(Session session) 
				throws HibernateException, SQLException { 
				//Query query = session.createQuery(hql); 
				Query query = null;
				if(is){
				    query = session.createSQLQuery(hql);
				}else{
				    query = session.createSQLQuery(hql).addEntity(com.oa.dao.SysEmployee.class);
				}
				if(pageIndex > 0 && pageSize > 0){
				   query.setFirstResult((pageIndex - 1) * pageSize); 
				   query.setMaxResults(pageSize); 
				}
				List list = query.list(); 
				return list; 
				} 
			}); 
		return list; 
	} 
	
	public List query(SysEmployee vo)throws Exception{
	     String tableName = "SYS_EMPLOYEE";
	     String ls_sql = "select * from " + tableName + " where 1 = 1 " ;
	     java.math.BigDecimal _Eid = vo.getEid();
	     if(_Eid != null && !_Eid.equals("")){
	             ls_sql = ls_sql + " and EID='" + _Eid + "'";
	     }
	     java.math.BigDecimal _PositionId = vo.getPositionId();
	     if(_PositionId != null && !_PositionId.equals("")){
	             ls_sql = ls_sql + " and POSITION_ID='" + _PositionId + "'";
	     }
	     java.math.BigDecimal _DepartmentId = vo.getDepartmentId();
	     if(_DepartmentId != null && !_DepartmentId.equals("")){
	             ls_sql = ls_sql + " and DEPARTMENT_ID='" + _DepartmentId + "'";
	     }
	     java.lang.String _Ename = vo.getEname();
	     if(_Ename != null && !_Ename.equals("")){
	             ls_sql = ls_sql + " and ENAME='" + _Ename + "'";
	     }
	     java.lang.String _Epassword = vo.getEpassword();
	     if(_Epassword != null && !_Epassword.equals("")){
	             ls_sql = ls_sql + " and EPASSWORD='" + _Epassword + "'";
	     }
	     java.lang.String _Status = vo.getStatus();
	     if(_Status != null && !_Status.equals("")){
	             ls_sql = ls_sql + " and STATUS='" + _Status + "'";
	     }
	     int _PageIndex = vo.getPageIndex();
	     int _PageSize = vo.getPageSize();
	     System.out.println("ִ�еĲ�ѯsql���:" + ls_sql);
	     return queryForPage(ls_sql,_PageIndex,_PageSize,false);
	}
//------------------------------------------------------------
}
