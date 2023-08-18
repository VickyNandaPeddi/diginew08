package com.aashdit.digiverifier.config.candidate.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.aashdit.digiverifier.common.enums.ContentViewType;
import com.aashdit.digiverifier.common.service.ContentService;
import com.aashdit.digiverifier.config.superadmin.Enum.ReportType;
import com.aashdit.digiverifier.config.superadmin.dto.ReportSearchDto;
import com.aashdit.digiverifier.config.superadmin.service.ReportService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aashdit.digiverifier.common.model.ServiceOutcome;
import com.aashdit.digiverifier.config.candidate.dto.CandidateCafAddressDto;
import com.aashdit.digiverifier.config.candidate.dto.CandidateCafEducationDto;
import com.aashdit.digiverifier.config.candidate.dto.CandidateCafExperienceDto;
import com.aashdit.digiverifier.config.candidate.dto.CandidationApplicationFormDto;
import com.aashdit.digiverifier.config.candidate.model.Candidate;
import com.aashdit.digiverifier.config.candidate.model.CandidateCafAddress;
import com.aashdit.digiverifier.config.candidate.model.CandidateCafExperience;
import com.aashdit.digiverifier.config.candidate.model.QualificationMaster;
import com.aashdit.digiverifier.config.candidate.model.SuspectClgMaster;
import com.aashdit.digiverifier.config.candidate.model.SuspectEmpMaster;
import com.aashdit.digiverifier.config.candidate.service.CandidateService;
import com.aashdit.digiverifier.config.candidate.dto.CandidateDetailsDto;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/allowAll")
public class CandidateApplicationFormController {
	
	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private ReportService reportService;
	
	
	@Autowired
	private ContentService contentService;
	
	@ApiOperation("Get Qualification List")
	@GetMapping("/getQualificationList")
	public ResponseEntity<ServiceOutcome<List<QualificationMaster>>> getQualificationList() {
		ServiceOutcome<List<QualificationMaster>> svcSearchResult = candidateService.getQualificationList();
		return new ResponseEntity<ServiceOutcome<List<QualificationMaster>>>(svcSearchResult, HttpStatus.OK);
	}
	
	@ApiOperation("Get all Candidate Application form details")
	@GetMapping("/candidateApplicationFormDetails/{candidateCode}")
	public ResponseEntity<ServiceOutcome<?>> candidateApplicationFormDetails(@PathVariable("candidateCode")String candidateCode) {
		ServiceOutcome<CandidationApplicationFormDto> svcSearchResult= candidateService.candidateApplicationFormDetails(candidateCode);
		return new ResponseEntity<ServiceOutcome<?>>(svcSearchResult, HttpStatus.OK);
	}
	
