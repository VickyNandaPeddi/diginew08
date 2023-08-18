package com.aashdit.digiverifier.config.candidate.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aashdit.digiverifier.config.candidate.model.Candidate;


@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    Candidate findByCandidateCode(String candidateCode);

    Candidate findByCandidateId(Long candidateId);

    Boolean existsByConventionalCandidateId(Long conventionalCandidateId);

    @Query(value = "select * from t_dgv_candidate_basic where candidate_id "
            + "in (select candidate_id from t_dgv_candidate_status where status_master_id in (:statusIds) and last_updated_on between :startDate and :endDate) and organization_id=:organizationId", nativeQuery = true)
    List<Candidate> getCandidateListByOrganizationIdAndStatusAndLastUpdated(@Param("organizationId") Long organizationId, @Param("statusIds") List<Long> statusIds, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "select * from t_dgv_candidate_basic where candidate_id "
            + "in (select candidate_id from t_dgv_candidate_status where status_master_id in (:statusIds) and last_updated_on between :startDate and :endDate) and created_by in (:agentIds)", nativeQuery = true)
    List<Candidate> getCandidateListByUserIdAndStatusAndLastUpdated(@Param("agentIds") List<Long> agentIds, @Param("statusIds") List<Long> statusIds, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    Candidate findByEmailId(String emailId);

    Candidate findByConventionalCandidateId(Long convetionalCandidateId);
    Candidate findByConventionalRequestId(Long conventionalRequestId);

    Candidate findByPanNumberAndCandidateCode(String pan, String candidateCode);


}
