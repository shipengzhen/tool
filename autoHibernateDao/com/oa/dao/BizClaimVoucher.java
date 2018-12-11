package com.oa.dao;


import java.util.*;
import javax.servlet.http.*; 
import java.sql.*;

public class BizClaimVoucher extends ValueObject{
       /**
	    * Constructer of this class ;
	    * ���ߣ�zyf
	    * ��дʱ�䣺2010.1.16
	    * ģ�����ͣ�VOģ�壬1.0��
	    */
       private java.math.BigDecimal  id = null;
       private java.math.BigDecimal  proposerEid = null;
       private java.math.BigDecimal  executorEid = null;
       private java.sql.Timestamp  begindate = null;
       private java.sql.Timestamp  finishdate = null;
       private java.lang.String  event = null;
       private java.math.BigDecimal  moeny = null;
       private java.lang.String  status = null;
//------------------------------------------------------------
        public void setId(java.math.BigDecimal id){
            this.id = id;
        }
        public java.math.BigDecimal getId(){
            return this.id;
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
        public void setBegindate(java.sql.Timestamp begindate){
            this.begindate = begindate;
        }
        public java.sql.Timestamp getBegindate(){
            return this.begindate;
        }   
        public void setFinishdate(java.sql.Timestamp finishdate){
            this.finishdate = finishdate;
        }
        public java.sql.Timestamp getFinishdate(){
            return this.finishdate;
        }   
        public void setEvent(java.lang.String event){
            this.event = event;
        }
        public java.lang.String getEvent(){
            return this.event;
        }   
        public void setMoeny(java.math.BigDecimal moeny){
            this.moeny = moeny;
        }
        public java.math.BigDecimal getMoeny(){
            return this.moeny;
        }   
        public void setStatus(java.lang.String status){
            this.status = status;
        }
        public java.lang.String getStatus(){
            return this.status;
        }   
//------------------------------------------------------------
    public static BizClaimVoucher getInstanceFrom(HttpServletRequest request) throws Exception  {
       BizClaimVoucher vo = new BizClaimVoucher();
       String _id = request.getParameter("id");
       
       if(_id != null && _id.length() > 0){
           vo.setId(new java.math.BigDecimal(_id));
       }
       String _proposerEid = request.getParameter("proposerEid");
       
       if(_proposerEid != null && _proposerEid.length() > 0){
           vo.setProposerEid(new java.math.BigDecimal(_proposerEid));
       }
       String _executorEid = request.getParameter("executorEid");
       
       if(_executorEid != null && _executorEid.length() > 0){
           vo.setExecutorEid(new java.math.BigDecimal(_executorEid));
       }
       String _begindate = request.getParameter("begindate");
       
       if(_begindate != null && _begindate.length() > 0){
           vo.setBegindate(java.sql.Timestamp.valueOf(_begindate));
       }
       String _finishdate = request.getParameter("finishdate");
       
       if(_finishdate != null && _finishdate.length() > 0){
           vo.setFinishdate(java.sql.Timestamp.valueOf(_finishdate));
       }
       String _event = request.getParameter("event");
       
       if(_event != null && _event.length() > 0){
           vo.setEvent(new java.lang.String(_event));
       }
       String _moeny = request.getParameter("moeny");
       
       if(_moeny != null && _moeny.length() > 0){
           vo.setMoeny(new java.math.BigDecimal(_moeny));
       }
       String _status = request.getParameter("status");
       
       if(_status != null && _status.length() > 0){
           vo.setStatus(new java.lang.String(_status));
       }
       return vo;
    }
    //-----------------------------------------------------------         

}
