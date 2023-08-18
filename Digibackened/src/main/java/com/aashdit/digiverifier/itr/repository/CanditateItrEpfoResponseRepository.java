package com.aashdit.digiverifier.itr.repository;

import com.aashdit.digiverifier.itr.model.CanditateItrResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CanditateItrEpfoResponseRepository extends JpaRepository<CanditateItrResponse,Long> {
	
	Optional<CanditateItrResponse> findByCandidateId(Long candidateId);
	
}
