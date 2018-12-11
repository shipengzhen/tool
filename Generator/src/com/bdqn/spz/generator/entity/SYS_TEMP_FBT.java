package com.bdqn.spz.generator.entity;

/**
 * SYS_TEMP_FBT实体类
 * Thu Apr 26 11:34:00 CST 2018 ???
 */ 
public class SYS_TEMP_FBT{

	private String sCHEMA;

	private String oBJECT_NAME;

	private Long oBJECT;

	private String rID;

	private String aCTION;

	public void setSCHEMA(String sCHEMA){
		this.sCHEMA=sCHEMA;
	}

	public String getSCHEMA(){
		return sCHEMA;
	}

	public void setOBJECT_NAME(String oBJECT_NAME){
		this.oBJECT_NAME=oBJECT_NAME;
	}

	public String getOBJECT_NAME(){
		return oBJECT_NAME;
	}

	public void setOBJECT(Long oBJECT){
		this.oBJECT=oBJECT;
	}

	public Long getOBJECT(){
		return oBJECT;
	}

	public void setRID(String rID){
		this.rID=rID;
	}

	public String getRID(){
		return rID;
	}

	public void setACTION(String aCTION){
		this.aCTION=aCTION;
	}

	public String getACTION(){
		return aCTION;
	}
}

