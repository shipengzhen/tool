package com.bdqn.spz.generator.entity;

/**
 * T_USER_LOCATION实体类
 * Thu Apr 26 11:34:12 CST 2018 ???
 */ 
public class T_USER_LOCATION{

	private String uSER_ID;

	private String sECOND_CITY_ID;

	private String fIRST_CITY_ID;

	private String tHIRD_CITY_ID;

	public void setUSER_ID(String uSER_ID){
		this.uSER_ID=uSER_ID;
	}

	public String getUSER_ID(){
		return uSER_ID;
	}

	public void setSECOND_CITY_ID(String sECOND_CITY_ID){
		this.sECOND_CITY_ID=sECOND_CITY_ID;
	}

	public String getSECOND_CITY_ID(){
		return sECOND_CITY_ID;
	}

	public void setFIRST_CITY_ID(String fIRST_CITY_ID){
		this.fIRST_CITY_ID=fIRST_CITY_ID;
	}

	public String getFIRST_CITY_ID(){
		return fIRST_CITY_ID;
	}

	public void setTHIRD_CITY_ID(String tHIRD_CITY_ID){
		this.tHIRD_CITY_ID=tHIRD_CITY_ID;
	}

	public String getTHIRD_CITY_ID(){
		return tHIRD_CITY_ID;
	}
}

