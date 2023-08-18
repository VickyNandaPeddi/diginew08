package com.aashdit.digiverifier.config.candidate.dto;

import com.aashdit.digiverifier.epfo.model.EpfoData;
import lombok.Data;

import java.util.List;

@Data
public class EPFODataDto {
	
	private String candidateName;
	
	private String UANno;
	
	private List<EpfoDataResDTO> epfoDataList;
}
