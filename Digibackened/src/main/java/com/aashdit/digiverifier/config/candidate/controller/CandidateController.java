package com.aashdit.digiverifier.config.candidate.controller;

import java.util.List;

import com.aashdit.digiverifier.common.enums.ContentViewType;
import com.aashdit.digiverifier.common.service.ContentService;
import com.aashdit.digiverifier.config.candidate.dto.*;
import com.aashdit.digiverifier.config.superadmin.Enum.ReportType;
import com.aashdit.digiverifier.config.superadmin.dto.ReportSearchDto;
import com.aashdit.digiverifier.config.superadmin.service.ReportService;
import com.aashdit.digiverifier.vendorcheck.dto.FetchVendorConventionalCandidateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.aashdit.digiverifier.config.candidate.model.CandidateCafEducation;
import com.aashdit.digiverifier.config.candidate.model.CandidateCafExperience;

import com.aashdit.digiverifier.common.model.ServiceOutcome;
import com.aashdit.digiverifier.config.admin.dto.VendorUploadChecksDto;
import com.aashdit.digiverifier.config.candidate.model.RemarkMaster;
import com.aashdit.digiverifier.config.candidate.model.StatusMaster;
import com.aashdit.digiverifier.config.candidate.service.CandidateService;
import com.aashdit.digiverifier.config.superadmin.dto.DashboardDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.aashdit.digiverifier.common.model.Content;


