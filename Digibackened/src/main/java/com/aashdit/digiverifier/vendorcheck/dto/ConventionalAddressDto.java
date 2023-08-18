package com.aashdit.digiverifier.vendorcheck.dto;

import com.aashdit.digiverifier.config.candidate.model.CandidateCafAddress;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class ConventionalAddressDto {

    private Long Id;

    private String addressType;


    private Date stayFromDate;

    private Date stayToDate;

    private String houseType;

    private String contactInfo;

    private String insufficiencyRemarks;

    private String candidateAddress;

    private String pincode;

    private String city;

    private String state;
}
