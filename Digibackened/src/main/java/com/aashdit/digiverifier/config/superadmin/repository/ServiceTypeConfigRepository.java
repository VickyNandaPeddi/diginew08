package com.aashdit.digiverifier.config.superadmin.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aashdit.digiverifier.config.superadmin.model.ServiceTypeConfig;

public interface ServiceTypeConfigRepository extends JpaRepository<ServiceTypeConfig, Long> {

	List<ServiceTypeConfig> findAllByOrganizationOrganizationId(Long organizationId);

	@Modifying
	@Transactional
	void deleteByOrganizationOrganizationId(Long organizationId);

	@Query("Select serviceSourceMaster.serviceCode FROM ServiceTypeConfig where organization.organizationId=:orgId")
	List<String> getServiceSourceMasterByOrgId(@Param("orgId")Long orgId);

}
