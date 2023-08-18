package com.aashdit.digiverifier.config.admin.dto;

import com.aashdit.digiverifier.config.admin.model.User;
import com.aashdit.digiverifier.config.candidate.model.Candidate;
import com.aashdit.digiverifier.config.superadmin.model.Source;
import com.aashdit.digiverifier.config.superadmin.model.VendorCheckStatusMaster;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
public class VendorChecksDto {
    private Long vendorcheckId;

    private Candidate candidate;

    private Long vendorId;

    private Source source;

    private Double tat;

    private String emailId;

    private Boolean expireson;


    private byte[] agentUploadedDocument;


    private User createdBy;

    private Date createdOn;

    private Boolean Isproofuploaded;

    private String documentname;

    private String candidateName;

    private String dateOfBirth;

    private String contactNo;

    private String fatherName;

    private String address;

    private String alternateContactNo;

    private String typeOfPanel;

    private VendorCheckStatusMaster vendorCheckStatusMaster;


    private String pathKey;

    private String checkUniqueId;
    private String modeOfVerificationPerformed;


}
