package com.aashdit.digiverifier.config.candidate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.digiverifier.config.candidate.model.CandidateEmailStatus;

public interface CandidateEmailStatusRepository extends JpaRepository<CandidateEmailStatus, Long> {

	CandidateEmailStatus findByCandidateCandidateCode(String candidateCode);

}
