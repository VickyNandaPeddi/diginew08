package com.aashdit.digiverifier.digilocker.service;

import javax.servlet.http.HttpServletResponse;
import com.aashdit.digiverifier.digilocker.dto.DigiLockerDetailsDto;
import com.aashdit.digiverifier.common.model.ServiceOutcome;

public interface DigilockerService {

	String getDigilockerDetails(String code, String state, HttpServletResponse response, String action);
	
	String getUserDetails(String accessToken,String code, String candidateCode,HttpServletResponse res, String action);

	// String getUserDetails(String accessToken,String code, String candidateCode,String res, String action);

	ServiceOutcome<String> getDigiLockerdetail(DigiLockerDetailsDto digilockerDetails);

	ServiceOutcome<DigiLockerDetailsDto> getDigiTansactionid(String candidateCode);

	ServiceOutcome<String> getDigiLockerAlldetail(DigiLockerDetailsDto digilockerDetails,HttpServletResponse res);
	
	ServiceOutcome<Boolean> getDLEdudocument(String digidetails,HttpServletResponse res) ;
}
