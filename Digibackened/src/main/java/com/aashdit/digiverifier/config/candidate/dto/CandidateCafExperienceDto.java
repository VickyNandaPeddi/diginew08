package com.aashdit.digiverifier.config.candidate.dto;

import lombok.Data;

@Data
public class CandidateCafExperienceDto {
	
	private Long candidateCafExperienceId;
	private String candidateCode;
	private String candidateEmployerName;
	private String uan;
	private String inputDateOfJoining;
	private String inputDateOfExit;
	private String outputDateOfJoining;
	private String outputDateOfExit;
	private String serviceName;
	private String colorColorName;
	private String colorColorCode;
	private String colorColorHexCode;
	private byte[] experienceCertificate;
	private String remarkMasterDescription;
	private Long suspectEmpMasterId;
	private String certificate;


}