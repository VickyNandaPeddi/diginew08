package com.aashdit.digiverifier.vendorcheck.model;

import com.aashdit.digiverifier.config.admin.model.User;
import com.aashdit.digiverifier.config.candidate.model.StatusMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "t_dgv_conventional_candidate_request")
public class ConventionalVendorCandidatesSubmitted {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "candidate_id")
    private Long candidateId;
    @Column(name = "ps_no")
    private String psNo;
    @Column(name = "name")
    private String name;
    @Column(name = "request_id", unique = true)
    private String requestId;
    @Column(name = "request_type")
    private String requestType;
    @Column(name = "vendor_id")
    private String vendorId;
    @Column(name = "applicant_id")
    private Integer applicantId;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
    @Column(name = "created_on")
    private Date createdOn;

    //status from vendor check master
    @OneToOne
    @JoinColumn(name = "status")
    private StatusMaster status;

    @Column(name = "verification_status")
    private String verificationStatus;


    @Column(name = "old_request_type")
    private String oldRequestType;
}
