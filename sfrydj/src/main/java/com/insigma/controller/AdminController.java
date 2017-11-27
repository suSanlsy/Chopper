package com.insigma.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.insigma.model.AdminInfo;
import com.insigma.service.AdminInfoService;
import com.insigma.util.MD5Util;

@Controller
public class AdminController {

	@Resource
	private AdminInfoService adminInfoService;
	
	@RequestMapping("/adResult")
	public ModelAndView addAdmin(@RequestParam("username")String username,
			@RequestParam("realname")String realname,@RequestParam("pass")String pass,
			@RequestParam("fzr")String fzr){
		String msg = "";
		Map<String, String> map = new HashMap<String,String>();
		if(username==null||"".equals(username)
				||pass==null||"".equals(pass)){
			msg += "addadmin";
			map.put("result", "fail");
		}else{
			if(realname==null){
				realname = "";
			}
			MD5Util md5 = new MD5Util();
			String passw = md5.GetMD5Code(pass);
			AdminInfo admin = new AdminInfo();
			admin.setUsername(username);
			admin.setRealname(realname);
			admin.setPass(passw);
			admin.setFzr(fzr);
			adminInfoService.addAdmin(admin);
			map.put("result", "success");
			msg = "login";
		}
		return new ModelAndView(msg,map);
	}

	@RequestMapping("/addadmin")
	public String addAdmin(){
		return "addadmin";
	}
	
}
