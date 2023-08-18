package com.aashdit.digiverifier.config.admin.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserDto {

	private Long userId;
	
	private Long organizationId;
	
	private String employeeId;
	
	private String userFirstName;
	
	private String userLastName;
	
	private String userEmailId;
	
	private String location;
	
	private String userMobileNum;
	
	private String userLandlineNum;
	
	private Long roleId;
	
	private Boolean isActive;
	
	private Boolean isUserBlocked;
	
	private String password;
	
	private String createdBy;
	
	private Date createdOn;
	
	private String lastUpdatedBy;

	private Date lastUpdatedOn;
	
	private String userName;
	
	private Boolean isLoggedIn;
	
	private Integer wrongLoginCount;
	
	private Boolean isLocked;
	
	private Long agentSupervisorId;
	
	private String roleName;
	
}
