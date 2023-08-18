package com.aashdit.digiverifier.digilocker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aashdit.digiverifier.digilocker.model.IssuedDocumentsType;

public interface IssuedDocumentsTypeRepository extends JpaRepository<IssuedDocumentsType, Long> {

	List<IssuedDocumentsType> findByIsActiveTrue();

	@Query("Select documentName FROM IssuedDocumentsType")
	List<String> findAllDocumentNameByIsActiveTrue();

	IssuedDocumentsType findByDocumentNameAndIsActiveTrue(String doctype);

}
