package com.aashdit.digiverifier.config.candidate.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aashdit.digiverifier.config.candidate.model.CandidateStatusHistory;

@Repository
public interface CandidateStatusHistoryRepository extends JpaRepository<CandidateStatusHistory, Long> {

	@Query("FROM CandidateStatusHistory  WHERE candidate.organization.organizationId=:organizationId AND candidateStatusChangeTimestamp between :startDate and :endDate")
	List<CandidateStatusHistory> findAllByOrganizationIdAndDateRange(@Param("organizationId")Long organizationId, @Param("startDate")Date startDate,@Param("endDate")Date endDate);

	@Query("FROM CandidateStatusHistory  WHERE candidateStatusChangeTimestamp between :startDate and :endDate")
	List<CandidateStatusHistory> findAllByDateRange( @Param("startDate")Date startDate,@Param("endDate")Date endDate);

	@Query("FROM CandidateStatusHistory  WHERE candidate.organization.organizationId in (:organizationId) AND candidateStatusChangeTimestamp between :startDate and :endDate AND statusMaster.statusCode =:status")
	List<CandidateStatusHistory> findAllByOrganizationIdAndDateRangeAndStatus(@Param("organizationId")List<Long> organizationId, @Param("startDate")Date startDate,@Param("endDate")Date endDate,
			@Param("status")String status);

	@Query("FROM CandidateStatusHistory  WHERE candidate.organization.organizationId =:organizationId AND candidateStatusChangeTimestamp between :startDate and :endDate AND statusMaster.statusCode in (:statusList)")
	List<CandidateStatusHistory> findAllByOrganizationIdAndDateRangeAndStatusCode(@Param("organizationId")Long organizationIds, @Param("startDate")Date startDate,
			@Param("endDate")Date endDate,@Param("statusList")List<String> statusList);
	
	List<CandidateStatusHistory> findAllByCandidateCandidateId(Long candidateId);
	
}
