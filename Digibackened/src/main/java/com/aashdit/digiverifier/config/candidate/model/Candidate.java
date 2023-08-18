package com.aashdit.digiverifier.config.candidate.model;

import com.aashdit.digiverifier.config.admin.model.User;
import com.aashdit.digiverifier.config.superadmin.model.Organization;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "t_dgv_candidate_basic")
public class Candidate implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6486326677138491369L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id")
    private Long candidateId;

    @Column(name = "conventional_candidate_id")
    private Long conventionalCandidateId;
    @Column(name = "conventional_request_id", unique = true)
    private Long conventionalRequestId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @Column(name = "candidate_name")
    private String candidateName;

    @Column(name = "candidate_code")
    private String candidateCode;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "pan_number")
    private String panNumber;

    @Column(name = "pan_name")
    private String panName;

    @Column(name = "pan_dob")
    private String panDob;

    @Column(name = "aadhar_number")
    private String aadharNumber;

    @Column(name = "aadhar_name")
    private String aadharName;

    @Column(name = "aadhar_father_name")
    private String aadharFatherName;

    @Column(name = "aadhar_dob")
    private String aadharDob;

    @Column(name = "aadhar_gender")
    private String aadharGender;

    @Column(name = "experience_in_month")
    private Integer experienceInMonth;

    @Column(name = "cc_email_id")
    private String ccEmailId;

    @Column(name = "applicant_id")
    private String applicantId;

    @Column(name = "submitted_on")
    private Date submittedOn;

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

    @Column(name = "approval_required")
    private boolean approvalRequired;

    @Column(name = "is_fresher")
    private Boolean isFresher;

    @Column(name = "is_loa_accepted")
    private Boolean isLoaAccepted = false;

    @Column(name = "is_uan_skipped")
    private Boolean isUanSkipped;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "uan")
    private String uan;

    @Column(name = "uan_name")
    private String uanName;

    @Column(name = "uan_dob")
    private String uanDob;

    @ManyToOne
    @JoinColumn(name = "candidate_sample_id")
    private CandidateSampleCsvXlsMaster candidateSampleId;


//	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private CandidateVerificationState candidateVerificationState;

    public Candidate(String candidateName, String emailId, String contactNumber, Integer experienceInMonth,
                     String ccEmailId, String applicantId) {
        super();
        this.candidateName = candidateName;
        this.emailId = emailId;
        this.contactNumber = contactNumber;
        this.experienceInMonth = experienceInMonth;
        this.ccEmailId = ccEmailId;
        this.applicantId = applicantId;
    }


}
