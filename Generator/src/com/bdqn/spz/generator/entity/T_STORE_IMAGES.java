package com.bdqn.spz.generator.entity;

import java.util.Date;

/**
 * T_STORE_IMAGES实体类
 * Thu Apr 26 11:34:12 CST 2018 ???
 */ 
public class T_STORE_IMAGES{

	private String iMAGE_ID;

	private String sTATUS;

	private String sTORE_ID;

	private String iMAGE_URL;

	private String iD;

	private String cREATE_BY;

	private Date cREATE_DATE;

	private String uPDATE_BY;

	private Date uPDATE_DATE;

	private String rEMARKS;

	private String dEL_FLAG;

	private String aUDITSTATUS;

	private String rEASONS;

	public void setIMAGE_ID(String iMAGE_ID){
		this.iMAGE_ID=iMAGE_ID;
	}

	public String getIMAGE_ID(){
		return iMAGE_ID;
	}

	public void setSTATUS(String sTATUS){
		this.sTATUS=sTATUS;
	}

	public String getSTATUS(){
		return sTATUS;
	}

	public void setSTORE_ID(String sTORE_ID){
		this.sTORE_ID=sTORE_ID;
	}

	public String getSTORE_ID(){
		return sTORE_ID;
	}

	public void setIMAGE_URL(String iMAGE_URL){
		this.iMAGE_URL=iMAGE_URL;
	}

	public String getIMAGE_URL(){
		return iMAGE_URL;
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

	public void setAUDITSTATUS(String aUDITSTATUS){
		this.aUDITSTATUS=aUDITSTATUS;
	}

	public String getAUDITSTATUS(){
		return aUDITSTATUS;
	}

	public void setREASONS(String rEASONS){
		this.rEASONS=rEASONS;
	}

	public String getREASONS(){
		return rEASONS;
	}
}

