package com.bdqn.spz.generator.entity;

import java.util.Date;

/**
 * T_CARD_LIST_INFO实体类
 * Thu Apr 26 11:34:14 CST 2018 ???
 */ 
public class T_CARD_LIST_INFO{

	private String cARD_TYPE;

	private String cARD_NAME;

	private String lOGO_URL;

	private String sMALL_LOGO_URL;

	private Date cREATE_TIME;

	private byte[] cARD_DETAIL;

	private byte[] aPPLI_MERCHANT;

	private byte[] cARD_FIELD;

	private String iS_USED;

	private byte[] aTTENTION;

	private String iD;

	private String cREATE_BY;

	private Date cREATE_DATE;

	private String uPDATE_BY;

	private Date uPDATE_DATE;

	private String rEMARKS;

	private String dEL_FLAG;

	public void setCARD_TYPE(String cARD_TYPE){
		this.cARD_TYPE=cARD_TYPE;
	}

	public String getCARD_TYPE(){
		return cARD_TYPE;
	}

	public void setCARD_NAME(String cARD_NAME){
		this.cARD_NAME=cARD_NAME;
	}

	public String getCARD_NAME(){
		return cARD_NAME;
	}

	public void setLOGO_URL(String lOGO_URL){
		this.lOGO_URL=lOGO_URL;
	}

	public String getLOGO_URL(){
		return lOGO_URL;
	}

	public void setSMALL_LOGO_URL(String sMALL_LOGO_URL){
		this.sMALL_LOGO_URL=sMALL_LOGO_URL;
	}

	public String getSMALL_LOGO_URL(){
		return sMALL_LOGO_URL;
	}

	public void setCREATE_TIME(Date cREATE_TIME){
		this.cREATE_TIME=cREATE_TIME;
	}

	public Date getCREATE_TIME(){
		return cREATE_TIME;
	}

	public void setCARD_DETAIL(byte[] cARD_DETAIL){
		this.cARD_DETAIL=cARD_DETAIL;
	}

	public byte[] getCARD_DETAIL(){
		return cARD_DETAIL;
	}

	public void setAPPLI_MERCHANT(byte[] aPPLI_MERCHANT){
		this.aPPLI_MERCHANT=aPPLI_MERCHANT;
	}

	public byte[] getAPPLI_MERCHANT(){
		return aPPLI_MERCHANT;
	}

	public void setCARD_FIELD(byte[] cARD_FIELD){
		this.cARD_FIELD=cARD_FIELD;
	}

	public byte[] getCARD_FIELD(){
		return cARD_FIELD;
	}

	public void setIS_USED(String iS_USED){
		this.iS_USED=iS_USED;
	}

	public String getIS_USED(){
		return iS_USED;
	}

	public void setATTENTION(byte[] aTTENTION){
		this.aTTENTION=aTTENTION;
	}

	public byte[] getATTENTION(){
		return aTTENTION;
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

