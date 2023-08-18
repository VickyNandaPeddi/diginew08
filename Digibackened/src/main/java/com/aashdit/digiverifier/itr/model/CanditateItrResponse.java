package com.aashdit.digiverifier.itr.model;

import com.aashdit.digiverifier.config.candidate.model.Candidate;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "t_dgv_candidate_itr_response")
public class CanditateItrResponse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "candidate_itr_response_id")
	private Long candidateItrResponseId;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "candidate_id",insertable = false,updatable = false)
	private Candidate candidate;
	
	@Column(name = "candidate_id")
	private Long candidateId;
	
	@Column(name = "organization_id")
	private Long organizationId;
	
	@Column(name = "form26as_response")
	private String form26AsResponse;
	
	@Column(name = "epfo_response")
	private String epfoResponse;
	
	@Column(name = "form26as_response_type")
	private String form26AsResponseType;
	
	@Column(name = "created_timestamp")
	private Date createdOn;
	
	@Column(name = "updated_timestamp")
	private Date lastUpdatedOn;
	
	
}
