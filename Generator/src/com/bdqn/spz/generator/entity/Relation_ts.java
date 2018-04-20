package com.bdqn.spz.generator.entity;

/**
 * relation_ts(教师学生关系表)实体类
 * Tue Nov 14 17:26:06 CST 2017 施鹏振
 */ 
public class Relation_ts{

	//教师编号(外键)
	private Integer teacherId;

	//学生编号(外键)
	private Integer studentNo;

	/**
	 * 教师编号(外键)
	 * @param teacherId
	 */
	public void setTeacherId(Integer teacherId){
		this.teacherId=teacherId;
	}

	/**
	 * 教师编号(外键)
	 * @return Integer teacherId
	 */
	public Integer getTeacherId(){
		return teacherId;
	}

	/**
	 * 学生编号(外键)
	 * @param studentNo
	 */
	public void setStudentNo(Integer studentNo){
		this.studentNo=studentNo;
	}

	/**
	 * 学生编号(外键)
	 * @return Integer studentNo
	 */
	public Integer getStudentNo(){
		return studentNo;
	}
}

