package com.aashdit.digiverifier.config.candidate.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "t_dgv_candidate_verification_state")
public class CandidateVerificationState {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "candidate_verification_state_id")
	private Long candidateVerificationStateId;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;
	
	@Column(name = "case_initiation_time")
	private ZonedDateTime caseInitiationTime;
	
	@Column(name = "interim_report_time")
	private ZonedDateTime interimReportTime;
	
	@Column(name = "final_report_time")
	private ZonedDateTime finalReportTime;
	
	@Column(name = "sr_report_time")
	private ZonedDateTime srReportTime;
	
	@Column(name = "pre_approval_report_time")
	private ZonedDateTime preApprovalTime;
	
}
