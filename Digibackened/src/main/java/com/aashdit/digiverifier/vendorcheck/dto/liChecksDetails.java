package com.aashdit.digiverifier.vendorcheck.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class liChecksDetails {

    @JsonProperty("checkStatus")
    private Integer CheckStatus;

    @JsonProperty("checkCode")
    private Integer CheckCode;

    @JsonProperty("completedDate")
    private String CompletedDate;

    @JsonProperty("checkName")
    private String CheckName;

    @JsonProperty("checkRemarks")
    private String CheckRemarks;

    @JsonProperty("modeOfVerficationRequired")
    private String ModeOfVerficationRequired;

    @JsonProperty("modeOfVerficationPerformed")
    private String ModeOfVerficationPerformed;


    public liChecksDetails(Long CheckCode, String CheckName, Long CheckStatus, String CheckRemarks, String ModeOfVerficationRequired, String ModeOfVerficationPerformed, String CompletedDate) {
        this.CheckCode = Math.toIntExact(CheckCode);
        this.CheckName = CheckName;
        this.CheckStatus = Math.toIntExact(CheckStatus);
        this.CheckRemarks = CheckRemarks;
        this.ModeOfVerficationRequired = ModeOfVerficationRequired;
        this.ModeOfVerficationPerformed = ModeOfVerficationPerformed;
        this.CompletedDate = CompletedDate;
    }
}
