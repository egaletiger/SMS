package com.cqut.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cqut.mapper.StudentMapper;
import com.cqut.pojo.Student;
import com.cqut.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	@Qualifier("studentMapper")
	StudentMapper sm;
	
	@Override
	public void updatePwdById(int id, String pwd) {
		sm.updatePwdById(id, pwd);
	}

	@Override
	public void updateIconById(int id, String iconName) {
		sm.updateIconById(id, iconName);
	}

	@Override
	public Student findByNum(String num) {
		return sm.findByNum(num);
	}

	@Override
	public String getRole(int rid) {
		return sm.getRole(rid);
	}

}
