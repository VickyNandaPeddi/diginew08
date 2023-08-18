package com.aashdit.digiverifier.config.superadmin.dto;

import lombok.Data;

@Data
public class ServiceConfigdashboardDto {

	Long organizationId;
	
	String organizationName;
	
	Double rate;
	
	public ServiceConfigdashboardDto(Object[] data){
		this.organizationId=Long.parseLong(data[0].toString());
		this.organizationName=data[1].toString();
		this.rate=Double.parseDouble(data[2].toString());
	}
}
