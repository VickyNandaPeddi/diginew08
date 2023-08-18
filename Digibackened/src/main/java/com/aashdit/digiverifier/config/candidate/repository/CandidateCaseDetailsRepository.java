package com.aashdit.digiverifier.config.candidate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.digiverifier.config.candidate.model.CandidateCaseDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateCaseDetailsRepository extends JpaRepository<CandidateCaseDetails, Long> {

    CandidateCaseDetails findByCandidateCandidateCode(String candidateCode);

}
