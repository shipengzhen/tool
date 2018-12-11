package com.oa.dao;


import java.util.*;
import javax.servlet.http.*; 
import java.sql.*;

public class Pro extends ValueObject{
       /**
	    * Constructer of this class ;
	    * ���ߣ�zyf
	    * ��дʱ�䣺2010.1.16
	    * ģ�����ͣ�VOģ�壬1.0��
	    */
       private java.math.BigDecimal  proid = null;
       private java.lang.String  proname = null;
//------------------------------------------------------------
        public void setProid(java.math.BigDecimal proid){
            this.proid = proid;
        }
        public java.math.BigDecimal getProid(){
            return this.proid;
        }   
        public void setProname(java.lang.String proname){
            this.proname = proname;
        }
        public java.lang.String getProname(){
            return this.proname;
        }   
//------------------------------------------------------------
    public static Pro getInstanceFrom(HttpServletRequest request) throws Exception  {
       Pro vo = new Pro();
       String _proid = request.getParameter("proid");
       
       if(_proid != null && _proid.length() > 0){
           vo.setProid(new java.math.BigDecimal(_proid));
       }
       String _proname = request.getParameter("proname");
       
       if(_proname != null && _proname.length() > 0){
           vo.setProname(new java.lang.String(_proname));
       }
       return vo;
    }
    //-----------------------------------------------------------         

}
