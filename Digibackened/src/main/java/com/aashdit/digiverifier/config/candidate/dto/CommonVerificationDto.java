package com.aashdit.digiverifier.config.candidate.dto;

import com.aashdit.digiverifier.config.superadmin.Enum.SourceEnum;
import com.aashdit.digiverifier.config.superadmin.Enum.VerificationStatus;
import lombok.Data;

@Data
public class CommonVerificationDto {
	
	private String input;
	
	private String output;
	
	private SourceEnum source;
	
	private VerificationStatus verificationStatus;
	
}
