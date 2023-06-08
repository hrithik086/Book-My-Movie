package com.movie.management.model;

public class RegisterUserDto {
	private String username;
	private String password;
	private String role;
	private boolean registerStatus;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isRegisterStatus() {
		return registerStatus;
	}
	public void setRegisterStatus(boolean registerStatus) {
		this.registerStatus = registerStatus;
	}
	public RegisterUserDto(String username, String password, String role, boolean registerStatus) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.registerStatus=registerStatus;
	}
	public RegisterUserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
