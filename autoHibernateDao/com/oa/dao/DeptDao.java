package com.oa.dao;


import java.util.*;
import java.sql.*;

public interface DeptDao{
       /**
	    * Constructer of this class ;
	    * ���ߣ�zyf
	    * ��дʱ�䣺2010.1.16
	    * ģ�����ͣ�DAOģ�壬1.0��
	    */

    public abstract void insert(Dept vo) throws Exception;
    public abstract void modify(Dept vo) throws Exception;
    public abstract void remove(Dept vo) throws Exception;
    public List queryForPage(final String hql,final int offset,final int length,boolean is)throws Exception;
    public List query(Dept vo)throws Exception;
//------------------------------------------------------------
}
