package com.aashdit.digiverifier.config.admin.model;

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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "t_dgv_role_master")
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -763414907911681633L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Long roleId;
	
	@NotBlank
	@Column(name = "role_name")
	private String roleName;
	
	@NotBlank
	@Column(name = "role_code")
	private String roleCode;
	
	@NotNull
	@Column(name = "updated_timestamp")
	private Date updatedTimestamp;
	
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
	
	@Column(name = "role_access")
	private String roleAccess;

}
