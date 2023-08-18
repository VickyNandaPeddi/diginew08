package com.aashdit.digiverifier.vendorcheck.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter

@Setter
@NoArgsConstructor
public class ConventionalCandidateDocDto {

    private String docName;

    private String docUrl;

    private String fileType;
    private String createdBy;

    private Date createdOn;

    public ConventionalCandidateDocDto(String docName, String docUrl, String fileType) {
        this.docName = docName;
        this.docUrl = docUrl;
        this.fileType = fileType;
    }

    public ConventionalCandidateDocDto(String docName, String docUrl, String fileType, Date createdOn) {
        this.docName = docName;
        this.docUrl = docUrl;
        this.fileType = fileType;
        this.createdOn = createdOn;
    }
}
