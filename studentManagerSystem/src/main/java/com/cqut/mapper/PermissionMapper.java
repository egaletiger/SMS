package com.cqut.mapper;

import java.util.List;

import com.cqut.pojo.Permission;

public interface PermissionMapper {
	public List<Permission> findPermissions(int rid);
}
