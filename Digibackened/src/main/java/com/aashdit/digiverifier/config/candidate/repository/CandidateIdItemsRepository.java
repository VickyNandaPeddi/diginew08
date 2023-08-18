package com.aashdit.digiverifier.config.candidate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.digiverifier.config.candidate.model.CandidateIdItems;

public interface CandidateIdItemsRepository extends JpaRepository<CandidateIdItems, Long> {

	List<CandidateIdItems> findByCandidateCandidateCode(String candidateCode);

	CandidateIdItems findByCandidateCandidateCodeAndServiceSourceMasterServiceCode(String candidateCode, String serviceCode);

}
