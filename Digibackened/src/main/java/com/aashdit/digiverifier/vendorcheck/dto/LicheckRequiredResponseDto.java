package com.aashdit.digiverifier.vendorcheck.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class LicheckRequiredResponseDto {

    private Long id;
    private Long checkCode;
    private String checkName;
    private String checkStatus;
    private String checkRemarks;
    private String modeOfVerificationRequired;
    private String modeOfVerificationPerformed;
    private String completedDateTime;
    private String createdBy;
    private Date createdOn;
    private String candidateId;
    private Long checkUniqueId;
    private Long vendorId;
    private String requestID;
    private Long sourceId;
    private String vendorName;
    private String sourceName;
    private String documentName;
    private CandidateuploadS3Documents candidateuploadS3Documents;


    public LicheckRequiredResponseDto(Long id, Long checkCode, String checkName, String checkStatus, String checkRemarks, String modeOfVerificationRequired, String modeOfVerificationPerformed, String completedDateTime) {
        this.checkCode = checkCode;
        this.id = id;
        this.checkName = checkName;
        this.checkStatus = checkStatus;
        this.checkRemarks = checkRemarks;
        this.modeOfVerificationRequired = modeOfVerificationRequired;
        this.modeOfVerificationPerformed = modeOfVerificationPerformed;
        this.completedDateTime = completedDateTime;
    }

    public LicheckRequiredResponseDto(Long id, Long checkCode, String checkName, String checkStatus, String checkRemarks, String modeOfVerificationRequired, String modeOfVerificationPerformed, String completedDateTime, Date createdOn, String candidateId) {
        this.checkCode = checkCode;
        this.id = id;
        this.checkName = checkName;
        this.checkStatus = checkStatus;
        this.checkRemarks = checkRemarks;
        this.modeOfVerificationRequired = modeOfVerificationRequired;
        this.modeOfVerificationPerformed = modeOfVerificationPerformed;
        this.completedDateTime = completedDateTime;
        this.createdOn = createdOn;
        this.candidateId = candidateId;
    }

    public LicheckRequiredResponseDto(Long id, Long checkCode, String checkName, String checkStatus, String checkRemarks, String modeOfVerificationRequired, String modeOfVerificationPerformed, String completedDateTime, Date createdOn, String candidateId, Long vendorId, Long sourceId) {
        this.checkCode = checkCode;
        this.id = id;
        this.checkName = checkName;
        this.checkStatus = checkStatus;
        this.checkRemarks = checkRemarks;
        this.modeOfVerificationRequired = modeOfVerificationRequired;
        this.modeOfVerificationPerformed = modeOfVerificationPerformed;
        this.completedDateTime = completedDateTime;
        this.createdOn = createdOn;
        this.candidateId = candidateId;
        this.vendorId = vendorId;
        this.sourceId = sourceId;
    }

    public LicheckRequiredResponseDto(Long id, Long checkCode, String checkName, String checkStatus, String checkRemarks, String modeOfVerificationRequired, String modeOfVerificationPerformed, String completedDateTime, Date createdOn, String candidateId, Long vendorId, Long sourceId, String sourceName, String vendorName) {
        this.checkCode = checkCode;
        this.id = id;
        this.checkName = checkName;
        this.checkStatus = checkStatus;
        this.checkRemarks = checkRemarks;
        this.modeOfVerificationRequired = modeOfVerificationRequired;
        this.modeOfVerificationPerformed = modeOfVerificationPerformed;
        this.completedDateTime = completedDateTime;
        this.createdOn = createdOn;
        this.candidateId = candidateId;
        this.vendorId = vendorId;
        this.sourceId = sourceId;
        this.sourceName = sourceName;
        this.vendorName = vendorName;
    }

}
