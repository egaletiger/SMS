package com.cqut.service;

import java.util.List;

import com.cqut.pojo.Student;
import com.cqut.pojo.Teacher;

public interface TeacherService extends UserService{
	public Teacher findByNum(String num);
	public List<Student> showStudents(int pageNum, int pageSize);
	public List<Student> findStudentByNum(String num);
	public List<Student> findStudentByUname(int pageNum, int pageSize, String uname);
	public int countStudents();
}
