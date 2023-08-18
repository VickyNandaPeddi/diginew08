package com.aashdit.digiverifier.config.superadmin.dto;

import lombok.Data;

import java.util.Date;



@Data
public class OrgDto {

	Long organizationId;
	String organizationName;
	String organizationEmailId;
	String organizationLocation;
	String customerName;
	String gstNumber;
	String panNumber;
	String saacCode;
	String shipmentAddress;
	String pocName;
	String customerPhoneNumber;
	String organizationWebsite;
	byte[] organizationLogo;
	String billingAddress;
	String accountsPoc;
	String accountsPocPhoneNumber;
	String accountPocEmail;
	String emailTemplate;
	String emailConfig;
	String daysToPurge;
	String reportBackupEmail;
	String noYearsToBeVerified;
	Boolean showValidation;
	Double total;
	Boolean isActive;
	Date updatedTimestamp;
	Date createdOn;
	Date lastUpdatedOn;
	String logoUrl;
	String lastUpdatedBy;

	
}
