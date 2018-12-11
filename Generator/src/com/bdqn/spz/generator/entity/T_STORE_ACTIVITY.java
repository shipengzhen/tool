package com.bdqn.spz.generator.entity;

import java.util.Date;

/**
 * T_STORE_ACTIVITY实体类
 * Thu Apr 26 11:34:02 CST 2018 ???
 */ 
public class T_STORE_ACTIVITY{

	private String aCTIVITY_ID;

	private String nAME;

	private Date sTART_TIME;

	private Date eND_TIME;

	private String cREATE_BY;

	private Date cREATE_TIME;

	private Date uPDATE_TIME;

	private String uPDATE_BY;

	private String sTATUS;

	private String sTORE_ID;

	private String iMAGE;

	public void setACTIVITY_ID(String aCTIVITY_ID){
		this.aCTIVITY_ID=aCTIVITY_ID;
	}

	public String getACTIVITY_ID(){
		return aCTIVITY_ID;
	}

	public void setNAME(String nAME){
		this.nAME=nAME;
	}

	public String getNAME(){
		return nAME;
	}

	public void setSTART_TIME(Date sTART_TIME){
		this.sTART_TIME=sTART_TIME;
	}

	public Date getSTART_TIME(){
		return sTART_TIME;
	}

	public void setEND_TIME(Date eND_TIME){
		this.eND_TIME=eND_TIME;
	}

	public Date getEND_TIME(){
		return eND_TIME;
	}

	public void setCREATE_BY(String cREATE_BY){
		this.cREATE_BY=cREATE_BY;
	}

	public String getCREATE_BY(){
		return cREATE_BY;
	}

	public void setCREATE_TIME(Date cREATE_TIME){
		this.cREATE_TIME=cREATE_TIME;
	}

	public Date getCREATE_TIME(){
		return cREATE_TIME;
	}

	public void setUPDATE_TIME(Date uPDATE_TIME){
		this.uPDATE_TIME=uPDATE_TIME;
	}

	public Date getUPDATE_TIME(){
		return uPDATE_TIME;
	}

	public void setUPDATE_BY(String uPDATE_BY){
		this.uPDATE_BY=uPDATE_BY;
	}

	public String getUPDATE_BY(){
		return uPDATE_BY;
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

	public void setIMAGE(String iMAGE){
		this.iMAGE=iMAGE;
	}

	public String getIMAGE(){
		return iMAGE;
	}
}

