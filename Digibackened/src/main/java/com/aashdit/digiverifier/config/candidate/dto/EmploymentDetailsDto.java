package com.aashdit.digiverifier.config.candidate.dto;


import java.text.SimpleDateFormat;
import java.util.Date;

import com.aashdit.digiverifier.config.candidate.model.CandidateCafExperience;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class EmploymentDetailsDto {

	Long candidateId;
	
	String companyName;
	
	String Source;
	
	String SourceCode;
	
	String outputTenure;
	
	String inputTenure;
	
	String result;
	
	String colorCode;
	
	String colorHexCode;
	
	Date outputDoj;
	
	Date outputDoe;
	
	Date inputDoj;
	
	Date inputDoe;
	
	String gap;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public EmploymentDetailsDto(CandidateCafExperience experience, String inputTenure, String outputTenure, String gap) {
		try {
			this.candidateId = experience.getCandidate().getCandidateId();
			this.companyName = experience.getCandidateEmployerName();
			this.Source = experience.getServiceSourceMaster()!=null?experience.getServiceSourceMaster().getServiceName():"Candidate";
			this.SourceCode = experience.getServiceSourceMaster()!=null?experience.getServiceSourceMaster().getServiceCode():"CANDIDATE";
			this.outputTenure = outputTenure;
			this.inputTenure = inputTenure;
			this.result = experience.getColor().getColorName();
			this.colorCode = experience.getColor().getColorCode();
			this.colorHexCode = experience.getColor().getColorHexCode();
			this.outputDoj = experience.getOutputDateOfJoining();
			this.outputDoe = experience.getOutputDateOfExit();
			this.inputDoj = experience.getInputDateOfJoining();
			this.inputDoe = experience.getInputDateOfExit();
			this.gap = gap;
			
		}catch(Exception e){
			log.error("Exception occured in EmploymentDetailsDto-->",e); 
		}
	}
}
