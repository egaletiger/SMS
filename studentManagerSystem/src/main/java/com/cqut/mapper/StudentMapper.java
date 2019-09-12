package com.cqut.mapper;


import com.cqut.pojo.Student;

public interface StudentMapper extends UserMapper{
	public Student findByNum(String num);
}
