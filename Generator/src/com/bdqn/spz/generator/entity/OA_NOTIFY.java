package com.bdqn.spz.generator.entity;

import java.util.Date;

/**
 * OA_NOTIFY实体类
 * Thu Apr 26 11:34:11 CST 2018 ???
 */ 
public class OA_NOTIFY{

	private String iD;

	private String tYPE;

	private String tITLE;

	private String cONTENT;

	private String fILES;

	private String sTATUS;

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

	public void setCONTENT(String cONTENT){
		this.cONTENT=cONTENT;
	}

	public String getCONTENT(){
		return cONTENT;
	}

	public void setFILES(String fILES){
		this.fILES=fILES;
	}

	public String getFILES(){
		return fILES;
	}

	public void setSTATUS(String sTATUS){
		this.sTATUS=sTATUS;
	}

	public String getSTATUS(){
		return sTATUS;
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

