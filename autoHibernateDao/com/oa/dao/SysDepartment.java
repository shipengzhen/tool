package com.oa.dao;


import java.util.*;
import javax.servlet.http.*; 
import java.sql.*;

public class SysDepartment extends ValueObject{
       /**
	    * Constructer of this class ;
	    * ���ߣ�zyf
	    * ��дʱ�䣺2010.1.16
	    * ģ�����ͣ�VOģ�壬1.0��
	    */
       private java.math.BigDecimal  did = null;
       private java.lang.String  dname = null;
//------------------------------------------------------------
        public void setDid(java.math.BigDecimal did){
            this.did = did;
        }
        public java.math.BigDecimal getDid(){
            return this.did;
        }   
        public void setDname(java.lang.String dname){
            this.dname = dname;
        }
        public java.lang.String getDname(){
            return this.dname;
        }   
//------------------------------------------------------------
    public static SysDepartment getInstanceFrom(HttpServletRequest request) throws Exception  {
       SysDepartment vo = new SysDepartment();
       String _did = request.getParameter("did");
       
       if(_did != null && _did.length() > 0){
           vo.setDid(new java.math.BigDecimal(_did));
       }
       String _dname = request.getParameter("dname");
       
       if(_dname != null && _dname.length() > 0){
           vo.setDname(new java.lang.String(_dname));
       }
       return vo;
    }
    //-----------------------------------------------------------         

}
