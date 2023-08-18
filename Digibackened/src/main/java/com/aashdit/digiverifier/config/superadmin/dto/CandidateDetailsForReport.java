package com.aashdit.digiverifier.config.superadmin.dto;

import lombok.Data;
import java.util.Date;

@Data
public class CandidateDetailsForReport {
	
	
	private String createdByUserFirstName;
	private String createdByUserLastName;
	private String candidateName;
	private String contactNumber;
	private String emailId;
	private String panNumber;
	private String applicantId;
	private String createdOn;
	private String candidateCode;
	private Long candidateId;
	private Integer experienceInMonth;
	private String dateOfEmailInvite;
	private String statusName;
	private String statusDate;
	private String currentStatusDate;
	private String colorName;
	private Integer numberofexpiredCount;
	private Integer reinviteCount;
	
	private String candidateUan;
	private String CandidateUanName;
	private String PanName;
	private String PanDob;
	private String dateOfBirth;
	private String address;
	private String aadharFatherName;
	private String relationship;
	private String relationName;
	private String organizationOrganizationName;
	private String aadharNumber;
	private String aadharName;
	private String aadharDob;
	private String aadharGender;
	
	
}
