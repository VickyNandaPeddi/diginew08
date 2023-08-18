package com.aashdit.digiverifier.config.superadmin.service;

import java.util.List;

import com.aashdit.digiverifier.config.superadmin.model.*;
import org.springframework.web.multipart.MultipartFile;

import com.aashdit.digiverifier.common.model.ServiceOutcome;
import com.aashdit.digiverifier.config.superadmin.dto.OrganizationDto;
import com.aashdit.digiverifier.config.superadmin.dto.ServiceConfigurationDto;
import com.aashdit.digiverifier.config.superadmin.dto.ServiceMasterDto;
import com.aashdit.digiverifier.config.superadmin.dto.SourceServiceListDto;
import com.aashdit.digiverifier.config.superadmin.dto.VendorMasterDto;
import com.aashdit.digiverifier.config.superadmin.dto.OrgDto;

public interface OrganizationService {

    ServiceOutcome<Organization> saveOrganization(String organization, Boolean showValidation, MultipartFile file);

    // ServiceOutcome<List<OrgDto>> getAllOrganization();

    ServiceOutcome<List<Organization>> getAllOrganization();

    ServiceOutcome<Organization> getOrganizationById(Long organizationId);

    ServiceOutcome<Organization> activeAndInactiveOrganizationById(Long organizationId, Boolean isActive);

    ServiceOutcome<List<Source>> getAllSource();

    ServiceOutcome<List<ServiceMaster>> saveOrganizationBilling(Long organizationId, List<ServiceMasterDto> serviceMaster);

    ServiceOutcome<List<ServiceMaster>> getAllServicesByOrganizationId(Long organizationId);

    ServiceOutcome<ServiceConfigurationDto> saveOrganizationServiceConfiguration(ServiceConfigurationDto serviceConfigurationDto);

    ServiceOutcome<List<Color>> getAllColor();

    ServiceOutcome<List<ServiceTypeConfig>> getServiceTypeConfigByOrgId(Long organizationId);

    ServiceOutcome<ToleranceConfig> getToleranceConfigByOrgId(Long organizationId);

    ServiceOutcome<List<SourceServiceListDto>> getSourceServiceList(Long organizationId);

    ServiceOutcome<List<OrganizationDto>> getOrganizationListAfterBilling();

    List<OrganizationExecutive> getOrganizationExecutiveByOrganizationId(Long organizationId);

    ServiceOutcome<Boolean> getShowvalidation(Long organizationId);

    ServiceOutcome<List<VendorMasterNew>> getAllVendorServicesUserId(Long organizationId);

    ServiceOutcome<List<VendorMasterNew>> saveVendorChecks(Long userId, List<VendorMasterDto> vendorMasterNew);

    ServiceOutcome<Boolean> saveclientscopeInformation(MultipartFile file);

    ServiceOutcome<List<VendorCheckStatusMaster>> getAllVenorcheckStatus();

    ServiceOutcome<List<VendorCheckStatusMaster>> getAllVenorcheckStatusForVendor();
}
