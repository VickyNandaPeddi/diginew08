package com.aashdit.digiverifier.config.admin.service;

import java.io.IOException;
import java.util.List;

import com.aashdit.digiverifier.config.admin.dto.VendorChecksDto;
import com.aashdit.digiverifier.config.superadmin.dto.DashboardDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.aashdit.digiverifier.common.model.ServiceOutcome;
import com.aashdit.digiverifier.config.admin.dto.UserDto;
import com.aashdit.digiverifier.config.admin.model.User;
import com.aashdit.digiverifier.config.admin.dto.VendorInitiatDto;
import com.aashdit.digiverifier.config.admin.model.VendorChecks;

@Service
public interface UserService {

    ServiceOutcome<UserDto> saveUser(UserDto user);

    ServiceOutcome<List<UserDto>> getUserByOrganizationIdAndUser(Long organizationId, User user);

    ServiceOutcome<UserDto> getUserById(Long userId);

    ServiceOutcome<User> activeAndInactiveUserById(Long userId, Boolean isActive);

    ServiceOutcome<User> findByUsername(String userName);

    ServiceOutcome<User> saveUserLoginData(User user);

    ServiceOutcome<User> getAdminDetailsForOrganization(Long organizationId);

    ServiceOutcome<List<User>> getAgentSupervisorList(Long organizationId);

    ServiceOutcome<Boolean> saveAgentInformation(MultipartFile file);

    ServiceOutcome<UserDto> getUserProfile();

    ServiceOutcome<List<User>> getAdminList();

    ServiceOutcome<User> activeNInAtiveAdmin(Long userId, Boolean isActive);

    ServiceOutcome<List<User>> getAgentList(Long organizationId);

    ServiceOutcome<User> getUserByUserId(Long userId);

    ServiceOutcome<List<User>> getUsersByRoleCode(String roleCode);

    void logoutUserAfter5Mins();

    ServiceOutcome<List<User>> getVendorList(Long vendorId);

    // ServiceOutcome<VendorInitiatDto> saveInitiateVendorChecks(VendorInitiatDto vendorInitiatDto);
    @Transactional(rollbackFor = Exception.class)
    ServiceOutcome<VendorChecks> saveInitiateVendorChecks(String vendorChecks, MultipartFile proofDocumentNew, String documentUrl) throws IOException;


    ServiceOutcome<VendorChecks> saveproofuploadVendorChecks(String vendorChecks, MultipartFile proofDocumentNew, String vendorRemarksReport);

    ServiceOutcome<List<VendorChecks>> getVendorCheckDetails(Long candidateId);

    ServiceOutcome<List<VendorChecksDto>> getallVendorCheckDetails(DashboardDto dashboardDto);

    ServiceOutcome<List<VendorChecks>> getallVendorChecsa(Long vendorId);

//    public String uploadVendorRemarksForChecks(Long vendorCheckId, String vendorRemarksJson);
}
