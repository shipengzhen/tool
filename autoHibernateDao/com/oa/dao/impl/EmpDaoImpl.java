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

public class EmpDaoImpl extends HibernateDaoSupport implements EmpDao{
       private static final Log log = LogFactory.getLog(EmpDaoImpl.class);
       /**
	    * Constructer of this class ;
	    * ���ߣ�zyf
	    * ��дʱ�䣺2010.1.16
	    * ģ�����ͣ�DAOImplģ�壬1.0��
	    */

    public void insert(Emp vo) throws Exception{
        try {
			getHibernateTemplate().save(vo);
			log.debug("save successful");
		}catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
    
    }
    public void modify(Emp vo) throws Exception{
        try {
			getHibernateTemplate().saveOrUpdate(vo);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
    }
    public void remove(Emp vo) throws Exception{
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
				    query = session.createSQLQuery(hql).addEntity(com.oa.dao.Emp.class);
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
	
	public List query(Emp vo)throws Exception{
	     String tableName = "EMP";
	     String ls_sql = "select * from " + tableName + " where 1 = 1 " ;
	     java.math.BigDecimal _Empno = vo.getEmpno();
	     if(_Empno != null && !_Empno.equals("")){
	             ls_sql = ls_sql + " and EMPNO='" + _Empno + "'";
	     }
	     java.lang.String _Empname = vo.getEmpname();
	     if(_Empname != null && !_Empname.equals("")){
	             ls_sql = ls_sql + " and EMPNAME='" + _Empname + "'";
	     }
	     java.lang.String _Job = vo.getJob();
	     if(_Job != null && !_Job.equals("")){
	             ls_sql = ls_sql + " and JOB='" + _Job + "'";
	     }
	     java.math.BigDecimal _Mgr = vo.getMgr();
	     if(_Mgr != null && !_Mgr.equals("")){
	             ls_sql = ls_sql + " and MGR='" + _Mgr + "'";
	     }
	     java.sql.Timestamp _Hiredate = vo.getHiredate();
	     if(_Hiredate != null && !_Hiredate.equals("")){
	             ls_sql = ls_sql + " and HIREDATE= to_date(" + _Hiredate + ",'yyyy-mm-dd')";
	     }
	     java.math.BigDecimal _Sal = vo.getSal();
	     if(_Sal != null && !_Sal.equals("")){
	             ls_sql = ls_sql + " and SAL='" + _Sal + "'";
	     }
	     java.math.BigDecimal _Comm = vo.getComm();
	     if(_Comm != null && !_Comm.equals("")){
	             ls_sql = ls_sql + " and COMM='" + _Comm + "'";
	     }
	     java.math.BigDecimal _Deptno = vo.getDeptno();
	     if(_Deptno != null && !_Deptno.equals("")){
	             ls_sql = ls_sql + " and DEPTNO='" + _Deptno + "'";
	     }
	     int _PageIndex = vo.getPageIndex();
	     int _PageSize = vo.getPageSize();
	     System.out.println("ִ�еĲ�ѯsql���:" + ls_sql);
	     return queryForPage(ls_sql,_PageIndex,_PageSize,false);
	}
//------------------------------------------------------------
}
