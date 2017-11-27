package com.insigma.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class SfryInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	//序号
	private int xh;
	//姓名
	private String xm;
	//性别
	private String xb;
	//年龄
	private int nl;
	//户口所在地
	private String hkszd;
	//问题类别
	private String wtlb;
	//上访（被查控）地点
	private String sfdd;
	//登记人
	private String djr;
	//登记时间
	private String djsj;
	public String getDjr() {
		return djr;
	}
	public void setDjr(String djr) {
		this.djr = djr;
	}
	public String getDjsj() {
		return djsj;
	}
	public void setDjsj(String djsj) {
		this.djsj = djsj;
	}
	//备注
	private String bz;
	
	public int getXh() {
		return xh;
	}
	public void setXh(int xh) {
		this.xh = xh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public int getNl() {
		return nl;
	}
	public void setNl(int nl) {
		this.nl = nl;
	}
	public String getHkszd() {
		return hkszd;
	}
	public void setHkszd(String hkszd) {
		this.hkszd = hkszd;
	}
	public String getWtlb() {
		return wtlb;
	}
	public void setWtlb(String wtlb) {
		this.wtlb = wtlb;
	}
	public String getSfdd() {
		return sfdd;
	}
	public void setSfdd(String sfdd) {
		this.sfdd = sfdd;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
