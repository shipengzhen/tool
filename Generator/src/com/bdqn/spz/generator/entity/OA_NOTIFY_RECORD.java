package com.bdqn.spz.generator.entity;

import java.util.Date;

/**
 * OA_NOTIFY_RECORD实体类
 * Thu Apr 26 11:34:11 CST 2018 ???
 */ 
public class OA_NOTIFY_RECORD{

	private String iD;

	private String oA_NOTIFY_ID;

	private String uSER_ID;

	private String rEAD_FLAG;

	private Date rEAD_DATE;

	public void setID(String iD){
		this.iD=iD;
	}

	public String getID(){
		return iD;
	}

	public void setOA_NOTIFY_ID(String oA_NOTIFY_ID){
		this.oA_NOTIFY_ID=oA_NOTIFY_ID;
	}

	public String getOA_NOTIFY_ID(){
		return oA_NOTIFY_ID;
	}

	public void setUSER_ID(String uSER_ID){
		this.uSER_ID=uSER_ID;
	}

	public String getUSER_ID(){
		return uSER_ID;
	}

	public void setREAD_FLAG(String rEAD_FLAG){
		this.rEAD_FLAG=rEAD_FLAG;
	}

	public String getREAD_FLAG(){
		return rEAD_FLAG;
	}

	public void setREAD_DATE(Date rEAD_DATE){
		this.rEAD_DATE=rEAD_DATE;
	}

	public Date getREAD_DATE(){
		return rEAD_DATE;
	}
}

