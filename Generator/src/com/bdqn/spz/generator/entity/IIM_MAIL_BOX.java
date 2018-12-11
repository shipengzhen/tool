package com.bdqn.spz.generator.entity;

import java.util.Date;

/**
 * IIM_MAIL_BOX实体类
 * Thu Apr 26 11:34:11 CST 2018 ???
 */ 
public class IIM_MAIL_BOX{

	private String iD;

	private String rEADSTATUS;

	private String sENDERID;

	private String rECEIVERID;

	private Date sENDTIME;

	private String mAILID;

	public void setID(String iD){
		this.iD=iD;
	}

	public String getID(){
		return iD;
	}

	public void setREADSTATUS(String rEADSTATUS){
		this.rEADSTATUS=rEADSTATUS;
	}

	public String getREADSTATUS(){
		return rEADSTATUS;
	}

	public void setSENDERID(String sENDERID){
		this.sENDERID=sENDERID;
	}

	public String getSENDERID(){
		return sENDERID;
	}

	public void setRECEIVERID(String rECEIVERID){
		this.rECEIVERID=rECEIVERID;
	}

	public String getRECEIVERID(){
		return rECEIVERID;
	}

	public void setSENDTIME(Date sENDTIME){
		this.sENDTIME=sENDTIME;
	}

	public Date getSENDTIME(){
		return sENDTIME;
	}

	public void setMAILID(String mAILID){
		this.mAILID=mAILID;
	}

	public String getMAILID(){
		return mAILID;
	}
}

