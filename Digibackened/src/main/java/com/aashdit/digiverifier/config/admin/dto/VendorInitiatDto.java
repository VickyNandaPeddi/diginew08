package com.aashdit.digiverifier.config.admin.dto;

import com.aashdit.digiverifier.config.superadmin.model.VendorCheckStatusMaster;
import com.aashdit.digiverifier.config.admin.model.VendorChecks;

import lombok.Data;

import java.util.List;

@Data
public class VendorInitiatDto {


    private Long vendorId;

    private Long sourceId;

    private Long candidateId;

    private String documentname;

    private String candidateName;

    private String dateOfBirth;

    private String contactNo;

    private String fatherName;

    private String address;

    private String alternateContactNo;

    private String typeOfPanel;

    private Long vendorCheckStatusMasterId;

    private String documentUrl;

    private String licheckId;

    private String vendorName;

    private String sourceName;

}
