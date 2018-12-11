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

public class BizCheckResultDaoImpl extends HibernateDaoSupport implements BizCheckResultDao{
       private static final Log log = LogFactory.getLog(BizCheckResultDaoImpl.class);
       /**
	    * Constructer of this class ;
	    * ���ߣ�zyf
	    * ��дʱ�䣺2010.1.16
	    * ģ�����ͣ�DAOImplģ�壬1.0��
	    */

    public void insert(BizCheckResult vo) throws Exception{
        try {
			getHibernateTemplate().save(vo);
			log.debug("save successful");
		}catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
    
    }
    public void modify(BizCheckResult vo) throws Exception{
        try {
			getHibernateTemplate().saveOrUpdate(vo);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
    }
    public void remove(BizCheckResult vo) throws Exception{
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
				    query = session.createSQLQuery(hql).addEntity(com.oa.dao.BizCheckResult.class);
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
	
	public List query(BizCheckResult vo)throws Exception{
	     String tableName = "BIZ_CHECK_RESULT";
	     String ls_sql = "select * from " + tableName + " where 1 = 1 " ;
	     java.math.BigDecimal _Id = vo.getId();
	     if(_Id != null && !_Id.equals("")){
	             ls_sql = ls_sql + " and ID='" + _Id + "'";
	     }
	     java.math.BigDecimal _ClaimId = vo.getClaimId();
	     if(_ClaimId != null && !_ClaimId.equals("")){
	             ls_sql = ls_sql + " and CLAIM_ID='" + _ClaimId + "'";
	     }
	     java.sql.Timestamp _CheckDate = vo.getCheckDate();
	     if(_CheckDate != null && !_CheckDate.equals("")){
	             ls_sql = ls_sql + " and CHECK_DATE= to_date(" + _CheckDate + ",'yyyy-mm-dd')";
	     }
	     java.math.BigDecimal _CheckEid = vo.getCheckEid();
	     if(_CheckEid != null && !_CheckEid.equals("")){
	             ls_sql = ls_sql + " and CHECK_EID='" + _CheckEid + "'";
	     }
	     java.lang.String _Result = vo.getResult();
	     if(_Result != null && !_Result.equals("")){
	             ls_sql = ls_sql + " and RESULT='" + _Result + "'";
	     }
	     java.lang.String _Comm = vo.getComm();
	     if(_Comm != null && !_Comm.equals("")){
	             ls_sql = ls_sql + " and COMM='" + _Comm + "'";
	     }
	     int _PageIndex = vo.getPageIndex();
	     int _PageSize = vo.getPageSize();
	     System.out.println("ִ�еĲ�ѯsql���:" + ls_sql);
	     return queryForPage(ls_sql,_PageIndex,_PageSize,false);
	}
//------------------------------------------------------------
}
