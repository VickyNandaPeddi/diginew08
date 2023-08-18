package com.aashdit.digiverifier.config.superadmin.dto;

import java.util.List;

import com.aashdit.digiverifier.config.candidate.dto.CandidateDetailsDto;
import com.aashdit.digiverifier.config.candidate.dto.CandidateStatusCountDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDto {

    private String fromDate;

    private String toDate;

    private List<Long> organizationIds;

    private List<String> activityCode;

    private List<CandidateStatusCountDto> candidateStatusCountDto;

    private Long userId;

    private String status;

    private List<CandidateDetailsDto> candidateDtoList;
}
