package com.aashdit.digiverifier.common.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EpfoItrResponseData {
	
	private String name;
		
	private String dob;
		
	private String pan;
		
	private String uan;
	
	private List<EmployerData> employerList = new ArrayList<>();
		
		
	
}
