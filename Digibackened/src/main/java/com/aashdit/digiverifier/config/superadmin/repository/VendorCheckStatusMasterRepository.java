package com.aashdit.digiverifier.config.superadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.digiverifier.config.superadmin.model.VendorCheckStatusMaster;

public interface VendorCheckStatusMasterRepository extends JpaRepository<VendorCheckStatusMaster, Long> {

//    VendorCheckStatusMaster findByCheckStatusCode();

    VendorCheckStatusMaster findByVendorCheckStatusMasterId(Long vendorCheckStatusMasterId);


}
