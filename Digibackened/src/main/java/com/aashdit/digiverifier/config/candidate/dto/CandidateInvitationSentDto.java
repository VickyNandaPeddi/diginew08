package com.aashdit.digiverifier.config.candidate.dto;

import java.util.List;

import lombok.Data;

@Data
public class CandidateInvitationSentDto {
	
	private List<String> candidateReferenceNo;
	
	private String statuscode;

}
