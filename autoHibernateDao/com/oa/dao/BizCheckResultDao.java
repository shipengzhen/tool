package com.oa.dao;


import java.util.*;
import java.sql.*;

public interface BizCheckResultDao{
       /**
	    * Constructer of this class ;
	    * ���ߣ�zyf
	    * ��дʱ�䣺2010.1.16
	    * ģ�����ͣ�DAOģ�壬1.0��
	    */

    public abstract void insert(BizCheckResult vo) throws Exception;
    public abstract void modify(BizCheckResult vo) throws Exception;
    public abstract void remove(BizCheckResult vo) throws Exception;
    public List queryForPage(final String hql,final int offset,final int length,boolean is)throws Exception;
    public List query(BizCheckResult vo)throws Exception;
//------------------------------------------------------------
}
