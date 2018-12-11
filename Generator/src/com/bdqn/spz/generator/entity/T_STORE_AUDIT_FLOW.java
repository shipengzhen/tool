package com.bdqn.spz.generator.entity;

import java.util.Date;

/**
 * T_STORE_AUDIT_FLOW实体类
 * Thu Apr 26 11:34:03 CST 2018 ???
 */ 
public class T_STORE_AUDIT_FLOW{

	private String iD;

	private String aUDIT_RECORD_ID;

	private String oPERATE_TYPE;

	private String uNPASSREASON;

	private String cREATE_BY;

	private Date cREATE_DATE;

	private String uPDATE_BY;

	private Date uPDATE_DATE;

	private String rEMARKS;

	private String dEL_FLAG;

	public void setID(String iD){
		this.iD=iD;
	}

	public String getID(){
		return iD;
	}

	public void setAUDIT_RECORD_ID(String aUDIT_RECORD_ID){
		this.aUDIT_RECORD_ID=aUDIT_RECORD_ID;
	}

	public String getAUDIT_RECORD_ID(){
		return aUDIT_RECORD_ID;
	}

	public void setOPERATE_TYPE(String oPERATE_TYPE){
		this.oPERATE_TYPE=oPERATE_TYPE;
	}

	public String getOPERATE_TYPE(){
		return oPERATE_TYPE;
	}

	public void setUNPASSREASON(String uNPASSREASON){
		this.uNPASSREASON=uNPASSREASON;
	}

	public String getUNPASSREASON(){
		return uNPASSREASON;
	}

	public void setCREATE_BY(String cREATE_BY){
		this.cREATE_BY=cREATE_BY;
	}

	public String getCREATE_BY(){
		return cREATE_BY;
	}

	public void setCREATE_DATE(Date cREATE_DATE){
		this.cREATE_DATE=cREATE_DATE;
	}

	public Date getCREATE_DATE(){
		return cREATE_DATE;
	}

	public void setUPDATE_BY(String uPDATE_BY){
		this.uPDATE_BY=uPDATE_BY;
	}

	public String getUPDATE_BY(){
		return uPDATE_BY;
	}

	public void setUPDATE_DATE(Date uPDATE_DATE){
		this.uPDATE_DATE=uPDATE_DATE;
	}

	public Date getUPDATE_DATE(){
		return uPDATE_DATE;
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
}

