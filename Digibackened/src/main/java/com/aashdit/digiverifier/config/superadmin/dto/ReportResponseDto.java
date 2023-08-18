package com.aashdit.digiverifier.config.superadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportResponseDto {
	
	private Long id;
	
	private String name;
	
	private Integer newuploadcount;
	
	private String newuploadStatusCode;
	
	private Integer reinvitecount;
	
	private String  reinviteStatusCode;
	
	private Integer finalreportCount;
	
	private String  finalReportStatusCode;
	
	private Integer interimReportCount;
	
	private String  interimReportStatusCode;
	
	private Integer pendingCount;
	
	private String  pendingStatusCode;
	
	private Integer processDeclinedCount;
	
	private String  processDeclinedStatusCode;
	
	private Integer invitationExpireCount;
	
	private String  invitationExpiredStatusCode;
	
	private Integer agentCount;
	
}
