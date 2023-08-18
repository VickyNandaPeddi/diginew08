package com.aashdit.digiverifier.config.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.aashdit.digiverifier.config.admin.model.UserRolePermissionMap;

public interface UserRolePermissionMapRepository extends JpaRepository<UserRolePermissionMap, Long> {

	List<UserRolePermissionMap> findAllByRoleRoleId(Long roleId);

	@Modifying
	void deleteByRoleRoleId(Long roleId);

	List<UserRolePermissionMap> findAllByRoleRoleCode(String roleCode);

	List<UserRolePermissionMap> findByCreatedByOrganizationOrganizationIdAndRoleRoleId(Long organizationId, Long roleId);

}
