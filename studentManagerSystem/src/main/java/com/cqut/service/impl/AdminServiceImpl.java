package com.cqut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cqut.mapper.AdminMapper;
import com.cqut.pojo.Admin;
import com.cqut.pojo.Student;
import com.cqut.pojo.Teacher;
import com.cqut.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	@Qualifier("adminMapper")
	AdminMapper am;

	@Override
	public void updatePwdById(int id, String pwd) {
		am.updatePwdById(id, pwd);
	}

	@Override
	public void updateIconById(int id, String iconName) {
		am.updateIconById(id, iconName);
	}

	@Override
	public Admin findByNum(String num) {
		return am.findByNum(num);
	}

	@Override
	public List<Teacher> showTeachers(int pageNum, int pageSize) {
		return am.showTeachers(pageNum, pageSize);
	}

	@Override
	public List<Student> showStudents(int pageNum, int pageSize) {
		return am.showStudents(pageNum, pageSize);
	}

	@Override
	public List<Teacher> findTeacherByNum(int pageNum, int pageSize, String num) {
		return am.findTeacherByNum(pageNum, pageSize, num);
	}

	@Override
	public List<Teacher> findTeacherByUname(int pageNum, int pageSize, String uname) {
		return am.findTeacherByUname(pageNum, pageSize, uname);
	}

	@Override
	public List<Student> findStudentByNum(int pageNum, int pageSize, String num) {
		return am.findStudentByNum(pageNum, pageSize, num);
	}

	@Override
	public List<Student> findStudentByUname(int pageNum, int pageSize, String uname) {
		return am.findStudentByUname(pageNum, pageSize, uname);
	}

	@Override
	public void updateTeacherInfo(Teacher teacher) {
		am.updateTeacherInfo(teacher);
	}

	@Override
	public void updateStudentInfo(Student student) {
		am.updateStudentInfo(student);
	}

	@Override
	public void deleteTeacher(int id) {
		am.deleteTeacher(id);
	}

	@Override
	public void deleteStudent(int id) {
		am.deleteStudent(id);	
	}

	@Override
	public void deleteTeachers(List<Integer> ids) {
		am.deleteTeachers(ids);
	}

	@Override
	public void deleteStudents(List<Integer> ids) {
		am.deleteStudents(ids);
	}

	@Override
	public int countStudents() {
		return am.countStudents();
	}

	@Override
	public int countTeachers() {
		return am.countTeachers();
	}

	@Override
	public int countStudentsByNum(String num) {
		return am.countStudentsByNum(num);
	}

	@Override
	public int countTeachersByNum(String num) {
		return am.countTeachersByNum(num);
	}

	@Override
	public int countStudentsByUname(String uname) {
		return am.countStudentsByUname(uname);
	}

	@Override
	public int countTeachersByUname(String uname) {
		return am.countTeachersByUname(uname);
	}

	@Override
	public String getRole(int rid) {
		return am.getRole(rid);
	}

	@Override
	public void addStudent(Student student) {
		am.addStudent(student);
	}

	@Override
	public void addTeacher(Teacher teacher) {
		am.addTeacher(teacher);
	}

	@Override
	public String findTeacherNoPermissions(String num) {
		return am.findTeacherNoPermissions(num);
	}

	@Override
	public String findStudentNoPermissions(String num) {
		return am.findStudentNoPermissions(num);
	}

}	
