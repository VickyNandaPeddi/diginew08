package com.aashdit.digiverifier.config.candidate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aashdit.digiverifier.config.candidate.model.CandidateCafAddress;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateCafAddressRepository extends JpaRepository<CandidateCafAddress, Long> {

    List<CandidateCafAddress> findAllByCandidateCandidateCode(String candidateCode);

    @Modifying
    void deleteAllByCandidateCandidateId(Long candidateID);


    List<CandidateCafAddress> findByCandidateCandidateId(String candidateId);

    CandidateCafAddress findByCandidateCandidateCodeAndServiceSourceMasterServiceCode(String candidateCode, String serviceCode);

    @Query(value = "select distinct (color.colorName) from CandidateCafAddress where candidate.candidateCode =:candidateCode")
    List<String> findDistinctColors(@Param("candidateCode") String candidateCode);

    @Query(value = "select count(*) from CandidateCafAddress where candidate.candidateCode =:candidateCode and addressVerification is not null")
    Long findCountByCandidateCodeAndRelAddrVerification(@Param("candidateCode") String candidateCode);

    CandidateCafAddress findByCandidateCandidateCodeAndAddressVerificationIsNotNull(String candidateCode);

    CandidateCafAddress findByCandidateCandidateCodeAndServiceSourceMasterServiceCodeAndAddressVerificationIsNull(String candidateCode, String string);

}
