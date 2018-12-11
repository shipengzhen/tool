package com.bdqn.spz.generator.entity;

/**
 * IIM_MAIL实体类
 * Thu Apr 26 11:34:11 CST 2018 ???
 */ 
public class IIM_MAIL{

	private String iD;

	private String tITLE;

	private String oVERVIEW;

	private String cONTENT;

	public void setID(String iD){
		this.iD=iD;
	}

	public String getID(){
		return iD;
	}

	public void setTITLE(String tITLE){
		this.tITLE=tITLE;
	}

	public String getTITLE(){
		return tITLE;
	}

	public void setOVERVIEW(String oVERVIEW){
		this.oVERVIEW=oVERVIEW;
	}

	public String getOVERVIEW(){
		return oVERVIEW;
	}

	public void setCONTENT(String cONTENT){
		this.cONTENT=cONTENT;
	}

	public String getCONTENT(){
		return cONTENT;
	}
}

