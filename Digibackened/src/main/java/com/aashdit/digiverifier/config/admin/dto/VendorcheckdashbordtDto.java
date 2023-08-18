package com.aashdit.digiverifier.config.admin.dto;

import com.aashdit.digiverifier.config.superadmin.model.VendorMasterNew;
import com.aashdit.digiverifier.config.admin.model.VendorChecks;
import com.aashdit.digiverifier.config.candidate.model.Candidate;
import com.aashdit.digiverifier.config.superadmin.model.VendorCheckStatusMaster;
import com.aashdit.digiverifier.config.admin.model.VendorUploadChecks;


import lombok.Data;

import java.util.List;

@Data
public class VendorcheckdashbordtDto {


    private String documentname;


    private Long colorid;

    private Long vendorcheckId;
    private String modeofverificationperformed;

    private Long vendorCheckStatusMasterId;

    private String status;

    private String remarks;
    private String value;

    // private Long VendorCheckStatusId;


    // private byte proofDocumentNew;


    // String candidateName;
    // String userName;
    // String emailId;
    // String sourceName;
    // String proofuploaded;
    // String lastUpdatedBy;
    // Boolean isActive;
    // Long vendorcheckId;
    // Boolean expireson;
    // Double tat;
    // String createdBy;


}
