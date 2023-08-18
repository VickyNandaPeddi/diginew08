package com.aashdit.digiverifier.itr.service;

import com.aashdit.digiverifier.common.model.ServiceOutcome;
import com.aashdit.digiverifier.itr.dto.ITRDetailsDto;

public interface ITRService {

	ServiceOutcome<String> getITRDetailsFromITRSite(ITRDetailsDto iTRDetails);

}
