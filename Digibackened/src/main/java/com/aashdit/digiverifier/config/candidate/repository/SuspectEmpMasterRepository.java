package com.aashdit.digiverifier.config.candidate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aashdit.digiverifier.config.candidate.model.SuspectEmpMaster;

public interface SuspectEmpMasterRepository extends JpaRepository<SuspectEmpMaster, Long> {

	List<SuspectEmpMaster> findAllByIsActiveTrue();

	List<SuspectEmpMaster> findAllByIsActiveTrueOrderBySuspectEmpMasterIdAsc();

	@Query("FROM SuspectEmpMaster where isActive=true AND suspectEmpMasterId > 0 order by suspectCompanyName ASC")
	List<SuspectEmpMaster> findAllByIsActiveTrueOrderBySuspectCompanyNameAsc();


}
