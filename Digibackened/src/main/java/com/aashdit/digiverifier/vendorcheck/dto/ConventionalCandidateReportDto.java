package com.aashdit.digiverifier.vendorcheck.dto;

import com.aashdit.digiverifier.config.admin.dto.VendorUploadChecksDto;
import com.aashdit.digiverifier.config.candidate.dto.*;
import com.aashdit.digiverifier.config.candidate.model.CandidateCafExperience;
import com.aashdit.digiverifier.config.superadmin.Enum.ConventionalVerificationStatus;
import com.aashdit.digiverifier.config.superadmin.Enum.ReportType;
import com.aashdit.digiverifier.config.superadmin.Enum.VerificationStatus;
import com.aashdit.digiverifier.vendorcheck.model.ConventionalVendorliChecksToPerform;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class ConventionalCandidateReportDto {
    private String name;

    private String comments;

    private String candidateId;

    private String applicantId;

    private String dob;

    private String contactNo;

    private String emailId;

    private String referenceId;

    private String experience;

    private String URNno;

    private String organizationName;

    private String organizationLocation;

    private String organizationLogo;

    private String organizationDOJ;

    private String jobLocation;

    private String project;

    private ReportType reportType;

    private String caseInitiationDate;

    private String finalReportDate;
    //
    private ConventionalVerificationStatus verificationStatus;
    private String interimReportDate;

    private List<ConventionalVendorliChecksToPerform> liChecksDetails;

    private List<VendorUploadChecksDto> vendorProofDetails;
    private String address;
}
