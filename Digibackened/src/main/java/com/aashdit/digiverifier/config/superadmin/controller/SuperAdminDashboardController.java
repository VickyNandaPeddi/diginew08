package com.aashdit.digiverifier.config.superadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aashdit.digiverifier.common.model.ServiceOutcome;
import com.aashdit.digiverifier.config.superadmin.dto.DashboardDto;
import com.aashdit.digiverifier.config.superadmin.dto.SuperAdminDashboardDto;
import com.aashdit.digiverifier.config.superadmin.service.SuperAdminDashboardService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/organization")
public class SuperAdminDashboardController {
	@Autowired
	private SuperAdminDashboardService superAdminDashboardService;

	@ApiOperation("Get Activity Details")
	@RequestMapping(value = "/getPendingDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<ServiceOutcome<SuperAdminDashboardDto>> getPendingDetails(@RequestBody SuperAdminDashboardDto superAdminDashboardDto) {
		ServiceOutcome<SuperAdminDashboardDto> svcSearchResult = superAdminDashboardService.getPendingDetails(superAdminDashboardDto);
		return new ResponseEntity<ServiceOutcome<SuperAdminDashboardDto>>(svcSearchResult, HttpStatus.OK);

	}
	
	@ApiOperation("Get ALL Activity Details")
	@RequestMapping(value = "/getActivityDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<ServiceOutcome<SuperAdminDashboardDto>> getActivityDetails(@RequestBody SuperAdminDashboardDto superAdminDashboardDto) {
		ServiceOutcome<SuperAdminDashboardDto> svcSearchResult = superAdminDashboardService.getActivityDetails(superAdminDashboardDto);
		return new ResponseEntity<ServiceOutcome<SuperAdminDashboardDto>>(svcSearchResult, HttpStatus.OK);

	}
	
	@ApiOperation("Get Service Utilization Rate per Item")
	@RequestMapping(value = "/getUtilizationRatePerItem", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<ServiceOutcome<SuperAdminDashboardDto>> getUtilizationRatePerItem(@RequestBody SuperAdminDashboardDto superAdminDashboardDto) {
		ServiceOutcome<SuperAdminDashboardDto> svcSearchResult = superAdminDashboardService.getUtilizationRatePerItem(superAdminDashboardDto);
		return new ResponseEntity<ServiceOutcome<SuperAdminDashboardDto>>(svcSearchResult, HttpStatus.OK);

	}
	
	@ApiOperation("Get Service Utilization Rate per Report")
	@RequestMapping(value = "/getUtilizationRatePerReport", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<ServiceOutcome<SuperAdminDashboardDto>> getUtilizationRatePerReport(@RequestBody SuperAdminDashboardDto superAdminDashboardDto) {
		ServiceOutcome<SuperAdminDashboardDto> svcSearchResult = superAdminDashboardService.getUtilizationRatePerReport(superAdminDashboardDto);
		return new ResponseEntity<ServiceOutcome<SuperAdminDashboardDto>>(svcSearchResult, HttpStatus.OK);

	}
	
	@ApiOperation("Get Company Comparision by Activity")
	@RequestMapping(value = "/getCompanyCountByActivity", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<ServiceOutcome<DashboardDto>> getCompanyCountByActivity(@RequestBody(required=false) DashboardDto dashboardDto) {
		ServiceOutcome<DashboardDto> svcSearchResult = superAdminDashboardService.getCompanyCountByActivity(dashboardDto);
		return new ResponseEntity<ServiceOutcome<DashboardDto>>(svcSearchResult, HttpStatus.OK);

	}
	
}
