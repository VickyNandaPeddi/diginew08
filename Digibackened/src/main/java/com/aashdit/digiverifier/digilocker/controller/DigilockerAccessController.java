package com.aashdit.digiverifier.digilocker.controller;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import com.aashdit.digiverifier.globalConfig.EnvironmentVal;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aashdit.digiverifier.client.securityDetails.DigilockerClient;
import com.aashdit.digiverifier.common.model.ServiceOutcome;
import com.aashdit.digiverifier.config.candidate.model.Candidate;
import com.aashdit.digiverifier.config.candidate.model.CandidateStatus;
import com.aashdit.digiverifier.config.candidate.repository.CandidateRepository;
import com.aashdit.digiverifier.config.candidate.service.CandidateService;
import com.aashdit.digiverifier.config.superadmin.model.ServiceSourceMaster;
import com.aashdit.digiverifier.config.superadmin.service.ServiceSource;
import com.aashdit.digiverifier.constants.DigilockerConstants;
import com.aashdit.digiverifier.digilocker.service.DigilockerService;
import com.aashdit.digiverifier.digilocker.dto.DigiLockerDetailsDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.MediaType;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/allowAll")
public class DigilockerAccessController {
	
	@Autowired
	private DigilockerClient clientSecurityDetails;
	
	@Autowired
	private DigilockerService digilockerService;
	
	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private ServiceSource serviceSource;
	
	@Autowired
	private EnvironmentVal environmentVal;
	
