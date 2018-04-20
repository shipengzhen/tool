package com.bdqn.spz.tools.entity;
import java.util.Date;
/**
 * 学生实现类
 * @author 施鹏振
 *
 */
public class Student {
	private int studentNo;//学号
	private String loginPwd;//密码
	private String studentName;//姓名
	private String sex;//性别
	private int gradeId;//年纪编号
	private String phone;//联系电话
	private String address;//地址
	private Date bornDate;//出生日期
	private String email;//邮件账号
	private String identityCard;//身份证号
	//private Grade grade;
//	public Grade getGrade() {
//		return grade;
//	}
//	public void setGrade(Grade grade) {
//		this.grade = grade;
//	}
	public int getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getGradeId() {
		return gradeId;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBornDate() {
		return bornDate;
	}
	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
}
