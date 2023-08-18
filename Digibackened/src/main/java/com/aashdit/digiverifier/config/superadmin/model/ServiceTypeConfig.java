package com.aashdit.digiverifier.config.superadmin.model;

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

import com.aashdit.digiverifier.config.admin.model.User;

import lombok.Data;

@Data
@Entity
@Table(name = "t_dgv_service_type_config_master")
public class ServiceTypeConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -286816654020225958L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "t_dgv_service_type_config_id")
	private Long serviceTypeConfigId;
	
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "organization_id")
	private Organization organization;
	
	
	@ManyToOne
	@JoinColumn(name = "service_id")
	private ServiceMaster serviceMaster;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "source_service_id")
	private ServiceSourceMaster serviceSourceMaster;
	
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
