package ${objs.pagckdir}.impl;

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
import ${objs.pagckdir}.*;

public class ${objs.className}DaoImpl extends HibernateDaoSupport implements ${objs.className}Dao{
       private static final Log log = LogFactory.getLog(${objs.className}DaoImpl.class);
       /**
	    * Constructer of this class ;
	    * 作者：zyf
	    * 编写时间：2010.1.16
	    * 模板类型：DAOImpl模板，1.0版
	    */

    public void insert(${objs.className} vo) throws Exception{
        try {
			getHibernateTemplate().save(vo);
			log.debug("save successful");
		}catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
    
    }
    public void modify(${objs.className} vo) throws Exception{
        try {
			getHibernateTemplate().saveOrUpdate(vo);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
    }
    public void remove(${objs.className} vo) throws Exception{
        try {
			getHibernateTemplate().delete(vo);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
    }
    /**
      * 实现分页的查询,通过SQL语句
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
				    query = session.createSQLQuery(hql).addEntity(${objs.pagckdir}.${objs.className}.class);
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
	
	public List query(${objs.className} vo)throws Exception{
	     String tableName = "${objs.tableName}";
	     String ls_sql = "select * from " + tableName + " where 1 = 1 " ;
     <#list polist as o>
	     ${o.ftype} _${o.fieldSet} = vo.get${o.fieldSet}();
	     <#if o.fname != 'pageIndex' && o.fname != 'pageSize'>
	     if(_${o.fieldSet} != null && !_${o.fieldSet}.equals("")){
	         <#if o.sql_type == 'date' || o.sql_type == 'datetime'>
	             ls_sql = ls_sql + " and ${o.columnName}= to_date(" + _${o.fieldSet} + ",'yyyy-mm-dd')";
	         <#else>
	             ls_sql = ls_sql + " and ${o.columnName}='" + _${o.fieldSet} + "'";
	         </#if>  
	     }
	     </#if>
	 </#list>
	     int _PageIndex = vo.getPageIndex();
	     int _PageSize = vo.getPageSize();
	     System.out.println("执行的查询sql语句:" + ls_sql);
	     return queryForPage(ls_sql,_PageIndex,_PageSize,false);
	}
//------------------------------------------------------------
}
