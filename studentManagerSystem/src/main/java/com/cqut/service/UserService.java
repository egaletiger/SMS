package com.cqut.service;

public interface UserService{
	public void updatePwdById(int id, String pwd);
	public void updateIconById(int id, String iconName);
	public String getRole(int rid);
}
