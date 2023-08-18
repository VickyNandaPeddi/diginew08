package com.aashdit.digiverifier.config.candidate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.digiverifier.config.candidate.model.StatusMaster;

public interface StatusMasterRepository extends JpaRepository<StatusMaster, Long> {

    StatusMaster findByStatusCode(String statusCode);

    List<StatusMaster> findByStatusCodeIn(List<String> statusCodes);

}
