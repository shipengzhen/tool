package com.bdqn.spz.generator.entity;

import java.util.Date;

/**
 * T_DICT_GROUP实体类
 * Thu Apr 26 11:34:13 CST 2018 ???
 */ 
public class T_DICT_GROUP{

	private String gROUP_ID;

	private String gROUP_NAME;

	private String gROUP_DESC;

	private String pARENT_ID;

	private String iD;

	private String cREATE_BY;

	private Date cREATE_DATE;

	private String uPDATE_BY;

	private Date uPDATE_DATE;

	private String rEMARKS;

	private String dEL_FLAG;

	public void setGROUP_ID(String gROUP_ID){
		this.gROUP_ID=gROUP_ID;
	}

	public String getGROUP_ID(){
		return gROUP_ID;
	}

	public void setGROUP_NAME(String gROUP_NAME){
		this.gROUP_NAME=gROUP_NAME;
	}

	public String getGROUP_NAME(){
		return gROUP_NAME;
	}

	public void setGROUP_DESC(String gROUP_DESC){
		this.gROUP_DESC=gROUP_DESC;
	}

	public String getGROUP_DESC(){
		return gROUP_DESC;
	}

	public void setPARENT_ID(String pARENT_ID){
		this.pARENT_ID=pARENT_ID;
	}

	public String getPARENT_ID(){
		return pARENT_ID;
	}

	public void setID(String iD){
		this.iD=iD;
	}

	public String getID(){
		return iD;
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

