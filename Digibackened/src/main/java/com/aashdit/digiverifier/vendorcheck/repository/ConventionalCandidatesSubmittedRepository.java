package com.aashdit.digiverifier.vendorcheck.repository;

import com.aashdit.digiverifier.vendorcheck.dto.SubmittedCandidates;
import com.aashdit.digiverifier.vendorcheck.dto.UpdateSubmittedCandidatesResponseDto;
import com.aashdit.digiverifier.vendorcheck.model.ConventionalVendorCandidatesSubmitted;
import org.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ConventionalCandidatesSubmittedRepository extends JpaRepository<ConventionalVendorCandidatesSubmitted, Long> {

    @Query("SELECT new com.aashdit.digiverifier.vendorcheck.dto.SubmittedCandidates(fm.candidateId,fm.psNo,fm.name,fm.requestId,fm.requestType,fm.vendorId,fm.applicantId,fm.createdOn,fm.status.statusCode) FROM #{#entityName} fm ")
    List<SubmittedCandidates> findAllSubmittedCandidates();


//    @Query("SELECT new com.aashdit.digiverifier.vendorcheck.dto.SubmittedCandidates(fm.candidateId,fm.psNo,fm.name,fm.requestId,fm.requestType,fm.vendorId,fm.applicantId,fm.createdOn,fm.status) FROM #{#entityName} fm WHERE fm.createdOn BETWEEN :startDate AND :endDate")

    @Query("SELECT new com.aashdit.digiverifier.vendorcheck.dto.SubmittedCandidates(fm.candidateId,fm.psNo,fm.name,fm.requestId,fm.requestType,fm.vendorId,fm.applicantId,fm.createdOn,fm.status.statusCode) FROM #{#entityName} fm WHERE fm.createdOn BETWEEN ?1 AND ?2")
//    List<SubmittedCandidates> findAllSubmittedCandidatesByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    List<SubmittedCandidates> findAllSubmittedCandidatesByDateRange(Date startDate, Date endDate);

    @Query("SELECT new com.aashdit.digiverifier.vendorcheck.dto.UpdateSubmittedCandidatesResponseDto(fm.candidateId,fm.psNo,fm.name,fm.requestId,fm.vendorId) FROM #{#entityName} fm where fm.candidateId=?1 ")
    UpdateSubmittedCandidatesResponseDto findUpdateSubmittedCandidatesResponseDtoByConventinalCandidateId(Long canidateId);

    Boolean existsByCandidateId(Long candidateId);

    Boolean existsByRequestId(String requestID);

    ConventionalVendorCandidatesSubmitted findByRequestId(String requestID);
}
