package com.aashdit.digiverifier.config.admin.service;

import java.util.List;

import com.aashdit.digiverifier.common.model.ServiceOutcome;
import com.aashdit.digiverifier.config.admin.dto.RoleHeadPermissionListDto;
import com.aashdit.digiverifier.config.admin.dto.RolePermissionDTO;
import com.aashdit.digiverifier.config.admin.model.Role;

public interface RoleService {

	ServiceOutcome<List<Role>> getAllRole();

	ServiceOutcome<List<RoleHeadPermissionListDto>> getAllRolePermission();

	ServiceOutcome<RolePermissionDTO> rolePermission(RolePermissionDTO rolePermissionDTO);

	ServiceOutcome<RolePermissionDTO> getAllUserRolePerMissionMap(Long roleId);

	ServiceOutcome<Role> saveNUpdateRole(Role role);

	ServiceOutcome<List<Role>> getRoleDropDownByUser();

	ServiceOutcome<List<String>> getAllRolePerMissionCodeByRoleCode(String roleCode);

}
