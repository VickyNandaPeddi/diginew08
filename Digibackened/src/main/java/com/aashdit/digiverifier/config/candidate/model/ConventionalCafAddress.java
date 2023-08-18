package com.aashdit.digiverifier.config.candidate.model;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "t_dgv_conventioanl_candidate_caf_address")
public class ConventionalCafAddress implements Serializable {


    private static final long serialVersionUID = -4386254957126811179L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Id;

    @Column(name = "conventional_candidateid")
    private Long conventionalCandidateId;

    @Column(name = "conventional_requestid")
    private Long conventionalRequestId;

    @NotNull
    @Column(name = "candidateid_caf_address_id")
    private Long candidateCafAddressId;


    @Column(name = "address_type")
    private String addressType;


    @Column(name = "stay_from_date")
    private Date stayFromDate;

    @Column(name = "stay_to_date")
    private Date stayToDate;

    @Column(name = "house_type")
    private String houseType;

    @Column(name = "contact_info")
    private String contactInfo;

    @Column(name = "insufficiency_remarks",length = 3000)
    private String insufficiencyRemarks;

}
