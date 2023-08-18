package com.aashdit.digiverifier.config.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.aashdit.digiverifier.common.enums.ContentSubCategory;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentFileDto {
	private Long id;
	private byte[] document;
	private ContentSubCategory contentSubCategory;
}
