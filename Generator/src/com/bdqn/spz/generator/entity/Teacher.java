package com.bdqn.spz.generator.entity;

/**
 * teacher(教师表)实体类
 * Tue Nov 14 17:26:06 CST 2017 施鹏振
 */ 
public class Teacher{

	//教师编号
	private Integer teacherId;

	//教师名称
	private String teacherName;

	//年龄
	private Integer age;

	//教龄
	private Integer teacherYear;

	//所教年级(外键)
	private Integer gradeId;

	/**
	 * 教师编号
	 * @param teacherId
	 */
	public void setTeacherId(Integer teacherId){
		this.teacherId=teacherId;
	}

	/**
	 * 教师编号
	 * @return Integer teacherId
	 */
	public Integer getTeacherId(){
		return teacherId;
	}

	/**
	 * 教师名称
	 * @param teacherName
	 */
	public void setTeacherName(String teacherName){
		this.teacherName=teacherName;
	}

	/**
	 * 教师名称
	 * @return String teacherName
	 */
	public String getTeacherName(){
		return teacherName;
	}

	/**
	 * 年龄
	 * @param age
	 */
	public void setAge(Integer age){
		this.age=age;
	}

	/**
	 * 年龄
	 * @return Integer age
	 */
	public Integer getAge(){
		return age;
	}

	/**
	 * 教龄
	 * @param teacherYear
	 */
	public void setTeacherYear(Integer teacherYear){
		this.teacherYear=teacherYear;
	}

	/**
	 * 教龄
	 * @return Integer teacherYear
	 */
	public Integer getTeacherYear(){
		return teacherYear;
	}

	/**
	 * 所教年级(外键)
	 * @param gradeId
	 */
	public void setGradeId(Integer gradeId){
		this.gradeId=gradeId;
	}

	/**
	 * 所教年级(外键)
	 * @return Integer gradeId
	 */
	public Integer getGradeId(){
		return gradeId;
	}
}

