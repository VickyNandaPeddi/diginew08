package com.aashdit.digiverifier.itr.model;

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
import javax.validation.constraints.NotNull;

import com.aashdit.digiverifier.config.candidate.model.Candidate;
import com.aashdit.digiverifier.config.superadmin.model.ServiceSourceMaster;

import lombok.Data;

@Data
@Entity
@Table(name="t_dgv_candidate_itr")
public class ITRData implements Serializable{

	private static final long serialVersionUID = 8527334490159831700L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "itr_id")
	private Long itrId;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;
	
	@Column(name = "deductor")
	private String deductor;
	
	@Column(name = "tan_no")
	private String tan;
	
	@Column(name = "amount")
	private String amount;
	
	@Column(name = "tds")
	private String tds;
	
	@Column(name = "date_value")
	private String date;
	
	@Column(name = "section_value")
	private String section;
	
	@Column(name = "assesment_year")
	private String assesmentYear;
	
	@Column(name = "financial_year")
	private String financialYear;
	
	@ManyToOne
	@JoinColumn(name = "service_source_master_id")
	private ServiceSourceMaster serviceSourceMaster;
	
	@Column(name = "filed_date")
	private Date filedDate;
	
}
