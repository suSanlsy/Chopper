package com.insigma.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.insigma.model.SfryInfo;
import com.insigma.service.AdminInfoService;
import com.insigma.service.SfryInfoService;
import com.insigma.util.MD5Util;

@Controller
public class LoginController {
	
	private static int MAXAGE = 50000;
	
	@Resource
	private AdminInfoService adminInfoService;
	
	@Resource
	private SfryInfoService sfryInfoService; 

	@RequestMapping("login")
	public String tologin(){
		return "login";
	}
	
	@RequestMapping("/search")
	public ModelAndView search(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		Cookie[] cs = request.getCookies();
		for(Cookie c : cs){
			if("xm".equals(c.getName())||"hkszd".equals(c.getName())
					||"wtlb".equals(c.getName())||"sfdd".equals(c.getName())
					||"djks".equals(c.getName())||"djjs".equals(c.getName())){
				c.setMaxAge(0);
			}
		}
		String xm = "";
		String hkszd = "";
		String sfdd = "";
		String wtlb = "";
		String djks = "";
		String djjs = "";
		Map<String, String> map = new HashMap<String, String>();
		if(request.getParameter("xm")!=null){
			xm = URLEncoder.encode(request.getParameter("xm"),"UTF-8");
			Cookie c= new Cookie("xm", xm);
			c.setMaxAge(MAXAGE);
			response.addCookie(c);
		}
		if(request.getParameter("hkszd")!=null){
			hkszd = URLEncoder.encode(request.getParameter("hkszd"),"UTF-8"); 
			Cookie c = new Cookie("hkszd", hkszd);
			c.setMaxAge(MAXAGE);
			response.addCookie(c);
		}
		if(request.getParameter("sfdd")!=null){
			sfdd = URLEncoder.encode(request.getParameter("sfdd"),"UTF-8"); 
			Cookie c = new Cookie("sfdd", sfdd);
			c.setMaxAge(MAXAGE);
			response.addCookie(c);
		}
		if(request.getParameter("wtlb")!=null){
			wtlb = URLEncoder.encode(request.getParameter("wtlb"),"UTF-8"); 
			Cookie c = new Cookie("wtlb", wtlb);
			c.setMaxAge(MAXAGE);
			response.addCookie(c);
		}
		if(request.getParameter("djks")!=null){
			djks = URLEncoder.encode(request.getParameter("djks"),"UTF-8"); 
			if(djks.matches("(\\d{4})-(\\d{1,2})-(\\d{1,2})")){
				Cookie c = new Cookie("djks", djks);
				c.setMaxAge(MAXAGE);
				response.addCookie(c);
			}else{
				Cookie c = new Cookie("djks", "");
				c.setMaxAge(MAXAGE);
				response.addCookie(c);
			}
		}
		if(request.getParameter("djjs")!=null){
			djjs = URLEncoder.encode(request.getParameter("djjs"),"UTF-8"); 
			if(djjs.matches("(\\d{4})-(\\d{1,2})-(\\d{1,2})")){
				Cookie c = new Cookie("djjs", djjs);
				c.setMaxAge(MAXAGE);
				response.addCookie(c);
			}else{
				Cookie c = new Cookie("djjs", "");
				c.setMaxAge(MAXAGE);
				response.addCookie(c);
			}
		}
		boolean xminput = URLEncoder.encode("请输入姓名", "UTF-8").equals(xm)?true:false;
		boolean hkszdinput = URLEncoder.encode("请输入户口所在地", "UTF-8").equals(hkszd)?true:false;
		boolean wtlbinput = URLEncoder.encode("请输入问题类别", "UTF-8").equals(wtlb)?true:false;
		boolean sfddinput = URLEncoder.encode("请输入上访（被查控）地点", "UTF-8").equals(sfdd)?true:false;
		boolean djksinput = URLEncoder.encode("请输入登记开始时间", "UTF-8").equals(djks)?true:false;
		boolean djjsinput = URLEncoder.encode("请输入登记结束时间", "UTF-8").equals(djjs)?true:false;
		List<SfryInfo> list = sfryInfoService.search(request, URLDecoder.decode(xm,"UTF-8"), URLDecoder.decode(hkszd,"UTF-8"), URLDecoder.decode(wtlb,"UTF-8"), URLDecoder.decode(sfdd,"UTF-8"), URLDecoder.decode(djks,"UTF-8"), URLDecoder.decode(djjs,"UTF-8"),0,1);
		List<SfryInfo> l = sfryInfoService.search(request, URLDecoder.decode(xm,"UTF-8"), URLDecoder.decode(hkszd,"UTF-8"), URLDecoder.decode(wtlb,"UTF-8"), URLDecoder.decode(sfdd,"UTF-8"), URLDecoder.decode(djks,"UTF-8"), URLDecoder.decode(djjs,"UTF-8"),0,0);
		JSONArray json = JSONArray.fromObject(list);
		if(xminput&&hkszdinput&&wtlbinput&&sfddinput&&djksinput&&djjsinput){
			map.put("pages", sfryInfoService.getPages()+"");//总页面
		}else{
			map.put("pages", (l.size()%10==0?l.size()/10:(l.size()/10+1))+"");//总页面
		}
		map.put("recent", 0+"");//当前页
		map.put("sfry", json.toString());
		return new ModelAndView("main",map);
	}
	
