package com.lulu.vo;
/**
 * @author 田露露
 * @version 1.0
 * 
 * userlogin表的实体类
 * */

public class UserLogin {
	
	
	private int userID;
	private String userName;
	private String password;
	private int role;
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
	

}
