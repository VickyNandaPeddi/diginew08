package com.aashdit.digiverifier.config.candidate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.digiverifier.config.candidate.model.CandidateResumeUpload;

public interface CandidateResumeUploadRepository extends JpaRepository<CandidateResumeUpload, Long> {

	CandidateResumeUpload findByCandidateCandidateCode(String candidateCode);

}
