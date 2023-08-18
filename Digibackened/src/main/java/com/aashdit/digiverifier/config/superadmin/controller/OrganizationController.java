package com.aashdit.digiverifier.config.superadmin.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aashdit.digiverifier.common.model.ServiceOutcome;
import com.aashdit.digiverifier.config.candidate.service.CandidateService;
import com.aashdit.digiverifier.config.superadmin.dto.OrganizationDto;
import com.aashdit.digiverifier.config.superadmin.dto.ServiceConfigurationDto;
import com.aashdit.digiverifier.config.superadmin.dto.ServiceMasterDto;
import com.aashdit.digiverifier.config.superadmin.dto.SourceServiceListDto;
import com.aashdit.digiverifier.config.superadmin.dto.OrgDto;
import com.aashdit.digiverifier.config.superadmin.model.Color;
import com.aashdit.digiverifier.config.superadmin.model.Organization;
import com.aashdit.digiverifier.config.superadmin.model.ServiceMaster;
import com.aashdit.digiverifier.config.superadmin.model.ServiceTypeConfig;
import com.aashdit.digiverifier.config.superadmin.model.Source;
import com.aashdit.digiverifier.config.superadmin.model.ToleranceConfig;
import com.aashdit.digiverifier.config.superadmin.service.OrganizationService;
import com.aashdit.digiverifier.config.superadmin.model.VendorMasterNew;
import com.aashdit.digiverifier.config.superadmin.dto.VendorMasterDto;
import com.aashdit.digiverifier.config.superadmin.model.VendorCheckStatusMaster;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping(value = "/api/organization")
@Slf4j
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private CandidateService candidateService;

    @ApiOperation("Save And Update Organization Information")
    @PostMapping(path = "/saveOrganization", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ServiceOutcome<Organization>> saveNUpdateOrganization(@RequestParam String organization, @RequestParam Boolean showValidation, @RequestHeader("Authorization") String authorization, @RequestParam(value = "file", required = false) MultipartFile file) {
        System.out.println(showValidation + "***************************************************");
        ServiceOutcome<Organization> svcSearchResult = organizationService.saveOrganization(organization, showValidation, file);
        return new ResponseEntity<ServiceOutcome<Organization>>(svcSearchResult, HttpStatus.OK);

    }

    // @ApiOperation("Get all Organization Information")
    // @GetMapping("/getAllOrganization")
    // public ResponseEntity<?> getAllOrganization(@RequestHeader("Authorization") String authorization) {
    // 	System.out.println("inside controller");
    // 	// ServiceOutcome<List<OrgDto>> svcSearchResult = organizationService.getAllOrganization();
    // 	ServiceOutcome<List> svcSearchResult = organizationService.getAllOrganization();
    // 	return new ResponseEntity<>(svcSearchResult, HttpStatus.OK);
    // }

    @ApiOperation("Get all Organization Information")
    @GetMapping("/getAllOrganization")
    public ResponseEntity<?> getAllOrganization(@RequestHeader("Authorization") String authorization) {
        System.out.println("---------------------------------------------getorg------------------------------------------------------------");
        ServiceOutcome<List<Organization>> svcSearchResult = organizationService.getAllOrganization();
        return new ResponseEntity<>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Get Organization Information By Id")
    @GetMapping("/getOrganizationById/{organizationId}")
    public ResponseEntity<?> getOrganizationById(@PathVariable Long organizationId, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<Organization> svcSearchResult = organizationService.getOrganizationById(organizationId);
        return new ResponseEntity<>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Active/Inactive Organization Information By organizationId")
    @PutMapping("/activeNInAtiveOrganization/{organizationId}/{isActive}")
    public ResponseEntity<?> activeNInAtiveOrganization(@PathVariable("organizationId") Long organizationId, @PathVariable("isActive") Boolean isActive, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<Organization> svcSearchResult = organizationService.activeAndInactiveOrganizationById(organizationId, isActive);
        return new ResponseEntity<>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Get All Source")
    @GetMapping("/getAllSource")
    public ResponseEntity<?> getAllSource(@RequestHeader("Authorization") String authorization) {
        ServiceOutcome<List<Source>> svcSearchResult = organizationService.getAllSource();
        return new ResponseEntity<>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("SaveNUpdate Organization Billing ")
    @PostMapping(path = "/saveOrganizationBilling/{organizationId}")
    public ResponseEntity<?> saveOrganizationBilling(@PathVariable Long organizationId, @RequestBody List<ServiceMasterDto> serviceMaster, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<List<ServiceMaster>> svcSearchResult = organizationService.saveOrganizationBilling(organizationId, serviceMaster);
        return new ResponseEntity<>(svcSearchResult, HttpStatus.OK);

    }

    @ApiOperation("Get Data for Organization Billing By organizationId")
    @GetMapping("/getAllServices/{organizationId}")
    public ResponseEntity<?> getAllServicesByOrganizationId(@PathVariable Long organizationId, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<List<ServiceMaster>> svcSearchResult = organizationService.getAllServicesByOrganizationId(organizationId);
        return new ResponseEntity<>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation(" Get All Color")
    @GetMapping("/getAllColor")
    public ResponseEntity<?> getAllColor(@RequestHeader("Authorization") String authorization) {
        ServiceOutcome<List<Color>> svcSearchResult = organizationService.getAllColor();
        return new ResponseEntity<>(svcSearchResult, HttpStatus.OK);
    }


    @ApiOperation("Get All Services For Organization Service Configuration By OrganizationId")
    @GetMapping("/getAllServicesForConfiguration/{organizationId}")
    public ResponseEntity<?> getAllServicesForConfigurationByOrganizationId(@PathVariable Long organizationId, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<List<SourceServiceListDto>> svcSearchResult = organizationService.getSourceServiceList(organizationId);
        return new ResponseEntity<>(svcSearchResult, HttpStatus.OK);
    }


    @ApiOperation("Save Service Configuration for Organization")
    @PostMapping(path = "/saveOrganizationServiceConfiguration")
    public ResponseEntity<?> saveOrganizationServiceConfiguration(@RequestBody ServiceConfigurationDto serviceConfigurationDto, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<ServiceConfigurationDto> svcSearchResult = organizationService.saveOrganizationServiceConfiguration(serviceConfigurationDto);
        return new ResponseEntity<>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Get Service Configuration Details for Organization")
    @GetMapping("/getServiceTypeConfigByOrgId/{organizationId}")
    public ResponseEntity<?> getServiceTypeConfigByOrgId(@PathVariable Long organizationId, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<ServiceConfigurationDto> svcSearchResult = new ServiceOutcome<>();
        ServiceConfigurationDto serviceConfigurationDto = new ServiceConfigurationDto();
        List<Long> collect = null;
        try {
            ServiceOutcome<List<ServiceTypeConfig>> serviceTypeConfig = organizationService.getServiceTypeConfigByOrgId(organizationId);
            ServiceOutcome<ToleranceConfig> toleranceConfig = organizationService.getToleranceConfigByOrgId(organizationId);
            if (serviceTypeConfig.getData() == null && toleranceConfig.getData() == null) {
                svcSearchResult.setData(null);
                svcSearchResult.setOutcome(false);
                svcSearchResult.setMessage("No service Configuration found for this Organization..");
            } else {
                collect = serviceTypeConfig.getData().stream().map(x -> x.getServiceSourceMaster().getSourceServiceId()).collect(Collectors.toList());
                serviceConfigurationDto.setSourceServiceId(collect);
                serviceConfigurationDto.setServiceCode(serviceTypeConfig.getData().stream().map(x -> x.getServiceSourceMaster().getServiceCode()).collect(Collectors.toList()));
                serviceConfigurationDto.setToleranceConfig(toleranceConfig.getData());
                serviceConfigurationDto.setOrganizationId(organizationId);
                svcSearchResult.setData(serviceConfigurationDto);
                svcSearchResult.setOutcome(true);
                svcSearchResult.setMessage("SUCCESS");

            }
            return new ResponseEntity<>(svcSearchResult, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occured in getServiceTypeConfigByOrgId method in OrganizationController-->" + e);
            return new ResponseEntity<>(svcSearchResult, HttpStatus.OK);
        }
    }

    @ApiOperation("Get organization list after billing")
    @GetMapping("/getOrganizationListAfterBilling")
    public ResponseEntity<?> getOrganizationListAfterBilling(@RequestHeader("Authorization") String authorization) {
        ServiceOutcome<List<OrganizationDto>> outcome = organizationService.getOrganizationListAfterBilling();
        return new ResponseEntity<>(outcome, HttpStatus.OK);
    }

    @ApiOperation("Get Service Config from candidate for customer")
    @GetMapping("/getServiceConfigCodes/{organizationId}")
    public ResponseEntity<ServiceOutcome<?>> getServiceConfigCodes(@PathVariable("organizationId") Long organizationId) {
        ServiceOutcome<List<String>> svcSearchResult = candidateService.getServiceConfigCodes("", organizationId);
        return new ResponseEntity<ServiceOutcome<?>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("Get show validation Information By Id")

    @GetMapping("/getShowvalid/{organizationId}")

    public ResponseEntity<ServiceOutcome<Boolean>> getShowvalidation(@PathVariable Long organizationId, @RequestHeader("Authorization") String authorization) {
        System.out.println("-------------------------------------" + organizationId);
        ServiceOutcome<Boolean> svcSearchResult = organizationService.getShowvalidation(organizationId);
        return new ResponseEntity<ServiceOutcome<Boolean>>(svcSearchResult, HttpStatus.OK);

    }

    @ApiOperation("Get Data for Organization Billing By organizationId")
    @GetMapping("/getAllVendorServices/{userId}")
    public ResponseEntity<?> getAllVendorServicesUserId(@PathVariable Long userId, @RequestHeader("Authorization") String authorization) {
        System.out.println("-----------------------------------------------------userid_new--------------------------------------" + userId);
        ServiceOutcome<List<VendorMasterNew>> svcSearchResult = organizationService.getAllVendorServicesUserId(userId);
        return new ResponseEntity<>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation("SaveNUpdate vendor checks")
    @PostMapping(path = "/saveVendorChecks/{userId}")
    public ResponseEntity<?> saveVendorChecks(@PathVariable Long userId, @RequestBody List<VendorMasterDto> vendorMasterNew, @RequestHeader("Authorization") String authorization) {
        System.out.println("-----------------------------------------------------userid_java--------------------------------------" + userId);
        System.out.println("-----------------------------------------------------userid_java--------------------------------------" + vendorMasterNew);

        ServiceOutcome<List<VendorMasterNew>> svcSearchResult = organizationService.saveVendorChecks(userId, vendorMasterNew);
        return new ResponseEntity<>(svcSearchResult, HttpStatus.OK);

    }

    @ApiOperation("upload client scope")
    @PostMapping("/uploadClientscope")
    public ResponseEntity<ServiceOutcome<Boolean>> uploadclientscope(@RequestParam("file") MultipartFile file, @RequestHeader("Authorization") String authorization) {
        ServiceOutcome<Boolean> svcSearchResult = organizationService.saveclientscopeInformation(file);
        return new ResponseEntity<ServiceOutcome<Boolean>>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation(" Get All vendor checkstatus")
    @GetMapping("/getAllVenorcheckStatus")
    public ResponseEntity<?> getAllVenorcheckStatus(@RequestHeader("Authorization") String authorization) {
        ServiceOutcome<List<VendorCheckStatusMaster>> svcSearchResult = organizationService.getAllVenorcheckStatus();
        return new ResponseEntity<>(svcSearchResult, HttpStatus.OK);
    }

    @ApiOperation(" Get All vendor checkstatus")
    @GetMapping("/getAllVenorcheckStatusForVendor")
    public ResponseEntity<?> getAllVenorcheckStatusForVendor(@RequestHeader("Authorization") String authorization) {
        ServiceOutcome<List<VendorCheckStatusMaster>> svcSearchResult = organizationService.getAllVenorcheckStatusForVendor();
        return new ResponseEntity<>(svcSearchResult, HttpStatus.OK);
    }

}
