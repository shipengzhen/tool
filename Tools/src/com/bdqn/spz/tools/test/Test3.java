package com.bdqn.spz.tools.test;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bdqn.spz.tools.dao.BaseDao;
import com.bdqn.spz.tools.dao.SqlHelper;
import com.bdqn.spz.tools.entity.Grade;
import com.bdqn.spz.tools.entity.Student;

public class Test3 {

//	Student student=baseDao.getObject(Student.class,sql,10000);
//	System.out.println(student.getStudentName());
//	System.out.println(student.getGrade()==null);
	public static void main(String[] args) {
		try {
			BaseDao baseDao=new BaseDao();
			String sql="select* from student inner join Grade on Grade.gradeId=Student.gradeId where Student.gradeId=1";
			
//			Map<String,String> map=new HashMap<String, String>();
//			map.put("studentName","studentName");
//			map.put("grade","grade");
//			baseDao.setMap(map);
			//Student student=baseDao.getObject(Student.class, sql);
			List<Grade> students=baseDao.getList(Grade.class, sql);
			for (Grade student2 : students) {
				//System.out.println(student2.getGradeName());
				System.out.println(student2.getStudent().getStudentName());
			}
//			System.out.println(student.getStudentName());
//			System.out.println(student.getGrade().getGradeName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
