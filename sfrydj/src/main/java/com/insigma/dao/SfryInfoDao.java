package com.insigma.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.insigma.model.SfryInfo;

public interface SfryInfoDao {

	public List<SfryInfo> getByXh(@Param("xh")int xh);
	
	public List<SfryInfo> getAllSfry(@Param("offset")int offset);
	
	public List<SfryInfo> getAllSfryByName(@Param("xm")String xm,@Param("offset")int offset,@Param("flag")int flag);
	
	public List<SfryInfo> getAllSfryByHkszd(@Param("hkszd")String hkszd,@Param("offset")int offset,@Param("flag")int flag);

	public List<SfryInfo> getAllSfryByWtlb(@Param("wtlb")String wtlb,@Param("offset")int offset,@Param("flag")int flag);

	public List<SfryInfo> getAllSfryBySfdd(@Param("sfdd")String sfdd,@Param("offset")int offset,@Param("flag")int flag);

	public List<SfryInfo> getAllSfryByNameAndHkszd(@Param("xm")String xm,@Param("hkszd")String hkszd,@Param("offset")int offset,@Param("flag")int flag);

	public List<SfryInfo> getAllSfryByNameAndWtlb(@Param("xm")String xm,@Param("wtlb")String wtlb,@Param("offset")int offset,@Param("flag")int flag);

	public List<SfryInfo> getAllSfryByNameAndSfdd(@Param("xm")String xm,@Param("sfdd")String sfdd,@Param("offset")int offset,@Param("flag")int flag);

	public List<SfryInfo> getAllSfryByHkszdAndWtlb(@Param("hkszd")String hkszd,@Param("wtlb")String wtlb,@Param("offset")int offset,@Param("flag")int flag);
	
	public List<SfryInfo> getAllSfryByHkszdAndSfdd(@Param("hkszd")String hkszd,@Param("sfdd")String sfdd,@Param("offset")int offset,@Param("flag")int flag);

	public List<SfryInfo> getAllSfryByWtlbAndSfdd(@Param("wtlb")String wtlb,@Param("sfdd")String sfdd,@Param("offset")int offset,@Param("flag")int flag);
	
	public List<SfryInfo> getAllSfryBy3xhw(@Param("xm")String xm,@Param("hkszd")String hkszd,@Param("wtlb")String wtlb,@Param("offset")int offset,@Param("flag")int flag);

	public List<SfryInfo> getAllSfryBy3xhs(@Param("xm")String xm,@Param("hkszd")String hkszd,@Param("sfdd")String sfdd,@Param("offset")int offset,@Param("flag")int flag);

	public List<SfryInfo> getAllSfryBy3hsw(@Param("sfdd")String sfdd,@Param("hkszd")String hkszd,@Param("wtlb")String wtlb,@Param("offset")int offset,@Param("flag")int flag);

	public List<SfryInfo> getAllSfryBy3xsw(@Param("xm")String xm,@Param("sfdd")String sfdd,@Param("wtlb")String wtlb,@Param("offset")int offset,@Param("flag")int flag);

	public List<SfryInfo> getAllSfryBy4(@Param("xm")String xm,@Param("hkszd")String hkszd,@Param("wtlb")String wtlb,@Param("sfdd")String sfdd,@Param("offset")int offset,@Param("flag")int flag);
	
	public List<SfryInfo> getAllByDjsj(@Param("djks")String djks,@Param("djjs")String djjs,@Param("xm")String xm,@Param("hkszd")String hkszd,@Param("wtlb")String wtlb,@Param("sfdd")String sfdd,@Param("offset")int offset,@Param("flag")int flag);
	
	public void addSfry(@Param("sfry")SfryInfo sfry);
	
	public int delete(@Param("xh")int xh);
	
	public int getCount();
}
