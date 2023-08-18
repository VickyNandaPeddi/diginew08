package com.aashdit.digiverifier.config.candidate.repository;

import com.aashdit.digiverifier.config.candidate.model.ConventionalCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConventionalCandidateRepository extends JpaRepository<ConventionalCandidate, Long> {
    @Query(
            "select fm from ConventionalCandidate fm where fm.conventionalCandidateId=?1"
    )
    ConventionalCandidate existsByConventionalCandidateId(Long conventionalCandidateId);


    ConventionalCandidate findByConventionalCandidateId(Long conventionalCandidateId);
    ConventionalCandidate findByConventionalRequestId(Long conventionalRequestId);

}
