package com.aashdit.digiverifier.vendorcheck.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AcknoledgementDto {
    @JsonProperty("CandidateID")
    private String CandidateID;

    @JsonProperty("PSNO")
    private String PSNO;

    @JsonProperty("Name")
    private String Name;

    @JsonProperty("RequestID")
    private String RequestID;

    @JsonProperty("VendorName")
    private String VendorName;

    @JsonProperty("VendorID")
    private String VENDORID;
    @JsonProperty("VendorReferenceID")
    private String VendorReferenceID;

}
