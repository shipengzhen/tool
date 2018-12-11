package com.oa.dao;


import java.util.*;
import javax.servlet.http.*; 
import java.sql.*;

public class SysEmployee extends ValueObject{
       /**
	    * Constructer of this class ;
	    * ���ߣ�zyf
	    * ��дʱ�䣺2010.1.16
	    * ģ�����ͣ�VOģ�壬1.0��
	    */
       private java.math.BigDecimal  eid = null;
       private java.math.BigDecimal  positionId = null;
       private java.math.BigDecimal  departmentId = null;
       private java.lang.String  ename = null;
       private java.lang.String  epassword = null;
       private java.lang.String  status = null;
//------------------------------------------------------------
        public void setEid(java.math.BigDecimal eid){
            this.eid = eid;
        }
        public java.math.BigDecimal getEid(){
            return this.eid;
        }   
        public void setPositionId(java.math.BigDecimal positionId){
            this.positionId = positionId;
        }
        public java.math.BigDecimal getPositionId(){
            return this.positionId;
        }   
        public void setDepartmentId(java.math.BigDecimal departmentId){
            this.departmentId = departmentId;
        }
        public java.math.BigDecimal getDepartmentId(){
            return this.departmentId;
        }   
        public void setEname(java.lang.String ename){
            this.ename = ename;
        }
        public java.lang.String getEname(){
            return this.ename;
        }   
        public void setEpassword(java.lang.String epassword){
            this.epassword = epassword;
        }
        public java.lang.String getEpassword(){
            return this.epassword;
        }   
        public void setStatus(java.lang.String status){
            this.status = status;
        }
        public java.lang.String getStatus(){
            return this.status;
        }   
//------------------------------------------------------------
    public static SysEmployee getInstanceFrom(HttpServletRequest request) throws Exception  {
       SysEmployee vo = new SysEmployee();
       String _eid = request.getParameter("eid");
       
       if(_eid != null && _eid.length() > 0){
           vo.setEid(new java.math.BigDecimal(_eid));
       }
       String _positionId = request.getParameter("positionId");
       
       if(_positionId != null && _positionId.length() > 0){
           vo.setPositionId(new java.math.BigDecimal(_positionId));
       }
       String _departmentId = request.getParameter("departmentId");
       
       if(_departmentId != null && _departmentId.length() > 0){
           vo.setDepartmentId(new java.math.BigDecimal(_departmentId));
       }
       String _ename = request.getParameter("ename");
       
       if(_ename != null && _ename.length() > 0){
           vo.setEname(new java.lang.String(_ename));
       }
       String _epassword = request.getParameter("epassword");
       
       if(_epassword != null && _epassword.length() > 0){
           vo.setEpassword(new java.lang.String(_epassword));
       }
       String _status = request.getParameter("status");
       
       if(_status != null && _status.length() > 0){
           vo.setStatus(new java.lang.String(_status));
       }
       return vo;
    }
    //-----------------------------------------------------------         

}
