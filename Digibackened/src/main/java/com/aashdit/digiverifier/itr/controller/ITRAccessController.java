package com.aashdit.digiverifier.itr.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aashdit.digiverifier.common.model.ServiceOutcome;
import com.aashdit.digiverifier.itr.dto.ITRDetailsDto;
import com.aashdit.digiverifier.itr.service.ITRService;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.annotations.ApiOperation;

@RequestMapping(value = "/api/allowAll")
@RestController
public class ITRAccessController {

	@Autowired
	private ITRService itrService;
	
	
	/**
	 * 
	 * @param candidateId
	 * @param itrUserName
	 * @param itrPassword
	 * @return
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	@ApiOperation(value = "generating an access token from the ITR site")
	@PostMapping(value = "/getITRDetailsFromITRSite")
	public ServiceOutcome<String> getITRDetailsFromITRSite(@RequestBody ITRDetailsDto iTRDetails) throws JsonProcessingException, IOException {
//		candidateId = "123456"; // Please remove this hard-coaded value, as this API should Be Called along with candidate ID as a Request Parameter.
//		itrUserName="AFVPD2344E";// Please remove this hard-coaded value as this should be pass as a parameter from the customized ITR screen
//		itrPassword="Sweet123!@#"; // // Please remove this hard-coaded value as this should be pass as a parameter from the customized ITR screen
		ServiceOutcome<String> svcSearchResult = itrService.getITRDetailsFromITRSite(iTRDetails);
		return svcSearchResult;

	    }
	
	
	
}
