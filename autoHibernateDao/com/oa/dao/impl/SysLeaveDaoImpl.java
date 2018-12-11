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

public class SysLeaveDaoImpl extends HibernateDaoSupport implements SysLeaveDao{
       private static final Log log = LogFactory.getLog(SysLeaveDaoImpl.class);
       /**
	    * Constructer of this class ;
	    * ���ߣ�zyf
	    * ��дʱ�䣺2010.1.16
	    * ģ�����ͣ�DAOImplģ�壬1.0��
	    */

    public void insert(SysLeave vo) throws Exception{
        try {
			getHibernateTemplate().save(vo);
			log.debug("save successful");
		}catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
    
    }
    public void modify(SysLeave vo) throws Exception{
        try {
			getHibernateTemplate().saveOrUpdate(vo);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
    }
    public void remove(SysLeave vo) throws Exception{
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
				    query = session.createSQLQuery(hql).addEntity(com.oa.dao.SysLeave.class);
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
	
	public List query(SysLeave vo)throws Exception{
	     String tableName = "SYS_LEAVE";
	     String ls_sql = "select * from " + tableName + " where 1 = 1 " ;
	     java.math.BigDecimal _Id = vo.getId();
	     if(_Id != null && !_Id.equals("")){
	             ls_sql = ls_sql + " and ID='" + _Id + "'";
	     }
	     java.sql.Timestamp _Begindate = vo.getBegindate();
	     if(_Begindate != null && !_Begindate.equals("")){
	             ls_sql = ls_sql + " and BEGINDATE= to_date(" + _Begindate + ",'yyyy-mm-dd')";
	     }
	     java.sql.Timestamp _Closuredate = vo.getClosuredate();
	     if(_Closuredate != null && !_Closuredate.equals("")){
	             ls_sql = ls_sql + " and CLOSUREDATE= to_date(" + _Closuredate + ",'yyyy-mm-dd')";
	     }
	     java.math.BigDecimal _Days = vo.getDays();
	     if(_Days != null && !_Days.equals("")){
	             ls_sql = ls_sql + " and DAYS='" + _Days + "'";
	     }
	     java.lang.String _Event = vo.getEvent();
	     if(_Event != null && !_Event.equals("")){
	             ls_sql = ls_sql + " and EVENT='" + _Event + "'";
	     }
	     java.lang.String _Leavetype = vo.getLeavetype();
	     if(_Leavetype != null && !_Leavetype.equals("")){
	             ls_sql = ls_sql + " and LEAVETYPE='" + _Leavetype + "'";
	     }
	     java.math.BigDecimal _ProposerEid = vo.getProposerEid();
	     if(_ProposerEid != null && !_ProposerEid.equals("")){
	             ls_sql = ls_sql + " and PROPOSER_EID='" + _ProposerEid + "'";
	     }
	     java.math.BigDecimal _ExecutorEid = vo.getExecutorEid();
	     if(_ExecutorEid != null && !_ExecutorEid.equals("")){
	             ls_sql = ls_sql + " and EXECUTOR_EID='" + _ExecutorEid + "'";
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
