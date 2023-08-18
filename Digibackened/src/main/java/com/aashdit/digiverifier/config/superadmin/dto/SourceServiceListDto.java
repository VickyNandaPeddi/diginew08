package com.aashdit.digiverifier.config.superadmin.dto;

import java.util.List;

import com.aashdit.digiverifier.config.superadmin.model.ServiceSourceMaster;
import com.aashdit.digiverifier.config.superadmin.model.Source;

import lombok.Data;

@Data
public class SourceServiceListDto {

	private Source source;
	private List<ServiceSourceMaster> serviceSourceMaster;
}
