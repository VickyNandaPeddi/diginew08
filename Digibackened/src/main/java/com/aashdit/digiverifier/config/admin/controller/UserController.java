package com.aashdit.digiverifier.config.admin.controller;

import java.io.IOException;
import java.util.List;

import com.aashdit.digiverifier.config.admin.dto.VendorChecksDto;
import com.aashdit.digiverifier.config.admin.model.VendorUploadChecks;
import com.aashdit.digiverifier.config.admin.repository.VendorUploadChecksRepository;
import com.aashdit.digiverifier.config.superadmin.dto.DashboardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aashdit.digiverifier.common.model.ServiceOutcome;
import com.aashdit.digiverifier.config.admin.dto.UserDto;
import com.aashdit.digiverifier.config.admin.model.User;
import com.aashdit.digiverifier.config.admin.service.UserService;
import com.aashdit.digiverifier.utils.SecurityHelper;
import com.aashdit.digiverifier.config.admin.dto.VendorInitiatDto;
import com.aashdit.digiverifier.config.admin.model.VendorChecks;
import org.springframework.http.MediaType;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping(value = "/api/user")
@Slf4j
public class UserController {


    @Autowired
    private UserService userService;


    @ApiOperation("Save User Information")
    @PostMapping(path = "/saveNUpdateUser")
    public ResponseEntity<ServiceOutcome<UserDto>> saveNUpdateUser(@RequestBody UserDto user, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<UserDto> svcSearchResult = userService.saveUser(user);
        return new ResponseEntity<ServiceOutcome<UserDto>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Get user By specific Organization Id")
    @GetMapping("/getUserByOrganizationId/{organizationId}")
    public ResponseEntity<ServiceOutcome<List<UserDto>>> getUserByOrganizationId(@PathVariable Long organizationId, @RequestHeader("Authorization") String authorization) {
        User user = SecurityHelper.getCurrentUser();
        ServiceOutcome<List<UserDto>> svcSearchResult = userService.getUserByOrganizationIdAndUser(organizationId, user);
        return new ResponseEntity<ServiceOutcome<List<UserDto>>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Get user By specific Organization Id and userId")
    @GetMapping("/getUserByOrganizationIdAndUserId/{organizationId}/{userId}")
    public ResponseEntity<ServiceOutcome<List<UserDto>>> getUserByOrganizationIdAndUserId(@PathVariable Long userId, @PathVariable Long organizationId, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<User> user = userService.getUserByUserId(userId);
        ServiceOutcome<List<UserDto>> svcSearchResult = userService.getUserByOrganizationIdAndUser(organizationId, user.getData());
        return new ResponseEntity<ServiceOutcome<List<UserDto>>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Get user By specific User Id")
    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<ServiceOutcome<UserDto>> getUserById(@PathVariable Long userId, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<UserDto> svcSearchResult = userService.getUserById(userId);
        return new ResponseEntity<ServiceOutcome<UserDto>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Active/Inactive user By specific User Id")
    @PutMapping("/activeNInAtiveUser/{userId}/{isActive}")
    public ResponseEntity<ServiceOutcome<User>> activeNInAtiveUser(@PathVariable("userId") Long userId, @PathVariable("isActive") Boolean isActive, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<User> svcSearchResult = userService.activeAndInactiveUserById(userId, isActive);
        return new ResponseEntity<ServiceOutcome<User>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Get Admin Details for the Organization")
    @GetMapping(path = "/getAdminDetailsForOrganization/{organizationId}")
    public ResponseEntity<ServiceOutcome<User>> getAdminDetailsForOrganization(@PathVariable("organizationId") Long organizationId, @RequestHeader("Authorization") String authorization) throws Exception {
        ServiceOutcome<User> svcSearchResult = userService.getAdminDetailsForOrganization(organizationId);
        return new ResponseEntity<ServiceOutcome<User>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Get Agent Supervisor List For The Organization")
    @GetMapping(path = "/getAgentSupervisorList/{organizationId}")
    public ResponseEntity<ServiceOutcome<List<User>>> getAgentSupervisorList(@PathVariable("organizationId") Long organizationId, @RequestHeader("Authorization") String authorization) throws Exception {
        ServiceOutcome<List<User>> svcSearchResult = userService.getAgentSupervisorList(organizationId);
        return new ResponseEntity<ServiceOutcome<List<User>>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Upload Agent Information file CSV Or XLS")
    @PostMapping("/uploadAgent")
    public ResponseEntity<ServiceOutcome<Boolean>> uploadAgentFile(@RequestParam("file") MultipartFile file, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<Boolean> svcSearchResult = new ServiceOutcome<Boolean>();
        try {
            svcSearchResult = userService.saveAgentInformation(file);
            return new ResponseEntity<ServiceOutcome<Boolean>>(svcSearchResult, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Exception occured in uploadCandidateFile method in UploadCandidateController-->" + ex);
            return new ResponseEntity<ServiceOutcome<Boolean>>(svcSearchResult, HttpStatus.EXPECTATION_FAILED);
        }

    }

    @ApiOperation("Get User Profile")
    @GetMapping("/getUserProfile")
    public ResponseEntity<ServiceOutcome<UserDto>> getUserProfile(@RequestHeader("Authorization") String authorization) {
        ServiceOutcome<UserDto> svcSearchResult = userService.getUserProfile();
        return new ResponseEntity<ServiceOutcome<UserDto>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Get List of Admins")
    @GetMapping(path = "/getAdminList")
    public ResponseEntity<ServiceOutcome<List<User>>> getAdminList(@RequestHeader("Authorization") String authorization) throws Exception {
        ServiceOutcome<List<User>> svcSearchResult = userService.getAdminList();
        return new ResponseEntity<ServiceOutcome<List<User>>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Active/Inactive admin By specific User Id")
    @PutMapping("/activeNInAtiveAdmin/{userId}/{isActive}")
    public ResponseEntity<ServiceOutcome<User>> activeNInAtiveAdmin(@PathVariable("userId") Long userId, @PathVariable("isActive") Boolean isActive, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<User> svcSearchResult = userService.activeNInAtiveAdmin(userId, isActive);
        return new ResponseEntity<ServiceOutcome<User>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Get Agent  List For The Organization")
    @GetMapping(path = "/getAgentList/{organizationId}")
    public ResponseEntity<ServiceOutcome<List<User>>> getAgentList(@PathVariable("organizationId") Long organizationId, @RequestHeader("Authorization") String authorization) throws Exception {
        ServiceOutcome<List<User>> svcSearchResult = userService.getAgentList(organizationId);
        return new ResponseEntity<ServiceOutcome<List<User>>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Get user By roleCode")
    @GetMapping("/getUsersByRoleCode/{roleCode}")
    public ResponseEntity<ServiceOutcome<List<User>>> getUserByRoleCode(@PathVariable String roleCode, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<List<User>> svcSearchResult = userService.getUsersByRoleCode(roleCode);
        return new ResponseEntity<ServiceOutcome<List<User>>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Get Vendor  List For The Organization")
    @GetMapping(path = "/getVendorList/{organizationId}")
    public ResponseEntity<ServiceOutcome<List<User>>> getVendorList(@PathVariable("organizationId") Long organizationId, @RequestHeader("Authorization") String authorization) throws Exception {
        ServiceOutcome<List<User>> svcSearchResult = userService.getVendorList(organizationId);
        return new ResponseEntity<ServiceOutcome<List<User>>>(svcSearchResult, HttpStatus.OK);

    }

    // @ApiOperation("Save And Update Organization Information")
    // @PostMapping(path = "/saveInitiateVendorChecks/")
    // public ResponseEntity<ServiceOutcome<VendorInitiatDto>> saveInitiateVendorChecks(@RequestBody VendorInitiatDto vendorInitiatDto,@RequestHeader("Authorization") String authorization) {
    //     System.out.println(vendorInitiatDto+"***************************************************");
    //     ServiceOutcome<VendorInitiatDto> svcSearchResult = userService.saveInitiateVendorChecks(vendorInitiatDto);
    //     return new ResponseEntity<ServiceOutcome<VendorInitiatDto>>(svcSearchResult, HttpStatus.OK);
    //     }

    @ApiOperation("Get vendor checks details")
    @GetMapping("/getVendorCheckDetails/{organizationId}")
    public ResponseEntity<?> getVendorCheckDetails(@RequestHeader("Authorization") String authorization, @PathVariable("organizationId") Long candidateId) {
        ServiceOutcome<List<VendorChecks>> svcSearchResult = userService.getVendorCheckDetails(candidateId);
        return new ResponseEntity<>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Get vendor checks details")
    @GetMapping("/getVendorCheckdata/{vendorId}")
    public ResponseEntity<?> getallVendorCheckDetails(@PathVariable("vendorId") Long vendorId) {
        ServiceOutcome<List<VendorChecks>> svcSearchResult = userService.getallVendorChecsa(vendorId);
        return new ResponseEntity<>(svcSearchResult, HttpStatus.OK);
    }


    @ApiOperation("Get vendor checks details")
    @PostMapping("/getVendorCheck")
    public ResponseEntity<?> getallVendorCheckDetails(@RequestBody DashboardDto dashboardDto) {
        ServiceOutcome<List<VendorChecksDto>> svcSearchResult = userService.getallVendorCheckDetails(dashboardDto);
        return new ResponseEntity<>(svcSearchResult, HttpStatus.OK);
    }

    @Autowired
    VendorUploadChecksRepository vendorUploadChecksRepository;

    @GetMapping("/getVendorUploadCheck")
    public ResponseEntity<?> getallVendorUploadCheckDetails() {
        VendorUploadChecks vendorUploadChecks = vendorUploadChecksRepository.findById(Long.valueOf("1")).get();
        return new ResponseEntity<>(vendorUploadChecks, HttpStatus.OK);
    }

    // @ApiOperation("Get vendor checks details")
    // @GetMapping("/getallVendorChecks/{vendorId}")
    // public ResponseEntity<?> getallVendorCheckDetails(@RequestHeader("Authorization") String authorization,@PathVariable("organizationId") Long vendorId) {
    // 	ServiceOutcome<List<VendorChecks>> svcSearchResult = userService.getallVendorCheckDetails(0vendorId);
    // 	return new ResponseEntity<>(svcSearchResult, HttpStatus.OK);
    // }

    @ApiOperation("Save And Update vendordashboard Information")
    @PostMapping(path = "/saveproofuploadVendorChecks", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ServiceOutcome<VendorChecks>> saveproofuploadVendorChecks(@RequestParam String vendorchecks, @RequestParam String vendorRemarksReport, @RequestHeader("Authorization") String authorization, @RequestParam(value = "file", required = false) MultipartFile proofDocumentNew) {
        System.out.println(vendorchecks + "***************************************************");
        ServiceOutcome<VendorChecks> svcSearchResult = userService.saveproofuploadVendorChecks(vendorchecks, proofDocumentNew, vendorRemarksReport);
        return new ResponseEntity<ServiceOutcome<VendorChecks>>(svcSearchResult, HttpStatus.OK);

    }

    //agentupload
    @ApiOperation("Save And Update vendordashboard Information")
    @PostMapping(path = "/saveInitiateVendorChecks", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ServiceOutcome<VendorChecks>> saveInitiateVendorChecks(@RequestParam String vendorchecks, @RequestHeader("Authorization") String authorization, @RequestParam(value = "file", required = false) MultipartFile proofDocumentNew, @RequestParam(name = "documentUrl", required = false) String docuementUrl) throws IOException {
        System.out.println(vendorchecks + "***************************************************");
        ServiceOutcome<VendorChecks> svcSearchResult = userService.saveInitiateVendorChecks(vendorchecks, proofDocumentNew, docuementUrl);
        return new ResponseEntity<ServiceOutcome<VendorChecks>>(svcSearchResult, HttpStatus.OK);

    }

//    @ApiOperation("Save And Update vendordashboard Information")
//    @PostMapping(path = "/uploadVenorRemarksVendorUploads/{vendorCheckId}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
//    public ServiceOutcome<String> uploadVendorRemarksForChecks(@PathVariable("vendorCheckId") Long vendorCheckId, @RequestParam String vendorRemarksJson) {
//        ServiceOutcome<String> stringServiceOutcome = new ServiceOutcome<>();
//        String vendorRemarks = userService.uploadVendorRemarksForChecks(vendorCheckId, vendorRemarksJson);
//
//
//        return stringServiceOutcome;
//    }


}
