package com.oa.dao;


import java.util.*;
import javax.servlet.http.*; 
import java.sql.*;

public class Emp extends ValueObject{
       /**
	    * Constructer of this class ;
	    * ���ߣ�zyf
	    * ��дʱ�䣺2010.1.16
	    * ģ�����ͣ�VOģ�壬1.0��
	    */
       private java.math.BigDecimal  empno = null;
       private java.lang.String  empname = null;
       private java.lang.String  job = null;
       private java.math.BigDecimal  mgr = null;
       private java.sql.Timestamp  hiredate = null;
       private java.math.BigDecimal  sal = null;
       private java.math.BigDecimal  comm = null;
       private java.math.BigDecimal  deptno = null;
//------------------------------------------------------------
        public void setEmpno(java.math.BigDecimal empno){
            this.empno = empno;
        }
        public java.math.BigDecimal getEmpno(){
            return this.empno;
        }   
        public void setEmpname(java.lang.String empname){
            this.empname = empname;
        }
        public java.lang.String getEmpname(){
            return this.empname;
        }   
        public void setJob(java.lang.String job){
            this.job = job;
        }
        public java.lang.String getJob(){
            return this.job;
        }   
        public void setMgr(java.math.BigDecimal mgr){
            this.mgr = mgr;
        }
        public java.math.BigDecimal getMgr(){
            return this.mgr;
        }   
        public void setHiredate(java.sql.Timestamp hiredate){
            this.hiredate = hiredate;
        }
        public java.sql.Timestamp getHiredate(){
            return this.hiredate;
        }   
        public void setSal(java.math.BigDecimal sal){
            this.sal = sal;
        }
        public java.math.BigDecimal getSal(){
            return this.sal;
        }   
        public void setComm(java.math.BigDecimal comm){
            this.comm = comm;
        }
        public java.math.BigDecimal getComm(){
            return this.comm;
        }   
        public void setDeptno(java.math.BigDecimal deptno){
            this.deptno = deptno;
        }
        public java.math.BigDecimal getDeptno(){
            return this.deptno;
        }   
//------------------------------------------------------------
    public static Emp getInstanceFrom(HttpServletRequest request) throws Exception  {
       Emp vo = new Emp();
       String _empno = request.getParameter("empno");
       
       if(_empno != null && _empno.length() > 0){
           vo.setEmpno(new java.math.BigDecimal(_empno));
       }
       String _empname = request.getParameter("empname");
       
       if(_empname != null && _empname.length() > 0){
           vo.setEmpname(new java.lang.String(_empname));
       }
       String _job = request.getParameter("job");
       
       if(_job != null && _job.length() > 0){
           vo.setJob(new java.lang.String(_job));
       }
       String _mgr = request.getParameter("mgr");
       
       if(_mgr != null && _mgr.length() > 0){
           vo.setMgr(new java.math.BigDecimal(_mgr));
       }
       String _hiredate = request.getParameter("hiredate");
       
       if(_hiredate != null && _hiredate.length() > 0){
           vo.setHiredate(java.sql.Timestamp.valueOf(_hiredate));
       }
       String _sal = request.getParameter("sal");
       
       if(_sal != null && _sal.length() > 0){
           vo.setSal(new java.math.BigDecimal(_sal));
       }
       String _comm = request.getParameter("comm");
       
       if(_comm != null && _comm.length() > 0){
           vo.setComm(new java.math.BigDecimal(_comm));
       }
       String _deptno = request.getParameter("deptno");
       
       if(_deptno != null && _deptno.length() > 0){
           vo.setDeptno(new java.math.BigDecimal(_deptno));
       }
       return vo;
    }
    //-----------------------------------------------------------         

}
