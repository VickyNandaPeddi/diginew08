package com.aashdit.digiverifier.epfo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "t_dgv_candidate_epfo_response")
public class CandidateEPFOResponse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "t_dgv_candidate_itr_response_id")
	private Long id;
	
	@Column(name = "candidate_id")
	private Long candidateId;
	
	@Column(name = "epfo_response")
	private String EPFOResponse;
	
	@Column(name = "uan")
	private String uan;
	
	@Column(name = "created_timestamp")
	private Date createdOn;
	
	
	@Column(name = "updated_timestamp")
	private Date lastUpdatedOn;
}
