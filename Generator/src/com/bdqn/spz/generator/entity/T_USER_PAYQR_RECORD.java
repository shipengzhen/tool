package com.bdqn.spz.generator.entity;

import java.util.Date;

/**
 * T_USER_PAYQR_RECORD实体类
 * Thu Apr 26 11:34:14 CST 2018 ???
 */ 
public class T_USER_PAYQR_RECORD{

	private String uSER_ID;

	private String pAY_QR;

	private String cARD_ID;

	private Date cREATE_TIME;

	public void setUSER_ID(String uSER_ID){
		this.uSER_ID=uSER_ID;
	}

	public String getUSER_ID(){
		return uSER_ID;
	}

	public void setPAY_QR(String pAY_QR){
		this.pAY_QR=pAY_QR;
	}

	public String getPAY_QR(){
		return pAY_QR;
	}

	public void setCARD_ID(String cARD_ID){
		this.cARD_ID=cARD_ID;
	}

	public String getCARD_ID(){
		return cARD_ID;
	}

	public void setCREATE_TIME(Date cREATE_TIME){
		this.cREATE_TIME=cREATE_TIME;
	}

	public Date getCREATE_TIME(){
		return cREATE_TIME;
	}
}

