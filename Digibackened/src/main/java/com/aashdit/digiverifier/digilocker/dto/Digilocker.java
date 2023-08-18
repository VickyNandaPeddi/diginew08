package com.aashdit.digiverifier.digilocker.dto;

public class Digilocker {
	
	private String accessCode;
	private String accessToken;
    
    public String getAccessCode() {
		return accessCode;
	}
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
    public String toString() {
        return "Digilocker [accessCode=" + accessCode + " Digilocker AccessToken="+accessToken+"]";
    }
}
