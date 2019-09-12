package com.cqut.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqut.pojo.Student;
import com.cqut.pojo.Teacher;
import com.cqut.service.AdminService;
import com.cqut.service.StudentService;
import com.cqut.service.TeacherService;
import com.cqut.util.EncryptUtils;
import com.cqut.util.JsonUtils;
import com.cqut.util.VCodeImgUtils;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	@Qualifier("adminServiceImpl")
	private AdminService as;
	
	@Autowired
	@Qualifier("studentServiceImpl")
	private StudentService ss;
	
	@Autowired
	@Qualifier("teacherServiceImpl")
	private TeacherService ts;
	
    /*注册处理*/
    @RequestMapping("/register")
    public String register(HttpServletRequest req, HttpServletResponse resp){
        try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        String pwd = req.getParameter("pwd");
        String uname = req.getParameter("username");
        String num = req.getParameter("workNum");
        String institute = req.getParameter("institute");
        String identify = req.getParameter("identify");
        //加密
        String realPwd = EncryptUtils.md5Encrypt(pwd, num);
        if("teacher".equals(identify)){
        	//检查工号是否重复
        	String msg = as.findTeacherNoPermissions(num);
        	if(msg != null) {
        		return "no";
        	}
        	Teacher t = new Teacher();
        	t.setNum(num);
        	t.setPwd(realPwd);
        	t.setUname(uname);
        	t.setInstitute(institute);
        	as.addTeacher(t);
        }else if("student".equals(identify)){
        	//检查学号是否重复
        	String msg = as.findStudentNoPermissions(num);
        	if(msg != null) {
        		return "no";
        	}
        	Student s = new Student();
        	s.setNum(num);
        	s.setPwd(realPwd);
        	s.setUname(uname);
        	s.setInstitute(institute);
        	as.addStudent(s);
        }
        return "yes";
    }

    /*获取验证码*/
    @RequestMapping("/getVCodeImage")
    public void getVCodeImage(HttpServletRequest req, HttpServletResponse resp){
        Map<String, Object> map = VCodeImgUtils.getVCodeMap();
        //验证码图片
        BufferedImage vcodeImg = (BufferedImage)map.get("vcodeImg");
        //验证码
        String vcode = (String)map.get("vcode");
        //储存验证码
        req.getSession().setAttribute("vcode",vcode);
        //发送验证码图片
        try {
            ServletOutputStream out = resp.getOutputStream();
            ImageIO.write(vcodeImg,"JPEG",out);
            out.flush(); 
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }
    
    @RequestMapping("/showStudents")
    public void showStudents(HttpServletRequest req, HttpServletResponse resp) {
    	int pageNum = Integer.parseInt(req.getParameter("page")); 
    	int pageSize = Integer.parseInt(req.getParameter("rows"));
    	List<Student> students = as.showStudents((pageNum-1)*pageSize, pageSize);
    	int total = as.countStudents();

    	String json = JsonUtils.studentInfoToJson(students, total);
    
    	resp.setContentType("application/json;charset=utf-8");
    	try {
			resp.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return;
    }
    
    //根据学号查找学生
    @RequestMapping("/selStudentsByNum")
    public void selStudentsByNum(HttpServletRequest req, HttpServletResponse resp) {
    	int pageNum = Integer.parseInt(req.getParameter("page")); 
    	int pageSize = Integer.parseInt(req.getParameter("rows"));
    	String num = req.getParameter("num");
    	List<Student> students = as.findStudentByNum(pageNum, pageSize, num);
    	
    	int total = as.countStudentsByNum(num);
    	String json = JsonUtils.studentInfoToJson(students, total);
    	
    	resp.setContentType("application/json;charset=utf-8");
    	try {
			resp.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return;
    }
    
    //根据姓名查找学生
    @RequestMapping("/selStudentsByUname")
    public void selStudentsByUname(HttpServletRequest req, HttpServletResponse resp) {
    	//设置请求编码格式
    	try {
	    	int pageNum = Integer.parseInt(req.getParameter("page")); 
	    	int pageSize = Integer.parseInt(req.getParameter("rows"));
	    	String uname = new String(req.getParameter("uname").getBytes("ISO-8859-1"),"UTF-8");
			List<Student> students = as.findStudentByUname(pageNum, pageSize, uname);
			int total = as.countStudentsByUname(uname);
			String json = JsonUtils.studentInfoToJson(students, total);
	    	resp.setContentType("application/json;charset=utf-8");
	    	resp.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return;
    }
    
    
    //执行删除单个学生的操作
    @RequestMapping("/delStudent")
    public void delStudent(HttpServletRequest req) {	
		int id = Integer.parseInt(req.getParameter("id"));
		as.deleteStudent(id);
		return;
    }
    //删除多个学生的操作
    @RequestMapping("/delStudents")
    public void delStudents(String[] arr) {	
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < arr.length; i++) {
			list.add(Integer.parseInt(arr[i]));
		}
		as.deleteStudents(list);
		return;
    }
    
    //修改学生信息的操作
    @RequestMapping("/updStudent")
    public void updStudent(Student student) {
    	as.updateStudentInfo(student);
    	return;
    }
    
    //查找老师的操作
    @RequestMapping("/showTeachers")
    public void showTeachers(HttpServletRequest req, HttpServletResponse resp) {
    	int pageNum = Integer.parseInt(req.getParameter("page")); 
    	int pageSize = Integer.parseInt(req.getParameter("rows"));
    	List<Teacher> teachers = as.showTeachers((pageNum-1)*pageSize, pageSize);
    	int total = as.countTeachers();

		String json = JsonUtils.teacherInfoToJson(teachers, total);
    
    	resp.setContentType("application/json;charset=utf-8");
    	try {
			resp.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return;
    }
    
    
    
    //执行删除单个老师的操作
    @RequestMapping("/delTeacher")
    public void delTeacher(HttpServletRequest req) {	
		int id = Integer.parseInt(req.getParameter("id"));
		as.deleteTeacher(id);
    	return;
    }
    
    //删除多个老师的操作
    @RequestMapping("/delTeachers")
    public void delTeachers(String[] arr) {	
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < arr.length; i++) {
			list.add(Integer.parseInt(arr[i]));
		}
		as.deleteTeachers(list);
		return;
    }
    
    //根据学号查找老师
    @RequestMapping("/selTeachersByNum")
    public void selTeachersByNum(HttpServletRequest req, HttpServletResponse resp) {
    	int pageNum = Integer.parseInt(req.getParameter("page")); 
    	int pageSize = Integer.parseInt(req.getParameter("rows"));
    	String num = req.getParameter("num");
    	List<Teacher> teachers = as.findTeacherByNum(pageNum, pageSize, num);
    	
    	int total = as.countStudentsByNum(num);
    	String json = JsonUtils.teacherInfoToJson(teachers, total);
    	
    	resp.setContentType("application/json;charset=utf-8");
    	try {
			resp.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return;
    }
    
    //根据姓名查找老师
    @RequestMapping("/selTeachersByUname")
    public void selTeachersByUname(HttpServletRequest req, HttpServletResponse resp) {
    	try {
    		int pageNum = Integer.parseInt(req.getParameter("page")); 
	    	int pageSize = Integer.parseInt(req.getParameter("rows"));
	    	String uname = new String(req.getParameter("uname").getBytes("ISO-8859-1"),"UTF-8");
    	
	    	List<Teacher> teachers = as.findTeacherByUname(pageNum, pageSize, uname);
	    	int total = as.countStudentsByUname(uname);
	    	
			String json = JsonUtils.teacherInfoToJson(teachers, total);
			
	    	resp.setContentType("application/json;charset=utf-8");
			resp.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return;
    }
    
    //修改老师信息的操作
    @RequestMapping("/updTeacher")
    public void updTeacher(Teacher teacher) {
    	as.updateTeacherInfo(teacher);
    	return;
    }
    
}
