package com.aashdit.digiverifier.vendorcheck.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SubmittedCandidates {

    private Long candidateId;
    private String psNo;
    private String name;
    private String requestId;
    private String requestType;
    private String vendorId;
    private Integer applicantId;
    private String status;
    private String createdBy;

    private Date createdOn;

    private List<LicheckRequiredResponseDto> licheckRequiredResponseDtos;

    public SubmittedCandidates(Long candidateId, String psNo, String name, String requestId, String requestType, String vendorId, Integer applicantId) {
        this.candidateId = candidateId;
        this.psNo = psNo;
        this.name = name;
        this.requestId = requestId;
        this.requestType = requestType;
        this.vendorId = vendorId;
        this.applicantId = applicantId;
    }

    public SubmittedCandidates(Long candidateId, String psNo, String name, String requestId, String requestType, String vendorId, Integer applicantId, Date createdOn, String status) {
        this.candidateId = candidateId;
        this.psNo = psNo;
        this.name = name;
        this.requestId = requestId;
        this.requestType = requestType;
        this.vendorId = vendorId;
        this.applicantId = applicantId;
        this.status = status;
        this.createdOn = createdOn;
    }
}
