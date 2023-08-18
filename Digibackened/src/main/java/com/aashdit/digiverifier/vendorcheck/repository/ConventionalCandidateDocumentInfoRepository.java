package com.aashdit.digiverifier.vendorcheck.repository;

import com.aashdit.digiverifier.vendorcheck.dto.ConventionalCandidateDocDto;
import com.aashdit.digiverifier.vendorcheck.dto.SubmittedCandidates;
import com.aashdit.digiverifier.vendorcheck.model.ConventionalCandidateDocumentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConventionalCandidateDocumentInfoRepository extends JpaRepository<ConventionalCandidateDocumentInfo, Long> {

    @Query("SELECT new com.aashdit.digiverifier.vendorcheck.dto.ConventionalCandidateDocDto(fm.documentName,fm.documentUrl,fm.fileType,fm.createdOn) FROM #{#entityName} fm ")
    List<ConventionalCandidateDocDto> findAllConventionalCandidateDocs();

    List<ConventionalCandidateDocumentInfo> findByCandidateId(String candidateId);

    List<ConventionalCandidateDocumentInfo> findByRequestID(String requestId);

    @Query("select ca from ConventionalCandidateDocumentInfo ca where ca.candidateId=?1")
    ConventionalCandidateDocumentInfo findByCandidateIdForInsufficiency(String candidateId);

    @Query("select ca from ConventionalCandidateDocumentInfo ca where ca.requestID=?1")
    ConventionalCandidateDocumentInfo findByRequestIdForInsufficiency(String requestId);

    Boolean existsByCandidateId(String candidateId);

    Boolean existsByRequestID(String requestId);

    @Query(value = "SELECT cd  FROM ConventionalCandidateDocumentInfo cd  WHERE cd.documentUrl LIKE %?1  AND cd.requestID = ?2")
    ConventionalCandidateDocumentInfo findByDocumentUrlAndRequestId(String checkName, String requestId);

}
