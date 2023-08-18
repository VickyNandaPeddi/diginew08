package com.aashdit.digiverifier.itr.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.aashdit.digiverifier.globalConfig.EnvironmentVal;
import com.aashdit.digiverifier.itr.model.CanditateItrResponse;
import com.aashdit.digiverifier.itr.repository.CanditateItrEpfoResponseRepository;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.aashdit.digiverifier.client.securityDetails.ITRSecurityConfig;
import com.aashdit.digiverifier.common.model.ServiceOutcome;
import com.aashdit.digiverifier.config.candidate.model.Candidate;
import com.aashdit.digiverifier.config.candidate.model.CandidateStatus;
import com.aashdit.digiverifier.config.candidate.repository.CandidateCafExperienceRepository;
import com.aashdit.digiverifier.config.candidate.repository.CandidateRepository;
import com.aashdit.digiverifier.config.candidate.repository.CandidateStatusRepository;
import com.aashdit.digiverifier.config.candidate.repository.StatusMasterRepository;
import com.aashdit.digiverifier.config.candidate.service.CandidateService;
import com.aashdit.digiverifier.config.superadmin.repository.ColorRepository;
import com.aashdit.digiverifier.config.superadmin.repository.ServiceSourceMasterRepository;
import com.aashdit.digiverifier.itr.dto.ITRDataFromApiDto;
import com.aashdit.digiverifier.itr.dto.ITRDetailsDto;
import com.aashdit.digiverifier.itr.model.ITRData;
import com.aashdit.digiverifier.itr.repository.ITRDataRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ITRServiceImpl implements ITRService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ITRSecurityConfig itrSecurityConfig;
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private ITRDataRepository itrDataRepository;
	
	@Autowired
	private ServiceSourceMasterRepository serviceSourceMasterRepository;
	
	@Autowired
	private CandidateStatusRepository candidateStatusRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ColorRepository colorRepository;
	
	@Autowired
	private CandidateCafExperienceRepository candidateCafExperienceRepository;
	
	@Autowired
	private StatusMasterRepository statusMasterRepository;
	
	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private CanditateItrEpfoResponseRepository canditateItrEpfoResponseRepository;
	
	@Autowired
	private EnvironmentVal environmentVal;
	
	/**
	 * 
	 * @param headers
	 * @param encodedCedential
	 * @return
	 */
	private HttpHeaders setHeaderDetails (HttpHeaders headers) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
	
	@Override
	public ServiceOutcome<String> getITRDetailsFromITRSite(ITRDetailsDto iTRDetails) {
		ResponseEntity<String> itrTokenResponse = null;
      HttpHeaders headers = new HttpHeaders();
      setHeaderDetails(headers);
      JSONObject request = new JSONObject();
      ServiceOutcome<String> result = new ServiceOutcome<String>();
      try {
      	request.put(itrSecurityConfig.getClientIdValue(),itrSecurityConfig.getClientId());
			request.put(itrSecurityConfig.getClientSecretValue(),itrSecurityConfig.getClientSecret());
			HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
      	
			itrTokenResponse = restTemplate.exchange(itrSecurityConfig.getAccessTokenUrl(), HttpMethod.POST, entity, String.class);
			
			String message=itrTokenResponse.getBody();
			JSONObject obj = new JSONObject(message);
			String token = null;
			if(obj != null) {
				token = obj.getJSONObject("message").getString("access_token");
			}
      	if(itrTokenResponse.getStatusCode() == HttpStatus.OK) {
      		log.info("Token Created Successfully. Calling ITR Transaction Id API");
      		result = getTransactionId(token, iTRDetails);
      		
      	}else if(itrTokenResponse.getStatusCode() == HttpStatus.UNAUTHORIZED){
      		result.setData("User is Unauthorized to access the ITR");
      		result.setOutcome(false);
      		result.setMessage("User is Unauthorized to access the ITR.");
      		log.error("User is Unauthorized to access the ITR");
      	}else if(itrTokenResponse.getStatusCode() == HttpStatus.GATEWAY_TIMEOUT){
      		result.setData("Server response is slow, getting timeout.");
      		result.setOutcome(false);
      		result.setMessage("Server response is slow, getting timeout.");
      		log.error("Server response is slow, getting timeout");
      	}else if(itrTokenResponse.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR){
      		result.setData("Server is down or Not responding at this Moment.");
      		result.setOutcome(false);
      		result.setMessage("Server is down or Not responding at this Moment.");
      		log.error("Server is down or Not responding at this Moment");
      	}
      }catch (Exception jsn) {
      	log.error("Exception occured: ",jsn); // Add the Proper logging Message here
      	result.setData("Something went wrong.");
  		result.setOutcome(false);
  		result.setMessage("Something went wrong.");
		}
	return result;
  }
	
	/**
	 * 
	 * @param accessToken
	 * @param candidateId
	 * @param itrUserName
	 * @param itrPassword
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public ServiceOutcome<String> getTransactionId(String accessToken,ITRDetailsDto iTRDetails) throws JsonProcessingException, IOException{
		ServiceOutcome<String> result = new ServiceOutcome<String>();
		if(StringUtils.isNoneBlank(accessToken) || StringUtils.isNoneBlank(iTRDetails.getCandidateCode())
				|| StringUtils.isNoneBlank(iTRDetails.getUserName()) || StringUtils.isNoneBlank(iTRDetails.getPassword())) {
			ResponseEntity<String> response = null;
			HttpHeaders headers = new HttpHeaders();
			setHeaderDetails(headers);
	        headers.setBearerAuth(accessToken);
	        headers.add("Bearer", accessToken); // This is required as by Simply adding the 
	        HttpEntity<String> request = new HttpEntity<String>(headers);
	        try {
			  	response = restTemplate.exchange(itrSecurityConfig.getTransactionIdUrl(), HttpMethod.GET, request, String.class);
			  	String message=response.getBody();
			  	try {
			  		JSONObject obj = new JSONObject(message);
			  		String transactionId = obj.getString("message");
			  		if(response.getStatusCode() == HttpStatus.OK) {
			  			log.info("Transaction Id Created Successfully. Response returned");
			  			result = getPostLogInInfo(accessToken,iTRDetails.getCandidateCode(), transactionId, iTRDetails.getUserName(), iTRDetails.getPassword());
			  		}else if(response.getStatusCode() == HttpStatus.UNAUTHORIZED){
			      		result.setData("User is Unauthorized to access the ITR");
			      		result.setOutcome(false);
			      		result.setMessage("User is Unauthorized to access the ITR.");
			      		log.error("User is Unauthorized to access the ITR");
			      	}else if(response.getStatusCode() == HttpStatus.GATEWAY_TIMEOUT){
			      		result.setData("Server response is slow, getting timeout.");
			      		result.setOutcome(false);
			      		result.setMessage("Server response is slow, getting timeout.");
			      		log.error("Server response is slow, getting timeout");
			      	}else if(response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR){
			      		result.setData("Server is down or Not responding at this Moment.");
			      		result.setOutcome(false);
			      		result.setMessage("Server is down or Not responding at this Moment.");
			      		log.error("Server is down or Not responding at this Moment");
			      	}
		  		}catch(JSONException jsn) {
		  			result.setData("Something went wrong.");
		      		result.setOutcome(false);
		      		result.setMessage("Something went wrong.");
		  			log.error("JSON Exception occured",jsn);
		  		}
			}catch(HttpClientErrorException e) {
				result.setData("Something went wrong.");
	      		result.setOutcome(false);
	      		result.setMessage("Something went wrong.");
	  			log.error("HttpClientErrorException occured in getTransactionId in ITRServiceImpl-->",e);
			} catch(HttpServerErrorException ex) {
				result.setData("Something went wrong.");
	      		result.setOutcome(false);
	      		result.setMessage("Something went wrong.");
	  			log.error("HttpServerErrorException occured in getTransactionId in ITRServiceImpl-->",ex);
			}
		}else {
			log.error("Invalid ITR Token generated Or Token is null, Please Check the ITR server might be down Or Not Responding");
		}
		return result;
  }
	
	/**
	 * 
	 * @param access_token
	 * @param transactionId
	 * @param candidateId
	 * @param itrUserName
	 * @param itrPassword
	 * @return
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	// This method should accept the details with the below parameters as @RequestParam.
	public ServiceOutcome<String> getPostLogInInfo(String access_token, String candidateId, String transactionId,  String itrUserName, String itrPassword)throws JsonProcessingException, IOException{
		 ServiceOutcome<String> outcome = new ServiceOutcome<String>();	
		if(StringUtils.isNotEmpty(transactionId) && StringUtils.isNotEmpty(access_token) && StringUtils.isNotEmpty(candidateId)) {
			Candidate candidate= candidateRepository.findByCandidateCode(candidateId);
				ResponseEntity<String> response = null;
				HttpHeaders headers = new HttpHeaders();
		        headers.add("Bearer", access_token); 
		        headers.setContentType(MediaType.APPLICATION_JSON);
		        JSONObject request = new JSONObject();
		        SimpleDateFormat sdfp = new SimpleDateFormat("yyyy-MM-dd");
		        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		        try {
		        	request.put("itr-user",itrUserName);
					request.put("itr-pwd",itrPassword);
					HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
					response = restTemplate.exchange(itrSecurityConfig.getPostLoginInfoUrl()+transactionId, HttpMethod.POST, entity, String.class);
					String message=response.getBody();
					String resMsg = message;
					JSONObject obj = new JSONObject(message);
				
					if(!obj.getBoolean("success")) {
						outcome.setData(obj.getString("message"));
						outcome.setOutcome(false);
						outcome.setMessage(obj.getString("message"));
					}else {
						String itrDetails = obj.getString("message");
						if(response.getStatusCode() == HttpStatus.OK && !itrDetails.equals("")) {
			        		log.info("Post Login Information retrieved successfully");
			        		JSONObject form26ASInfo = new JSONObject(itrDetails).getJSONObject("Form26ASInfo");
							resMsg = form26ASInfo.toString();
			        		JSONArray tDSDetails = form26ASInfo.getJSONArray("TDSDetails");		
			        		List<ITRDataFromApiDto> finalItrList = new ArrayList<ITRDataFromApiDto>();
			        		for(int i=0; i<tDSDetails.length();i++) {
			        			JSONObject object = tDSDetails.getJSONObject(i);
			        			if(object.length()!=0) {
			        				JSONObject year = object.getJSONObject("$");
				        			JSONArray tdss = object.getJSONArray("TDSs");
				        			for(int j=0; j<tdss.length();j++) {
				        				JSONObject preFinalObject = tdss.getJSONObject(j);
				        				JSONArray tds = preFinalObject.getJSONArray("TDS");
				        				for(int k=0; k<tds.length();k++) {
											JSONObject finalObject = tds.getJSONObject(k);
											JSONObject tdsData = finalObject.getJSONObject("$");
											final ObjectMapper objectMapper = new ObjectMapper();
											ITRDataFromApiDto itr = objectMapper.readValue(tdsData.toString(), ITRDataFromApiDto.class);
											itr.setAssesmentYear(year.getString("ay"));
											itr.setFinancialYear(year.getString("fy"));
											itr.setFiledDate(dateFormat.parse(itr.getDate()));
											finalItrList.add(itr);
				        				}
				        			}
			        			}
			        		}
			        		List<ITRData> itrDataList = new ArrayList<ITRData>();
			        		for(ITRDataFromApiDto itr: finalItrList) {
			        			ITRData itrData = new ITRData();
			        			BeanUtils.copyProperties(itr, itrData);
			        			itrData.setCandidate(candidate);
			        			itrData.setServiceSourceMaster(serviceSourceMasterRepository.findByServiceCode("ITR"));
			        			itrDataList.add(itrData);
			        		}
			        		if(itrDataList!=null && itrDataList.size()>0) {
			        			itrDataRepository.saveAll(itrDataList);
			        			
//			        			StringBuilder query = new StringBuilder();
//			        			query.append("select itr.tan_no,itr.deductor,max(itr.filed_date),min(itr.filed_date) from t_dgv_candidate_itr itr\n");
//			        			query.append("where itr.candidate_id =:candidateId\n");
//			        			query.append("group by itr.deductor,itr.tan_no\n");
//
//			        			Query resultQuery = entityManager.createNativeQuery(query.toString());
//								resultQuery.setParameter("candidateId", candidate.getCandidateId());
//								 List<CandidateCafExperience> experiencesList = new ArrayList<CandidateCafExperience>();
//								List<Object[]> itrTenureList = resultQuery.getResultList();
//								for(Object[] itrTenure:itrTenureList) {
//									CandidateCafExperience candidateCafExperience = new CandidateCafExperience();
//				        			candidateCafExperience.setCandidateEmployerName(itrTenure[1].toString());
//				        			candidateCafExperience.setOutputDateOfJoining(sdfp.parse(itrTenure[3].toString()));
//				        			candidateCafExperience.setInputDateOfJoining(sdfp.parse(itrTenure[3].toString()));
//				        			candidateCafExperience.setServiceSourceMaster(serviceSourceMasterRepository.findByServiceCode("ITR"));
//				        			candidateCafExperience.setOutputDateOfExit(sdfp.parse(itrTenure[2].toString()));
//				        			candidateCafExperience.setInputDateOfExit(sdfp.parse(itrTenure[2].toString()));
//				        			candidateCafExperience.setCandidate(candidate);
//				        			candidateCafExperience.setCreatedOn(new Date());
//				        			candidateCafExperience.setColor(colorRepository.findByColorCode("GREEN"));
//				        			candidateCafExperience.setTanNo(itrTenure[0].toString());
//				        			experiencesList.add(candidateCafExperience);
//								}
//								if(experiencesList!=null && experiencesList.size()>0) {
//				        			candidateCafExperienceRepository.saveAll(experiencesList);
//
				        			CandidateStatus candidateStatus = candidateStatusRepository.findByCandidateCandidateCode(candidateId);
				        			candidateStatus.setServiceSourceMaster(serviceSourceMasterRepository.findByServiceCode("ITR"));
				        			candidateStatus.setStatusMaster(statusMasterRepository.findByStatusCode("ITR"));
				        			candidateStatus.setLastUpdatedOn(new Date());
				        			candidateStatusRepository.save(candidateStatus);
				        			candidateService.createCandidateStatusHistory(candidateStatus,"CANDIDATE");
//				        		}
			        			

			        			outcome.setData("ITR data recieved successfully.");
					      		outcome.setOutcome(true);
					      		outcome.setMessage("ITR data recieved successfully.");
					      		log.info("ITR data recieved successfully");
			        		}else {
			        			CandidateStatus candidateStatus = candidateStatusRepository.findByCandidateCandidateCode(candidateId);
			        			candidateStatus.setServiceSourceMaster(serviceSourceMasterRepository.findByServiceCode("ITR"));
			        			candidateStatus.setLastUpdatedOn(new Date());
			        			candidateStatusRepository.save(candidateStatus);
			        			candidateService.createCandidateStatusHistory(candidateStatus,"CANDIDATE");
			        			outcome.setData("No Data Found.");
					      		outcome.setOutcome(true);
					      		outcome.setMessage("No Data Found.");
			        		}
			        	}else if(response.getStatusCode() == HttpStatus.UNAUTHORIZED){
				      		outcome.setData("User is Unauthorized to access the ITR");
				      		outcome.setOutcome(false);
				      		outcome.setMessage("User is Unauthorized to access the ITR.");
				      		log.error("User is Unauthorized to access the ITR");
				      	}else if(response.getStatusCode() == HttpStatus.GATEWAY_TIMEOUT){
				      		outcome.setData("Server response is slow, getting timeout.");
				      		outcome.setOutcome(false);
				      		outcome.setMessage("Server response is slow, getting timeout.");
				      		log.error("Server response is slow, getting timeout");
				      	}else if(response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR){
				      		outcome.setData("Server is down or Not responding at this Moment.");
				      		outcome.setOutcome(false);
				      		outcome.setMessage("Server is down or Not responding at this Moment.");
				      		log.error("Server is down or Not responding at this Moment");
				      	}
					}
					CanditateItrResponse canditateItrEpfoResponse = canditateItrEpfoResponseRepository
						.findByCandidateId(candidate.getCandidateId()).orElse(new CanditateItrResponse());
					canditateItrEpfoResponse.setForm26AsResponse(resMsg);
					canditateItrEpfoResponse.setCandidateId(candidate.getCandidateId());
					canditateItrEpfoResponse.setCandidate(candidate);
					canditateItrEpfoResponse.setCreatedOn(new Date());
					canditateItrEpfoResponse.setLastUpdatedOn(new Date());
					canditateItrEpfoResponseRepository.save(canditateItrEpfoResponse);
					return outcome;
		        }catch (Exception jsn) {
		        	log.error("Exception occured in itr: ",jsn); 
		        	outcome.setData("Something went wrong.");
		      		outcome.setOutcome(false);
		      		outcome.setMessage("Something went wrong.");
		  			log.error("JSON Exception occured",jsn);
		  			return outcome;
				}
				
			}else {
				log.error("Either ITR Token Or TransactionId Or candidateId Or UserName Or Password is not provided / Missing");
				outcome.setData("Either ITR Token Or TransactionId Or candidateId Or UserName Or Password is not provided / Missing");
	      		outcome.setOutcome(false);
	      		outcome.setMessage("Something went wrong.");
				return  outcome;
			}
		
	}

}
