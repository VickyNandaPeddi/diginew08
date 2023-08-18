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

import lombok.Data;

@Data
@Entity
@Table(name="t_dgv_candidate_status_history")
public class CandidateStatusHistory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 643870971332134602L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "candidate_status_history_id")
	private Long candidateStatusHistoryId;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;
	
	@OneToOne
	@JoinColumn(name = "status_master_id")
	private StatusMaster statusMaster;
	
	@Column(name = "candidate_status_change_timestamp")
	private Date candidateStatusChangeTimestamp;
	
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