	@RequestMapping("logResult")
	public ModelAndView logResult(@RequestParam("username")String username,
			@RequestParam("password")String password,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		String msg = "";
		Map<String, String> map = new HashMap<String, String>();
		if(username==null||"".equals(username)||
				password==null||"".equals(password)){
			msg = "login";
			map.put("result", "fail");//传到登录页面
		}else{
			String passMD5 = null;
			if(adminInfoService.getPassByUsername(username)!=null&&!"".equals(adminInfoService.getPassByUsername(username))){
				passMD5 = adminInfoService.getPassByUsername(username);
			}
			MD5Util md5 = new MD5Util();
			if(passMD5.equals(md5.GetMD5Code(password))){
				msg = "main";
				map.put("pages", sfryInfoService.getPages()+"");//总页面
				map.put("recent", 0+"");//当前页
				
				Cookie cook = new Cookie("user",username);//用于前端判断登录用户
				cook.setMaxAge(MAXAGE);
				response.addCookie(cook);
				
				List<SfryInfo> list = sfryInfoService.getAllSfry(0);
				JSONArray json = JSONArray.fromObject(list);
				map.put("sfry", json.toString());
			}else{
				msg = "login";
				map.put("result", "fail");//传到登录页面
			}
		}
		return new ModelAndView(msg,map);
	}
	
