package com.aashdit.digiverifier.config.candidate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aashdit.digiverifier.config.candidate.model.CandidateAddComments;

public interface CandidateAddCommentRepository extends JpaRepository<CandidateAddComments, Long> {

    CandidateAddComments findByCandidateCandidateId(Long candidateId);


}
