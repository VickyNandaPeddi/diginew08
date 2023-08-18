package com.aashdit.digiverifier.config.superadmin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.digiverifier.config.superadmin.model.ServiceSourceMaster;

public interface ServiceSourceMasterRepository extends JpaRepository<ServiceSourceMaster, Long> {

	List<ServiceSourceMaster> findAllBySourceSourceId(Long sourceId);

	ServiceSourceMaster findByServiceCode(String serviceCode);

}
