package com.aashdit.digiverifier.config.candidate.dto;

import java.util.List;

import com.aashdit.digiverifier.config.admin.dto.VendorUploadChecksDto;
import com.aashdit.digiverifier.config.candidate.model.Candidate;

import lombok.Data;

@Data
public class ConventionalCReportApprovalDto {

    private List<VendorUploadChecksDto> vendorProofDetails;
    //	private Candidate candidate;
    private String candidateName;

    private Long finalReportStatus;
}
