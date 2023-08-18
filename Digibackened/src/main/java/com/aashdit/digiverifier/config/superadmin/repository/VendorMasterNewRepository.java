package com.aashdit.digiverifier.config.superadmin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.aashdit.digiverifier.config.admin.model.User;
import org.springframework.stereotype.Repository;
import com.aashdit.digiverifier.config.superadmin.model.Source;

import com.aashdit.digiverifier.config.superadmin.model.VendorMasterNew;

@Repository
public interface VendorMasterNewRepository extends JpaRepository<VendorMasterNew, Long> {
    VendorMasterNew findByVendorId(Long userId);
    // List<VendorMasterNew> findByOrganizationOrganizationId(Long userId);
    List<VendorMasterNew> findByUserId(Long userId);
    // List<User> findAllByAgentSupervisorUserId(Long userId);
    // @Query("FROM VendorMasterNew where userId.userId=:userId and ratePerItem > :value and tatPerItem > :value")
	// List<VendorMasterNew> findByOrganizationOrganizationIdAndRatePerReportAndRatePerItem(@Param("userId")Long userId,@Param("value")Double value);

    @Query(value="select tatPerItem from VendorMasterNew where userId.userId=:userId and sourceId.sourceId=:sourceId",nativeQuery=true)
    List<Long> findBytatforitem(@Param("userId") Long userid, @Param("sourceId") Long sourceid);
	
}