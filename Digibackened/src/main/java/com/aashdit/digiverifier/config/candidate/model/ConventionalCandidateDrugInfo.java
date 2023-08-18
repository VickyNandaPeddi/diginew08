package com.aashdit.digiverifier.config.candidate.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "t_dgv_conventional_candidate_drug_info")
public class ConventionalCandidateDrugInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "drug_id")
    private Long drugId;

    @Column(name = "conventional_candidate_id")
    private Long conventionalCandidateId;

    @Column(name = "conventional_requestid")
    private Long conventionalRequestId;

    @Column(name = "candidate_id")
    private Long candidateId;


    @Column(name = "name")
    private String name;


    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "sample_collection_date")
    private Date sampleCollectionDate;


    @Column(name = "remarks")
    private String remarks;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "street_address",length = 3000)
    private String streetAddress;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "prominent_landmark",length = 3000)
    private String prominentLandmark;

    @Column(name = "created_on")
    private Date createdOn;
}
