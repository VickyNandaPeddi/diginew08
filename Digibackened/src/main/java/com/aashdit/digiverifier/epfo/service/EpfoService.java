package com.aashdit.digiverifier.epfo.service;

import com.aashdit.digiverifier.common.model.ServiceOutcome;
import com.aashdit.digiverifier.epfo.dto.EpfoDetailsDto;

public interface EpfoService {

	ServiceOutcome<EpfoDetailsDto>  getEpfoCaptcha(String candidateId);

	ServiceOutcome<String> getEpfodetail(EpfoDetailsDto epfoDetails);

	ServiceOutcome<String> getEpfodetailNew(EpfoDetailsDto epfoDetails);
}
