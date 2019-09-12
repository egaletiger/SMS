package com.cqut.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqut.pojo.Admin;
import com.cqut.pojo.Permission;
import com.cqut.pojo.Student;
import com.cqut.pojo.Teacher;

public interface AdminMapper extends UserMapper{
	public Admin findByNum(String num);//登录时根据工号查找
	public List<Teacher> showTeachers(@Param("pageNum")int pageNum, @Param("pageSize")int pageSize);
	public List<Student> showStudents(@Param("pageNum")int pageNum, @Param("pageSize")int pageSize);
	public List<Teacher> findTeacherByNum(@Param("pageNum")int pageNum, @Param("pageSize")int pageSize, @Param("num")String num);
	public List<Teacher> findTeacherByUname(@Param("pageNum")int pageNum, @Param("pageSize")int pageSize, @Param("uname")String uname);
	public List<Student> findStudentByNum(@Param("pageNum")int pageNum, @Param("pageSize")int pageSize, @Param("num")String num);
	public List<Student> findStudentByUname(@Param("pageNum")int pageNum, @Param("pageSize")int pageSize, @Param("uname")String uname);
	public String findTeacherNoPermissions(String num);
	public String findStudentNoPermissions(String num);
	public int countStudents();
	public int countTeachers();
	public int countStudentsByNum(String num);
	public int countTeachersByNum(String num);
	public int countStudentsByUname(String uname);
	public int countTeachersByUname(String uname);
	public void updateTeacherInfo(Teacher teacher);
	public void updateStudentInfo(Student student);
	public void deleteTeacher(int id);
	public void deleteStudent(int id);
	public void deleteTeachers(@Param("ids")List<Integer> ids);
	public void deleteStudents(@Param("ids")List<Integer> ids);
	public void addTeacher(Teacher teacher);
	public void addStudent(Student student);
}	

