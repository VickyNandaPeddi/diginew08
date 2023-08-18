package com.aashdit.digiverifier.vendorcheck.repository;


import com.aashdit.digiverifier.vendorcheck.model.ConventionalVendorCandidatesSubmitted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ConventionalVendorCandidatesSubmittedRepository extends JpaRepository<ConventionalVendorCandidatesSubmitted, Long> {

    @Query("FROM ConventionalVendorCandidatesSubmitted WHERE createdBy.userId =:userId AND createdOn between :startDate and :endDate")
    List<ConventionalVendorCandidatesSubmitted> findAllByUserIdAndDateRange(@Param("userId") Long userId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);


}