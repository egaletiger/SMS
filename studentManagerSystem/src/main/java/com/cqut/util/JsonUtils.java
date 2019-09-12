package com.cqut.util;

import java.util.List;

import com.cqut.pojo.Student;
import com.cqut.pojo.Teacher;



public class JsonUtils {
	public static String studentInfoToJson(List<Student> teachers,int total) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":");
		sb.append(total);
		sb.append(",\"rows\":[");
		for(int i = 0; i < teachers.size(); i++) {
			sb.append("{\"id\":" + teachers.get(i).getId( ) + ",");
			sb.append("\"box\":\"<input type='checkbox'>\",");
			sb.append("\"workNum\":\"" + teachers.get(i).getNum() + "\",");
			sb.append("\"uname\":\"" + teachers.get(i).getUname() + "\",");
			sb.append("\"institute\":\"" + teachers.get(i).getInstitute() + "\"," );
			sb.append("\"operation\":\"<a onclick='del(this)' href='javascript:void(0)'>删除</a>&nbsp;&nbsp;<a onclick='edit(this)' href='javascript:void(0)'>编辑</a>&nbsp;&nbsp;<a name='save' onclick='save(this)' href='javascript:void(0)' hidden=true>保存</a>\"}");
			if(i != teachers.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]}");
		
		return sb.toString();
	}
	
	public static String teacherInfoToJson(List<Teacher> teachers,int total) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":");
		sb.append(total);
		sb.append(",\"rows\":[");
		for(int i = 0; i < teachers.size(); i++) {
			sb.append("{\"id\":" + teachers.get(i).getId( ) + ",");
			sb.append("\"box\":\"<input type='checkbox'>\",");
			sb.append("\"workNum\":\"" + teachers.get(i).getNum() + "\",");
			sb.append("\"uname\":\"" + teachers.get(i).getUname() + "\",");
			sb.append("\"institute\":\"" + teachers.get(i).getInstitute() + "\"," );
			sb.append("\"operation\":\"<a onclick='del(this)' href='javascript:void(0)'>删除</a>&nbsp;&nbsp;<a onclick='edit(this)' href='javascript:void(0)'>编辑</a>&nbsp;&nbsp;<a name='save' onclick='save(this)' href='javascript:void(0)' hidden=true>保存</a>\"}");
			if(i != teachers.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]}");
		
		return sb.toString();
	}
}
