package com.cqut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cqut.mapper.TeacherMapper;
import com.cqut.pojo.Student;
import com.cqut.pojo.Teacher;
import com.cqut.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{
	@Autowired
	@Qualifier("teacherMapper")
	private TeacherMapper tm;
	
	@Override
	public void updatePwdById(int id, String pwd) {
		tm.updatePwdById(id, pwd);
	}

	@Override
	public void updateIconById(int id, String iconName) {
		tm.updateIconById(id, iconName);
	}

	@Override
	public Teacher findByNum(String num) {
		return tm.findByNum(num);
	}

	@Override
	public List<Student> showStudents(int pageNum, int pageSize) {
		return tm.showStudents(pageNum, pageSize);
	}

	@Override
	public List<Student> findStudentByNum(String num) {
		return tm.findStudentByNum(num);
	}

	@Override
	public List<Student> findStudentByUname(int pageNum, int pageSize, String uname) {
		return tm.findStudentByUname(pageNum, pageSize, uname);
	}

	@Override
	public int countStudents() {
		return tm.countStudents();
	}

	@Override
	public String getRole(int rid) {
		return tm.getRole(rid);
	}

}
