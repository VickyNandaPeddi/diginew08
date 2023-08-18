package com.aashdit.digiverifier.config.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.digiverifier.config.admin.model.RolePermissionMaster;

public interface RolePermissionMasterRepository extends JpaRepository<RolePermissionMaster, Long> {

	List<RolePermissionMaster> findAllByIsActiveTrue();

	List<RolePermissionMaster> findAllByRolePermissionHeadPermissionHeadIdAndIsActiveTrue(Long permissionHeadId);

}
