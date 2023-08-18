package com.aashdit.digiverifier.config.candidate.repository;

import com.aashdit.digiverifier.config.candidate.model.ConventionalCandidate;
import com.aashdit.digiverifier.config.candidate.model.ConventionalCandidateDrugInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConventionalCandidateDrugInfoRepository extends JpaRepository<ConventionalCandidateDrugInfo, Long> {
    Boolean existsByCandidateId(Long candidateId);

    @Modifying
    void deleteAllByConventionalCandidateId(Long conventionalCandidateId);
    @Modifying
    void deleteAllByConventionalRequestId(Long conventionalRequestID);

    Boolean existsByConventionalCandidateId(Long conventionalCandidateId);

    Boolean existsByConventionalRequestId(Long conventionalRequestId);

    List<ConventionalCandidateDrugInfo> findByConventionalCandidateId(Long conventionalCandidateId);


}
