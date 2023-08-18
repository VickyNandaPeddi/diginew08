package com.aashdit.digiverifier.config.candidate.dto;

import lombok.Data;

@Data
public class AadharVerificationDTO extends CommonVerificationDto {
	
	private String name;
	
	private String dob;
	
	private String fatherName;
	
	private String aadharNo;
	
	private String remarks;
}
