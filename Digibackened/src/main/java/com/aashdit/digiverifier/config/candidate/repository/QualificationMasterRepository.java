package com.aashdit.digiverifier.config.candidate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.digiverifier.config.candidate.model.QualificationMaster;

public interface QualificationMasterRepository extends JpaRepository<QualificationMaster, Long> {

	QualificationMaster findByQualificationCode(String code);

}
