package com.aashdit.digiverifier.epfo.model;

import com.aashdit.digiverifier.config.candidate.model.Candidate;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "t_dgv_candidate_epfo")
public class EpfoData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "epfo_id")
	private Long epfoId;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;
	
	@Column(name = "uan")
	private String uan;
	
	@Column(name = "name")
	String name;
	
	@Column(name = "company")
	String company;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "doe")
	Date doe;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "doj")
	Date doj;
}
