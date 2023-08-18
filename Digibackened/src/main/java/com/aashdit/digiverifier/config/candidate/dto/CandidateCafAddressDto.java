package com.aashdit.digiverifier.config.candidate.dto;

import lombok.Data;

@Data
public class CandidateCafAddressDto {
	
	private Long candidateCafAddressId;
	
	private String candidateAddress;
	
	private String candidateCode;
	
	private Boolean isPresentAddress;
	
	private Boolean isPermanentAddress;
	
	private Boolean isAssetDeliveryAddress;
	
	private String colorColorName;
	
	private String colorColorCode;
	
	private String colorColorHexCode;
	
	private String serviceSourceMasterSourceServiceId;
	
	private String serviceName;

	private Long addressVerificationCandidateAddressVerificationId;
	
	private String name;
	
	private Integer pinCode;
	
	private String city;
	
	private String state;
	
	private byte[] addressVerificationCandidateCafRelationshipDocumentUploaded;
}
