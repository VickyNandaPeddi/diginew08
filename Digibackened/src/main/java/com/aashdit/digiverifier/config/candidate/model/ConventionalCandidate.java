package com.aashdit.digiverifier.config.candidate.model;

import com.aashdit.digiverifier.config.superadmin.model.Organization;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "t_dgv_conventional_candidate_basic")
public class ConventionalCandidate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @NotNull
    @Column(name = "candidate_id")
    private Long candidateId;
    @NotNull
    @Column(name = "conventional_candidate_id")
    private Long conventionalCandidateId;

    @Column(name = "conventional_request_id")
    private Long conventionalRequestId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_place")
    private String birthPlace;
    @Column(name = "gender")
    private String gender;
    @Column(name = "nationality")
    private String nationality;
    @Column(name = "father_name")
    private String fatherName;
    @Column(name = "father_date_of_birth")
    private String fatherDateOfBirth;

    @Column(name = "insufficiency_remarks",length = 3000)
    private String insufficiencyRemarks;


}
