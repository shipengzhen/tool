package com.bdqn.spz.generator.entity;

/**
 * teacher(��ʦ��)ʵ����
 * Tue Nov 14 17:26:06 CST 2017 ʩ����
 */ 
public class Teacher{

	//��ʦ���
	private Integer teacherId;

	//��ʦ����
	private String teacherName;

	//����
	private Integer age;

	//����
	private Integer teacherYear;

	//�����꼶(���)
	private Integer gradeId;

	/**
	 * ��ʦ���
	 * @param teacherId
	 */
	public void setTeacherId(Integer teacherId){
		this.teacherId=teacherId;
	}

	/**
	 * ��ʦ���
	 * @return Integer teacherId
	 */
	public Integer getTeacherId(){
		return teacherId;
	}

	/**
	 * ��ʦ����
	 * @param teacherName
	 */
	public void setTeacherName(String teacherName){
		this.teacherName=teacherName;
	}

	/**
	 * ��ʦ����
	 * @return String teacherName
	 */
	public String getTeacherName(){
		return teacherName;
	}

	/**
	 * ����
	 * @param age
	 */
	public void setAge(Integer age){
		this.age=age;
	}

	/**
	 * ����
	 * @return Integer age
	 */
	public Integer getAge(){
		return age;
	}

	/**
	 * ����
	 * @param teacherYear
	 */
	public void setTeacherYear(Integer teacherYear){
		this.teacherYear=teacherYear;
	}

	/**
	 * ����
	 * @return Integer teacherYear
	 */
	public Integer getTeacherYear(){
		return teacherYear;
	}

	/**
	 * �����꼶(���)
	 * @param gradeId
	 */
	public void setGradeId(Integer gradeId){
		this.gradeId=gradeId;
	}

	/**
	 * �����꼶(���)
	 * @return Integer gradeId
	 */
	public Integer getGradeId(){
		return gradeId;
	}
}

