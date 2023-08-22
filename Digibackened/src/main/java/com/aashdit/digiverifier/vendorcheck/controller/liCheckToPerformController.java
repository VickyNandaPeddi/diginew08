/**
 *
 */
package com.aashdit.digiverifier.vendorcheck.controller;

import com.aashdit.digiverifier.common.model.ServiceOutcome;
import com.aashdit.digiverifier.config.admin.model.VendorChecks;
import com.aashdit.digiverifier.config.superadmin.Enum.ReportType;
import com.aashdit.digiverifier.config.superadmin.dto.DashboardDto;
import com.aashdit.digiverifier.config.superadmin.model.VendorCheckStatusMaster;
import com.aashdit.digiverifier.utils.AwsUtils;
import com.aashdit.digiverifier.vendorcheck.dto.*;
import com.aashdit.digiverifier.vendorcheck.model.ConventionalAttributesMaster;
import com.aashdit.digiverifier.vendorcheck.model.ConventionalVendorCandidatesSubmitted;
import com.aashdit.digiverifier.vendorcheck.model.ModeOfVerificationStatusMaster;
import com.aashdit.digiverifier.vendorcheck.service.liCheckToPerformService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author ${Nanda Kishore}
 */

@Slf4j
@RestController
@RequestMapping("/api/vendorCheck")

public class liCheckToPerformController {

    @Autowired
    liCheckToPerformService liCheckToPerformService;


    //when created it will be newupl

