package com.bdqn.spz.generator.entity;

/**
 * T_ROLE_INFO实体类
 * Thu Apr 26 11:34:12 CST 2018 ???
 */ 
public class T_ROLE_INFO{

	private String rOLE_ID;

	private String rOLE_NAME;

	private String sTATUS;

	public void setROLE_ID(String rOLE_ID){
		this.rOLE_ID=rOLE_ID;
	}

	public String getROLE_ID(){
		return rOLE_ID;
	}

	public void setROLE_NAME(String rOLE_NAME){
		this.rOLE_NAME=rOLE_NAME;
	}

	public String getROLE_NAME(){
		return rOLE_NAME;
	}

	public void setSTATUS(String sTATUS){
		this.sTATUS=sTATUS;
	}

	public String getSTATUS(){
		return sTATUS;
	}
}

