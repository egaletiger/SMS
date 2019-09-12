package com.cqut.service;

import java.util.List;

import com.cqut.pojo.Admin;
import com.cqut.pojo.Student;
import com.cqut.pojo.Teacher;

public interface AdminService extends UserService{
	public Admin findByNum(String num);//登录时根据工号查找
	public List<Teacher> showTeachers(int pageNum, int pageSize);
	public List<Student> showStudents(int pageNum, int pageSize);
	public List<Teacher> findTeacherByNum(int pageNum, int pageSize, String num);
	public List<Teacher> findTeacherByUname(int pageNum, int pageSize, String uname);
	public List<Student> findStudentByNum(int pageNum, int pageSize, String num);
	public List<Student> findStudentByUname(int pageNum, int pageSize, String uname);
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
	public void deleteTeachers(List<Integer> ids);
	public void deleteStudents(List<Integer> ids);
	public void addStudent(Student student);
	public void addTeacher(Teacher teacher);
	
}