    /**
     * @param code
     * @param state
     * @throws JsonProcessingException
     * @throws IOException
     * @returnx
     */
    @ApiOperation(value = "performs vendorcheck and save the data")
    @PostMapping(value = "/udpdateBgvCheckStatusRowwise", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ServiceOutcome<String> udpdateBgvCheckStatusRowwise(@RequestParam String vendorchecks, @RequestParam(value = "file", required = false) MultipartFile proofDocumentNew) {
        String data = liCheckToPerformService.UpdateBGVCheckStatusRowwise(vendorchecks, proofDocumentNew, "4");
        ServiceOutcome<String> response = new ServiceOutcome<>();
        response.setData(data);
        response.setData(response.getData());
        response.setStatus("200");
        response.setOutcome(true);
        response.setMessage("liCheckPerform Saved Sucessfully");
        return response;
    }

    @ApiOperation(value = "updateBgv check rowwise")
    @PostMapping(value = "/liCheck")
    public ServiceOutcome<LicheckRequiredResponseDto> addUpdateLichecks
            (@RequestBody FetchVendorConventionalCandidateDto fetchVendorConventionalCandidateDto) throws Exception {
        ServiceOutcome<LicheckRequiredResponseDto> response = liCheckToPerformService.addUpdateLiCheckToPerformData(fetchVendorConventionalCandidateDto);
        response.setStatus("200");
        response.setOutcome(true);
        response.setMessage("liCheckPerform Saved Sucessfully");
        return response;
    }

    @ApiOperation(value = "finds All LiChecksRequired and returns the data")
    @GetMapping(value = "/findAllLiChecks")
    public ServiceOutcome<List<LicheckRequiredResponseDto>> findAllLiChecksRequired() throws Exception {
        ServiceOutcome<List<LicheckRequiredResponseDto>> response = liCheckToPerformService.findAllLiChecksRequired();
        response.setData(response.getData());
        response.setStatus("200");
        response.setOutcome(true);
        response.setMessage("liCheckPerform Saved Sucessfully");
        return response;
    }


    @ApiOperation(value = "finds All LiChecksRequired and returns the data")
    @GetMapping(value = "/findAllLiChecks/{candidateId}")
    public ServiceOutcome<List<LicheckRequiredResponseDto>> findAllLiChecksRequiredbyCandidateId
            (@PathVariable("candidateId") String candidateId) throws Exception {
        ServiceOutcome<List<LicheckRequiredResponseDto>> response = liCheckToPerformService.findAllLiChecksRequiredbyCandidateId(candidateId);
        response.setData(response.getData());
        response.setStatus("200");
        response.setOutcome(true);
        response.setMessage("liCheckPerform Saved Sucessfully");
        return response;
    }

    @ApiOperation(value = "finds All LiChecksRequired and returns the data")
    @GetMapping(value = "/findAllNewUploadLiChecks/{candidateId}")
    public ServiceOutcome<List<LicheckRequiredResponseDto>> findAllNewUploadLiChecksRequiredbyCandidateId
            (@PathVariable("candidateId") String candidateId) throws Exception {
        ServiceOutcome<List<LicheckRequiredResponseDto>> response = liCheckToPerformService.findAllNewUploadLiChecksRequiredbyCandidateId(candidateId);
        response.setData(response.getData());
        response.setStatus("200");
        response.setOutcome(true);
        response.setMessage("liCheckPerform Saved Sucessfully");
        return response;
    }




    @ApiOperation(value = "finds All LiChecksRequired and returns the data")
    @GetMapping(value = "/findConventionalCandidate/{candidateId}")
    public ServiceOutcome<ConventionalVendorCandidatesSubmitted> findConventionalCandidateByCandidateId
            (@PathVariable("candidateId") Long candidateId) throws Exception {
        ServiceOutcome<ConventionalVendorCandidatesSubmitted> response = liCheckToPerformService.findConventionalCandidateByCandidateId(candidateId);
        response.setData(response.getData());
        response.setStatus("200");
        response.setOutcome(true);
        response.setMessage("liCheckPerform Saved Sucessfully");
        return response;
    }

    @ApiOperation(value = "finds All LiChecksRequired and returns the data")
    @GetMapping(value = "/findAllLiChecksByCheckStatus/{checkStatus}")
    public ServiceOutcome<List<LicheckRequiredResponseDto>> findAllLiChecksRequiredbyCheckStatus
            (@PathVariable("checkStatus") String checkStatus) throws Exception {
        ServiceOutcome<List<LicheckRequiredResponseDto>> response = liCheckToPerformService.findAllLiChecksRequiredbyCheckStatus(checkStatus);
        response.setData(response.getData());
        response.setStatus("200");
        response.setOutcome(true);
        response.setMessage("liCheckPerform Saved Sucessfully");
        return response;
    }

    @ApiOperation(value = "saves the submittedCandidate and returns the data")
    @PostMapping(value = "/saveSubmittedCandidates", produces = "application/json")
    public ServiceOutcome<SubmittedCandidates> saveConventionalVendorSubmittedCandidates(@RequestBody String
                                                                                                 VendorID) throws Exception {
        ServiceOutcome<SubmittedCandidates> response = liCheckToPerformService.saveConventionalVendorSubmittedCandidates(VendorID);
        response.setData(response.getData());
        response.setStatus(response.getStatus());
        response.setOutcome(response.getOutcome());
        response.setMessage(response.getMessage());
        return response;
    }

    @ApiOperation(value = "finds All submittedCandidates and returns the data")
    @GetMapping(value = "/findAllSubmittedCandidates")
    public ServiceOutcome<List<SubmittedCandidates>> findAllConventionalVendorSubmittedCandidates() throws
            Exception {
        ServiceOutcome<List<SubmittedCandidates>> response = liCheckToPerformService.findAllConventionalVendorSubmittedCandidates();
        response.setData(response.getData());
        response.setStatus("200");
        response.setOutcome(true);
        response.setMessage("candidates Fetched Sucessfully");
        return response;
    }

    @ApiOperation(value = "saves the submittedCandidate and returns the data")
    @GetMapping(value = "/saveCandidateDocuments/{candidateId}")
    public ServiceOutcome<ConventionalCandidateDocDto> saveConventionalCandidateDocuments
            (@RequestBody FetchVendorConventionalCandidateDto fetchVendorConventionalCandidateDto) throws Exception {
        ServiceOutcome<ConventionalCandidateDocDto> response = liCheckToPerformService.saveConventionalCandidateDocumentInfo(fetchVendorConventionalCandidateDto);
        response.setData(response.getData());
        response.setStatus("200");
        response.setOutcome(true);
        response.setMessage("candidateDocument Saved Sucessfully");
        return response;
    }

    @ApiOperation(value = "finds All submittedCandidates and returns the data")
    @GetMapping(value = "/findAllCandidateDocuments")
    public ServiceOutcome<List<ConventionalCandidateDocDto>> findAllConventionalCandidatesDocuments() throws
            Exception {
        ServiceOutcome<List<ConventionalCandidateDocDto>> response = liCheckToPerformService.findAllConventionalCandidateDocumentInfo();
        response.setData(response.getData());
        response.setStatus("200");
        response.setOutcome(true);
        response.setMessage("conventional Candidates documents Fetched Sucessfully");
        return response;
    }


    @ApiOperation(value = "finds All submittedCandidates and returns the data")
    @GetMapping(value = "/findPrecisedUrl/{conventionalCandidateId}/{checkName}")
    public ServiceOutcome<CandidateuploadS3Documents> findAllfilesUploadedurls
            (@PathVariable("conventionalCandidateId") String caonvetionalCandidateId, @PathVariable("checkName") String checkName) throws Exception {
        ServiceOutcome<CandidateuploadS3Documents> response = liCheckToPerformService.findAllfilesUploadedurls(caonvetionalCandidateId, checkName);
        response.setData(response.getData());
        response.setStatus("200");
        response.setOutcome(true);
        response.setMessage("conventional Candidates documents Fetched Sucessfully");
        return response;
    }

    //which will be done on vendor uploads
    @ApiOperation(value = "finds All submittedCandidates and returns the data")
    @GetMapping(value = "/updateLicheckStatusByVendor/{vendorCheckStatusMasterId}/{vendorCheckId}/{remarks}/{modeOfVericationPerformed}")
    public ServiceOutcome<?> updateLiCheckStatusByVendor(@PathVariable(value = "vendorCheckStatusMasterId") String
                                                                 vendorCheckStatusMasterId, @PathVariable(value = "vendorCheckId") String
                                                                 vendorCheckId, @PathVariable(value = "remarks") String
                                                                 remarks, @PathVariable("modeOfVericationPerformed") String modeOfVericationPerformed) throws Exception {
        ServiceOutcome<String> response = liCheckToPerformService.updateLiCheckStatusByVendor(vendorCheckStatusMasterId, vendorCheckId, remarks, modeOfVericationPerformed);
        response.setData(response.getData());
        response.setStatus("200");
        response.setOutcome(true);
        response.setMessage("conventional Candidates documents Fetched Sucessfully");
        return new ServiceOutcome<String>();

    }

    @ApiOperation(value = "")
    @GetMapping(value = "/updateLicheckWithVendorcheck/{vendorCheckId}/{liCheckid}")
    public ServiceOutcome<?> findUpdateLicheckWithVendorCheck(@PathVariable(value = "vendorCheckId") String
                                                                      vendorId, @PathVariable(value = "liCheckid") String liCheckId) throws Exception {
        ServiceOutcome<String> response = liCheckToPerformService.findUpdateLicheckWithVendorCheck(vendorId, liCheckId);
        response.setData(response.getData());
        response.setStatus("200");
        response.setOutcome(true);
        response.setMessage("conventionaffindAllSubmittedCandidatesByDateRangel Candidates documents Fetched Sucessfully");
        return new ServiceOutcome<String>();
    }


    @ApiOperation(value = "finds All submittedCandidates and returns the data")
    @GetMapping(value = "/findAllVendorCheckStatus")
    public ServiceOutcome<?> findAllVendorCheckStatusMasterStatuses() {
        ServiceOutcome<List<VendorCheckStatusMaster>> response = liCheckToPerformService.findAllVendorCheckStatus();
        response.setData(response.getData());
        response.setStatus("200");
        response.setOutcome(true);
        response.setMessage("conventional Candidates documents Fetched Sucessfully");
        return response;

    }


//    @ApiOperation(value = "finds All submittedCandidates and returns the data")
//    @GetMapping(value = "/updateConvetionalStatusByLicheckStatus")
//    public ServiceOutcome<?> updateConventionalCandidateStatusBasedOnLiCheckStatus() throws Exception {
//        ServiceOutcome<String> response = liCheckToPerformService.updateConventionalCandidateStatusBasedOnLiCheckStatus();
//        response.setData(response.getData());
//        response.setStatus("200");
//        response.setOutcome(true);
//        response.setMessage("conventional Candidates documents Fetched Sucessfully");
//        return new ServiceOutcome<String>();
//
//    }


    @ApiOperation(value = "finds All LiChecksRequired and returns the data")
    @GetMapping(value = "/updateCandidateStatus")
    public ServiceOutcome<String> updateConventionalCandidateStatusByLicheckStatus() throws Exception {
        ServiceOutcome<String> response = liCheckToPerformService.updateCandidateStatusByLicheckStatus();
        response.setData(response.getData());
        response.setStatus("200");
        response.setOutcome(true);
        response.setMessage("liCheckPerform Saved Sucessfully");
        return response;
    }

    @ApiOperation(value = "update verifcationstatus of candidate")
    @GetMapping(value = "/updateCandidateVerificationStatus")
    public ServiceOutcome<String> updateCandidateVerificationStatus() throws Exception {
        ServiceOutcome<String> response = liCheckToPerformService.updateCandidateVerificationStatus("");
        response.setData(response.getData());
        response.setStatus("200");
        response.setOutcome(true);
        response.setMessage("liCheckPerform Saved Sucessfully");
        return response;
    }

    @ApiOperation(value = "finds All submittedCandidates by date range and returns the data")
    @RequestMapping(value = "/findAllSubmittedCandidatesByDateRange", method = {RequestMethod.POST})
    public ServiceOutcome<List<ConventionalVendorCandidatesSubmitted>> findAllConventionalVendorSubmittedCandidatesByDateRange
            (@RequestHeader("Authorization") String authorization, @RequestBody DashboardDto dashboardDto) throws Exception {
        ServiceOutcome<List<ConventionalVendorCandidatesSubmitted>> response = liCheckToPerformService.findAllConventionalVendorSubmittedCandidatesByDateRange(dashboardDto);
        response.setData(response.getData());
        response.setStatus("200");
        response.setOutcome(true);
        response.setMessage("candidates Fetched Sucessfully");
        response.getData().forEach(resp -> {
            log.info("Created Date {}", resp.getCreatedOn());
        });
        return response;
    }

    @ApiOperation(value = "finds All submittedCandidates by date range and returns the data")
    @RequestMapping(value = "/findCandidateSubmittedForInterimandFinal", method = {RequestMethod.POST})
    public ServiceOutcome<List<ConventionalVendorCandidatesSubmitted>> findCandidateSubmittedForInterimandFinal
            (@RequestHeader("Authorization") String authorization, @RequestBody DashboardDto dashboardDto) throws Exception {
        ServiceOutcome<List<ConventionalVendorCandidatesSubmitted>> response = liCheckToPerformService.findAllSubmittedCandidatesByDateRangeOnInterimAndFinal(dashboardDto);
        response.setData(response.getData());
        response.setStatus("200");
        response.setOutcome(true);
        response.setMessage("candidates Fetched Sucessfully");
        response.getData().forEach(resp -> {
            log.info("Created Date {}", resp.getCreatedOn());
        });
        return response;
    }

    @Autowired
    AwsUtils awsUtils;


    @ApiOperation(value = "finds All submittedCandidates and returns the data")
    @PostMapping(value = "/generatePrecisedUrl")
    public ServiceOutcome<String> findAllfilesUploadedufdsfssdarls(@RequestBody String precisedUrl) throws
            Exception {
        ServiceOutcome<String> serviceOutcome = new ServiceOutcome<>();
//        String path = "Candidate/Convetional/" + candidateID + "/" + documentName;
        precisedUrl = precisedUrl.replace("\"", "");
        String presignedUrl = awsUtils.getPresignedUrl("digiverifier-new", precisedUrl);
        System.out.println(presignedUrl);
        serviceOutcome.setData(presignedUrl);
        serviceOutcome.setStatus("200");
        serviceOutcome.setOutcome(true);
        serviceOutcome.setMessage("conventional Candidates documents Fetched Sucessfully");
        return serviceOutcome;
    }

    //generate report


//    @ApiOperation(value = "finds All submittedCandidates by date range and returns the data")
//    @RequestMapping(value = "/generateReport/{authToken}", method = {RequestMethod.GET, RequestMethod.POST})
//    public ServiceOutcome<String> generateReport( @PathVariable("authToken") String authToken) throws Exception {
//        ServiceOutcome<String> response = new ServiceOutcome<>();
////        String s = reportService.generateDocument("2Z5G8HCUYWK6", authToken, ReportType.INTERIM);
////        response.setData(s);
//        response.setStatus("200");
//        response.setOutcome(true);
//        response.setMessage("candidates Fetched Sucessfully");
//        return response;
//    }


    @ApiOperation(value = "finds All submittedCandidates by date range and returns the data")
    @RequestMapping(value = "/generateReportForConventionalCandidate/{canididateId}/{reportType}", method = {RequestMethod.GET, RequestMethod.POST})
    public ServiceOutcome<List<liReportDetails>> generateDocumentReportConventional
            (@PathVariable(name = "canididateId") String candidateId, @PathVariable("reportType") String reportType) throws
            Exception {
        ServiceOutcome<List<liReportDetails>> response = liCheckToPerformService.generateDocumentConventional(candidateId, reportType);
        response.setData(response.getData());
        response.setStatus("200");
        response.setOutcome(true);
        response.setMessage("candidates Fetched Sucessfully");
        return response;
    }

    @ApiOperation(value = "generate response for Report response")
    @RequestMapping(value = "/generateJsonByCandidateId/{canididateId}/{reportType}/{updated}", method = {RequestMethod.GET}, produces = "application/json")
    public ServiceOutcome<String> generateJsonByCandidateId(@PathVariable(name = "canididateId") String
                                                                    candidateId, @PathVariable("reportType") ReportType reportType, @PathVariable("updated") String update) throws
            Exception {
        ServiceOutcome<String> response = liCheckToPerformService.generateJsonRepsonseByConventionalCandidateId(candidateId, reportType, update);
        response.setData(response.getData());
        response.setStatus("200");
        response.setOutcome(true);
        return response;
    }

    @ApiOperation(value = "generate response for Report response")
    @RequestMapping(value = "/generateJsonResponse", method = {RequestMethod.GET}, produces = "application/json")
    public ServiceOutcome<List<ReportUtilizationDto>> generateJsonResponse() throws Exception {
        ServiceOutcome<List<ReportUtilizationDto>> response = liCheckToPerformService.generateJsonResponse3();
        response.setData(response.getData());
        response.setStatus("200");
        response.setOutcome(true);
        return response;
    }

    @ApiOperation(value = "generate response for Report response")
    @RequestMapping(value = "/generateReferenceDataForVendor/{candidateId}/{checkName}", method = {RequestMethod.GET}, produces = "application/json")
    public ServiceOutcome<List<VendorReferenceDataDto>> generateReferenceDataToVendor
            (@PathVariable("candidateId") Long candidateId, @PathVariable("checkName") Long checkName) throws Exception {
        ServiceOutcome<List<VendorReferenceDataDto>> response = liCheckToPerformService.generateReferenceDataToVendor(candidateId, checkName);
        response.setData(response.getData());
        response.setStatus("200");
        response.setOutcome(true);
        return response;
    }

    @ApiOperation(value = "generate response for Report response")
    @RequestMapping(value = "/findAllModeOfVerificationPerformed", method = {RequestMethod.GET}, produces = "application/json")
    public ServiceOutcome<List<ModeOfVerificationStatusMaster>> findAllModeofVerificationPerformed() throws
            Exception {
        ServiceOutcome<List<ModeOfVerificationStatusMaster>> response = liCheckToPerformService.findAllModeOfVerifcationPerformed();
        response.setData(response.getData());
        response.setStatus("200");
        response.setOutcome(true);
        return response;
    }

    //    @ApiOperation(value = "generate attributes for Report response")
//    @RequestMapping(value = "/generateReportAttributes", method = {RequestMethod.GET}, produces = "application/json")
//    public ServiceOutcome<ConventionalReportVendor> addConventionalVendorReportAttributes() throws Exception {
//        ServiceOutcome<ConventionalReportVendor> response = liCheckToPerformService.addConventionalVendorReportAttributes();
//        response.setData(response.getData());
//        response.setStatus("200");
//        response.setOutcome(true);
//        return response;
//    }
//    @ApiOperation("Save and  Update ConventionalAttributesMaster")
//    @PostMapping("/saveConventionalAttributesMaster")
//    public ResponseEntity<ServiceOutcome<ConventionalAttributesMaster>> saveConventionalAttributesMaster(@RequestBody ConventionalAttributesMaster conventionalAttributesMaster, @RequestHeader("Authorization") String authorization) {
//
//        System.out.println("ConventionalAttributesMaster**************" + conventionalAttributesMaster);
//        ServiceOutcome<ConventionalAttributesMaster> svcSearchResult = liCheckToPerformService.saveConventionalAttributesMaster(conventionalAttributesMaster);
//        return new ResponseEntity<ServiceOutcome<ConventionalAttributesMaster>>(svcSearchResult, HttpStatus.OK);
//
//    }


    @ApiOperation("Get By Id ConventionalAttributesMaster")
    @GetMapping("/getConventionalAttributesMaster/{vendorCheckId}")
    public ResponseEntity<ServiceOutcome<ConventionalAttributesMaster>> getConventionalAttributesMasterById(@PathVariable("vendorCheckId")Long sourceId) {
        ServiceOutcome<ConventionalAttributesMaster> svcSearchResult = liCheckToPerformService.getConventionalAttributesMasterById(sourceId);
        return new ResponseEntity<ServiceOutcome<ConventionalAttributesMaster>>(svcSearchResult, HttpStatus.OK);

    }

}


//application id

// render data lichecks

