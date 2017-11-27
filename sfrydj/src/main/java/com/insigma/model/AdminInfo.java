package com.insigma.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AdminInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String username;

	private String pass;

	private String realname;
	//添加人
	private String fzr;

	public String getFzr() {
		return fzr;
	}

	public void setFzr(String fzr) {
		this.fzr = fzr;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
