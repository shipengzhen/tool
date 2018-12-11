package com.bdqn.spz.generator.entity;

import java.util.Date;

/**
 * SYS_ROLE实体类
 * Thu Apr 26 11:34:10 CST 2018 ???
 */ 
public class SYS_ROLE{

	private String iD;

	private String oFFICE_ID;

	private String nAME;

	private String eNNAME;

	private String rOLE_TYPE;

	private String dATA_SCOPE;

	private String iS_SYS;

	private String uSEABLE;

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

	public void setOFFICE_ID(String oFFICE_ID){
		this.oFFICE_ID=oFFICE_ID;
	}

	public String getOFFICE_ID(){
		return oFFICE_ID;
	}

	public void setNAME(String nAME){
		this.nAME=nAME;
	}

	public String getNAME(){
		return nAME;
	}

	public void setENNAME(String eNNAME){
		this.eNNAME=eNNAME;
	}

	public String getENNAME(){
		return eNNAME;
	}

	public void setROLE_TYPE(String rOLE_TYPE){
		this.rOLE_TYPE=rOLE_TYPE;
	}

	public String getROLE_TYPE(){
		return rOLE_TYPE;
	}

	public void setDATA_SCOPE(String dATA_SCOPE){
		this.dATA_SCOPE=dATA_SCOPE;
	}

	public String getDATA_SCOPE(){
		return dATA_SCOPE;
	}

	public void setIS_SYS(String iS_SYS){
		this.iS_SYS=iS_SYS;
	}

	public String getIS_SYS(){
		return iS_SYS;
	}

	public void setUSEABLE(String uSEABLE){
		this.uSEABLE=uSEABLE;
	}

	public String getUSEABLE(){
		return uSEABLE;
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

