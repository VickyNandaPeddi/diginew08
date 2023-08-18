package com.aashdit.digiverifier.config.candidate.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aashdit.digiverifier.config.candidate.model.CandidateStatus;

@Repository
public interface CandidateStatusRepository extends JpaRepository<CandidateStatus, Long> {

    CandidateStatus findByCandidateCandidateCode(String candidateCode);

    CandidateStatus findByCandidateCandidateId(Long candidateId);

    List<CandidateStatus> findAllByStatusMasterStatusCode(String status);

    List<CandidateStatus> findAllByCandidateOrganizationOrganizationIdAndLastUpdatedOnBetween(Long organizationId,
                                                                                              Date startDate, Date endDate);

    List<CandidateStatus> findAllByCandidateCreatedByUserIdInAndLastUpdatedOnBetween(List<Long> agentIds, Date startDate,
                                                                                     Date endDate);

    List<CandidateStatus> findAllByStatusMasterStatusCodeIn(List<String> statusList);

    List<CandidateStatus> findAllByCreatedOnBetweenAndCandidateOrganizationOrganizationIdIn(Date startDate,
                                                                                            Date endDate, List<Long> organizationIds);

    @Query("FROM CandidateStatus  WHERE candidate.organization.organizationId=:organizationId AND lastUpdatedOn between :startDate and :endDate")
    List<CandidateStatus> findAllByOrganizationIdAndDateRange(@Param("organizationId") Long organizationId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("FROM CandidateStatus  WHERE lastUpdatedOn between :startDate and :endDate")
    List<CandidateStatus> findAllByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    List<CandidateStatus> findAllByCreatedOnBetweenAndCandidateOrganizationOrganizationIdInAndCreatedByUserIdIn(
            Date startDate, Date endDate, List<Long> organizationIds, List<Long> agentIds);

    List<CandidateStatus> findAllByCreatedByUserIdInAndStatusMasterStatusCodeIn(List<Long> agentIds,
                                                                                List<String> statusList);

    List<CandidateStatus> findAllByCandidateOrganizationOrganizationIdInAndStatusMasterStatusCodeIn(
            List<Long> organizationIds, List<String> statusList);

}
