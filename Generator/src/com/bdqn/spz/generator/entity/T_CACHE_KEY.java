package com.bdqn.spz.generator.entity;

/**
 * T_CACHE_KEY实体类
 * Thu Apr 26 11:34:09 CST 2018 ???
 */ 
public class T_CACHE_KEY{

	private Long f_KID;

	private String f_KEY;

	private String f_DES;

	private Long f_TYPE;

	private Long f_SID;

	public void setF_KID(Long f_KID){
		this.f_KID=f_KID;
	}

	public Long getF_KID(){
		return f_KID;
	}

	public void setF_KEY(String f_KEY){
		this.f_KEY=f_KEY;
	}

	public String getF_KEY(){
		return f_KEY;
	}

	public void setF_DES(String f_DES){
		this.f_DES=f_DES;
	}

	public String getF_DES(){
		return f_DES;
	}

	public void setF_TYPE(Long f_TYPE){
		this.f_TYPE=f_TYPE;
	}

	public Long getF_TYPE(){
		return f_TYPE;
	}

	public void setF_SID(Long f_SID){
		this.f_SID=f_SID;
	}

	public Long getF_SID(){
		return f_SID;
	}
}

