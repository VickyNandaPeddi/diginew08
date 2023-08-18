package com.aashdit.digiverifier.config.admin.dto;

import java.util.List;

import com.aashdit.digiverifier.config.admin.model.RolePermissionHead;
import com.aashdit.digiverifier.config.admin.model.RolePermissionMaster;

import lombok.Data;

@Data
public class RoleHeadPermissionListDto {
	
	private RolePermissionHead rolePermissionHead;
	private List<RolePermissionMaster> rolePermissionMaster;

}
