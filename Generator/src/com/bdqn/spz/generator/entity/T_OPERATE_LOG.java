package com.bdqn.spz.generator.entity;

import java.util.Date;

/**
 * T_OPERATE_LOG实体类
 * Thu Apr 26 11:34:04 CST 2018 ???
 */ 
public class T_OPERATE_LOG{

	private String iD;

	private String uSER_ID;

	private String oPERATE_TYPE;

	private byte[] oPERATE_DETAIL;

	private String oPERATE_FLAG;

	private String uNPASS_REASON;

	private Date cREATE_DATE;

	private String rEMARKS;

	private String dEL_FLAG;

	private String oPERATE_USER;

	private Date oPERATE_DATE;

	public void setID(String iD){
		this.iD=iD;
	}

	public String getID(){
		return iD;
	}

	public void setUSER_ID(String uSER_ID){
		this.uSER_ID=uSER_ID;
	}

	public String getUSER_ID(){
		return uSER_ID;
	}

	public void setOPERATE_TYPE(String oPERATE_TYPE){
		this.oPERATE_TYPE=oPERATE_TYPE;
	}

	public String getOPERATE_TYPE(){
		return oPERATE_TYPE;
	}

	public void setOPERATE_DETAIL(byte[] oPERATE_DETAIL){
		this.oPERATE_DETAIL=oPERATE_DETAIL;
	}

	public byte[] getOPERATE_DETAIL(){
		return oPERATE_DETAIL;
	}

	public void setOPERATE_FLAG(String oPERATE_FLAG){
		this.oPERATE_FLAG=oPERATE_FLAG;
	}

	public String getOPERATE_FLAG(){
		return oPERATE_FLAG;
	}

	public void setUNPASS_REASON(String uNPASS_REASON){
		this.uNPASS_REASON=uNPASS_REASON;
	}

	public String getUNPASS_REASON(){
		return uNPASS_REASON;
	}

	public void setCREATE_DATE(Date cREATE_DATE){
		this.cREATE_DATE=cREATE_DATE;
	}

	public Date getCREATE_DATE(){
		return cREATE_DATE;
	}

	public void setREMARKS(String rEMARKS){
		this.rEMARKS=rEMARKS;
	}

	public String getREMARKS(){
		return rEMARKS;
	}

	public void setDEL_FLAG(String dEL_FLAG){
		this.dEL_FLAG=dEL_FLAG;
	}

	public String getDEL_FLAG(){
		return dEL_FLAG;
	}

	public void setOPERATE_USER(String oPERATE_USER){
		this.oPERATE_USER=oPERATE_USER;
	}

	public String getOPERATE_USER(){
		return oPERATE_USER;
	}

	public void setOPERATE_DATE(Date oPERATE_DATE){
		this.oPERATE_DATE=oPERATE_DATE;
	}

	public Date getOPERATE_DATE(){
		return oPERATE_DATE;
	}
}

