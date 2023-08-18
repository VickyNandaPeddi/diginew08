package com.aashdit.digiverifier.config.candidate.dto; 


import com.aashdit.digiverifier.common.dto.ContentDTO;
import lombok.Data;

import java.util.List;

@Data
public class CandidateDetailsDto {
	
	private String candidateName;
	private String candidateCode;
	private String emailId;
	private String contactNumber;
	private Integer experienceInMonth;
	private String ccEmailId;
	private String applicantId;
	private Long CandidateId;
	private String createdByUserFirstName;
	private String createdByUserLastName;
	private String createdOn;
	private Long createdByUserId;
	
	private String dateOfEmailInvite;
	private String dateOfEmailFailure;
	private String dateOfEmailReInvite;
	private String dateOfEmailExpire;
	
	private String submittedOn;
	private String candidateStatusName;
	private List<ContentDTO> contentDTOList;
	private String aadharDob;
	private String aadharName;
}
