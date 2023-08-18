package com.aashdit.digiverifier.config.candidate.repository;

import com.aashdit.digiverifier.config.candidate.model.CandidateCafEducation;
import com.aashdit.digiverifier.config.candidate.model.ConventionalCandidateCafEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConventionalCafCandidateEducationRepository extends JpaRepository<ConventionalCandidateCafEducation, Long> {

    Boolean existsByConventionalCandidateId(Long conventionalCandidateId);

    Boolean existsByConventionalRequestId(Long conventionalRequestId);

    ConventionalCandidateCafEducation findByConventionalRequestId(Long conventionalRequestId);

    List<ConventionalCandidateCafEducation> findByConventionalCandidateId(Long conventionalCandidateId);

    @Modifying
    void deleteAllByConventionalCandidateId(Long conventionalCandidateId);
    @Modifying
    void deleteAllByConventionalRequestId(Long conventionalRequestId);

}
