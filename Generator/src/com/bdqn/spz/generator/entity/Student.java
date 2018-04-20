package com.bdqn.spz.generator.entity;

import java.util.Date;

/**
 * student(学生表)实体类
 * Tue Nov 14 17:26:06 CST 2017 施鹏振
 */ 
public class Student{

	//学好
	private Integer studentNo;

	//密码
	private String loginPwd;

	//学生姓名
	private String studentName;

	//性别
	private String sex;

	//年纪编号
	private Integer gradeId;

	//联系电话
	private String phone;

	//地址
	private String address;

	//出生日期
	private Date bornDate;

	//电子邮箱
	private String email;

	//身份证号
	private String identityCard;

	/**
	 * 学好
	 * @param studentNo
	 */
	public void setStudentNo(Integer studentNo){
		this.studentNo=studentNo;
	}

	/**
	 * 学好
	 * @return Integer studentNo
	 */
	public Integer getStudentNo(){
		return studentNo;
	}

	/**
	 * 密码
	 * @param loginPwd
	 */
	public void setLoginPwd(String loginPwd){
		this.loginPwd=loginPwd;
	}

	/**
	 * 密码
	 * @return String loginPwd
	 */
	public String getLoginPwd(){
		return loginPwd;
	}

	/**
	 * 学生姓名
	 * @param studentName
	 */
	public void setStudentName(String studentName){
		this.studentName=studentName;
	}

	/**
	 * 学生姓名
	 * @return String studentName
	 */
	public String getStudentName(){
		return studentName;
	}

	/**
	 * 性别
	 * @param sex
	 */
	public void setSex(String sex){
		this.sex=sex;
	}

	/**
	 * 性别
	 * @return String sex
	 */
	public String getSex(){
		return sex;
	}

	/**
	 * 年纪编号
	 * @param gradeId
	 */
	public void setGradeId(Integer gradeId){
		this.gradeId=gradeId;
	}

	/**
	 * 年纪编号
	 * @return Integer gradeId
	 */
	public Integer getGradeId(){
		return gradeId;
	}

	/**
	 * 联系电话
	 * @param phone
	 */
	public void setPhone(String phone){
		this.phone=phone;
	}

	/**
	 * 联系电话
	 * @return String phone
	 */
	public String getPhone(){
		return phone;
	}

	/**
	 * 地址
	 * @param address
	 */
	public void setAddress(String address){
		this.address=address;
	}

	/**
	 * 地址
	 * @return String address
	 */
	public String getAddress(){
		return address;
	}

	/**
	 * 出生日期
	 * @param bornDate
	 */
	public void setBornDate(Date bornDate){
		this.bornDate=bornDate;
	}

	/**
	 * 出生日期
	 * @return Date bornDate
	 */
	public Date getBornDate(){
		return bornDate;
	}

	/**
	 * 电子邮箱
	 * @param email
	 */
	public void setEmail(String email){
		this.email=email;
	}

	/**
	 * 电子邮箱
	 * @return String email
	 */
	public String getEmail(){
		return email;
	}

	/**
	 * 身份证号
	 * @param identityCard
	 */
	public void setIdentityCard(String identityCard){
		this.identityCard=identityCard;
	}

	/**
	 * 身份证号
	 * @return String identityCard
	 */
	public String getIdentityCard(){
		return identityCard;
	}
}

