package com.aashdit.digiverifier.config.superadmin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "t_dgv_source_service_master")
public class ServiceSourceMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1760822105264530176L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "source_service_id")
	private Long sourceServiceId;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "source_id")
	private Source source;
	
	@Column(name = "service_name")
	private String serviceName;
	
	@Column(name = "service_api")
	private String serviceApi;
	
	@Column(name = "service_code")
	private String serviceCode;

}
