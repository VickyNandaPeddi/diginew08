package com.aashdit.digiverifier.config.candidate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="t_dgv_status_master")
public class StatusMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7858395421908518082L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "status_master_id")
	private Long statusMasterId;
	
	@Column(name = "status_name")
	private String statusName;
	
	@Column(name = "status_code")
	private String statusCode;

}
