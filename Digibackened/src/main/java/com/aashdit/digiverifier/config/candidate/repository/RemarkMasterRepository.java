package com.aashdit.digiverifier.config.candidate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.digiverifier.config.candidate.model.RemarkMaster;

public interface RemarkMasterRepository extends JpaRepository<RemarkMaster, Long> {

	List<RemarkMaster> findAllByRemarkType(String remarkType);

	RemarkMaster findByRemarkCode(String string);

}
