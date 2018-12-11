package com.oa.dao;


import java.util.*;
import javax.servlet.http.*; 
import java.sql.*;

public class BizCvDetailly extends ValueObject{
       /**
	    * Constructer of this class ;
	    * ���ߣ�zyf
	    * ��дʱ�䣺2010.1.16
	    * ģ�����ͣ�VOģ�壬1.0��
	    */
       private java.math.BigDecimal  id = null;
       private java.math.BigDecimal  claimId = null;
       private java.lang.String  item = null;
       private java.math.BigDecimal  acount = null;
       private java.lang.String  des = null;
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
        public void setItem(java.lang.String item){
            this.item = item;
        }
        public java.lang.String getItem(){
            return this.item;
        }   
        public void setAcount(java.math.BigDecimal acount){
            this.acount = acount;
        }
        public java.math.BigDecimal getAcount(){
            return this.acount;
        }   
        public void setDes(java.lang.String des){
            this.des = des;
        }
        public java.lang.String getDes(){
            return this.des;
        }   
//------------------------------------------------------------
    public static BizCvDetailly getInstanceFrom(HttpServletRequest request) throws Exception  {
       BizCvDetailly vo = new BizCvDetailly();
       String _id = request.getParameter("id");
       
       if(_id != null && _id.length() > 0){
           vo.setId(new java.math.BigDecimal(_id));
       }
       String _claimId = request.getParameter("claimId");
       
       if(_claimId != null && _claimId.length() > 0){
           vo.setClaimId(new java.math.BigDecimal(_claimId));
       }
       String _item = request.getParameter("item");
       
       if(_item != null && _item.length() > 0){
           vo.setItem(new java.lang.String(_item));
       }
       String _acount = request.getParameter("acount");
       
       if(_acount != null && _acount.length() > 0){
           vo.setAcount(new java.math.BigDecimal(_acount));
       }
       String _des = request.getParameter("des");
       
       if(_des != null && _des.length() > 0){
           vo.setDes(new java.lang.String(_des));
       }
       return vo;
    }
    //-----------------------------------------------------------         

}
