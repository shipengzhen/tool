package com.spz.tools.tool.entity;


/**
 * ���ʵ����
 * @author ʩ����
 *
 */
public class Grade {

	public int gradeId;//���id
	private String gradeName;//�������
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
