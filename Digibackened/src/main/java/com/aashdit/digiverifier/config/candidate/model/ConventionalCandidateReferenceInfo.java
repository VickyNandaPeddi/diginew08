package com.aashdit.digiverifier.config.candidate.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "t_dgv_conventional_candidate_reference_info")
public class ConventionalCandidateReferenceInfo implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reference_id")
    private Long referenceId;

    @Column(name = "candidate_id")
    private Long candidateId;
    @Column(name = "conventional_candidate_id")
    private Long conventionalCandiateId;

    @Column(name = "conventional_requestid")
    private Long conventionalRequestId;

    @Column(name = "reference_number")
    private String referenceNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "duration_known")
    private String durationKnown;


    @Column(name = "designation")
    private String designation;

    @Column(name = "emailId")
    private String emailId;

    @Column(name = "professional_relation")
    private String professionalRelation;

    @Column(name = "insufficiency_remarks",length = 3000)
    private String insufficiencyRemarks;


}
