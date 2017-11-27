package com.insigma.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.insigma.dao.SfryInfoDao;
import com.insigma.model.SfryInfo;

@Service
public class SfryInfoService {

	@Resource
	private SfryInfoDao sfryInfoDao;
	
	public List<SfryInfo> getByXh(int xh){
		return sfryInfoDao.getByXh(xh);
	}
	
	public List<SfryInfo> getAllSfry(int offset){
		if(offset>-1){
			return sfryInfoDao.getAllSfry(offset);
		}
		return null;
	}
	
	public void addSfry(SfryInfo sfry){
		if(sfry!=null){
			sfryInfoDao.addSfry(sfry);
		}
	}
	
	public int delete(int xh){
		if(xh!=0){
			return sfryInfoDao.delete(xh);
		}
		return -1;
	}
	
	public int getCount(){
		return sfryInfoDao.getCount();
	}
	
	public int getPages(){
		return sfryInfoDao.getCount()%10==0?sfryInfoDao.getCount()/10:sfryInfoDao.getCount()/10+1;
	}
	
	public List<SfryInfo> search(HttpServletRequest request,String xm,String hkszd,String wtlb,String sfdd,String djks,String djjs,int offset,int flag)throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		List<SfryInfo> list = new ArrayList<SfryInfo>();
		boolean xminput = !"".equals(xm)&&!URLDecoder.decode(URLEncoder.encode("请输入姓名", "UTF-8"),"UTF-8").equals(xm)?true:false;
		boolean hkszdinput = !"".equals(hkszd)&&!URLDecoder.decode(URLEncoder.encode("请输入户口所在地", "UTF-8"),"UTF-8").equals(hkszd)?true:false;
		boolean wtlbinput = !"".equals(wtlb)&&!URLDecoder.decode(URLEncoder.encode("请输入问题类别", "UTF-8"),"UTF-8").equals(wtlb)?true:false;
		boolean sfddinput = !"".equals(sfdd)&&!URLDecoder.decode(URLEncoder.encode("请输入上访（被查控）地点", "UTF-8"),"UTF-8").equals(sfdd)?true:false;
		boolean djksinput = !"".equals(djks)&&!URLDecoder.decode(URLEncoder.encode("请输入登记开始时间", "UTF-8"),"UTF-8").equals(djks)?true:false;
		boolean djjsinput = !"".equals(djjs)&&!URLDecoder.decode(URLEncoder.encode("请输入登记结束时间", "UTF-8"),"UTF-8").equals(djjs)?true:false;
		if(!djksinput&&!djjsinput){
			if(!xminput&&!hkszdinput&&!wtlbinput&&!sfddinput){
				list = sfryInfoDao.getAllSfry(offset);
				Cookie[] cs = request.getCookies();
				if(cs!=null){
					for(Cookie c : cs){
						if("xm".equals(c.getName())||"hkszd".equals(c.getName())
								||"wtlb".equals(c.getName())||"sfdd".equals(c.getName())
								||"djks".equals(c.getName())||"djjs".equals(c.getName())){
							c.setMaxAge(0);
						}
					}
				}
			}else if(xminput&&!hkszdinput&&!wtlbinput&&!sfddinput){
				list = sfryInfoDao.getAllSfryByName("%"+xm+"%", offset,flag);
			}else if(!xminput&&hkszdinput&&!wtlbinput&&!sfddinput){
				list = sfryInfoDao.getAllSfryByHkszd("%"+hkszd+"%", offset,flag);
			}else if(!xminput&&!hkszdinput&&wtlbinput&&!sfddinput){
				list = sfryInfoDao.getAllSfryByWtlb("%"+wtlb+"%", offset,flag);
			}else if(!xminput&&!hkszdinput&&!wtlbinput&&sfddinput){
				list = sfryInfoDao.getAllSfryBySfdd("%"+sfdd+"%", offset,flag);
			}else if(xminput&&hkszdinput&&!wtlbinput&&!sfddinput){
				list = sfryInfoDao.getAllSfryByNameAndHkszd("%"+xm+"%", "%"+hkszd+"%", offset,flag);
			}else if(xminput&&!hkszdinput&&wtlbinput&&!sfddinput){
				list = sfryInfoDao.getAllSfryByNameAndWtlb("%"+xm+"%", "%"+wtlb+"%", offset,flag);
			}else if(xminput&&!hkszdinput&&!wtlbinput&&sfddinput){
				list = sfryInfoDao.getAllSfryByNameAndSfdd("%"+xm+"%", "%"+sfdd+"%", offset,flag);
			}else if(!xminput&&hkszdinput&&wtlbinput&&!sfddinput){
				list = sfryInfoDao.getAllSfryByHkszdAndWtlb("%"+hkszd+"%", "%"+wtlb+"%", offset,flag);
			}else if(!xminput&&hkszdinput&&!wtlbinput&&sfddinput){
				list = sfryInfoDao.getAllSfryByHkszdAndSfdd("%"+hkszd+"%", "%"+sfdd+"%", offset,flag);
			}else if(!xminput&&!hkszdinput&&wtlbinput&&sfddinput){
				list = sfryInfoDao.getAllSfryByWtlbAndSfdd("%"+wtlb+"%", "%"+sfdd+"%", offset,flag);
			}else if(xminput&&hkszdinput&&wtlbinput&&!sfddinput){
				list = sfryInfoDao.getAllSfryBy3xhw("%"+xm+"%","%"+hkszd+"%", "%"+wtlb+"%", offset,flag);
			}else if(xminput&&hkszdinput&&!wtlbinput&&sfddinput){
				list = sfryInfoDao.getAllSfryBy3xhs("%"+xm+"%", "%"+hkszd+"%", "%"+sfdd+"%", offset,flag);
			}else if(!xminput&&hkszdinput&&wtlbinput&&sfddinput){
				list = sfryInfoDao.getAllSfryBy3hsw("%"+sfdd+"%", "%"+hkszd+"%", "%"+wtlb+"%", offset,flag);
			}else if(xminput&&!hkszdinput&&wtlbinput&&sfddinput){
				list = sfryInfoDao.getAllSfryBy3xsw("%"+xm+"%", "%"+sfdd+"%", "%"+wtlb+"%", offset,flag);
			}else if(xminput&&hkszdinput&&wtlbinput&&sfddinput){
				list = sfryInfoDao.getAllSfryBy4("%"+xm+"%", "%"+hkszd+"%", "%"+wtlb+"%", "%"+sfdd+"%", offset,flag);
			}
		}else if(djksinput&&!djjsinput){
			if(!xminput){
				xm = "";
			}
			if(!hkszdinput){
				hkszd = "";
			}
			if(!wtlbinput){
				wtlb = "";
			}
			if(!sfddinput){
				sfdd = "";
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			list = sfryInfoDao.getAllByDjsj(djks, sdf.format(new Date()), "%"+xm+"%", "%"+hkszd+"%", "%"+wtlb+"%", "%"+sfdd+"%", offset,flag);
		}else if(!djksinput&&djjsinput){
			if(!xminput){
				xm = "";
			}
			if(!hkszdinput){
				hkszd = "";
			}
			if(!wtlbinput){
				wtlb = "";
			}
			if(!sfddinput){
				sfdd = "";
			}
			list = sfryInfoDao.getAllByDjsj("2016-09-01", djjs, "%"+xm+"%", "%"+hkszd+"%", "%"+wtlb+"%", "%"+sfdd+"%", offset,flag);
		}else{
			if(!xminput){
				xm = "";
			}
			if(!hkszdinput){
				hkszd = "";
			}
			if(!wtlbinput){
				wtlb = "";
			}
			if(!sfddinput){
				sfdd = "";
			}
			list = sfryInfoDao.getAllByDjsj(djks, djjs, "%"+xm+"%", "%"+hkszd+"%", "%"+wtlb+"%", "%"+sfdd+"%", offset,flag);
		}
		return list;
	}
	
}