@RestController
@RequestMapping(value = "/api/candidate")
@Slf4j
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private ReportService reportService;

    @Autowired
    @Lazy
    private ContentService contentService;

    @ApiOperation("Upload Candidate Information file CSV Or XLS")
    @PostMapping("/uploadCandidate")
    public ResponseEntity<ServiceOutcome<List>> uploadCandidateFile(@RequestParam("file") MultipartFile file, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<List> svcSearchResult = candidateService.saveCandidateInformation(file);
        return new ResponseEntity<ServiceOutcome<List>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Upload Conventional  Candidate Drug Information")
    @PostMapping("/uploadConventionalCandidateDrug")
    public ResponseEntity<ServiceOutcome<List>> uploadConventionalCandidateDrugInformation(@RequestBody FetchVendorConventionalCandidateDto conventionalCandidateDto, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<List> svcSearchResult = candidateService.saveConventionalCandidateDrugInformation(conventionalCandidateDto);
        return new ResponseEntity<ServiceOutcome<List>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Upload Conventional  Candidate Reference Information")
    @PostMapping("/uploadConventionalCandidateReference")
    public ResponseEntity<ServiceOutcome<List>> uploadConventionalCandidateReferenceInformation(@RequestBody FetchVendorConventionalCandidateDto conventionalCandidateDto, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<List> svcSearchResult = candidateService.saveConventionalCandidateReferenceInformation(conventionalCandidateDto);
        return new ResponseEntity<ServiceOutcome<List>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Upload Conventional  Candidate Experience Information")
    @PostMapping("/uploadConventionalCandidateExperience")
    public ResponseEntity<ServiceOutcome<List>> uploadConventionalCandidateExperienceInformation(@RequestBody FetchVendorConventionalCandidateDto conventionalCandidateDto, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<List> svcSearchResult = candidateService.saveConventionalCandidateExperienceInformation(conventionalCandidateDto);
        return new ResponseEntity<ServiceOutcome<List>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Upload Conventional  Candidate Educational Information")
    @PostMapping("/uploadConventionalCandidateEducation")
    public ResponseEntity<ServiceOutcome<List>> uploadConventionalCandidateEducationalInformation(@RequestBody FetchVendorConventionalCandidateDto conventionalCandidateDto, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<List> svcSearchResult = candidateService.saveConventionalCandidateEducationalInformation(conventionalCandidateDto);
        return new ResponseEntity<ServiceOutcome<List>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Upload Conventional  Candidate Information")
    @PostMapping("/uploadConventionalCandidate")
    public ResponseEntity<ServiceOutcome<List>> uploadConventionalCandidate(@RequestBody FetchVendorConventionalCandidateDto conventionalCandidateDto, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<List> svcSearchResult = candidateService.saveConventionalCandidateInformation(conventionalCandidateDto);
        return new ResponseEntity<ServiceOutcome<List>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Upload Conventional  Candidate Address Information")
    @PostMapping("/uploadConventionalCandidateAddress")
    public ResponseEntity<ServiceOutcome<List>> uploadConventionalCandidateAddressInformation(@RequestBody FetchVendorConventionalCandidateDto conventionalCandidateDto, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<List> svcSearchResult = candidateService.saveConventionalCandidateAddressInformation(conventionalCandidateDto);
        return new ResponseEntity<ServiceOutcome<List>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Get all Candidate Information")
    @RequestMapping(value = "/candidateList", method = { RequestMethod.POST})
    public ResponseEntity<ServiceOutcome<DashboardDto>> getCandidateList(@RequestHeader("Authorization") String authorization, @RequestBody DashboardDto dashboardDto) {
        ServiceOutcome<DashboardDto> svcSearchResult = candidateService.getAllCandidateList(dashboardDto);
        return new ResponseEntity<ServiceOutcome<DashboardDto>>(svcSearchResult, HttpStatus.OK);
    }

//    @ApiOperation("Get CandidateList Status And Count")
//    @RequestMapping(value = "/getCandidateStatusAndCount", method = {RequestMethod.GET, RequestMethod.POST})
//    public ResponseEntity<ServiceOutcome<DashboardDto>> getCandidateStatusAndCount(@RequestHeader("Authorization") String authorization, @RequestBody DashboardDto dashboardDto) {
//        ServiceOutcome<DashboardDto> svcSearchResult = candidateService.getCandidateStatusAndCount(dashboardDto);
//        return new ResponseEntity<ServiceOutcome<DashboardDto>>(svcSearchResult, HttpStatus.OK);
//    }

    @ApiOperation("Send Email For Candidate")
    @PostMapping("/invitationSent")
    public ResponseEntity<ServiceOutcome<Boolean>> invitationSent(@RequestBody CandidateInvitationSentDto candidateInvitationSentDto, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<Boolean> svcSearchResult = candidateService.invitationSent(candidateInvitationSentDto);
        return new ResponseEntity<ServiceOutcome<Boolean>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Get Candidate by Reference No")
    @GetMapping("/getCandidate/{referenceNo}")
    public ResponseEntity<ServiceOutcome<CandidateDetailsDto>> getCandidate(@PathVariable("referenceNo") String referenceNo, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<CandidateDetailsDto> svcSearchResult = candidateService.getCandidateByCandidateCode(referenceNo);
        return new ResponseEntity<ServiceOutcome<CandidateDetailsDto>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Candidate UPDATE by Reference No")
    @PutMapping("/updateCandidate")
    public ResponseEntity<ServiceOutcome<CandidateDetailsDto>> updateCandidate(@RequestBody CandidateDetailsDto candidateDetails, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<CandidateDetailsDto> svcSearchResult = candidateService.updateCandidate(candidateDetails);
        return new ResponseEntity<ServiceOutcome<CandidateDetailsDto>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Candidate Cancel by Reference No")
    @PutMapping("/cancelCandidate/{referenceNo}")
    public ResponseEntity<ServiceOutcome<Boolean>> cancelCandidate(@PathVariable("referenceNo") String referenceNo, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<Boolean> svcSearchResult = candidateService.cancelCandidate(referenceNo);
        return new ResponseEntity<ServiceOutcome<Boolean>>(svcSearchResult, HttpStatus.OK);
    }

//    @ApiOperation("Get Report Delivery Details Status And Count")
//    @RequestMapping(value = "/getReportDeliveryDetailsStatusAndCount", method = {RequestMethod.GET, RequestMethod.POST})
//    public ResponseEntity<ServiceOutcome<DashboardDto>> getReportDeliveryDetailsStatusAndCount(@RequestHeader("Authorization") String authorization, @RequestBody DashboardDto dashboardDto) {
//        ServiceOutcome<DashboardDto> svcSearchResult = candidateService.getReportDeliveryDetailsStatusAndCount(dashboardDto);
//        return new ResponseEntity<ServiceOutcome<DashboardDto>>(svcSearchResult, HttpStatus.OK);
//    }

    @ApiOperation("Get all Candidate Application form details")
    @GetMapping("/candidateApplicationFormDetails/{candidateCode}")
    public ResponseEntity<ServiceOutcome<?>> candidateApplicationFormDetails(@PathVariable("candidateCode") String candidateCode) {
        ServiceOutcome<CandidationApplicationFormDto> svcSearchResult = candidateService.candidateApplicationFormDetailsExceptCandidate(candidateCode);
        return new ResponseEntity<ServiceOutcome<?>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation(" Get All Remark")
    @GetMapping("/getAllRemark/{remarkType}")
    public ResponseEntity<?> getAllRemark(@RequestHeader("Authorization") String authorization, @PathVariable("remarkType") String remarkType) {
        ServiceOutcome<List<RemarkMaster>> svcSearchResult = candidateService.getAllRemark(remarkType);
        return new ResponseEntity<>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Candidate education UPDATE")
    @PutMapping("/updateCandidateEducationStatusAndRemark")
    public ResponseEntity<ServiceOutcome<Boolean>> updateCandidateEducationStatusAndRemark(@RequestBody ApprovalStatusRemarkDto approvalStatusRemarkDto, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<Boolean> svcSearchResult = candidateService.updateCandidateEducationStatusAndRemark(approvalStatusRemarkDto);
        return new ResponseEntity<ServiceOutcome<Boolean>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Candidate Experience Status And Remark")
    @PutMapping("/updateCandidateExperienceStatusAndRemark")
    public ResponseEntity<ServiceOutcome<Boolean>> updateCandidateExperienceStatusAndRemark(@RequestBody ApprovalStatusRemarkDto approvalStatusRemarkDto, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<Boolean> svcSearchResult = candidateService.updateCandidateExperienceStatusAndRemark(approvalStatusRemarkDto);
        return new ResponseEntity<ServiceOutcome<Boolean>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Candidate Experience Status And Remark")
    @PutMapping("/updateCandidateAddressStatusAndRemark")
    public ResponseEntity<ServiceOutcome<Boolean>> updateCandidateAddressStatusAndRemark(@RequestBody ApprovalStatusRemarkDto approvalStatusRemarkDto, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<Boolean> svcSearchResult = candidateService.updateCandidateAddressStatusAndRemark(approvalStatusRemarkDto);
        return new ResponseEntity<ServiceOutcome<Boolean>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Candidate Application form Approved")
    @PutMapping(value = "/candidateApplicationFormApproved", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ServiceOutcome<?>> candidateApplicationFormApproved(@RequestParam String candidateReportApproval, @RequestParam String candidateCode, @RequestParam(value = "criminalVerificationDocument", required = false) MultipartFile criminalVerificationDocument, @RequestParam(value = "globalDatabseCaseDetailsDocument", required = false) MultipartFile globalDatabseCaseDetailsDocument, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<Boolean> svcSearchResult = new ServiceOutcome<Boolean>();
        try {
            CandidateApprovalDto candidateApprovalDto = new ObjectMapper().readValue(candidateReportApproval, CandidateApprovalDto.class);
            svcSearchResult = candidateService.candidateApplicationFormApproved(candidateCode, criminalVerificationDocument, candidateApprovalDto.getCriminalVerificationColorId(), globalDatabseCaseDetailsDocument, candidateApprovalDto.getGlobalDatabseCaseDetailsColorId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<ServiceOutcome<?>>(svcSearchResult, HttpStatus.OK);
    }


    @ApiOperation("Upload Candidate Fake Company List Xls")
    @PostMapping("/uploadFakeCompanyDetails")
    public ResponseEntity<ServiceOutcome<Boolean>> uploadFakeCompanyDetails(@RequestParam("file") MultipartFile file, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<Boolean> svcSearchResult = new ServiceOutcome<Boolean>();
        try {
            svcSearchResult = candidateService.saveFakeCompanyDetails(file);
            return new ResponseEntity<ServiceOutcome<Boolean>>(svcSearchResult, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occured in uploadFakeCompanyDetails method in CandidateController-->", e);
            return new ResponseEntity<ServiceOutcome<Boolean>>(svcSearchResult, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @ApiOperation("Upload Candidate Fake College List Xls")
    @PostMapping("/uploadFakeCollegeDetails")
    public ResponseEntity<ServiceOutcome<Boolean>> uploadFakeCollegeDetails(@RequestParam("file") MultipartFile file, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<Boolean> svcSearchResult = new ServiceOutcome<Boolean>();
        try {
            svcSearchResult = candidateService.saveFakeCollegeDetails(file);
            return new ResponseEntity<ServiceOutcome<Boolean>>(svcSearchResult, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occured in uploadFakeCollegeDetails method in CandidateController-->", e);
            return new ResponseEntity<ServiceOutcome<Boolean>>(svcSearchResult, HttpStatus.EXPECTATION_FAILED);
        }
    }

//    @ApiOperation("View Pending Details Status And Count For Dashboard")
//    @RequestMapping(value = "/getPendingDetailsStatusAndCount", method = {RequestMethod.GET, RequestMethod.POST})
//    public ResponseEntity<ServiceOutcome<DashboardDto>> getPendingDetailsStatusAndCount(@RequestHeader("Authorization") String authorization, @RequestBody DashboardDto dashboardDto) {
//        ServiceOutcome<DashboardDto> svcSearchResult = candidateService.getPendingDetailsStatusAndCount(dashboardDto);
//        return new ResponseEntity<ServiceOutcome<DashboardDto>>(svcSearchResult, HttpStatus.OK);
//    }

    @ApiOperation(" Get All Status List")
    @GetMapping("/getAllStatus")
    public ResponseEntity<?> getAllStatus(@RequestHeader("Authorization") String authorization) {
        ServiceOutcome<List<StatusMaster>> svcSearchResult = candidateService.getAllStatus();
        return new ResponseEntity<>(svcSearchResult, HttpStatus.OK);
    }

    @GetMapping(value = "content")
    public ResponseEntity getContent(@RequestParam Long contentId, @RequestParam ContentViewType type) {
        ServiceOutcome svcSearchResult = new ServiceOutcome();
        String url = contentService.getContentById(contentId, type);
        svcSearchResult.setData(url);
        return new ResponseEntity<ServiceOutcome<ReportSearchDto>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("candidate deleteexp Id")
    @PutMapping("/deletecandidateExp/{id}")
    public ResponseEntity<ServiceOutcome<CandidateCafExperience>> deletecandidateExpById(@PathVariable("id") Long id, @RequestHeader("Authorization") String authorization) {
        // System.out.println("------------------------exp_id"+id);
        ServiceOutcome<CandidateCafExperience> svcSearchResult = candidateService.deletecandidateExpById(id);
        return new ResponseEntity<ServiceOutcome<CandidateCafExperience>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("candidate deleteEducation Id")
    @PutMapping("/deletecandidateEducationById/{id}")
    public ResponseEntity<ServiceOutcome<CandidateCafEducation>> deletecandidateEducationById(@PathVariable("id") Long id, @RequestHeader("Authorization") String authorization) {
        System.out.println("-----------------------education_id" + id);
        ServiceOutcome<CandidateCafEducation> svcSearchResult = candidateService.deletecandidateEducationById(id);
        return new ResponseEntity<ServiceOutcome<CandidateCafEducation>>(svcSearchResult, HttpStatus.OK);
    }

    // update the vendor proof color by agent ///
    @ApiOperation("Candidate vendor proof Status")
    @PutMapping("/updateCandidateVendorProofColor")
    public ResponseEntity<ServiceOutcome<Boolean>> updateCandidateVendorProofColor(@RequestBody VendorUploadChecksDto vendorUploadChecksDto, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<Boolean> svcSearchResult = candidateService.updateCandidateVendorProofColor(vendorUploadChecksDto);
        return new ResponseEntity<ServiceOutcome<Boolean>>(svcSearchResult, HttpStatus.OK);
    }

    // @ApiOperation("get Candidate pre approval content id ")
    // @GetMapping(value = "/CandidateCode")
    // public ResponseEntity getContentId(@RequestParam String CandidateCode) {
    // 	ServiceOutcome svcSearchResult = new ServiceOutcome();
    // 	ServiceOutcome<Content> svcSearchResult = contentService.getApplicantById(CandidateCode);
    // 	svcSearchResult.setData(content_Id);
    // 	return new ResponseEntity<ServiceOutcome<Content>>(svcSearchResult, HttpStatus.OK);
    // 	}

    @ApiOperation(" get Candidate pre approval content id ")
    @GetMapping("/CandidateCode")
    public ResponseEntity<?> getContentById(@RequestHeader("Authorization") String authorization, @RequestParam String CandidateCode) {
        ServiceOutcome<Long> svcSearchResult = candidateService.getContentById(CandidateCode);
        return new ResponseEntity<>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("AddCommentsReports details")
    @PutMapping("/AddCommentsReports")
    public ResponseEntity<ServiceOutcome<Boolean>> AddCommentsReports(@RequestBody CandidateCaseDetailsDTO candidateCaseDetailsDTO, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<Boolean> svcSearchResult = candidateService.AddCommentsReports(candidateCaseDetailsDTO);
        return new ResponseEntity<ServiceOutcome<Boolean>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation(" get Candidate pre approval content id ")
    @GetMapping("/conventionalCandidateId/{conventionalCandidateId}")
    public ResponseEntity<?> getContentById(@RequestHeader("Authorization") String authorization, @PathVariable("conventionalCandidateId") Long conventionalCandidateId) {
        ServiceOutcome<Long> svcSearchResult = candidateService.getCandidateIdByConventionalCandidateId(conventionalCandidateId);
        return new ResponseEntity<>(svcSearchResult, HttpStatus.OK);
    }
    // added for conventional upload details

    // added for conventional upload details

    @ApiOperation("Get Upload Details Status And Count")
    @RequestMapping(value = "/getUploadDetailsStatusAndCountConventional", method = { RequestMethod.POST})
    public ResponseEntity<ServiceOutcome<DashboardDto>> getUploadDetailsStatusAndCountConventional(@RequestHeader("Authorization") String authorization, @RequestBody DashboardDto dashboardDto) {
        ServiceOutcome<DashboardDto> svcSearchResult = candidateService.getUploadDetailsStatusAndCountConventional(dashboardDto);
        return new ResponseEntity<ServiceOutcome<DashboardDto>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Get Upload Details Status And Count")
    @RequestMapping(value = "/getConvCandInterimAndFinal", method = { RequestMethod.POST})
    public ResponseEntity<ServiceOutcome<DashboardDto>> findConvCandidateForInterimAndFinal(@RequestHeader("Authorization") String authorization, @RequestBody DashboardDto dashboardDto) {
        ServiceOutcome<DashboardDto> svcSearchResult = candidateService.findConvCandidateForInterimAndFinal(dashboardDto);
        return new ResponseEntity<ServiceOutcome<DashboardDto>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Get Candidate Code By Candidate Id")
    @GetMapping("/getVendorUploadChecksByCandidateCode/{candidateCode}")
    public ResponseEntity<?> getVendorUploadChecksByCandidateId(@RequestHeader("Authorization") String authorization, @PathVariable("candidateCode") String candidateCode) {
        ServiceOutcome<ConventionalCReportApprovalDto> svcSearchResult = candidateService.getVendorUploadChecksByCandidateId(candidateCode);
        return new ResponseEntity<>(svcSearchResult, HttpStatus.OK);
    }
}
