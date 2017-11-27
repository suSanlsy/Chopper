package com.insigma.service;

import javax.annotation.Resource;

import com.insigma.dao.AdminInfoDao;
import com.insigma.model.AdminInfo;

import org.springframework.stereotype.Service;

@Service
public class AdminInfoService {

	@Resource
	private AdminInfoDao adminInfoDao;
	
	public void addAdmin(AdminInfo admin){
		if(admin!=null){
			adminInfoDao.addAdmin(admin);
		}
	}
	
	public String getPassByUsername(String username){
		if(username!=null && !"".equals(username)){
			return adminInfoDao.getPassByUsername(username);
		}
		return null;
	}
	
}
