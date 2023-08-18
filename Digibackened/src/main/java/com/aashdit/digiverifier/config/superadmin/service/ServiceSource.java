package com.aashdit.digiverifier.config.superadmin.service;

import com.aashdit.digiverifier.common.model.ServiceOutcome;
import com.aashdit.digiverifier.config.superadmin.model.ServiceSourceMaster;

public interface ServiceSource {

	ServiceOutcome<ServiceSourceMaster> getServiceSourceMasterByServiceCode(String statusCode);

}
