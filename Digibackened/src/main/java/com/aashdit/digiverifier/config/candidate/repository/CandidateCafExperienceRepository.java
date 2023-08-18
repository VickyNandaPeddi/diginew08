package com.aashdit.digiverifier.config.candidate.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aashdit.digiverifier.config.candidate.model.CandidateCafExperience;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateCafExperienceRepository extends JpaRepository<CandidateCafExperience, Long> {

    List<CandidateCafExperience> findAllByCandidateCandidateCodeOrderByInputDateOfJoiningDesc(String candidateCode);

    @Query(value = "select distinct uan from t_dgv_candidate_caf_experience where candidate_id=:candidateId and uan is NOT NULL", nativeQuery = true)
    List<String> getCandidateUan(@Param("candidateId") Long candidateId);

    @Query(value = "From CandidateCafExperience where candidate.candidateId=:candidateId and serviceSourceMaster.serviceCode=:code")
    List<CandidateCafExperience> findByServiceSourceMasterCode(@Param("candidateId") Long candidateId, @Param("code") String code);

    @Query(value = "select distinct (color.colorName) from CandidateCafExperience where candidate.candidateCode =:candidateCode")
    List<String> findDistinctColors(@Param("candidateCode") String candidateCode);

    @Query(value = "select candidateCafExperienceId From CandidateCafExperience where candidate.candidateCode=:candidateCode and serviceSourceMaster.serviceCode=:serviceCode and inputDateOfJoining >= :inputJoiningDate and inputDateOfExit <= :inputExitDate")
    List<Long> findByCandidateCodeAndServiceSourceMasterCodeAndDates(@Param("candidateCode") String candidateCode, @Param("serviceCode") String serviceCode, @Param("inputJoiningDate") Date inputJoiningDate, @Param("inputExitDate") Date inputExitDate);

    List<CandidateCafExperience> findAllByCandidateCandidateId(Long candidateId);

    @Modifying
    void deleteAllByCandidateConventionalRequestId(Long conventionalRequestId);

    void deleteById(Long id);
}
