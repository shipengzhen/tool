package com.bdqn.spz.generator.entity;

import java.util.Date;

/**
 * result(�ɼ���)ʵ����
 * Tue Nov 14 17:26:06 CST 2017 ʩ����
 */ 
public class Result{

	//ѧ��
	private Integer studentNo;

	//�γ̱��
	private Integer subjectNo;

	//���Գɼ�
	private Integer studentResult;

	//��������
	private Date examDate;

	/**
	 * ѧ��
	 * @param studentNo
	 */
	public void setStudentNo(Integer studentNo){
		this.studentNo=studentNo;
	}

	/**
	 * ѧ��
	 * @return Integer studentNo
	 */
	public Integer getStudentNo(){
		return studentNo;
	}

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
	 * ���Գɼ�
	 * @param studentResult
	 */
	public void setStudentResult(Integer studentResult){
		this.studentResult=studentResult;
	}

	/**
	 * ���Գɼ�
	 * @return Integer studentResult
	 */
	public Integer getStudentResult(){
		return studentResult;
	}

	/**
	 * ��������
	 * @param examDate
	 */
	public void setExamDate(Date examDate){
		this.examDate=examDate;
	}

	/**
	 * ��������
	 * @return Date examDate
	 */
	public Date getExamDate(){
		return examDate;
	}
}

