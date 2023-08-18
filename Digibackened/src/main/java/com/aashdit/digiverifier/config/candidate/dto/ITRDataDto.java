package com.aashdit.digiverifier.config.candidate.dto;

import com.aashdit.digiverifier.itr.model.ITRData;
import lombok.Data;

import java.util.List;

@Data
public class ITRDataDto {
	
	private String candidateName;
	
	private String panNumber;
	
	private List<ITRData> itrDataList;
	
}
