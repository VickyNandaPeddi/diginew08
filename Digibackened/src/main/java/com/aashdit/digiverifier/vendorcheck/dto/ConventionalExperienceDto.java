package com.aashdit.digiverifier.vendorcheck.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
public class ConventionalExperienceDto {

    private Long Id;
    private String employmentType;
    private String duration;
    private String designation;
    private String candidateEmployerName;
    private Date inputDateOfJoining;
    private Date inputDateOfExit;
    private String employeeCode;

    private String hrName;

    private String hrContactNumber;

    private String hrEmailId;

    private String superiorName;

    private String superiorContactNumber;

    private String superiorEmailID;

    private String superiorDesignation;

    private String lastSalary;

    private String grossSalary;

    private String insufficiencyRemarks;
}
