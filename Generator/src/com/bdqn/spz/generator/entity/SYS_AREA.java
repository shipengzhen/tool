package com.bdqn.spz.generator.entity;

import java.util.Date;

/**
 * SYS_AREA实体类
 * Thu Apr 26 11:34:10 CST 2018 ???
 */ 
public class SYS_AREA{

	private String iD;

	private String pARENT_ID;

	private String pARENT_IDS;

	private String nAME;

	private Long sORT;

	private String cODE;

	private String tYPE;

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

	public void setPARENT_ID(String pARENT_ID){
		this.pARENT_ID=pARENT_ID;
	}

	public String getPARENT_ID(){
		return pARENT_ID;
	}

	public void setPARENT_IDS(String pARENT_IDS){
		this.pARENT_IDS=pARENT_IDS;
	}

	public String getPARENT_IDS(){
		return pARENT_IDS;
	}

	public void setNAME(String nAME){
		this.nAME=nAME;
	}

	public String getNAME(){
		return nAME;
	}

	public void setSORT(Long sORT){
		this.sORT=sORT;
	}

	public Long getSORT(){
		return sORT;
	}

	public void setCODE(String cODE){
		this.cODE=cODE;
	}

	public String getCODE(){
		return cODE;
	}

	public void setTYPE(String tYPE){
		this.tYPE=tYPE;
	}

	public String getTYPE(){
		return tYPE;
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

