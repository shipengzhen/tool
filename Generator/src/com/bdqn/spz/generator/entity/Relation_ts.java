package com.bdqn.spz.generator.entity;

/**
 * relation_ts(��ʦѧ����ϵ��)ʵ����
 * Tue Nov 14 17:26:06 CST 2017 ʩ����
 */ 
public class Relation_ts{

	//��ʦ���(���)
	private Integer teacherId;

	//ѧ�����(���)
	private Integer studentNo;

	/**
	 * ��ʦ���(���)
	 * @param teacherId
	 */
	public void setTeacherId(Integer teacherId){
		this.teacherId=teacherId;
	}

	/**
	 * ��ʦ���(���)
	 * @return Integer teacherId
	 */
	public Integer getTeacherId(){
		return teacherId;
	}

	/**
	 * ѧ�����(���)
	 * @param studentNo
	 */
	public void setStudentNo(Integer studentNo){
		this.studentNo=studentNo;
	}

	/**
	 * ѧ�����(���)
	 * @return Integer studentNo
	 */
	public Integer getStudentNo(){
		return studentNo;
	}
}

