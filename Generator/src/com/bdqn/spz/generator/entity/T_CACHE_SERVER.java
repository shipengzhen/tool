package com.bdqn.spz.generator.entity;

/**
 * T_CACHE_SERVER实体类
 * Thu Apr 26 11:34:08 CST 2018 ???
 */ 
public class T_CACHE_SERVER{

	private Long f_SID;

	private String f_IP;

	private String f_PORT;

	private String f_DES;

	public void setF_SID(Long f_SID){
		this.f_SID=f_SID;
	}

	public Long getF_SID(){
		return f_SID;
	}

	public void setF_IP(String f_IP){
		this.f_IP=f_IP;
	}

	public String getF_IP(){
		return f_IP;
	}

	public void setF_PORT(String f_PORT){
		this.f_PORT=f_PORT;
	}

	public String getF_PORT(){
		return f_PORT;
	}

	public void setF_DES(String f_DES){
		this.f_DES=f_DES;
	}

	public String getF_DES(){
		return f_DES;
	}
}

