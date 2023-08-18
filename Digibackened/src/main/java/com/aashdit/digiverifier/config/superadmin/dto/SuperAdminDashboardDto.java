package com.aashdit.digiverifier.config.superadmin.dto;

import java.util.List;

import com.aashdit.digiverifier.config.candidate.dto.CandidateStatusCountDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuperAdminDashboardDto {
	
	private String fromDate;
	
	private String toDate;
	
	private Long organizationId;
	
	private Long sourceId;
	
	private List<CandidateStatusCountDto> candidateStatusCountDto;
	
	private List<ServiceConfigdashboardDto> serviceConfigdashboardDto;

}
