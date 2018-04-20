package com.bdqn.spz.tools.entity;


/**
 * 年纪实现类
 * @author 施鹏振
 *
 */
public class Grade {

	public int gradeId;//年纪id
	private String gradeName;//年纪名称
	private Student student;
	public int getGradeId() {
		return gradeId;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
}