	@ApiOperation(value = "Getting the access code from Digilocker site")
	@GetMapping(value = "/checkMail/{candidateCode}")
    public void redirect(@PathVariable String candidateCode,HttpServletResponse res){
		try {
		if(candidateCode!=null) {
			ServiceOutcome<CandidateStatus> candidate = candidateService.getCandidateStatusByCandidateCode(candidateCode);
			ServiceOutcome<List<String>> configCodes = candidateService.getServiceConfigCodes(candidateCode, null);
			if(candidate.getOutcome()) {
				switch (candidate.getData().getStatusMaster().getStatusCode()) {
				case "INVITATIONSENT": case "REINVITE":
					if(!candidate.getData().getCandidate().getIsLoaAccepted()) {
						res.sendRedirect(environmentVal.getLetterAuthPage()+candidateCode);
					}else {
						// String responseString = createAccessCodeUriForSelf(candidateCode);
						String responseString = environmentVal.getRedirectAngularToDigilocker()+candidateCode;
						// String responseString="http://ec2-3-7-78-21.ap-south-1.compute.amazonaws.com:4200/#/candidate/digiLocker/"+candidateCode;
						System.out.println(responseString+"==================================");
						res.sendRedirect(responseString);
						
					}
					
					break;
				case "DIGILOCKER":
					if(candidate.getData().getCandidate().getIsFresher()==null) {
						res.sendRedirect(environmentVal.getIsFreshPage()+candidateCode);
					}else {
						if(!candidate.getData().getCandidate().getIsFresher()) {
							if(configCodes.getOutcome()) {
								if(configCodes.getData().contains("ITR") && candidate.getData().getStatusMaster().getStatusCode().equals("DIGILOCKER")) {
									ServiceOutcome<ServiceSourceMaster> ssm = serviceSource.getServiceSourceMasterByServiceCode("DIGILOCKER");
									res.sendRedirect(ssm.getData().getServiceApi()+candidateCode);
								}else if(configCodes.getData().contains("EPFO") && !candidate.getData().getCandidate().getIsUanSkipped() && (candidate.getData().getStatusMaster().getStatusCode().equals("DIGILOCKER") || candidate.getData().getStatusMaster().getStatusCode().equals("ITR"))) {
									ServiceOutcome<ServiceSourceMaster> ssm = serviceSource.getServiceSourceMasterByServiceCode("ITR");
									res.sendRedirect(ssm.getData().getServiceApi()+candidateCode);
								}else if(configCodes.getData().contains("RELBILLTRUE") && (candidate.getData().getStatusMaster().getStatusCode().equals("DIGILOCKER") || candidate.getData().getStatusMaster().getStatusCode().equals("ITR")|| candidate.getData().getStatusMaster().getStatusCode().equals("EPFO"))) {
									res.sendRedirect(environmentVal.getRelativeBillPage()+candidateCode);
								}else {
									res.sendRedirect(environmentVal.getCafPage()+candidateCode);
								}
							}
						}else if(candidate.getData().getCandidate().getIsFresher() && configCodes.getData().contains("RELBILLTRUE")) {
							res.sendRedirect(environmentVal.getRelativeBillPage()+candidateCode);
						}else if(candidate.getData().getCandidate().getIsFresher() && configCodes.getData().contains("RELBILLFALSE")){
							res.sendRedirect(environmentVal.getCafPage()+candidateCode);
						}
					}
					
					
					break;
				case "ITR": 
					if(configCodes.getOutcome()) {
						if(configCodes.getData().contains("EPFO") && candidate.getData().getCandidate().getIsUanSkipped()==null) {
							res.sendRedirect(environmentVal.getUanConfirmPage()+candidateCode+"/1");
						}else if(configCodes.getData().contains("EPFO") && !candidate.getData().getCandidate().getIsUanSkipped()) {
							ServiceOutcome<ServiceSourceMaster> ssm = serviceSource.getServiceSourceMasterByServiceCode("ITR");
							res.sendRedirect(ssm.getData().getServiceApi()+candidateCode);
						}else if(configCodes.getData().contains("EPFO") && candidate.getData().getCandidate().getIsUanSkipped()) {
							//res.sendRedirect(environmentVal.getUanConfirmPage()+candidateCode+"/2");
							if(configCodes.getData().contains("RELBILLTRUE")) {
								res.sendRedirect(environmentVal.getRelativeBillPage()+candidateCode);
							}else {
								res.sendRedirect(environmentVal.getCafPage()+candidateCode);
							}
						}else {
							res.sendRedirect(environmentVal.getCafPage()+candidateCode);
						}
					}
					break;
				case "EPFO":
					
					if(configCodes.getOutcome()) {
						if(configCodes.getData().contains("RELBILLTRUE")) {
							res.sendRedirect(environmentVal.getRelativeBillPage()+candidateCode);
						}else {
							res.sendRedirect(environmentVal.getCafPage()+candidateCode);
						}
					}
					break;
				case "INVITATIONEXPIRED": case "PROCESSDECLINED":
					res.sendRedirect(environmentVal.getStaticPage()+candidate.getData().getStatusMaster().getStatusCode());
					break;
				case "PENDINGAPPROVAL": case "FINALREPORT":
					res.sendRedirect(environmentVal.getStaticPage()+"SUBMITTED");
					break;
				case "CANCELLED":
					res.sendRedirect(environmentVal.getCafPage()+"CANCELLED");
					break;
				case "RELATIVEADDRESS":
					res.sendRedirect(environmentVal.getCafPage()+candidateCode);
					break;
				}
			}else{
				log.error("CANDIDATE NIKHOJA--->Candidate not found-->"+candidateCode);
			}
		}else {
			log.error("CANDIDATE CODE NAHI--->Candidate code is either empty or null-->"+candidateCode);
		}
		
		}catch(Exception e) {
			log.error("Something went wrong in redirect method-->"+candidateCode,e);
		}
    }
	
	@ApiOperation(value = "Decline in letter of authorization")
	@PostMapping(value = "/declineAuthLetter")
    public ResponseEntity<ServiceOutcome<Boolean>> declineAuthLetter(@RequestBody String candidateObj){
		ServiceOutcome<Boolean> outcome=new ServiceOutcome<Boolean>();
		if(candidateObj!=null) {
			String candidateCode = new JSONObject(candidateObj).getString("candidateCode");
			outcome = candidateService.declineAuthLetter(candidateCode);
		}
		
		return new ResponseEntity<ServiceOutcome<Boolean>>(outcome, HttpStatus.OK);
    }
	
	
	/**
	 * createAccessCodeUri
	 * @param state
	 * @return
	 */
	private String createAccessCodeUriForSelf(String state) {
		String access_token_url = null;
		access_token_url = clientSecurityDetails.getAccessCodeUri();
		access_token_url += DigilockerConstants._RESPONSE_TYPE+clientSecurityDetails.getResponseType();
		access_token_url += DigilockerConstants._URI_CLIENT_ID+clientSecurityDetails.getUsername();
		access_token_url += DigilockerConstants._CLIENT_REDIRECT_URI+clientSecurityDetails.getAccessCodeRedirectUri();
		access_token_url += DigilockerConstants._CLIENT_STATE+state; // This Code needs to be replaced with  
		System.out.println(access_token_url+"--------------------");
		return access_token_url;
	}

	
	
