package com.bdqn.spz.generator.entity;

import java.util.Date;

/**
 * T_APPSERVICE_SYS_OUT_LOG实体类
 * Thu Apr 26 11:34:03 CST 2018 ???
 */ 
public class T_APPSERVICE_SYS_OUT_LOG{

	private String iD;

	private String tYPE;

	private byte[] cONTENT;

	private Date cREATE_DATE;

	public void setID(String iD){
		this.iD=iD;
	}

	public String getID(){
		return iD;
	}

	public void setTYPE(String tYPE){
		this.tYPE=tYPE;
	}

	public String getTYPE(){
		return tYPE;
	}

	public void setCONTENT(byte[] cONTENT){
		this.cONTENT=cONTENT;
	}

	public byte[] getCONTENT(){
		return cONTENT;
	}

	public void setCREATE_DATE(Date cREATE_DATE){
		this.cREATE_DATE=cREATE_DATE;
	}

	public Date getCREATE_DATE(){
		return cREATE_DATE;
	}
}

