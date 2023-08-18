package com.aashdit.digiverifier.config.candidate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.digiverifier.config.candidate.model.CandidateAdressVerification;

public interface CandidateAdressVerificationRepository extends JpaRepository<CandidateAdressVerification, Long> {

	CandidateAdressVerification findByCandidateCandidateCode(String candidateCode);

}
