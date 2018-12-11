package com.oa.dao;


import java.util.*;
import javax.servlet.http.*; 
import java.sql.*;

public class SysLeave extends ValueObject{
       /**
	    * Constructer of this class ;
	    * ���ߣ�zyf
	    * ��дʱ�䣺2010.1.16
	    * ģ�����ͣ�VOģ�壬1.0��
	    */
       private java.math.BigDecimal  id = null;
       private java.sql.Timestamp  begindate = null;
       private java.sql.Timestamp  closuredate = null;
       private java.math.BigDecimal  days = null;
       private java.lang.String  event = null;
       private java.lang.String  leavetype = null;
       private java.math.BigDecimal  proposerEid = null;
       private java.math.BigDecimal  executorEid = null;
       private java.lang.String  status = null;
//------------------------------------------------------------
        public void setId(java.math.BigDecimal id){
            this.id = id;
        }
        public java.math.BigDecimal getId(){
            return this.id;
        }   
        public void setBegindate(java.sql.Timestamp begindate){
            this.begindate = begindate;
        }
        public java.sql.Timestamp getBegindate(){
            return this.begindate;
        }   
        public void setClosuredate(java.sql.Timestamp closuredate){
            this.closuredate = closuredate;
        }
        public java.sql.Timestamp getClosuredate(){
            return this.closuredate;
        }   
        public void setDays(java.math.BigDecimal days){
            this.days = days;
        }
        public java.math.BigDecimal getDays(){
            return this.days;
        }   
        public void setEvent(java.lang.String event){
            this.event = event;
        }
        public java.lang.String getEvent(){
            return this.event;
        }   
        public void setLeavetype(java.lang.String leavetype){
            this.leavetype = leavetype;
        }
        public java.lang.String getLeavetype(){
            return this.leavetype;
        }   
        public void setProposerEid(java.math.BigDecimal proposerEid){
            this.proposerEid = proposerEid;
        }
        public java.math.BigDecimal getProposerEid(){
            return this.proposerEid;
        }   
        public void setExecutorEid(java.math.BigDecimal executorEid){
            this.executorEid = executorEid;
        }
        public java.math.BigDecimal getExecutorEid(){
            return this.executorEid;
        }   
        public void setStatus(java.lang.String status){
            this.status = status;
        }
        public java.lang.String getStatus(){
            return this.status;
        }   
//------------------------------------------------------------
    public static SysLeave getInstanceFrom(HttpServletRequest request) throws Exception  {
       SysLeave vo = new SysLeave();
       String _id = request.getParameter("id");
       
       if(_id != null && _id.length() > 0){
           vo.setId(new java.math.BigDecimal(_id));
       }
       String _begindate = request.getParameter("begindate");
       
       if(_begindate != null && _begindate.length() > 0){
           vo.setBegindate(java.sql.Timestamp.valueOf(_begindate));
       }
       String _closuredate = request.getParameter("closuredate");
       
       if(_closuredate != null && _closuredate.length() > 0){
           vo.setClosuredate(java.sql.Timestamp.valueOf(_closuredate));
       }
       String _days = request.getParameter("days");
       
       if(_days != null && _days.length() > 0){
           vo.setDays(new java.math.BigDecimal(_days));
       }
       String _event = request.getParameter("event");
       
       if(_event != null && _event.length() > 0){
           vo.setEvent(new java.lang.String(_event));
       }
       String _leavetype = request.getParameter("leavetype");
       
       if(_leavetype != null && _leavetype.length() > 0){
           vo.setLeavetype(new java.lang.String(_leavetype));
       }
       String _proposerEid = request.getParameter("proposerEid");
       
       if(_proposerEid != null && _proposerEid.length() > 0){
           vo.setProposerEid(new java.math.BigDecimal(_proposerEid));
       }
       String _executorEid = request.getParameter("executorEid");
       
       if(_executorEid != null && _executorEid.length() > 0){
           vo.setExecutorEid(new java.math.BigDecimal(_executorEid));
       }
       String _status = request.getParameter("status");
       
       if(_status != null && _status.length() > 0){
           vo.setStatus(new java.lang.String(_status));
       }
       return vo;
    }
    //-----------------------------------------------------------         

}
