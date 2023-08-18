package com.aashdit.digiverifier.config.candidate.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EmploymentTenureVerificationDto extends CommonVerificationDto {
	private Date doj;
	private String employerName;
	
	
}
