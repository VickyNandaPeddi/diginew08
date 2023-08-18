package com.aashdit.digiverifier.vendorcheck.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@Setter
@Entity
@Table(name = "t_dgv_conventional_mode_of_verification_master")
public class ModeOfVerificationStatusMaster {
    @Id
    private Long modeTypeCode;
    private String modeOfVerification;
}
