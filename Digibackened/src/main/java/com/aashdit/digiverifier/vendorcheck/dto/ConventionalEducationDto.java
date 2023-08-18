package com.aashdit.digiverifier.vendorcheck.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
public class ConventionalEducationDto {

    private Long Id;

    private Long conventionalCandidateId;

    private String educationType;

    private String degreeType;


    private Date startDate;

    private Date endDate;

    private String insufficiecyRemarks;


    private String qualificationName;


    private String schoolOrCollegeName;

    private String boardOrUniversityName;

}