	@RequestMapping("/previous")
	public ModelAndView previous(HttpServletRequest request) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		String xm = "";
		String hkszd = "";
		String sfdd = "";
		String wtlb = "";
		String djks = "";
		String djjs = "";
		Cookie[] cs = request.getCookies();
		for(Cookie c:cs){
			if("xm".equals(c.getName())){
				xm = URLDecoder.decode(c.getValue(),"utf-8");
			}else if("hkszd".equals(c.getName())){
				hkszd = URLDecoder.decode(c.getValue(),"utf-8");
			}else if("sfdd".equals(c.getName())){
				sfdd = URLDecoder.decode(c.getValue(),"utf-8");
			}else if("wtlb".equals(c.getName())){
				wtlb = URLDecoder.decode(c.getValue(),"utf-8");
			}else if("djks".equals(c.getName())){
				djks = URLDecoder.decode(c.getValue(),"utf-8");
			}else if("djjs".equals(c.getName())){
				djjs = URLDecoder.decode(c.getValue(),"utf-8");
			}
		}
		String recent = request.getParameter("recent");
		boolean xminput = "".equals(xm)||URLDecoder.decode(URLEncoder.encode("请输入姓名", "UTF-8"),"UTF-8").equals(xm)?true:false;
		boolean hkszdinput = "".equals(hkszd)||URLDecoder.decode(URLEncoder.encode("请输入户口所在地", "UTF-8"),"UTF-8").equals(hkszd)?true:false;
		boolean wtlbinput = "".equals(wtlb)||URLDecoder.decode(URLEncoder.encode("请输入问题类别", "UTF-8"),"UTF-8").equals(wtlb)?true:false;
		boolean sfddinput = "".equals(sfdd)||URLDecoder.decode(URLEncoder.encode("请输入上访（被查控）地点", "UTF-8"),"UTF-8").equals(sfdd)?true:false;
		boolean djksinput = "".equals(djks)||URLDecoder.decode(URLEncoder.encode("请输入登记开始时间", "UTF-8"),"UTF-8").equals(djks)?true:false;
		boolean djjsinput = "".equals(djjs)||URLDecoder.decode(URLEncoder.encode("请输入登记结束时间", "UTF-8"),"UTF-8").equals(djjs)?true:false;
		Map<String, String> map = new HashMap<String, String>();
		if(Integer.parseInt(recent)>0){
			List<SfryInfo> list = sfryInfoService.search(request, URLDecoder.decode(xm,"UTF-8"), URLDecoder.decode(hkszd,"UTF-8"), URLDecoder.decode(wtlb,"UTF-8"), URLDecoder.decode(sfdd,"UTF-8"), URLDecoder.decode(djks,"UTF-8"), URLDecoder.decode(djjs,"UTF-8"), (Integer.parseInt(recent)-1)*10,1);
			List<SfryInfo> l = sfryInfoService.search(request, URLDecoder.decode(xm,"UTF-8"), URLDecoder.decode(hkszd,"UTF-8"), URLDecoder.decode(wtlb,"UTF-8"), URLDecoder.decode(sfdd,"UTF-8"), URLDecoder.decode(djks,"UTF-8"), URLDecoder.decode(djjs,"UTF-8"), (Integer.parseInt(recent)-1)*10,0);
			JSONArray json = JSONArray.fromObject(list);
			if(xminput&&hkszdinput&&wtlbinput&&sfddinput&&djksinput&&djjsinput){
				map.put("pages", sfryInfoService.getPages()+"");//总页面
			}else{
				map.put("pages", (l.size()%10==0?l.size()/10:(l.size()/10+1))+"");//总页面
			}
			map.put("recent", Integer.parseInt(recent)-1+"");//当前页
			map.put("sfry", json.toString());
			return new ModelAndView("main",map);
		}else{
			List<SfryInfo> list = sfryInfoService.search(request, URLDecoder.decode(xm,"UTF-8"), URLDecoder.decode(hkszd,"UTF-8"), URLDecoder.decode(wtlb,"UTF-8"),URLDecoder.decode(sfdd,"UTF-8"), URLDecoder.decode(djks,"UTF-8"), URLDecoder.decode(djjs,"UTF-8"),  0,1);
			List<SfryInfo> l = sfryInfoService.search(request, URLDecoder.decode(xm,"UTF-8"), URLDecoder.decode(hkszd,"UTF-8"), URLDecoder.decode(wtlb,"UTF-8"),URLDecoder.decode(sfdd,"UTF-8"), URLDecoder.decode(djks,"UTF-8"), URLDecoder.decode(djjs,"UTF-8"),  (Integer.parseInt(recent))*10,0);
			if(xminput&&hkszdinput&&wtlbinput&&sfddinput&&djksinput&&djjsinput){
				map.put("pages", sfryInfoService.getPages()+"");//总页面
			}else{
				map.put("pages", (l.size()%10==0?l.size()/10:(l.size()/10+1))+"");
			}
			map.put("recent", Integer.parseInt(recent)+"");//当前页
			JSONArray json = JSONArray.fromObject(list);
			map.put("sfry", json.toString());
			return new ModelAndView("main",map);
		}
	}

	@RequestMapping("/next")
	public ModelAndView next(HttpServletRequest request) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		String xm = "";
		String hkszd = "";
		String sfdd = "";
		String wtlb = "";
		String djks = "";
		String djjs = "";
		Cookie[] cs = request.getCookies();
		for(Cookie c:cs){
			if("xm".equals(c.getName())){
				xm = URLDecoder.decode(c.getValue(),"utf-8");
			}else if("hkszd".equals(c.getName())){
				hkszd = URLDecoder.decode(c.getValue(),"utf-8");
			}else if("sfdd".equals(c.getName())){
				sfdd = URLDecoder.decode(c.getValue(),"utf-8");
			}else if("wtlb".equals(c.getName())){
				wtlb = URLDecoder.decode(c.getValue(),"utf-8");
			}else if("djks".equals(c.getName())){
				djks = URLDecoder.decode(c.getValue(),"utf-8");
			}else if("djjs".equals(c.getName())){
				djjs = URLDecoder.decode(c.getValue(),"utf-8");
			}
		}
		String recent = request.getParameter("recent");
		boolean xminput = "".equals(xm)||URLDecoder.decode(URLEncoder.encode("请输入姓名", "UTF-8"),"UTF-8").equals(xm)?true:false;
		boolean hkszdinput = "".equals(hkszd)||URLDecoder.decode(URLEncoder.encode("请输入户口所在地", "UTF-8"),"UTF-8").equals(hkszd)?true:false;
		boolean wtlbinput = "".equals(wtlb)||URLDecoder.decode(URLEncoder.encode("请输入问题类别", "UTF-8"),"UTF-8").equals(wtlb)?true:false;
		boolean sfddinput = "".equals(sfdd)||URLDecoder.decode(URLEncoder.encode("请输入上访（被查控）地点", "UTF-8"),"UTF-8").equals(sfdd)?true:false;
		boolean djksinput = "".equals(djks)||URLDecoder.decode(URLEncoder.encode("请输入登记开始时间", "UTF-8"),"UTF-8").equals(djks)?true:false;
		boolean djjsinput = "".equals(djjs)||URLDecoder.decode(URLEncoder.encode("请输入登记结束时间", "UTF-8"),"UTF-8").equals(djjs)?true:false;
		Map<String, String> map = new HashMap<String, String>();
		if(Integer.parseInt(recent)<sfryInfoService.getPages()-1){
			List<SfryInfo> list = sfryInfoService.search(request, URLDecoder.decode(xm,"UTF-8"), URLDecoder.decode(hkszd,"UTF-8"), URLDecoder.decode(wtlb,"UTF-8"), URLDecoder.decode(sfdd,"UTF-8"), URLDecoder.decode(djks,"UTF-8"), URLDecoder.decode(djjs,"UTF-8"), (Integer.parseInt(recent)+1)*10,1);
			List<SfryInfo> l = sfryInfoService.search(request, URLDecoder.decode(xm,"UTF-8"), URLDecoder.decode(hkszd,"UTF-8"), URLDecoder.decode(wtlb,"UTF-8"), URLDecoder.decode(sfdd,"UTF-8"), URLDecoder.decode(djks,"UTF-8"), URLDecoder.decode(djjs,"UTF-8"), (Integer.parseInt(recent)+1)*10,0);
			JSONArray json = new JSONArray();
			if(xminput&&hkszdinput&&wtlbinput&&sfddinput&&djksinput&&djjsinput){
				json = JSONArray.fromObject(list);
				map.put("pages", sfryInfoService.getPages()+"");//总页面
				map.put("recent", Integer.parseInt(recent)+1+"");//当前页
			}else{
				map.put("pages", (l.size()%10==0?l.size()/10:(l.size()/10+1))+"");//总页面
				json = JSONArray.fromObject(list);
				if(Integer.parseInt(recent)>=(l.size()%10==0?l.size()/10:(l.size()/10+1))-1){
					map.put("recent", (l.size()%10==0?l.size()/10:(l.size()/10+1))+"");
				}else{
					map.put("recent", Integer.parseInt(recent)+1+"");
				}
			}
			map.put("sfry", json.toString());
			return new ModelAndView("main",map);
		}else{
			List<SfryInfo> list = sfryInfoService.search(request, URLDecoder.decode(xm,"UTF-8"), URLDecoder.decode(hkszd,"UTF-8"), URLDecoder.decode(wtlb,"UTF-8"), URLDecoder.decode(sfdd,"UTF-8"), URLDecoder.decode(djks,"UTF-8"), URLDecoder.decode(djjs,"UTF-8"), Integer.parseInt(recent)*10,1);
			List<SfryInfo> l = sfryInfoService.search(request, URLDecoder.decode(xm,"UTF-8"), URLDecoder.decode(hkszd,"UTF-8"), URLDecoder.decode(wtlb,"UTF-8"), URLDecoder.decode(sfdd,"UTF-8"), URLDecoder.decode(djks,"UTF-8"), URLDecoder.decode(djjs,"UTF-8"), (Integer.parseInt(recent))*10,0);
			if(xminput&&hkszdinput&&wtlbinput&&sfddinput){
				map.put("pages", sfryInfoService.getPages()+"");//总页面
			}else{
				map.put("pages", (l.size()%10==0?l.size()/10:(l.size()/10+1))+"");
			}
			map.put("recent", Integer.parseInt(recent)+"");//当前页
			JSONArray json = JSONArray.fromObject(list);
			map.put("sfry", json.toString());
			return new ModelAndView("main",map);
		}
	}
	
	@RequestMapping("/turnTo")
	public ModelAndView turnTo(HttpServletRequest request) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		String xm = "";
		String hkszd = "";
		String sfdd = "";
		String wtlb = "";
		String djks = "";
		String djjs = "";
		Cookie[] cs = request.getCookies();
		for(Cookie c:cs){
			if("xm".equals(c.getName())){
				xm = URLDecoder.decode(c.getValue(),"utf-8");
			}else if("hkszd".equals(c.getName())){
				hkszd = URLDecoder.decode(c.getValue(),"utf-8");
			}else if("sfdd".equals(c.getName())){
				sfdd = URLDecoder.decode(c.getValue(),"utf-8");
			}else if("wtlb".equals(c.getName())){
				wtlb = URLDecoder.decode(c.getValue(),"utf-8");
			}else if("djks".equals(c.getName())){
				djks = URLDecoder.decode(c.getValue(),"utf-8");
			}else if("djjs".equals(c.getName())){
				djjs = URLDecoder.decode(c.getValue(),"utf-8");
			}
		}
		boolean xminput = "".equals(xm)||URLDecoder.decode(URLEncoder.encode("请输入姓名", "UTF-8"),"UTF-8").equals(xm)?true:false;
		boolean hkszdinput = "".equals(hkszd)||URLDecoder.decode(URLEncoder.encode("请输入户口所在地", "UTF-8"),"UTF-8").equals(hkszd)?true:false;
		boolean wtlbinput = "".equals(wtlb)||URLDecoder.decode(URLEncoder.encode("请输入问题类别", "UTF-8"),"UTF-8").equals(wtlb)?true:false;
		boolean sfddinput = "".equals(sfdd)||URLDecoder.decode(URLEncoder.encode("请输入上访（被查控）地点", "UTF-8"),"UTF-8").equals(sfdd)?true:false;
		boolean djksinput = "".equals(djks)||URLDecoder.decode(URLEncoder.encode("请输入登记开始时间", "UTF-8"),"UTF-8").equals(djks)?true:false;
		boolean djjsinput = "".equals(djjs)||URLDecoder.decode(URLEncoder.encode("请输入登记结束时间", "UTF-8"),"UTF-8").equals(djjs)?true:false;
		String index = request.getParameter("index");
		Map<String, String> map = new HashMap<String, String>();
		if(Integer.parseInt(index)>-1&&Integer.parseInt(index)<sfryInfoService.getPages()){
			map.put("recent", Integer.parseInt(index)+"");//当前页-1
			List<SfryInfo> list = sfryInfoService.search(request, URLDecoder.decode(xm,"UTF-8"), URLDecoder.decode(hkszd,"UTF-8"), URLDecoder.decode(wtlb,"UTF-8"), URLDecoder.decode(sfdd,"UTF-8"), URLDecoder.decode(djks,"UTF-8"), URLDecoder.decode(djjs,"UTF-8"), (Integer.parseInt(index))*10,1);
			List<SfryInfo> l = sfryInfoService.search(request, URLDecoder.decode(xm,"UTF-8"), URLDecoder.decode(hkszd,"UTF-8"), URLDecoder.decode(wtlb,"UTF-8"), URLDecoder.decode(sfdd,"UTF-8"), URLDecoder.decode(djks,"UTF-8"), URLDecoder.decode(djjs,"UTF-8"), (Integer.parseInt(index))*10,0);
			JSONArray json = JSONArray.fromObject(list);
			if(xminput&&hkszdinput&&wtlbinput&&sfddinput&&djksinput&&djjsinput){
				map.put("pages", sfryInfoService.getPages()+"");//总页面
			}else{
				map.put("pages", (l.size()%10==0?l.size()/10:(l.size()/10+1))+"");//总页面
			}
			map.put("sfry", json.toString());
			return new ModelAndView("main",map);
		}
		map.put("pages", sfryInfoService.getPages()+"");//总页面
		map.put("recent", "0");//当前页-1
		List<SfryInfo> list = sfryInfoService.getAllSfry(0);
		JSONArray json = JSONArray.fromObject(list);
		map.put("sfry", json.toString());
		return new ModelAndView("main",map);
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response){
		Cookie[] cookies = request.getCookies();
		for(Cookie c:cookies){
			if("xm".equals(c.getName())||"hkszd".equals(c.getName())
					||"wtlb".equals(c.getName())||"sfdd".equals(c.getName())
					||"djks".equals(c.getName())||"djjs".equals(c.getName())){
				c.setMaxAge(0);
			}
		}
		response.reset();
		return "login";
	}
	
	@RequestMapping("/main")
	public ModelAndView home(HttpServletRequest request){
		String user = request.getParameter("username");
		if(adminInfoService.getPassByUsername(user)!=null){
			Map<String, String> map = new HashMap<String, String>();
			map.put("pages", sfryInfoService.getPages()+"");//总页面
			map.put("recent", 0+"");//当前页
			
			List<SfryInfo> list = sfryInfoService.getAllSfry(0);
			JSONArray json = JSONArray.fromObject(list);
			map.put("sfry", json.toString());
			return new ModelAndView("main",map);
		}
		return new ModelAndView("login");	
	}
}
