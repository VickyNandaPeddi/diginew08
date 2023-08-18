package com.aashdit.digiverifier.config.candidate.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateCafEducationDto {
	
	private Long candidateCafEducationId;
	
	private String candidateCode;
	
	private String yearOfPassing;
	
	private String schoolOrCollegeName;
	
	private String boardOrUniversityName;
	
	private Integer totalMarks;
	
	private String percentage;
	
	private Long qualificationId;
	
	private Boolean isHighestQualification;
	
	private String qualificationName;
	
	private String colorColorName;
	
	private String colorColorCode;
	
	private String colorColorHexCode;
	
	private byte[] certificate;
	
	private String remarkMasterDescription;
	
	private String candidateRollNumber;
	
	private Long suspectClgMasterId;
	
	private String file;
	
	private String serviceSourceMasterSourceServiceId;
	
	private String courseName;
	
}