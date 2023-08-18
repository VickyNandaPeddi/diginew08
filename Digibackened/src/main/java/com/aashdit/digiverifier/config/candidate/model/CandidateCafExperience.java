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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import com.aashdit.digiverifier.config.admin.model.User;
import com.aashdit.digiverifier.config.superadmin.model.Color;
import com.aashdit.digiverifier.config.superadmin.model.ServiceSourceMaster;

import lombok.Data;

@Data
@Entity
@Table(name = "t_dgv_candidate_caf_experience")
public class CandidateCafExperience implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2086543930823503203L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_caf_experience_id")
    private Long candidateCafExperienceId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "service_source_master_id")
    private ServiceSourceMaster serviceSourceMaster;

    /*conventional candidate exp company name*/
    @Column(name = "candidate_employer_name")
    private String candidateEmployerName;

    @Column(name = "uan")
    private String uan;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "output_date_of_joining")
    private Date outputDateOfJoining;


    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "output_date_of_exit")
    private Date outputDateOfExit;
    /*conventional candidate exp doj*/
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "input_date_of_joining")
    private Date inputDateOfJoining;
    /*conventional candidate exp doe*/
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "input_date_of_exit")
    private Date inputDateOfExit;

    @ManyToOne
    @JoinColumn(name = "remark_id")
    private RemarkMaster remarkMaster;

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

    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "experience_certificate", columnDefinition = "BLOB")
    private byte[] experienceCertificate;

    @ManyToOne
    @JoinColumn(name = "candidate_status_id")
    private CandidateStatus candidateStatus;

    @ManyToOne
    @JoinColumn(name = "suspect_emp_master_id")
    private SuspectEmpMaster suspectEmpMaster;

    @Column(name = "company_tan_no")
    private String tanNo;
//    @Column(name = "id")
//    private Long id;

}
