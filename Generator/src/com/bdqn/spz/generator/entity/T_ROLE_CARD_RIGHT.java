package com.bdqn.spz.generator.entity;

import java.util.Date;

/**
 * T_ROLE_CARD_RIGHT实体类
 * Thu Apr 26 11:34:08 CST 2018 ???
 */ 
public class T_ROLE_CARD_RIGHT{

	private String iD;

	private String rOLEID;

	private String rIGHTTYPE;

	private String cARDID;

	private String cREATE_BY;

	private Date cREATE_DATE;

	private String uPDATE_BY;

	private Date uPDATE_DATE;

	private String rEMARKS;

	private String dEL_FLAG;

	private Long sORT;

	public void setID(String iD){
		this.iD=iD;
	}

	public String getID(){
		return iD;
	}

	public void setROLEID(String rOLEID){
		this.rOLEID=rOLEID;
	}

	public String getROLEID(){
		return rOLEID;
	}

	public void setRIGHTTYPE(String rIGHTTYPE){
		this.rIGHTTYPE=rIGHTTYPE;
	}

	public String getRIGHTTYPE(){
		return rIGHTTYPE;
	}

	public void setCARDID(String cARDID){
		this.cARDID=cARDID;
	}

	public String getCARDID(){
		return cARDID;
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

	public void setSORT(Long sORT){
		this.sORT=sORT;
	}

	public Long getSORT(){
		return sORT;
	}
}

