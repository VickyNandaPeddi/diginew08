package com.aashdit.digiverifier.config.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateStatusCountDto {

    private String statusName;
    private String statusCode;
    private Integer count;
    Long organizationId;
    String organizationName;

    public CandidateStatusCountDto(String statusName, String statusCode, Integer count) {
        super();
        this.statusName = statusName;
        this.statusCode = statusCode;
        this.count = count;
    }

}
