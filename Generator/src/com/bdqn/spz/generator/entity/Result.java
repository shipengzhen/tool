package com.bdqn.spz.generator.entity;

import java.util.Date;

/**
 * result(成绩表)实体类
 * Tue Nov 14 17:26:06 CST 2017 施鹏振
 */ 
public class Result{

	//学号
	private Integer studentNo;

	//课程编号
	private Integer subjectNo;

	//考试成绩
	private Integer studentResult;

	//考试日期
	private Date examDate;

	/**
	 * 学号
	 * @param studentNo
	 */
	public void setStudentNo(Integer studentNo){
		this.studentNo=studentNo;
	}

	/**
	 * 学号
	 * @return Integer studentNo
	 */
	public Integer getStudentNo(){
		return studentNo;
	}

	/**
	 * 课程编号
	 * @param subjectNo
	 */
	public void setSubjectNo(Integer subjectNo){
		this.subjectNo=subjectNo;
	}

	/**
	 * 课程编号
	 * @return Integer subjectNo
	 */
	public Integer getSubjectNo(){
		return subjectNo;
	}

	/**
	 * 考试成绩
	 * @param studentResult
	 */
	public void setStudentResult(Integer studentResult){
		this.studentResult=studentResult;
	}

	/**
	 * 考试成绩
	 * @return Integer studentResult
	 */
	public Integer getStudentResult(){
		return studentResult;
	}

	/**
	 * 考试日期
	 * @param examDate
	 */
	public void setExamDate(Date examDate){
		this.examDate=examDate;
	}

	/**
	 * 考试日期
	 * @return Date examDate
	 */
	public Date getExamDate(){
		return examDate;
	}
}

