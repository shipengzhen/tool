package com.bdqn.spz.generator.entity;

import java.util.Date;

/**
 * T_DATA_DICT实体类
 * Thu Apr 26 11:34:13 CST 2018 ???
 */ 
public class T_DATA_DICT{

	private String iD;

	private String gROUP_ID;

	private String dICT_VALUE;

	private String dICT_NAME;

	private String dICT_DESC;

	private String pARENT_ID;

	private String cREATE_BY;

	private Date cREATE_DATE;

	private String uPDATE_BY;

	private Date uPDATE_DATE;

	private String rEMARKS;

	private String dEL_FLAG;

	private String iS_ALL;

	private String sORT;

	public void setID(String iD){
		this.iD=iD;
	}

	public String getID(){
		return iD;
	}

	public void setGROUP_ID(String gROUP_ID){
		this.gROUP_ID=gROUP_ID;
	}

	public String getGROUP_ID(){
		return gROUP_ID;
	}

	public void setDICT_VALUE(String dICT_VALUE){
		this.dICT_VALUE=dICT_VALUE;
	}

	public String getDICT_VALUE(){
		return dICT_VALUE;
	}

	public void setDICT_NAME(String dICT_NAME){
		this.dICT_NAME=dICT_NAME;
	}

	public String getDICT_NAME(){
		return dICT_NAME;
	}

	public void setDICT_DESC(String dICT_DESC){
		this.dICT_DESC=dICT_DESC;
	}

	public String getDICT_DESC(){
		return dICT_DESC;
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

	public void setIS_ALL(String iS_ALL){
		this.iS_ALL=iS_ALL;
	}

	public String getIS_ALL(){
		return iS_ALL;
	}

	public void setSORT(String sORT){
		this.sORT=sORT;
	}

	public String getSORT(){
		return sORT;
	}
}

