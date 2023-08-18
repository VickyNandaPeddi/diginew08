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
@Table(name = "t_dgv_conventional_candidate_caf_education")
public class ConventionalCandidateCafEducation implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4386254957126811179L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Id;
    @Column(name = "conventional_candidate_id")
    private Long conventionalCandidateId;
    @NotNull
    @Column(name = "candidate_cafeducation_id")
    private Long candidateCafEducationId;

    @Column(name = "conventional_requestid")
    private Long conventionalRequestId;

    @Column(name = "education_type")
    private String educationType;

    @Column(name = "degree_type")
    private String degreeType;


    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "insufficiency_remarks",length = 3000)
    private String insufficiencyRemarks;

}
