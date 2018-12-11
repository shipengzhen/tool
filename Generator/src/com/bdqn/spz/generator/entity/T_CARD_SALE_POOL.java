package com.bdqn.spz.generator.entity;

import java.util.Date;

/**
 * T_CARD_SALE_POOL实体类
 * Thu Apr 26 11:34:04 CST 2018 ???
 */ 
public class T_CARD_SALE_POOL{

	private String cARD_ID;

	private String cARD_TYPE;

	private Date vALID_TIME;

	private String lOGO_URL;

	private String cARD_INTRO;

	private String iS_SALE;

	private String oRDER_ID;

	public void setCARD_ID(String cARD_ID){
		this.cARD_ID=cARD_ID;
	}

	public String getCARD_ID(){
		return cARD_ID;
	}

	public void setCARD_TYPE(String cARD_TYPE){
		this.cARD_TYPE=cARD_TYPE;
	}

	public String getCARD_TYPE(){
		return cARD_TYPE;
	}

	public void setVALID_TIME(Date vALID_TIME){
		this.vALID_TIME=vALID_TIME;
	}

	public Date getVALID_TIME(){
		return vALID_TIME;
	}

	public void setLOGO_URL(String lOGO_URL){
		this.lOGO_URL=lOGO_URL;
	}

	public String getLOGO_URL(){
		return lOGO_URL;
	}

	public void setCARD_INTRO(String cARD_INTRO){
		this.cARD_INTRO=cARD_INTRO;
	}

	public String getCARD_INTRO(){
		return cARD_INTRO;
	}

	public void setIS_SALE(String iS_SALE){
		this.iS_SALE=iS_SALE;
	}

	public String getIS_SALE(){
		return iS_SALE;
	}

	public void setORDER_ID(String oRDER_ID){
		this.oRDER_ID=oRDER_ID;
	}

	public String getORDER_ID(){
		return oRDER_ID;
	}
}

