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

import org.hibernate.annotations.Type;

import com.aashdit.digiverifier.config.admin.model.User;
import com.aashdit.digiverifier.config.superadmin.model.Color;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="t_dgv_candidate_case_details")
public class CandidateCaseDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 412509704577618389L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "candidate_case_details_id")
	private Long candidateCaseDetailsId;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;
	
	@Type(type="org.hibernate.type.BinaryType")
    @Column(name = "criminal_verification_document", columnDefinition="BLOB")
    private byte[] criminalVerificationDocument;
	
	@OneToOne
	@JoinColumn(name = "criminal_verification_is_exist")
	private Color criminalVerificationisExist;
	
	@Type(type="org.hibernate.type.BinaryType")
    @Column(name = "global_database_case_details_document", columnDefinition="BLOB")
    private byte[] globalDatabaseCaseDetailsDocument;
	
	@OneToOne
	@JoinColumn(name = "global_database_case_details_is_exist")
	private Color globalDatabaseCaseDetailsIsExist;
	
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
