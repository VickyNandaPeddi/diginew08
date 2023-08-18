package com.aashdit.digiverifier.config.candidate.dto;

import lombok.Data;

import java.util.Date;
@Data
public class EmploymentVerificationDto extends CommonVerificationDto {
	private Date doj;
	private Date doe;
	Integer index;

}
