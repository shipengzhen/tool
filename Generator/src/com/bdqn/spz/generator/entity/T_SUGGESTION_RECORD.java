package com.bdqn.spz.generator.entity;

import java.util.Date;

/**
 * T_SUGGESTION_RECORD实体类
 * Thu Apr 26 11:34:06 CST 2018 ???
 */ 
public class T_SUGGESTION_RECORD{

	private Long iD;

	private Date tIME;

	private String tELNUM;

	private String sUGGESTION;

	public void setID(Long iD){
		this.iD=iD;
	}

	public Long getID(){
		return iD;
	}

	public void setTIME(Date tIME){
		this.tIME=tIME;
	}

	public Date getTIME(){
		return tIME;
	}

	public void setTELNUM(String tELNUM){
		this.tELNUM=tELNUM;
	}

	public String getTELNUM(){
		return tELNUM;
	}

	public void setSUGGESTION(String sUGGESTION){
		this.sUGGESTION=sUGGESTION;
	}

	public String getSUGGESTION(){
		return sUGGESTION;
	}
}

