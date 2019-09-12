package com.cqut.service;

import com.cqut.pojo.Student;

public interface StudentService extends UserService{
	public Student findByNum(String num);
}
