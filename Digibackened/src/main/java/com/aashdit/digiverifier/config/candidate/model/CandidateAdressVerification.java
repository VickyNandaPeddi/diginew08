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

import org.hibernate.annotations.Type;

import com.aashdit.digiverifier.config.admin.model.User;

import lombok.Data;

@Data
@Entity
@Table(name="t_dgv_candidate_adress_verification")
public class CandidateAdressVerification implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 8280884734895146097L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "candidate_address_verification_id")
	private Long candidateAddressVerificationId;
	
	@ManyToOne
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;
	
	@ManyToOne
	@JoinColumn(name = "candidate_relationship_id")
	private CandidateCafRelationship candidateCafRelationship;
	
	@ManyToOne
	@JoinColumn(name = "candidate_status_id")
	private CandidateStatus candidateStatus;
	
	@Column(name = "verification_email_id")
	private String verificationEmailId;
	
	@Column(name = "date_of_adress_verification")
	private Date dateOfAdressVerification;
	
	@Type(type="org.hibernate.type.BinaryType")
    @Column(name = "verification_address", columnDefinition="BLOB")
    private byte[] verificationAddress;
	
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
