package com.bdqn.spz.generator.entity;

/**
 * grade(�꼶��)ʵ����
 * Tue Nov 14 17:26:06 CST 2017 ʩ����
 */ 
public class Grade{

	//��ͱ��
	private Integer gradeId;

	//�������
	private String gradeName;

	/**
	 * ��ͱ��
	 * @param gradeId
	 */
	public void setGradeId(Integer gradeId){
		this.gradeId=gradeId;
	}

	/**
	 * ��ͱ��
	 * @return Integer gradeId
	 */
	public Integer getGradeId(){
		return gradeId;
	}

	/**
	 * �������
	 * @param gradeName
	 */
	public void setGradeName(String gradeName){
		this.gradeName=gradeName;
	}

	/**
	 * �������
	 * @return String gradeName
	 */
	public String getGradeName(){
		return gradeName;
	}
}

