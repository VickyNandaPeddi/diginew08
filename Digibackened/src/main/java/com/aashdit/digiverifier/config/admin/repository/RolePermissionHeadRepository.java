package com.aashdit.digiverifier.config.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.digiverifier.config.admin.model.RolePermissionHead;

public interface RolePermissionHeadRepository extends JpaRepository<RolePermissionHead, Long> {

	List<RolePermissionHead> findAllByIsActiveTrue();

}