	@ApiOperation("Save And Update Education Details")
	@PostMapping(value = "/saveNUpdateEducation",consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<ServiceOutcome<Boolean>> saveNUpdateCandidateEducation(@RequestParam String candidateCafEducation
			,@RequestParam(value = "file", required = false)  MultipartFile certificate) {
		ServiceOutcome<Boolean> svcSearchResult=candidateService.saveNUpdateCandidateEducation(candidateCafEducation,certificate);
		return new ResponseEntity<ServiceOutcome<Boolean>>(svcSearchResult, HttpStatus.OK);

	}
	
	@ApiOperation("Get Education By specific Candidate Education Id")
	@GetMapping("/getCandidateEducationById/{candidateCafEducationId}")
	public ResponseEntity<ServiceOutcome<CandidateCafEducationDto>> getCandidateEducationById(@PathVariable Long candidateCafEducationId) {
		ServiceOutcome<CandidateCafEducationDto> svcSearchResult = candidateService.getCandidateEducationById(candidateCafEducationId);
		return new ResponseEntity<ServiceOutcome<CandidateCafEducationDto>>(svcSearchResult, HttpStatus.OK);
	}
	
	@ApiOperation("Save And Update Candidate Experience Details")
	@PostMapping(path = "/saveNUpdateCandidateExperience")
	public ResponseEntity<ServiceOutcome<Boolean>> saveNUpdateCandidateExperience(@RequestParam String candidateCafExperience,
			   @RequestParam(value = "file", required = false)  MultipartFile certificate) {
		ServiceOutcome<Boolean> svcSearchResult=candidateService.saveNUpdateCandidateExperience(candidateCafExperience,certificate);
		return new ResponseEntity<ServiceOutcome<Boolean>>(svcSearchResult, HttpStatus.OK);

	}
	
	@ApiOperation("Get Experience By specific Candidate Experience Id")
	@GetMapping("/getCandidateExperienceById/{candidateCafExperienceId}")
	public ResponseEntity<ServiceOutcome<CandidateCafExperienceDto>> getCandidateExperienceById(@PathVariable Long candidateCafExperienceId) {
		ServiceOutcome<CandidateCafExperienceDto> svcSearchResult = candidateService.getCandidateExperienceById(candidateCafExperienceId);
		return new ResponseEntity<ServiceOutcome<CandidateCafExperienceDto>>(svcSearchResult, HttpStatus.OK);
	}
	
	@ApiOperation("Save Candidate Application form")
	@PostMapping(path = "/saveCandidateApplicationForm")
	public ResponseEntity<ServiceOutcome<Boolean>> saveCandidateApplicationForm(@RequestParam(value = "candidateCafEducationDto", required = false) String candidateCafEducation,
			@RequestParam(value = "candidateCafAddressDto", required = false) JSONArray candidateCafAddress,@RequestParam(value = "resume", required = false)  MultipartFile resume,@RequestParam String candidateCode) {
		ServiceOutcome<Boolean> svcSearchResult=candidateService.saveCandidateApplicationForm(candidateCafEducation,candidateCafAddress,resume,candidateCode);
		System.out.println(candidateCode+"---------------");
		return new ResponseEntity<ServiceOutcome<Boolean>>(svcSearchResult, HttpStatus.OK);

	}
	
	@ApiOperation(" Get All Suspect College List")
	@GetMapping("/getAllSuspectClgList")
	public ResponseEntity<ServiceOutcome<List<SuspectClgMaster>>> getAllSuspectClgList() {
		ServiceOutcome<List<SuspectClgMaster>> svcSearchResult = candidateService.getAllSuspectClgList();
		return new ResponseEntity<ServiceOutcome<List<SuspectClgMaster>>>(svcSearchResult, HttpStatus.OK);
	}
	
	@ApiOperation(" Get All Suspect College List")
	@GetMapping("/getAllSuspectEmpList")
	public ResponseEntity<ServiceOutcome<List<SuspectEmpMaster>>> getAllSuspectEmpList() {
		ServiceOutcome<List<SuspectEmpMaster>> svcSearchResult = candidateService.getAllSuspectEmpList();
		return new ResponseEntity<ServiceOutcome<List<SuspectEmpMaster>>>(svcSearchResult, HttpStatus.OK);
	}
	
	@ApiOperation("Candidate Address verification")
	@PostMapping("/relationshipAddressVerification")
	public ResponseEntity<ServiceOutcome<Boolean>> relationshipAddressVerification(
			@RequestParam String candidateCafRelation,
			@RequestParam(value="file", required = false) MultipartFile  document) {
		
		ServiceOutcome<Boolean> svcSearchResult = candidateService.relationshipAddressVerification(candidateCafRelation,document);
		return new ResponseEntity<ServiceOutcome<Boolean>>(svcSearchResult, HttpStatus.OK);
	}
	
	@ApiOperation("Set A Candidate is fresher or experienced")
	@PostMapping("/isFresher")
	public ResponseEntity<ServiceOutcome<Candidate>> isFresher(@RequestParam String  candidateCode,@RequestParam Boolean isFresher) {
		ServiceOutcome<Candidate> svcSearchResult = candidateService.saveIsFresher(candidateCode,isFresher);
		return new ResponseEntity<ServiceOutcome<Candidate>>(svcSearchResult, HttpStatus.OK);
	}
	
	@ApiOperation("Update Experience Data")
	@PostMapping("/updateExperience")
	public ResponseEntity<ServiceOutcome<CandidateCafExperience>> updateExperience(@RequestHeader("Authorization") String authorization,@RequestBody CandidateCafExperienceDto candidateCafExperienceDto) {
		ServiceOutcome<CandidateCafExperience> svcSearchResult=  candidateService.updateCandidateExperience(candidateCafExperienceDto);
		return new ResponseEntity<ServiceOutcome<CandidateCafExperience>>(svcSearchResult, HttpStatus.OK);
	}
	
	@ApiOperation("Save candidate address")
	@PostMapping("/saveCandidateAddress")
	public ResponseEntity<ServiceOutcome<CandidateCafAddress>> saveCandidateAddress(@RequestBody CandidateCafAddressDto candidateCafAddressDto,@RequestHeader("Authorization") String authorization) {
		ServiceOutcome<CandidateCafAddress> svcSearchResult = candidateService.saveCandidateAddress(candidateCafAddressDto);
		return new ResponseEntity<ServiceOutcome<CandidateCafAddress>>(svcSearchResult, HttpStatus.OK);
	}
	
	@ApiOperation("Get Service Config from candidate for customer")
	@GetMapping("/getServiceConfigCodes/{candidateCode}")
	public ResponseEntity<ServiceOutcome<?>> getServiceConfigForCandidate(@PathVariable("candidateCode")String candidateCode) {
		ServiceOutcome<List<String>> svcSearchResult= candidateService.getServiceConfigCodes(candidateCode,null);
		return new ResponseEntity<ServiceOutcome<?>>(svcSearchResult, HttpStatus.OK);
	}
	
	@ApiOperation("Generate interim report")
	@GetMapping("/generateInterimReport/{candidateCode}")
	public ResponseEntity<ServiceOutcome<?>> generateInterimReport(@PathVariable("candidateCode")String candidateCode) throws FileNotFoundException, IOException {
		ServiceOutcome<String> svcSearchResult= candidateService.generateInterimReport(candidateCode);
		return new ResponseEntity<ServiceOutcome<?>>(svcSearchResult, HttpStatus.OK);
	}
	
	@ApiOperation("Set A Candidate if uan skipped or not")
	@PostMapping("/isUanSkipped")
	public ResponseEntity<ServiceOutcome<Candidate>> isUanSkipped(@RequestParam String  candidateCode,@RequestParam String isUanSkipped) {
		ServiceOutcome<Candidate> svcSearchResult = candidateService.saveIsUanSkipped(candidateCode,isUanSkipped);
		return new ResponseEntity<ServiceOutcome<Candidate>>(svcSearchResult, HttpStatus.OK);
	}
	
	@GetMapping(value = "/report")
	public ResponseEntity getReport(@RequestParam("Authorization") String authorization,@RequestParam String candidateCode,@RequestParam
		ReportType type) {
		ServiceOutcome svcSearchResult = new ServiceOutcome();
		String url = reportService.generateDocument(candidateCode, authorization, type);
		svcSearchResult.setData(url);
		return new ResponseEntity<ServiceOutcome<ReportSearchDto>>(svcSearchResult, HttpStatus.OK);
	}

	@ApiOperation("qcpending status set")
	@PostMapping(path = "/qcPendingstatus/{candidateCode}")
	public ResponseEntity<ServiceOutcome<Boolean>> qcPendingstatus(@PathVariable("candidateCode")String candidateCode) {
		ServiceOutcome<Boolean> svcSearchResult=candidateService.qcPendingstatus(candidateCode);
		return new ResponseEntity<ServiceOutcome<Boolean>>(svcSearchResult, HttpStatus.OK);

	}

	@ApiOperation("Get all Candidate digilocker details")
	@GetMapping("/candidateDLdata/{candidateCode}")
	public ResponseEntity<ServiceOutcome<?>> candidateDLdata(@RequestHeader("Authorization") String authorization,@PathVariable("candidateCode")String candidateCode) {
		System.out.println("entry for dl data============================"+authorization);
		ServiceOutcome<CandidateDetailsDto> svcSearchResult= candidateService.candidateDLdata(candidateCode);
		return new ResponseEntity<ServiceOutcome<?>>(svcSearchResult, HttpStatus.OK);
	}
}
