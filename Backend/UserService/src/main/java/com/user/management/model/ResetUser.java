package com.user.management.model;

public class ResetUser {
	private String username;
	private int userId;
	private String newPassword;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public ResetUser(String username, int userId, String password) {
		super();
		this.username = username;
		this.userId = userId;
		this.newPassword=password;
	}
	public ResetUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
