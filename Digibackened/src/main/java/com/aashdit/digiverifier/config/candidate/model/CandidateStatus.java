package com.aashdit.digiverifier.config.candidate.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.aashdit.digiverifier.config.admin.model.User;
import com.aashdit.digiverifier.config.superadmin.model.ServiceSourceMaster;

import lombok.Data;

@Data
@Entity
@Table(name = "t_dgv_candidate_status")
public class CandidateStatus implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8358339938720997566L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_status_id")
    private Long candidateStatusId;


    @NotNull
    @OneToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "source_service_id")
    private ServiceSourceMaster serviceSourceMaster;

    @OneToOne
    @JoinColumn(name = "status_master_id")
    private StatusMaster statusMaster;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "created_on")
    private Date createdOn;

    @ManyToOne
    @JoinColumn(name = "last_updated_by")
    private User lastUpdatedBy;

    @Column(name = "last_updated_on")
    private Date lastUpdatedOn;


}