	/**
	 * 
	 * @param code
	 * @param stateR
	 * @return
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	@ApiOperation(value = "generating an access token from the Digilocker site")
	@GetMapping(value = "/getDigilockerDetails")
	public ResponseEntity<String> getDigilockerDetails(String code,String state,HttpServletResponse response) throws JsonProcessingException, IOException {
		System.out.println("code:"+code);
		String message = digilockerService.getDigilockerDetails(code,state,response,"SELF");
		return new ResponseEntity<>(message, HttpStatus.OK);
    }
	
	public String decodeBase64(String encodedData){
		String decodedByteString = null;
		byte[] base64Decoded = DatatypeConverter.parseBase64Binary(encodedData);
        decodedByteString =(new String(base64Decoded));
        return decodedByteString;
	}
	
	@ApiOperation(value = "Getting the access code from Digilocker site for relation")
	@PostMapping(value = "/verifyRelation")
    public ResponseEntity<ServiceOutcome<String>> relationVerify(@RequestBody String candidateObj){
		ServiceOutcome<String> outcome = new ServiceOutcome<>();
		try {
		if(candidateObj!=null) {
			String candidateCode = new JSONObject(candidateObj).getString("candidateCode");
			ServiceOutcome<CandidateStatus> candidate = candidateService.getCandidateStatusByCandidateCode(candidateCode);
			if(candidate.getOutcome()) {
				String response = createAccessCodeUriForRelation(candidateCode);
				outcome.setData(candidateCode + "#" + candidate.getData().getStatusMaster().getStatusCode() + "#" + response);
				outcome.setMessage(candidate.getMessage());
				outcome.setOutcome(true);
			}else{
				outcome.setData("Candidate not found");
				outcome.setMessage("Candidate not found.");
				outcome.setOutcome(false);
			}
		}else {
			outcome.setData("CANDIDATE CODE not found");
			outcome.setMessage("Candidate code is either empty or null.");
			outcome.setOutcome(false);
		}
		
		}catch(Exception e) {
			outcome.setData("KICHI GOTE HELA AAU");
			outcome.setMessage("Something went wrong.");
			outcome.setOutcome(false);
			}
		
		return new ResponseEntity<ServiceOutcome<String>>(outcome, HttpStatus.OK);
    }
	
	@ApiOperation(value = "generating an access token from the Digilocker site and get relationship address details.")
	@GetMapping(value = "/getRelationDigilockerDetails" )
	public ResponseEntity<String> getRelationDigilockerDetails(String code,String state,HttpServletResponse response) throws JsonProcessingException, IOException {
		String message = digilockerService.getDigilockerDetails(code,state,response,"RELATION");
		return new ResponseEntity<>(message, HttpStatus.OK);
    }
	
	private String createAccessCodeUriForRelation(String state) {
		String access_token_url = null;
		access_token_url = clientSecurityDetails.getAccessCodeUri();
		access_token_url += DigilockerConstants._RESPONSE_TYPE+clientSecurityDetails.getResponseType();
		access_token_url += DigilockerConstants._URI_CLIENT_ID+clientSecurityDetails.getRelationUsername();
		access_token_url += DigilockerConstants._CLIENT_REDIRECT_URI+clientSecurityDetails.getAccessCodeRelationRedirectUri();
		access_token_url += DigilockerConstants._CLIENT_STATE+state; // This Code needs to be replaced with  
		
		return access_token_url;
	}
	
	@ApiOperation(value = "Create Access Code URI for self .")
	@PostMapping(value = "/createAccessCodeUriForSelf" )
	public ResponseEntity<ServiceOutcome<String>> createAccessCodeUri(@RequestBody String candidateObj,HttpServletResponse res) throws JsonProcessingException, IOException {
		ServiceOutcome<String> outcome = new ServiceOutcome<>();
		if(candidateObj!=null) {
			String candidateCode = new JSONObject(candidateObj).getString("candidateCode");
			ServiceOutcome<CandidateStatus> candidateStatus = candidateService.getCandidateStatusByCandidateCode(candidateCode);
			System.out.println(candidateStatus.getData().getStatusMaster().getStatusCode()+"status");
			if(candidateStatus.getData().getStatusMaster().getStatusCode().equals("PROCESSDECLINED")) {
				outcome.setData(null);
				outcome.setMessage("You have already declined the process.");
				outcome.setOutcome(false);
			}else {
				Candidate candidate = candidateService.setIsLoaAccepted(candidateCode).getData();
				// String response = createAccessCodeUriForSelf(candidateCode);
				
				// outcome.setData(response);
				// System.out.println(response+"------------------response");
				outcome.setOutcome(true);
			}
			
		}else {
			outcome.setData(null);
			outcome.setMessage("Candidate not found.");
			outcome.setOutcome(false);
		}
		return new ResponseEntity<ServiceOutcome<String>>(outcome, HttpStatus.OK);
		
    }
	
	@GetMapping(value = "/temp" )
	public ResponseEntity<ServiceOutcome<String>> getAadhar(@RequestParam("token") String token,@RequestParam("candidateCode") String candidateCode,HttpServletResponse res){
		digilockerService.getUserDetails(token,"code",candidateCode,res,"SELF");
		return new ResponseEntity<ServiceOutcome<String>>(new ServiceOutcome<>(), HttpStatus.OK);
	}

	

	@ApiOperation(value = "Getting the digi details from Digilocker site")
	@PostMapping(value = "/getDigiLockerdetail")
	public ServiceOutcome<String> getDigiLockerdetail(@RequestBody DigiLockerDetailsDto digilockerDetails){
		System.out.println(digilockerDetails+"digi----------------details");
		ServiceOutcome<String> response =  digilockerService.getDigiLockerdetail(digilockerDetails);
		return response;
	}

	@ApiOperation(value = "Getting the digi all details from Digilocker site")
	@PostMapping(value = "/getDigiLockerAlldetail")
	public ServiceOutcome<String> getDigiLockerAlldetail(@RequestBody DigiLockerDetailsDto digilockerDetails,HttpServletResponse res){
		System.out.println(digilockerDetails+"digi----------------details");
		ServiceOutcome<String> response =  digilockerService.getDigiLockerAlldetail(digilockerDetails,res);
		return response;
	}


	@ApiOperation(value = "generating an access token from the DigiLocker site")
	@GetMapping(value = "/getDigiTansactionid/{candidateCode}")
	public ServiceOutcome<DigiLockerDetailsDto> getDigiTansactionid(@PathVariable String candidateCode) {
		System.out.println(candidateCode+"------------candidatecode-----------------");
		ServiceOutcome<DigiLockerDetailsDto> svcSearchResult = new ServiceOutcome<>();
		if(candidateCode!=null) {
			svcSearchResult = digilockerService.getDigiTansactionid(candidateCode);
		}else {
			svcSearchResult.setData(null);
			svcSearchResult.setOutcome(false);
			svcSearchResult.setMessage("Candidate code is null.");
		}
		
		return svcSearchResult;
    }
	

	@ApiOperation("Get all Candidate digilocker details")
	@PostMapping(value = "/getDLEdudocument",consumes = {  MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<ServiceOutcome<Boolean>> getDLEdudocument(@RequestParam String digidetails,@RequestHeader("Authorization") String authorization,HttpServletResponse res) {
		System.out.println(digidetails+"============");
		ServiceOutcome<Boolean> svcSearchResult=digilockerService.getDLEdudocument(digidetails,res);
		return new ResponseEntity<ServiceOutcome<Boolean>>(svcSearchResult, HttpStatus.OK);

	}
	
}
