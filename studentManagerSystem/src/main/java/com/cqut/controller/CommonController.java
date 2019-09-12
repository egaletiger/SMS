 package com.cqut.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cqut.pojo.Admin;
import com.cqut.pojo.Student;
import com.cqut.pojo.Teacher;
import com.cqut.pojo.User;
import com.cqut.service.AdminService;
import com.cqut.service.StudentService;
import com.cqut.service.TeacherService;
import com.cqut.shiro.MyToken;
import com.cqut.util.EncryptUtils;

@Controller
@RequestMapping("/common")
public class CommonController {
	private static final String User = null;

	@Autowired
	@Qualifier("adminServiceImpl")
	private AdminService as;
	
	@Autowired
	@Qualifier("studentServiceImpl")
	private StudentService ss;
	
	@Autowired
	@Qualifier("teacherServiceImpl")
	private TeacherService ts;
	
	
	@RequestMapping("/login")
	public String doLogin(HttpServletRequest req) throws AuthenticationException {
		String uname = req.getParameter("username");
		String pwd = req.getParameter("password");
		String identify = req.getParameter("identify");//前端传来的identify为admin,student,teacher
		Subject subject = SecurityUtils.getSubject();
		MyToken token = new MyToken(uname,pwd,identify);
		try {
          subject.login(token);
		}catch (UnknownAccountException e) {
          return "redirect:"+req.getContextPath()+"/view/public/error.jsp";
		}catch (IncorrectCredentialsException e) {
          return "redirect:"+req.getContextPath()+" /view/public/error.jsp";
		}
		
		if("admin".equals(identify)) {
			req.getSession().setAttribute("user", (Admin)subject.getPrincipal());
		}else if("teacher".equals(identify)){
			req.getSession().setAttribute("user", (Teacher)subject.getPrincipal());
		}else if("student".equals(identify)) {
			req.getSession().setAttribute("user", (Student)subject.getPrincipal());
		}
		return "redirect:"+req.getContextPath()+"/view/system/main.jsp";
	}
	
	
	@RequestMapping("/iconImgUpload")
	public void iconImgUpdate(@RequestParam(value="file") MultipartFile img,HttpServletRequest req,HttpServletResponse resp) {
		String originalFilename = img.getOriginalFilename();
		String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).trim().toLowerCase();
		//根据不同身份设置不同文件名称,并更新到数据库内
		String filename;
		User temp = (User)req.getSession().getAttribute("user");
		if(temp.getRid() == 1) {
			filename = "admin" + suffix;
			as.updateIconById(temp.getId(), filename);
		}else if(temp.getRid() == 2) {
			filename = "teacher" + suffix;
			ts.updateIconById(temp.getId(), filename);
		}else {
			filename = "student" + suffix;
			ss.updateIconById(temp.getId(), filename);
		}
		//将头像文件名称存储在相应的实体内
		temp.setIconName(filename);
	
		//将文件存入本地
		String realPath = req.getSession().getServletContext().getRealPath("/");
		String path = realPath +"image/userIcon/"+ filename ;
		File file = new File(path);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			
			img.transferTo(file);
			return;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/getIconImg")
	public void getIconImg(HttpServletRequest req, HttpServletResponse resp) {
		User temp = (User)req.getSession().getAttribute("user");
		String iconName = temp.getIconName();
		String suffix = iconName.substring(iconName.lastIndexOf(".")).toLowerCase().trim();
		if(iconName == null) 
			return;
		
		String realPath = req.getSession().getServletContext().getRealPath("/");
		String path = realPath +"image/userIcon/"+ iconName ;
		File file = new File(path);
		if(!file.exists()) 
			return;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(file));
			resp.setContentType("image/"+suffix);
			bos = new BufferedOutputStream(resp.getOutputStream());
			byte[] data = new byte[1024];
			int len;
			while((len = bis.read(data)) != -1) {
				bos.write(data,0,len);
			}
			
			bos.flush();
			bis.close();
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/upPwd")
	public void upPwd(HttpServletRequest req, HttpServletResponse resp) {
		String newPwd = req.getParameter("newPwd");
		User user = (User)req.getSession().getAttribute("user");
		String realPwd = EncryptUtils.md5Encrypt(newPwd, user.getNum());
		int rid = user.getRid();
		if(rid == 1) {
			as.updatePwdById(user.getId(), realPwd);
		}else if(rid == 2) {
			ts.updatePwdById(user.getId(), realPwd);
		}else if(rid == 3) {
			ss.updatePwdById(user.getId(), realPwd);
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req) {
		return req.getContextPath()+"/view/public/login.jsp";
	}
}
