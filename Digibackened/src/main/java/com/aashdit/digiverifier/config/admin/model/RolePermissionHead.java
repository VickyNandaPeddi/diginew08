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

import lombok.Data;


@Data
@Entity
@Table(name = "t_dgv_role_permission_head")
public class RolePermissionHead implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6815088365746878755L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "permission_head_id")
	private Long permissionHeadId;
	
	@NotBlank
	@Column(name = "permission_head_name")
	private String permissionHeadName;
	

	@Column(name = "is_active")
	private Boolean isActive;
	
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
