package com.cqut.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqut.pojo.Student;
import com.cqut.pojo.Teacher;

public interface TeacherMapper extends UserMapper{
	public Teacher findByNum(String num);
	public List<Student> showStudents(@Param("pageNum")int pageNum, @Param("pageSize")int pageSize);
	public List<Student> findStudentByNum(String num);
	public List<Student> findStudentByUname(@Param("pageNum")int pageNum, @Param("pageSize")int pageSize, @Param("uname")String uname);
	public int countStudents();
}
 