package com.aashdit.digiverifier.config.candidate.dto;

import com.aashdit.digiverifier.config.candidate.Enum.IDtype;
import com.aashdit.digiverifier.config.superadmin.Enum.SourceEnum;
import com.aashdit.digiverifier.config.superadmin.Enum.VerificationStatus;
import lombok.Data;

@Data
public class IDVerificationDTO {
	
	private String iDtype;
	
	private String name;
	
	private String idNo;
	
	private SourceEnum sourceEnum;
	
	private VerificationStatus verificationStatus;

}
