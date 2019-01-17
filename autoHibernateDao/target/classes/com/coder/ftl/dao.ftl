package ${objs.pagckdir};


import java.util.*;
import java.sql.*;

public interface ${objs.className}Dao{
       /**
	    * Constructer of this class ;
	    * 作者：zyf
	    * 编写时间：2010.1.16
	    * 模板类型：DAO模板，1.0版
	    */

    public abstract void insert(${objs.className} vo) throws Exception;
    public abstract void modify(${objs.className} vo) throws Exception;
    public abstract void remove(${objs.className} vo) throws Exception;
    public List queryForPage(final String hql,final int offset,final int length,boolean is)throws Exception;
    public List query(${objs.className} vo)throws Exception;
//------------------------------------------------------------
}
