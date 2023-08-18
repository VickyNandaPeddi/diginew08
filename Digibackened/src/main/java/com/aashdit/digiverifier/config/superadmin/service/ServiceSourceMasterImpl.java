package com.aashdit.digiverifier.config.superadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aashdit.digiverifier.common.model.ServiceOutcome;
import com.aashdit.digiverifier.config.superadmin.model.ServiceSourceMaster;
import com.aashdit.digiverifier.config.superadmin.repository.ServiceSourceMasterRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ServiceSourceMasterImpl implements ServiceSource {
	
	@Autowired
	private ServiceSourceMasterRepository serviceSourceMasterRepository;
	
	@Override
	public ServiceOutcome<ServiceSourceMaster> getServiceSourceMasterByServiceCode(String serviceCode) {
		ServiceOutcome<ServiceSourceMaster> svcSearchResult = new ServiceOutcome<ServiceSourceMaster>();
		try {
			ServiceSourceMaster serviceSourceMaster= serviceSourceMasterRepository.findByServiceCode(serviceCode);
			if (serviceSourceMaster != null) {
				svcSearchResult.setData(serviceSourceMaster);
				svcSearchResult.setOutcome(true);
				svcSearchResult.setMessage("SUCCESS");
			} else {
				svcSearchResult.setData(null);
				svcSearchResult.setOutcome(false);
				svcSearchResult.setMessage("Service code not found.");
			}
		}
		catch(Exception ex)
		{
			log.error("Exception occured in getServiceSourceMasterByServiceCode method in ServiceSourceMasterImpl-->",ex);
			svcSearchResult.setData(null);
			svcSearchResult.setOutcome(false);
			svcSearchResult.setMessage("Something Went Wrong, Please Try After Sometimes.");
		}
		return svcSearchResult;
	}

	
}
