package com.bean;

public class AdminInfo {
	private int admin_id;
	private String admin_userName;
	private String admin_password;
	
	public int getAId() {
		return admin_id;
	}
	public void setAId(int admin_id) {
		this.admin_id = admin_id;
	}
	
	public String getAUserName() {
		return admin_userName;
	}
	public void setAUserName(String admin_userName) {
		this.admin_userName = admin_userName;
	}
	
	public String getAPassword() {
		return admin_password;
	}
	public void setAPassword(String admin_password) {
		this.admin_password = admin_password;
	}
	
	public AdminInfo(String admin_name, String admin_password) {
		super();
		this.admin_userName = admin_name;
		this.admin_password = admin_password;
	}

	public AdminInfo() {
		super();
	}
}

