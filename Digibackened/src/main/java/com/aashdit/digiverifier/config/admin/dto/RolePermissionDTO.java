package com.aashdit.digiverifier.config.admin.dto;

import java.util.List;

import lombok.Data;

@Data
public class RolePermissionDTO {
	
	private List<Long> permissionId;
	private Long roleId;

}
