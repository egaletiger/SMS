package com.cqut.mapper;

import org.apache.ibatis.annotations.Param;

public interface UserMapper{
	public void updatePwdById(@Param("id")int id, @Param("pwd")String pwd);
	public void updateIconById(@Param("id")int id, @Param("iconName")String iconName);
	public String getRole(int rid);
}
