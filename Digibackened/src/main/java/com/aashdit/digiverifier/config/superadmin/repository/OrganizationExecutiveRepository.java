package com.aashdit.digiverifier.config.superadmin.repository;


import com.aashdit.digiverifier.config.superadmin.model.OrganizationExecutive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationExecutiveRepository extends JpaRepository<OrganizationExecutive,Long> {
	
	@Query(value = "select * from t_dgv_organization_executive where organization_id = :organizationId order by weight desc",nativeQuery = true)
	List<OrganizationExecutive> findAllByOrganizationIdAndOrderByWeightDesc(@Param(value = "organizationId") Long organizationId);
	
}
