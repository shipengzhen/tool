package com.bdqn.spz.generator.entity;

import java.util.Date;

/**
 * SYS_DICT实体类
 * Thu Apr 26 11:34:10 CST 2018 ???
 */ 
public class SYS_DICT{

	private String iD;

	private String vALUE;

	private String lABEL;

	private String tYPE;

	private String dESCRIPTION;

	private Long sORT;

	private String pARENT_ID;

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

	public void setVALUE(String vALUE){
		this.vALUE=vALUE;
	}

	public String getVALUE(){
		return vALUE;
	}

	public void setLABEL(String lABEL){
		this.lABEL=lABEL;
	}

	public String getLABEL(){
		return lABEL;
	}

	public void setTYPE(String tYPE){
		this.tYPE=tYPE;
	}

	public String getTYPE(){
		return tYPE;
	}

	public void setDESCRIPTION(String dESCRIPTION){
		this.dESCRIPTION=dESCRIPTION;
	}

	public String getDESCRIPTION(){
		return dESCRIPTION;
	}

	public void setSORT(Long sORT){
		this.sORT=sORT;
	}

	public Long getSORT(){
		return sORT;
	}

	public void setPARENT_ID(String pARENT_ID){
		this.pARENT_ID=pARENT_ID;
	}

	public String getPARENT_ID(){
		return pARENT_ID;
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

