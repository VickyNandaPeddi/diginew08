package com.aashdit.digiverifier.config.candidate.repository;

import com.aashdit.digiverifier.config.candidate.model.CandidateVerificationState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateVerificationStateRepository extends JpaRepository<CandidateVerificationState,Long> {
	
	CandidateVerificationState findByCandidateCandidateId(Long candidateId);
	
}
