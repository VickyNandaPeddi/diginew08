package com.aashdit.digiverifier.config.superadmin.service;

import com.aashdit.digiverifier.common.model.ServiceOutcome;
import com.aashdit.digiverifier.config.superadmin.Enum.ReportType;
import com.aashdit.digiverifier.config.superadmin.dto.ReportSearchDto;

public interface ReportService {

    ServiceOutcome<ReportSearchDto> getCustomerUtilizationReportData(ReportSearchDto reportSearchDto);

    ServiceOutcome<ReportSearchDto> getCustomerUtilizationReportByAgent(ReportSearchDto reportSearchDto);

    ServiceOutcome<ReportSearchDto> getCanididateDetailsByStatus(ReportSearchDto reportSearchDto);

    ServiceOutcome<ReportSearchDto> eKycReportData(ReportSearchDto reportSearchDto);

    String generateDocument(String candidateCode, String token, ReportType documentType);


}
