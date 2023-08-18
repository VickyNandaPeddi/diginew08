package com.aashdit.digiverifier.vendorcheck.dto;

import com.aashdit.digiverifier.config.admin.dto.VendorUploadChecksDto;
import com.aashdit.digiverifier.config.superadmin.Enum.ConventionalVerificationStatus;
import com.aashdit.digiverifier.config.superadmin.Enum.ReportType;
import com.aashdit.digiverifier.vendorcheck.model.ConventionalVendorliChecksToPerform;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportUtilizationDto {
    private String vendorName;
    //vendorname
    private String caseInititatedBy;
    //appilicant id
    private String urnRefNo;
    private Long vendorId;

    private String candidateName;

    private String caseAssignedDate;

    private String reportSubmittedDate;
    //report status(verification status)
    private String reportCode;

    private String excelBase64;

    private List<ChecksDto> checksDtos;


    private String checkName;
}
