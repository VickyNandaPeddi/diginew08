package com.aashdit.digiverifier.config.superadmin.dto;

import java.util.List;

import com.aashdit.digiverifier.config.superadmin.model.ToleranceConfig;

import lombok.Data;

@Data
public class ServiceConfigurationDto {
	
	private Long serviceConfigurationDtoId;
	private List<Long> sourceServiceId;
	private List<Long> executiveId;
	private List<Long> weight;
	private List<String> serviceCode;
	private ToleranceConfig toleranceConfig;
	private Long organizationId;
	private Long toleranceConfigId;
	private Integer tenure;
	
	private Integer dataRetentionPeriod;
	
	private Integer anonymousDataRetentionPeriod;
	
	private Integer dualEmployment;
	
	private String dualEmploymentTolerance;
	
	private String numberYrsOfExperience;
	
	private Integer numberOfEmployment;
	
	private Boolean accessToRelativesBill;
	
	private Integer numberOfLatestEducation;
	
	private Integer colorId;
	
}
