package com.insigma.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.insigma.model.SfryInfo;
import com.insigma.service.SfryInfoService;

@Controller
public class SfryController {
	
	private int MAXAGE=50000;

	@Resource
	private SfryInfoService sfryInfoService;

	@RequestMapping("/addSfry")
	public String addSfry() {
		return "addSfry";
	}

	@RequestMapping("/addSfryjg")
	public ModelAndView addSfryjg(HttpServletRequest request)
			throws UnsupportedEncodingException, ParseException {
		request.setCharacterEncoding("UTF-8");
		String xm = request.getParameter("xm");
		String xb = request.getParameter("xb");
		String nl = request.getParameter("nl");
		String hkszd = request.getParameter("hkszd");
		String wtlb = request.getParameter("wtlb");
		String sfdd = request.getParameter("sfdd");
		String djr = request.getParameter("djr");
		String djsj = request.getParameter("djsj");
		String bz = request.getParameter("bz");
		String msg = "";
		Map<String, String> map = new HashMap<String, String>();
		if (xm == null || "".equals(xm) || hkszd == null || "".equals(hkszd)
				|| wtlb == null || "".equals(wtlb) || sfdd == null
				|| "".equals(sfdd)|| djr == null || "".equals(djr)
				|| djsj == null || "".equals(djsj)) {
			msg = "addSfry";
			map.put("result", "fail");
		} else {
			SfryInfo sfry = new SfryInfo();
			sfry.setXm(URLDecoder.decode(xm,"UTF-8"));
			if (xb != null && !"".equals(xb)) {
				sfry.setXb(URLDecoder.decode(xb,"UTF-8"));
			}
			if (nl != null && !"".equals(nl.trim())) {
				if (nl.matches("[0-9]+")) {
					sfry.setNl(Integer.parseInt(nl));
				} else {
					msg = "addSfry";
					map.put("result", "nlerror");
					return new ModelAndView(msg, map);
				}
			}
			sfry.setHkszd(URLDecoder.decode(hkszd,"UTF-8"));
			sfry.setWtlb(URLDecoder.decode(wtlb,"UTF-8"));
			sfry.setSfdd(URLDecoder.decode(sfdd,"UTF-8"));
			sfry.setDjr(URLDecoder.decode(djr,"UTF-8"));
			sfry.setDjsj(djsj);
			if (bz != null && !"".equals(bz)) {
				sfry.setBz(URLDecoder.decode(bz,"UTF-8"));
			}
			sfryInfoService.addSfry(sfry);
			msg = "main";
			map.put("result", "addsuccess");
			map.put("pages", sfryInfoService.getPages()+"");//总页面
			map.put("recent", "0");//当前页
			List<SfryInfo> list = sfryInfoService.getAllSfry(0);
			JSONArray json = JSONArray.fromObject(list);
			map.put("sfry", json.toString());
		}
		return new ModelAndView(msg, map);
	}
	
	@RequestMapping("/addSfrys")
	public String addSfrys(){
		return "addSfrys";
	}
	
