package com.oa.dao;


import java.util.*;
import javax.servlet.http.*; 
import java.sql.*;

public class Dept extends ValueObject{
       /**
	    * Constructer of this class ;
	    * ���ߣ�zyf
	    * ��дʱ�䣺2010.1.16
	    * ģ�����ͣ�VOģ�壬1.0��
	    */
       private java.math.BigDecimal  deptno = null;
       private java.lang.String  deptname = null;
       private java.lang.String  loc = null;
//------------------------------------------------------------
        public void setDeptno(java.math.BigDecimal deptno){
            this.deptno = deptno;
        }
        public java.math.BigDecimal getDeptno(){
            return this.deptno;
        }   
        public void setDeptname(java.lang.String deptname){
            this.deptname = deptname;
        }
        public java.lang.String getDeptname(){
            return this.deptname;
        }   
        public void setLoc(java.lang.String loc){
            this.loc = loc;
        }
        public java.lang.String getLoc(){
            return this.loc;
        }   
//------------------------------------------------------------
    public static Dept getInstanceFrom(HttpServletRequest request) throws Exception  {
       Dept vo = new Dept();
       String _deptno = request.getParameter("deptno");
       
       if(_deptno != null && _deptno.length() > 0){
           vo.setDeptno(new java.math.BigDecimal(_deptno));
       }
       String _deptname = request.getParameter("deptname");
       
       if(_deptname != null && _deptname.length() > 0){
           vo.setDeptname(new java.lang.String(_deptname));
       }
       String _loc = request.getParameter("loc");
       
       if(_loc != null && _loc.length() > 0){
           vo.setLoc(new java.lang.String(_loc));
       }
       return vo;
    }
    //-----------------------------------------------------------         

}
