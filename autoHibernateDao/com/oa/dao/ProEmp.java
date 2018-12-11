package com.oa.dao;


import java.util.*;
import javax.servlet.http.*; 
import java.sql.*;

public class ProEmp extends ValueObject{
       /**
	    * Constructer of this class ;
	    * ���ߣ�zyf
	    * ��дʱ�䣺2010.1.16
	    * ģ�����ͣ�VOģ�壬1.0��
	    */
       private java.math.BigDecimal  empno = null;
       private java.math.BigDecimal  proid = null;
//------------------------------------------------------------
        public void setEmpno(java.math.BigDecimal empno){
            this.empno = empno;
        }
        public java.math.BigDecimal getEmpno(){
            return this.empno;
        }   
        public void setProid(java.math.BigDecimal proid){
            this.proid = proid;
        }
        public java.math.BigDecimal getProid(){
            return this.proid;
        }   
//------------------------------------------------------------
    public static ProEmp getInstanceFrom(HttpServletRequest request) throws Exception  {
       ProEmp vo = new ProEmp();
       String _empno = request.getParameter("empno");
       
       if(_empno != null && _empno.length() > 0){
           vo.setEmpno(new java.math.BigDecimal(_empno));
       }
       String _proid = request.getParameter("proid");
       
       if(_proid != null && _proid.length() > 0){
           vo.setProid(new java.math.BigDecimal(_proid));
       }
       return vo;
    }
    //-----------------------------------------------------------         

}
