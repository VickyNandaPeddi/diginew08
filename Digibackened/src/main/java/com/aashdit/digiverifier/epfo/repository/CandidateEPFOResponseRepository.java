package com.aashdit.digiverifier.epfo.repository;

import com.aashdit.digiverifier.epfo.model.CandidateEPFOResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateEPFOResponseRepository extends JpaRepository<CandidateEPFOResponse,Long> {
	
	Optional<CandidateEPFOResponse> findByCandidateIdAndUan(Long candidateId,String uan);
	
	List<CandidateEPFOResponse> findByCandidateId(Long candidateId);
	
}
