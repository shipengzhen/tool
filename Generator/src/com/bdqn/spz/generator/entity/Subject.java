package com.bdqn.spz.generator.entity;

/**
 * subject(��Ŀ��)ʵ����
 * Tue Nov 14 17:26:06 CST 2017 ʩ����
 */ 
public class Subject{

	//�γ̱��
	private Integer subjectNo;

	//�γ�����
	private String subjectName;

	//ѧʱ
	private Integer classHour;

	//�꼶���
	private Integer gradeId;

	/**
	 * �γ̱��
	 * @param subjectNo
	 */
	public void setSubjectNo(Integer subjectNo){
		this.subjectNo=subjectNo;
	}

	/**
	 * �γ̱��
	 * @return Integer subjectNo
	 */
	public Integer getSubjectNo(){
		return subjectNo;
	}

	/**
	 * �γ�����
	 * @param subjectName
	 */
	public void setSubjectName(String subjectName){
		this.subjectName=subjectName;
	}

	/**
	 * �γ�����
	 * @return String subjectName
	 */
	public String getSubjectName(){
		return subjectName;
	}

	/**
	 * ѧʱ
	 * @param classHour
	 */
	public void setClassHour(Integer classHour){
		this.classHour=classHour;
	}

	/**
	 * ѧʱ
	 * @return Integer classHour
	 */
	public Integer getClassHour(){
		return classHour;
	}

	/**
	 * �꼶���
	 * @param gradeId
	 */
	public void setGradeId(Integer gradeId){
		this.gradeId=gradeId;
	}

	/**
	 * �꼶���
	 * @return Integer gradeId
	 */
	public Integer getGradeId(){
		return gradeId;
	}
}

