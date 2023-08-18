package com.aashdit.digiverifier.epfo.dto;

import lombok.Data;

@Data
public class EpfoDetailsDto {

	String candidateCode;
	String uanusername;
	String uanpassword;
	String captcha;
	String transactionid;
	String errorMessage;
}
