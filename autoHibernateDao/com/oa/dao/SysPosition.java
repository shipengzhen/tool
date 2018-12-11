package com.oa.dao;


import java.util.*;
import javax.servlet.http.*; 
import java.sql.*;

public class SysPosition extends ValueObject{
       /**
	    * Constructer of this class ;
	    * ���ߣ�zyf
	    * ��дʱ�䣺2010.1.16
	    * ģ�����ͣ�VOģ�壬1.0��
	    */
       private java.math.BigDecimal  pid = null;
       private java.lang.String  pname = null;
//------------------------------------------------------------
        public void setPid(java.math.BigDecimal pid){
            this.pid = pid;
        }
        public java.math.BigDecimal getPid(){
            return this.pid;
        }   
        public void setPname(java.lang.String pname){
            this.pname = pname;
        }
        public java.lang.String getPname(){
            return this.pname;
        }   
//------------------------------------------------------------
    public static SysPosition getInstanceFrom(HttpServletRequest request) throws Exception  {
       SysPosition vo = new SysPosition();
       String _pid = request.getParameter("pid");
       
       if(_pid != null && _pid.length() > 0){
           vo.setPid(new java.math.BigDecimal(_pid));
       }
       String _pname = request.getParameter("pname");
       
       if(_pname != null && _pname.length() > 0){
           vo.setPname(new java.lang.String(_pname));
       }
       return vo;
    }
    //-----------------------------------------------------------         

}
