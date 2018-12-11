package com.bdqn.spz.generator.entity;

import java.util.Date;

/**
 * T_AUTOLOGIN_SECRET实体类
 * Thu Apr 26 11:34:05 CST 2018 ???
 */ 
public class T_AUTOLOGIN_SECRET{

	private String mOBILE;

	private String uNIQUE_KEY;

	private String sECRET;

	private Date tIME;

	private String vERSION;

	private String iMEI;

	private String iMSI;

	private String pHONE_MODEL;

	private String pLATFORM;

	private String sCREEN;

	private String nETWORK;

	public void setMOBILE(String mOBILE){
		this.mOBILE=mOBILE;
	}

	public String getMOBILE(){
		return mOBILE;
	}

	public void setUNIQUE_KEY(String uNIQUE_KEY){
		this.uNIQUE_KEY=uNIQUE_KEY;
	}

	public String getUNIQUE_KEY(){
		return uNIQUE_KEY;
	}

	public void setSECRET(String sECRET){
		this.sECRET=sECRET;
	}

	public String getSECRET(){
		return sECRET;
	}

	public void setTIME(Date tIME){
		this.tIME=tIME;
	}

	public Date getTIME(){
		return tIME;
	}

	public void setVERSION(String vERSION){
		this.vERSION=vERSION;
	}

	public String getVERSION(){
		return vERSION;
	}

	public void setIMEI(String iMEI){
		this.iMEI=iMEI;
	}

	public String getIMEI(){
		return iMEI;
	}

	public void setIMSI(String iMSI){
		this.iMSI=iMSI;
	}

	public String getIMSI(){
		return iMSI;
	}

	public void setPHONE_MODEL(String pHONE_MODEL){
		this.pHONE_MODEL=pHONE_MODEL;
	}

	public String getPHONE_MODEL(){
		return pHONE_MODEL;
	}

	public void setPLATFORM(String pLATFORM){
		this.pLATFORM=pLATFORM;
	}

	public String getPLATFORM(){
		return pLATFORM;
	}

	public void setSCREEN(String sCREEN){
		this.sCREEN=sCREEN;
	}

	public String getSCREEN(){
		return sCREEN;
	}

	public void setNETWORK(String nETWORK){
		this.nETWORK=nETWORK;
	}

	public String getNETWORK(){
		return nETWORK;
	}
}

