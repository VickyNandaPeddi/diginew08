package com.aashdit.digiverifier.config.superadmin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aashdit.digiverifier.config.superadmin.model.ServiceMaster;

public interface ServiceMasterRepository extends JpaRepository<ServiceMaster, Long> {

	List<ServiceMaster> findByOrganizationOrganizationId(Long organizationId);

	@Query("FROM ServiceMaster where organization.organizationId=:organizationId and ratePerReport > :value and ratePerItem > :value")
	List<ServiceMaster> findByOrganizationOrganizationIdAndRatePerReportAndRatePerItem(@Param("organizationId")Long organizationId,@Param("value")Double value);

}
