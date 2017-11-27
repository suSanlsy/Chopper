package com.insigma.dao;

import org.apache.ibatis.annotations.Param;

import com.insigma.model.AdminInfo;

public interface AdminInfoDao {

	public void addAdmin(@Param("admin")AdminInfo adminInfo);
	
	public String getPassByUsername(@Param("username")String username);
	
}
