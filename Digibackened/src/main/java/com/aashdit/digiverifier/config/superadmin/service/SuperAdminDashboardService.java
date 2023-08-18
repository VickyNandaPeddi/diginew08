package com.aashdit.digiverifier.config.superadmin.service;

import com.aashdit.digiverifier.common.model.ServiceOutcome;
import com.aashdit.digiverifier.config.superadmin.dto.DashboardDto;
import com.aashdit.digiverifier.config.superadmin.dto.SuperAdminDashboardDto;

public interface SuperAdminDashboardService {

	ServiceOutcome<SuperAdminDashboardDto> getPendingDetails(SuperAdminDashboardDto superAdminDashboardDto);

	ServiceOutcome<SuperAdminDashboardDto> getActivityDetails(SuperAdminDashboardDto superAdminDashboardDto);

	ServiceOutcome<SuperAdminDashboardDto>  getUtilizationRatePerItem(SuperAdminDashboardDto superAdminDashboardDto);

	ServiceOutcome<SuperAdminDashboardDto>  getUtilizationRatePerReport(SuperAdminDashboardDto superAdminDashboardDto);

	ServiceOutcome<DashboardDto> getCompanyCountByActivity(DashboardDto dashboardDto);

}
