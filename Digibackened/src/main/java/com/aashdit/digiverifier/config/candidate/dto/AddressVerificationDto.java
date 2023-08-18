package com.aashdit.digiverifier.config.candidate.dto;

import com.aashdit.digiverifier.config.superadmin.Enum.SourceEnum;
import lombok.Data;

@Data
public class AddressVerificationDto extends CommonVerificationDto {

	private String type;
	
}
