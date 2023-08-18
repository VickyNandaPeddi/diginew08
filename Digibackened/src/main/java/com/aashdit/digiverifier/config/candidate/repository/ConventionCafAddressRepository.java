package com.aashdit.digiverifier.config.candidate.repository;

import com.aashdit.digiverifier.config.candidate.model.ConventionalCafAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConventionCafAddressRepository extends JpaRepository<ConventionalCafAddress, Long> {

    Boolean existsByConventionalCandidateId(Long candidateId);
    Boolean existsByConventionalRequestId(Long requestId);

    @Modifying
    void deleteAllByConventionalCandidateId(Long conventionalCandidateId);
    @Modifying
    void deleteAllByConventionalRequestId(Long conventionalRequestId);

    List<ConventionalCafAddress> findByConventionalCandidateId(Long conventionalCandidateId);
}
