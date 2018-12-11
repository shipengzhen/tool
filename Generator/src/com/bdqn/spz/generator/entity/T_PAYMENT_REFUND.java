package com.bdqn.spz.generator.entity;

import java.util.Date;

/**
 * T_PAYMENT_REFUND实体类
 * Thu Apr 26 11:34:13 CST 2018 ???
 */ 
public class T_PAYMENT_REFUND{

	private String rEFUND_ID;

	private String oRDER_ID;

	private String rEFUND_PRICE;

	private String sTATUS;

	private Date cREATE_TIME;

	private Date fINISH_TIME;

	private String rEFUND_CODE;

	private String oPERATOR_ID;

	private byte[] rES_DEC;

	private Date sETTLE_DATE;

	public void setREFUND_ID(String rEFUND_ID){
		this.rEFUND_ID=rEFUND_ID;
	}

	public String getREFUND_ID(){
		return rEFUND_ID;
	}

	public void setORDER_ID(String oRDER_ID){
		this.oRDER_ID=oRDER_ID;
	}

	public String getORDER_ID(){
		return oRDER_ID;
	}

	public void setREFUND_PRICE(String rEFUND_PRICE){
		this.rEFUND_PRICE=rEFUND_PRICE;
	}

	public String getREFUND_PRICE(){
		return rEFUND_PRICE;
	}

	public void setSTATUS(String sTATUS){
		this.sTATUS=sTATUS;
	}

	public String getSTATUS(){
		return sTATUS;
	}

	public void setCREATE_TIME(Date cREATE_TIME){
		this.cREATE_TIME=cREATE_TIME;
	}

	public Date getCREATE_TIME(){
		return cREATE_TIME;
	}

	public void setFINISH_TIME(Date fINISH_TIME){
		this.fINISH_TIME=fINISH_TIME;
	}

	public Date getFINISH_TIME(){
		return fINISH_TIME;
	}

	public void setREFUND_CODE(String rEFUND_CODE){
		this.rEFUND_CODE=rEFUND_CODE;
	}

	public String getREFUND_CODE(){
		return rEFUND_CODE;
	}

	public void setOPERATOR_ID(String oPERATOR_ID){
		this.oPERATOR_ID=oPERATOR_ID;
	}

	public String getOPERATOR_ID(){
		return oPERATOR_ID;
	}

	public void setRES_DEC(byte[] rES_DEC){
		this.rES_DEC=rES_DEC;
	}

	public byte[] getRES_DEC(){
		return rES_DEC;
	}

	public void setSETTLE_DATE(Date sETTLE_DATE){
		this.sETTLE_DATE=sETTLE_DATE;
	}

	public Date getSETTLE_DATE(){
		return sETTLE_DATE;
	}
}

