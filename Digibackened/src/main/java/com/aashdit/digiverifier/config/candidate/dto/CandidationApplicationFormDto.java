package com.aashdit.digiverifier.config.candidate.dto;

import java.util.List;

import com.aashdit.digiverifier.config.candidate.model.Candidate;
import com.aashdit.digiverifier.config.candidate.model.CandidateEmailStatus;
import com.aashdit.digiverifier.config.candidate.model.CandidateIdItems;
import com.aashdit.digiverifier.config.candidate.model.CandidateStatus;
import com.aashdit.digiverifier.itr.dto.ITRDataFromApiDto;
import com.aashdit.digiverifier.config.admin.dto.VendorUploadChecksDto;
import com.aashdit.digiverifier.config.candidate.model.CandidateAddComments;

import lombok.Data;

@Data
public class CandidationApplicationFormDto {
	private List<CandidateCafEducationDto> candidateCafEducationDto;
	private List<CandidateCafExperienceDto> candidateCafExperienceDto;
	private List<CandidateCafAddressDto> candidateCafAddressDto;
	private List<ITRDataFromApiDto> iTRDataFromApiDto;
	
	@Deprecated
	private CandidateFileDto candidateResume;
	
	private String candidateResumeUrl;
	private CandidateFileDto caseDetails;
	private CandidateFileDto globalDatabaseCaseDetails;
	private List<VendorUploadChecksDto> vendorProofDetails;
	private List<ContentFileDto> document;
	private String candidateUan;
	private Candidate candidate;
	private CandidateStatus candidateStatus;
	private List<CandidateIdItems> candidateIdItems;
	private List<ExecutiveSummaryDto> executiveSummary;
	private List<EmploymentDetailsDto> employmentDetails;
	private CandidateEmailStatus emailStatus;
	private String gapSum;
	private String outputTenureSum;
	private String inputTenureSum;
	private CandidateAddComments candidateAddComments;
}
