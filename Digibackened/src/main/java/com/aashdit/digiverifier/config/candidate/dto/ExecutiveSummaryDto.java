package com.aashdit.digiverifier.config.candidate.dto;

import com.aashdit.digiverifier.config.superadmin.Enum.ExecutiveName;
import com.aashdit.digiverifier.config.superadmin.Enum.VerificationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecutiveSummaryDto {

	String verificationItem;
	
	String source;
	
	String result;

	String colorCode;
	
	String colorHexCode;
	
	private int position;
	
	private String details;
	
	private VerificationStatus verificationStatus;
	
	public ExecutiveSummaryDto(ExecutiveName verificationItem, String details, VerificationStatus status) {
		this.setVerificationItem(StringUtils.capitalize(verificationItem.name()));
		this.setDetails(details);
		this.setVerificationStatus(status);
	}
	
}
