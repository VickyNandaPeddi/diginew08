package com.aashdit.digiverifier.login.dto;

public class AuthenticationRequest {
	private String userName;
	private String password;
	//private String captcha;
	
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
//	public String getCaptcha() {
//		return captcha;
//	}
//	public void setCaptcha(String captcha) {
//		this.captcha = captcha;
//	}
	public AuthenticationRequest() {
	}
	
	public AuthenticationRequest(String userName,String password) {
		this.userName=userName;
		this.password=password;
	}
}
