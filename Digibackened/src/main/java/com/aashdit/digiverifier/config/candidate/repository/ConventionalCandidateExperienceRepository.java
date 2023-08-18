package com.aashdit.digiverifier.config.candidate.repository;

import com.aashdit.digiverifier.config.candidate.model.ConventionalCandidateExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConventionalCandidateExperienceRepository extends JpaRepository<ConventionalCandidateExperience, Long> {

    Boolean existsByConventionalCandidateId(Long candidateId);
    Boolean existsByConventionalRequestId(Long conventionalRequestId);

    List<ConventionalCandidateExperience> findByConventionalCandidateId(Long conventionalCandidateId);

    @Modifying
    void deleteAllByConventionalCandidateId(Long conventionalCandidateID);
    @Modifying
    void deleteAllByConventionalRequestId(Long conventionalRequestID);
}
