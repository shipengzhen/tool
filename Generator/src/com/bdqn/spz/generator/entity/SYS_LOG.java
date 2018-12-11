package com.bdqn.spz.generator.entity;

import java.util.Date;

/**
 * SYS_LOG实体类
 * Thu Apr 26 11:34:02 CST 2018 ???
 */ 
public class SYS_LOG{

	private String iD;

	private String tYPE;

	private String tITLE;

	private String cREATE_BY;

	private Date cREATE_DATE;

	private String rEMOTE_ADDR;

	private String uSER_AGENT;

	private String rEQUEST_URI;

	private String mETHOD;

	private String pARAMS;

	private String eXCEPTION;

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

	public void setTITLE(String tITLE){
		this.tITLE=tITLE;
	}

	public String getTITLE(){
		return tITLE;
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

	public void setREMOTE_ADDR(String rEMOTE_ADDR){
		this.rEMOTE_ADDR=rEMOTE_ADDR;
	}

	public String getREMOTE_ADDR(){
		return rEMOTE_ADDR;
	}

	public void setUSER_AGENT(String uSER_AGENT){
		this.uSER_AGENT=uSER_AGENT;
	}

	public String getUSER_AGENT(){
		return uSER_AGENT;
	}

	public void setREQUEST_URI(String rEQUEST_URI){
		this.rEQUEST_URI=rEQUEST_URI;
	}

	public String getREQUEST_URI(){
		return rEQUEST_URI;
	}

	public void setMETHOD(String mETHOD){
		this.mETHOD=mETHOD;
	}

	public String getMETHOD(){
		return mETHOD;
	}

	public void setPARAMS(String pARAMS){
		this.pARAMS=pARAMS;
	}

	public String getPARAMS(){
		return pARAMS;
	}

	public void setEXCEPTION(String eXCEPTION){
		this.eXCEPTION=eXCEPTION;
	}

	public String getEXCEPTION(){
		return eXCEPTION;
	}
}

