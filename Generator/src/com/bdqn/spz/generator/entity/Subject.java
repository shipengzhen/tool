package com.bdqn.spz.generator.entity;

/**
 * subject(科目表)实体类
 * Tue Nov 14 17:26:06 CST 2017 施鹏振
 */ 
public class Subject{

	//课程编号
	private Integer subjectNo;

	//课程名称
	private String subjectName;

	//学时
	private Integer classHour;

	//年级编号
	private Integer gradeId;

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
	 * 课程名称
	 * @param subjectName
	 */
	public void setSubjectName(String subjectName){
		this.subjectName=subjectName;
	}

	/**
	 * 课程名称
	 * @return String subjectName
	 */
	public String getSubjectName(){
		return subjectName;
	}

	/**
	 * 学时
	 * @param classHour
	 */
	public void setClassHour(Integer classHour){
		this.classHour=classHour;
	}

	/**
	 * 学时
	 * @return Integer classHour
	 */
	public Integer getClassHour(){
		return classHour;
	}

	/**
	 * 年级编号
	 * @param gradeId
	 */
	public void setGradeId(Integer gradeId){
		this.gradeId=gradeId;
	}

	/**
	 * 年级编号
	 * @return Integer gradeId
	 */
	public Integer getGradeId(){
		return gradeId;
	}
}

