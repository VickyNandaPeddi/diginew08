package com.aashdit.digiverifier.config.candidate.repository;

import com.aashdit.digiverifier.config.candidate.model.ConventionalCandidateReferenceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConventionalCandidateReferenceInfoRepository extends JpaRepository<ConventionalCandidateReferenceInfo, Long> {
    Boolean existsByConventionalCandiateId(Long conventionalCandidateId);

    Boolean existsByConventionalRequestId(Long conventionalRequestId);

    List<ConventionalCandidateReferenceInfo> findByConventionalCandiateId(Long conventionalCandidateId);

    @Modifying
    void deleteAllByConventionalCandiateId(Long conventionalCandidateId);
    @Modifying
    void deleteAllByConventionalRequestId(Long conventionalRequestID);
}
