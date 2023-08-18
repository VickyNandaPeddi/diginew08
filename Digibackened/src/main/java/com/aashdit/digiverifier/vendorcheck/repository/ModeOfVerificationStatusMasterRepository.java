package com.aashdit.digiverifier.vendorcheck.repository;

import com.aashdit.digiverifier.vendorcheck.model.ModeOfVerificationStatusMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeOfVerificationStatusMasterRepository extends JpaRepository<ModeOfVerificationStatusMaster, Long> {
}