	@RequestMapping("/addSfryResult")
	public ModelAndView addSfryResult(HttpServletRequest request,
			@RequestParam(value = "file", required = false) MultipartFile file)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		
		List<SfryInfo> sfrys = new ArrayList<SfryInfo>();
		List<String> strings = new ArrayList<String>();
		Map<String, String> map = new HashMap<String,String>();
		try {
			InputStream inp = file.getInputStream();
			XSSFWorkbook work = new XSSFWorkbook(inp);
			inp.close();
			XSSFSheet sheet = work.getSheetAt(0);
			for (int cell = 1; cell < sheet.getLastRowNum() + 1; cell++) {
				XSSFRow xrow = sheet.getRow(cell);
				for (int row = 0; row < xrow.getLastCellNum(); row++) {
					if (xrow.getLastCellNum() > 7) {
						return new ModelAndView("addFail");
					}
					XSSFCell xcell = xrow.getCell(row);
					if (xcell != null) {
						xcell.setCellType(XSSFCell.CELL_TYPE_STRING);
						strings.add(xcell.getStringCellValue());
					} else {
						strings.add("");
					}
				}
			}
			for (int row = 0; row < sheet.getLastRowNum(); row++) {
				SfryInfo sfry = new SfryInfo();
				for (int cell = 0; cell < sheet.getRow(row + 1)
						.getLastCellNum(); cell++) {
					switch (cell) {
					case 0:
						sfry.setXm(strings.get(cell + row * 7));
						break;
					case 1:
						sfry.setXb(strings.get(cell + row * 7));
						break;
					case 2:
						sfry.setNl(Integer.parseInt(strings.get(cell + row * 7)));
						break;
					case 3:
						sfry.setHkszd(strings.get(cell + row
								* 7));
						break;
					case 4:
						sfry.setWtlb(strings.get(cell + row
								* 7));
						break;
					case 5:
						sfry.setSfdd(strings
								.get(cell + row * 7));
						break;
					case 6:
						sfry.setBz(strings
								.get(cell + row * 7));
						break;
					}
				}
				Cookie[] cs = request.getCookies();
				String user = "";
				for(Cookie c:cs){
					if("user".equals(c.getName())){
						user = c.getValue();
					}
				}
				sfry.setDjr(user);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				sfry.setDjsj(sdf.format(new Date()));
				sfrys.add(sfry);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "fail");
			return new ModelAndView("addsfrys",map);
		}
		for(SfryInfo sfry:sfrys){
			sfryInfoService.addSfry(sfry);
		}
		map.put("result", "addsuccess");
		map.put("pages", sfryInfoService.getPages()+"");//总页面
		map.put("recent", "0");//当前页-1
		List<SfryInfo> list = sfryInfoService.getAllSfry(0);
		JSONArray json = JSONArray.fromObject(list);
		map.put("sfry", json.toString());
		return new ModelAndView("main",map);
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		String xh = request.getParameter("xh");
		String recent = request.getParameter("recent");
		Map<String, String> map = new HashMap<String, String>();
		List<SfryInfo> sfrys = sfryInfoService.getByXh(Integer.parseInt(xh));
		if(sfrys!=null && sfrys.size()>0){
			int total = sfryInfoService.delete(Integer.parseInt(xh));
			if(total>1){
				map.put("result", "error");
				map.put("pages", sfryInfoService.getPages()+"");//总页面
				map.put("recent", "0");//当前页
				map.put("result", "delfail");
				List<SfryInfo> list = sfryInfoService.getAllSfry(0);
				JSONArray json = JSONArray.fromObject(list);
				map.put("sfry", json.toString());
				return new ModelAndView("main",map);
			}
			map.put("recent", Integer.parseInt(recent)+"");//当前页
			map.put("result", "delsuccess");
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
			List<SfryInfo> list = sfryInfoService.search(request, URLDecoder.decode(xm,"UTF-8"), URLDecoder.decode(hkszd,"UTF-8"), URLDecoder.decode(wtlb,"UTF-8"), URLDecoder.decode(sfdd,"UTF-8"),  URLDecoder.decode(djks,"UTF-8"), URLDecoder.decode(djjs,"UTF-8"), (Integer.parseInt(recent))*10,1);
			List<SfryInfo> l = sfryInfoService.search(request, URLDecoder.decode(xm,"UTF-8"), URLDecoder.decode(hkszd,"UTF-8"), URLDecoder.decode(wtlb,"UTF-8"), URLDecoder.decode(sfdd,"UTF-8"),  URLDecoder.decode(djks,"UTF-8"), URLDecoder.decode(djjs,"UTF-8"), (Integer.parseInt(recent))*10,0);
			JSONArray json = JSONArray.fromObject(list);
			if(xminput&&hkszdinput&&wtlbinput&&sfddinput&&djksinput&&djjsinput){
				map.put("pages", sfryInfoService.getPages()+"");//总页面
			}else{
				map.put("pages", (l.size()%10==0?l.size()/10:(l.size()/10+1))+"");//总页面
			}
			map.put("sfry", json.toString());
			return new ModelAndView("main",map);
		}else{
			String msg = "main";
			map.put("pages", sfryInfoService.getPages()+"");//总页面
			map.put("recent", 0+"");//当前页
			
			List<SfryInfo> list = sfryInfoService.getAllSfry(0);
			JSONArray json = JSONArray.fromObject(list);
			map.put("sfry", json.toString());
			return new ModelAndView(msg,map);
		}
	}
	
	@RequestMapping("/loadOut")
	public String loadOut(){
		return "loadOut";
	}

	/**
	 * 导出数据
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/loadResult")
	public void loadResult(HttpServletRequest request,HttpServletResponse response) throws IOException{

	        // 创建excel
	        XSSFWorkbook wb = new XSSFWorkbook();

	        // 创建sheet
	        XSSFSheet sheet = wb.createSheet("涉访人员登记表");

	        // 创建一行
	        XSSFRow rowTitle = sheet.createRow(0);

	        // 创建标题栏样式
	        XSSFCellStyle styleTitle = wb.createCellStyle();
	        styleTitle.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 居中
	        XSSFFont fontTitle = wb.createFont();
	        // 宋体加粗
	        fontTitle.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
	        fontTitle.setFontName("宋体");
	        fontTitle.setFontHeight((short) 200);
	        styleTitle.setFont(fontTitle);

	        // 在行上创建1列
	        XSSFCell cellTitle = rowTitle.createCell(0);

	        // 列标题及样式
	        cellTitle.setCellValue("序号");
	        cellTitle.setCellStyle(styleTitle);

	        // 在行上创建2列
	        cellTitle = rowTitle.createCell(1);
	        cellTitle.setCellValue("姓名");
	        cellTitle.setCellStyle(styleTitle);

	        cellTitle = rowTitle.createCell(2);
	        cellTitle.setCellValue("性别");
	        cellTitle.setCellStyle(styleTitle);
	        
	        cellTitle = rowTitle.createCell(3);
	        cellTitle.setCellValue("年龄");
	        cellTitle.setCellStyle(styleTitle);
	        
	        cellTitle = rowTitle.createCell(4);
	        cellTitle.setCellValue("户口所在地");
	        cellTitle.setCellStyle(styleTitle);
	        
	        cellTitle = rowTitle.createCell(5);
	        cellTitle.setCellValue("问题类别");
	        cellTitle.setCellStyle(styleTitle);
	        
	        cellTitle = rowTitle.createCell(6);
	        cellTitle.setCellValue("上访（被查控）地点");
	        cellTitle.setCellStyle(styleTitle);

	        cellTitle = rowTitle.createCell(7);
	        cellTitle.setCellValue("备注");
	        cellTitle.setCellStyle(styleTitle);
	        
	        XSSFCellStyle styleCenter = wb.createCellStyle();
	        styleCenter.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 居中

	        // 取数据
	        List<SfryInfo> data = getData();

	        for (int i = 0; i < data.size(); i++) {

	        	SfryInfo sfry = data.get(i);
	            XSSFRow row = sheet.createRow(i + 1);

	            XSSFCell cell = row.createCell(0);
	            cell.setCellValue(sfry.getXh());
	            cell.setCellStyle(styleCenter);

	            cell = row.createCell(1);
	            cell.setCellValue(sfry.getXm());
	            cell.setCellStyle(styleCenter);

	            cell = row.createCell(2);
	            cell.setCellValue(sfry.getXb());
	            cell.setCellStyle(styleCenter);

	            cell = row.createCell(3);
	            cell.setCellValue(sfry.getNl());
	            cell.setCellStyle(styleCenter);

	            cell = row.createCell(4);
	            cell.setCellValue(sfry.getHkszd());
	            cell.setCellStyle(styleCenter);

	            cell = row.createCell(5);
	            cell.setCellValue(sfry.getWtlb());
	            cell.setCellStyle(styleCenter);

	            cell = row.createCell(6);
	            cell.setCellValue(sfry.getSfdd());
	            cell.setCellStyle(styleCenter);

	            cell = row.createCell(7);
	            cell.setCellValue(sfry.getBz());
	            cell.setCellStyle(styleCenter);
	        }

	        String filename = "涉访人员.xlsx";
	    	response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
	        OutputStream fout = response.getOutputStream();
	        wb.write(fout);
	        fout.close();
	        wb.close();

	}

	/**
	 * 获取涉访人员信息
	 * @return
	 */
	private List<SfryInfo> getData() {
	    List<SfryInfo> data = new ArrayList<SfryInfo>();
	    for(int i=0;i<sfryInfoService.getPages();i++){
	    	List<SfryInfo> list = sfryInfoService.getAllSfry(i*10);
	    	for(SfryInfo sfry:list){
	    		data.add(sfry);
	    	}
	    }
	    return data;
	}

}
