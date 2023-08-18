package com.aashdit.digiverifier.config.superadmin.dto;

import lombok.Data;

@Data
public class OrganizationDto {

	Long organizationId;
	String organizationName;
	
	
	public OrganizationDto(Object[] value) {
		this.organizationId = Long.parseLong(value[0].toString());
		this.organizationName = value[1].toString();
	}
}
