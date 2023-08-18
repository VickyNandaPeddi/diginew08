package com.aashdit.digiverifier.config.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateFileDto {
	private Long id;
	private byte[] document;
	private String colorName;
}
