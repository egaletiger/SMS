package com.cqut.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqut.pojo.Student;
import com.cqut.service.TeacherService;
import com.cqut.util.JsonUtils;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	@Autowired
	@Qualifier("teacherServiceImpl")
	private TeacherService ts;
	
	@RequestMapping("/showStudents")
    public void showStudents(HttpServletRequest req, HttpServletResponse resp) {
    	int pageNum = Integer.parseInt(req.getParameter("page")); 
    	int pageSize = Integer.parseInt(req.getParameter("rows"));
    	List<Student> students = ts.showStudents((pageNum-1)*pageSize, pageSize);
    	int total = ts.countStudents();

    	String json = JsonUtils.studentInfoToJson(students, total);
    
    	resp.setContentType("application/json;charset=utf-8");
    	try {
			resp.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return;
    }
}
