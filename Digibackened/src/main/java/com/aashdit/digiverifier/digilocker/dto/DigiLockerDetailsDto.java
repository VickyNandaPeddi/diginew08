package com.aashdit.digiverifier.digilocker.dto;

import lombok.Data;

@Data
public class DigiLockerDetailsDto {

	String candidateCode;
	String aadhaar;
	String transactionid;
	String errorMessage;
	String otp;
	String securitypin;
	String digi_code;
	String access_token;
	
}
