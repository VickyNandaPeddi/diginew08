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

import com.aashdit.digiverifier.config.admin.model.User;

import lombok.Data;

@Data
@Entity
@Table(name = "t_dgv_tolerance_config_master")
public class ToleranceConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1207598841063656236L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tolerance_master_id")
	private Long toleranceMasterId;
	
	@Column(name = "tenure")
	private Integer tenure;
	
	@Column(name = "data_retention_period")
	private Integer dataRetentionPeriod;
	
	@Column(name = "anonymous_data_retention_period")
	private Integer anonymousDataRetentionPeriod;
	
	@Column(name = "dual_employment")
	private Integer dualEmployment;
	
	@Column(name = "dual_employment_tolerance")
	private String dualEmploymentTolerance;
	
	@Column(name = "number_yrs_of_experience")
	private String numberYrsOfExperience;
	
	@Column(name = "number_of_employment")
	private Integer numberOfEmployment;
	
	@Column(name = "access_to_relatives_bill")
	private Boolean accessToRelativesBill;
	
	@Column(name = "updated_timestamp")
	private Date updatedTimestamp;
	
	@Column(name = "number_of_latest_education")
	private Integer numberOfLatestEducation;
	
	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color color;
	
	@ManyToOne
	@JoinColumn(name = "organization_id")
	private Organization organization;
	
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
