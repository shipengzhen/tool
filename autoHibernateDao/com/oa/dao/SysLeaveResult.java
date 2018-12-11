package com.oa.dao;


import java.util.*;
import javax.servlet.http.*; 
import java.sql.*;

public class SysLeaveResult extends ValueObject{
       /**
	    * Constructer of this class ;
	    * ���ߣ�zyf
	    * ��дʱ�䣺2010.1.16
	    * ģ�����ͣ�VOģ�壬1.0��
	    */
       private java.math.BigDecimal  id = null;
       private java.math.BigDecimal  leaveId = null;
       private java.sql.Timestamp  shdate = null;
       private java.lang.String  shsuggestion = null;
//------------------------------------------------------------
        public void setId(java.math.BigDecimal id){
            this.id = id;
        }
        public java.math.BigDecimal getId(){
            return this.id;
        }   
        public void setLeaveId(java.math.BigDecimal leaveId){
            this.leaveId = leaveId;
        }
        public java.math.BigDecimal getLeaveId(){
            return this.leaveId;
        }   
        public void setShdate(java.sql.Timestamp shdate){
            this.shdate = shdate;
        }
        public java.sql.Timestamp getShdate(){
            return this.shdate;
        }   
        public void setShsuggestion(java.lang.String shsuggestion){
            this.shsuggestion = shsuggestion;
        }
        public java.lang.String getShsuggestion(){
            return this.shsuggestion;
        }   
//------------------------------------------------------------
    public static SysLeaveResult getInstanceFrom(HttpServletRequest request) throws Exception  {
       SysLeaveResult vo = new SysLeaveResult();
       String _id = request.getParameter("id");
       
       if(_id != null && _id.length() > 0){
           vo.setId(new java.math.BigDecimal(_id));
       }
       String _leaveId = request.getParameter("leaveId");
       
       if(_leaveId != null && _leaveId.length() > 0){
           vo.setLeaveId(new java.math.BigDecimal(_leaveId));
       }
       String _shdate = request.getParameter("shdate");
       
       if(_shdate != null && _shdate.length() > 0){
           vo.setShdate(java.sql.Timestamp.valueOf(_shdate));
       }
       String _shsuggestion = request.getParameter("shsuggestion");
       
       if(_shsuggestion != null && _shsuggestion.length() > 0){
           vo.setShsuggestion(new java.lang.String(_shsuggestion));
       }
       return vo;
    }
    //-----------------------------------------------------------         

}
