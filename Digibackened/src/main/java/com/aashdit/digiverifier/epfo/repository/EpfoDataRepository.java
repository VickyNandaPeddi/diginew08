package com.aashdit.digiverifier.epfo.repository;

import com.aashdit.digiverifier.epfo.model.EpfoData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpfoDataRepository extends JpaRepository<EpfoData,Long> {
	
	List<EpfoData> findAllByCandidateCandidateCode(String candidateCode);
	List<EpfoData> findAllByCandidateCandidateId(Long candidateId);
	EpfoData  findByCandidateCandidateCode(String getCandidateCode);
	
}
