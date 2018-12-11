package com.oa.dao;


import java.util.*;
import javax.servlet.http.*; 
import java.sql.*;

public class BizCheckResult extends ValueObject{
       /**
	    * Constructer of this class ;
	    * ���ߣ�zyf
	    * ��дʱ�䣺2010.1.16
	    * ģ�����ͣ�VOģ�壬1.0��
	    */
       private java.math.BigDecimal  id = null;
       private java.math.BigDecimal  claimId = null;
       private java.sql.Timestamp  checkDate = null;
       private java.math.BigDecimal  checkEid = null;
       private java.lang.String  result = null;
       private java.lang.String  comm = null;
//------------------------------------------------------------
        public void setId(java.math.BigDecimal id){
            this.id = id;
        }
        public java.math.BigDecimal getId(){
            return this.id;
        }   
        public void setClaimId(java.math.BigDecimal claimId){
            this.claimId = claimId;
        }
        public java.math.BigDecimal getClaimId(){
            return this.claimId;
        }   
        public void setCheckDate(java.sql.Timestamp checkDate){
            this.checkDate = checkDate;
        }
        public java.sql.Timestamp getCheckDate(){
            return this.checkDate;
        }   
        public void setCheckEid(java.math.BigDecimal checkEid){
            this.checkEid = checkEid;
        }
        public java.math.BigDecimal getCheckEid(){
            return this.checkEid;
        }   
        public void setResult(java.lang.String result){
            this.result = result;
        }
        public java.lang.String getResult(){
            return this.result;
        }   
        public void setComm(java.lang.String comm){
            this.comm = comm;
        }
        public java.lang.String getComm(){
            return this.comm;
        }   
//------------------------------------------------------------
    public static BizCheckResult getInstanceFrom(HttpServletRequest request) throws Exception  {
       BizCheckResult vo = new BizCheckResult();
       String _id = request.getParameter("id");
       
       if(_id != null && _id.length() > 0){
           vo.setId(new java.math.BigDecimal(_id));
       }
       String _claimId = request.getParameter("claimId");
       
       if(_claimId != null && _claimId.length() > 0){
           vo.setClaimId(new java.math.BigDecimal(_claimId));
       }
       String _checkDate = request.getParameter("checkDate");
       
       if(_checkDate != null && _checkDate.length() > 0){
           vo.setCheckDate(java.sql.Timestamp.valueOf(_checkDate));
       }
       String _checkEid = request.getParameter("checkEid");
       
       if(_checkEid != null && _checkEid.length() > 0){
           vo.setCheckEid(new java.math.BigDecimal(_checkEid));
       }
       String _result = request.getParameter("result");
       
       if(_result != null && _result.length() > 0){
           vo.setResult(new java.lang.String(_result));
       }
       String _comm = request.getParameter("comm");
       
       if(_comm != null && _comm.length() > 0){
           vo.setComm(new java.lang.String(_comm));
       }
       return vo;
    }
    //-----------------------------------------------------------         

}
