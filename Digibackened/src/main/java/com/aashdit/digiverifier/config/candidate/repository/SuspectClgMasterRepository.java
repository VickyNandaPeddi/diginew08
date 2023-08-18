package com.aashdit.digiverifier.config.candidate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aashdit.digiverifier.config.candidate.model.SuspectClgMaster;

public interface SuspectClgMasterRepository extends JpaRepository<SuspectClgMaster, Long> {

	List<SuspectClgMaster> findAllByIsActiveTrue();

	List<SuspectClgMaster> findAllByIsActiveTrueOrderBySuspectClgMasterIdAsc();

	@Query("FROM SuspectClgMaster where isActive=true AND suspectClgMasterId > 0 order by suspectInstitutionName ASC")
	List<SuspectClgMaster> findAllByIsActiveTrueOrderBySuspectInstitutionNameAsc();

}
