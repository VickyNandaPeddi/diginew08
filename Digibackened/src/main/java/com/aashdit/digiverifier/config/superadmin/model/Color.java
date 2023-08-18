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
import javax.validation.constraints.NotBlank;

import com.aashdit.digiverifier.config.admin.model.User;

import lombok.Data;

@Data
@Entity
@Table(name = "t_dgv_color_master")
public class Color implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 798446441583277554L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "color_id")
	private Long colorId;
	
	@NotBlank
	@Column(name = "color_code")
	private String colorCode;
	
	@Column(name = "color_name")
	private String colorName;
	
	@Column(name = "color_hex_code")
	private String colorHexCode;
	
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
