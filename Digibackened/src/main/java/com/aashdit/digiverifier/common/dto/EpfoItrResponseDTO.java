package com.aashdit.digiverifier.common.dto;

import lombok.Data;

@Data
public class EpfoItrResponseDTO {

	private String code;
	
	private String status;
	
	private EpfoItrResponseData data;
	
	
}
