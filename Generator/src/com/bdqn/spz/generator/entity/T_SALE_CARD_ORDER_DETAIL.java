package com.bdqn.spz.generator.entity;

import java.util.Date;

/**
 * T_SALE_CARD_ORDER_DETAIL实体类
 * Thu Apr 26 11:34:04 CST 2018 ???
 */ 
public class T_SALE_CARD_ORDER_DETAIL{

	private String oRDER_ID;

	private String gOODS_CODE;

	private String qUANTITY;

	private String pRICE;

	private Date cREATE_TIME;

	private String oRDER_DETAIL;

	public void setORDER_ID(String oRDER_ID){
		this.oRDER_ID=oRDER_ID;
	}

	public String getORDER_ID(){
		return oRDER_ID;
	}

	public void setGOODS_CODE(String gOODS_CODE){
		this.gOODS_CODE=gOODS_CODE;
	}

	public String getGOODS_CODE(){
		return gOODS_CODE;
	}

	public void setQUANTITY(String qUANTITY){
		this.qUANTITY=qUANTITY;
	}

	public String getQUANTITY(){
		return qUANTITY;
	}

	public void setPRICE(String pRICE){
		this.pRICE=pRICE;
	}

	public String getPRICE(){
		return pRICE;
	}

	public void setCREATE_TIME(Date cREATE_TIME){
		this.cREATE_TIME=cREATE_TIME;
	}

	public Date getCREATE_TIME(){
		return cREATE_TIME;
	}

	public void setORDER_DETAIL(String oRDER_DETAIL){
		this.oRDER_DETAIL=oRDER_DETAIL;
	}

	public String getORDER_DETAIL(){
		return oRDER_DETAIL;
	}
}

