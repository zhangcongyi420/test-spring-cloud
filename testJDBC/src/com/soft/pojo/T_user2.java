package com.soft.pojo;

public class T_user2 {
	
	private Integer id;
	private String pwd;
	private String username;
	private java.sql.Time regTime;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPwd() {
		return pwd;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setRegTime(java.sql.Time regTime) {
		this.regTime = regTime;
	}

	public java.sql.Time getRegTime() {
		return regTime;
	}
}
