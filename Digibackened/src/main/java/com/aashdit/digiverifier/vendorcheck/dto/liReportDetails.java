package com.aashdit.digiverifier.vendorcheck.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class liReportDetails {
    @JsonProperty("ReportFileName")
    private String ReportFileName;

    @JsonProperty("ReportFileExtention")
    private String ReportFileExtention;

    @JsonProperty("ReportAttachment")
    private String ReportAttachment;

    @JsonProperty("VendorReferenceID")
    private String VendorReferenceID;

    @JsonProperty("ReportType")
    private String ReportType;

    @JsonProperty("ReportStatus")
    private String ReportStatus;


}
