package com.aashdit.digiverifier.config.admin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aashdit.digiverifier.common.model.ServiceOutcome;
import com.aashdit.digiverifier.config.admin.dto.RoleHeadPermissionListDto;
import com.aashdit.digiverifier.config.admin.dto.RolePermissionDTO;
import com.aashdit.digiverifier.config.admin.model.Role;
import com.aashdit.digiverifier.config.admin.model.RolePermissionHead;
import com.aashdit.digiverifier.config.admin.model.RolePermissionMaster;
import com.aashdit.digiverifier.config.admin.model.User;
import com.aashdit.digiverifier.config.admin.model.UserRolePermissionMap;
import com.aashdit.digiverifier.config.admin.repository.RolePermissionHeadRepository;
import com.aashdit.digiverifier.config.admin.repository.RolePermissionMasterRepository;
import com.aashdit.digiverifier.config.admin.repository.RoleRepository;
import com.aashdit.digiverifier.config.admin.repository.UserRolePermissionMapRepository;
import com.aashdit.digiverifier.utils.SecurityHelper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RolePermissionMasterRepository rolePermissionMasterRepository;
	
	@Autowired
	private UserRolePermissionMapRepository userRolePermissionMapRepository;
	
	@Autowired
	private RolePermissionHeadRepository rolePermissionHeadRepository;
	
	@Override
	public ServiceOutcome<List<Role>> getAllRole() {
		ServiceOutcome<List<Role>> svcSearchResult = new ServiceOutcome<List<Role>>();
		try {
			List<Role> roleList= roleRepository.findAll();
			if(!roleList.isEmpty()) {
				svcSearchResult.setData(roleList);
				svcSearchResult.setOutcome(true);
				svcSearchResult.setMessage("SUCCESS");
			}else {
				svcSearchResult.setData(null);
				svcSearchResult.setOutcome(false);
				svcSearchResult.setMessage("NO Role FOUND");
			}
		}
		catch(Exception ex)
		{
			log.error("Exception occured in getAllRole method in RoleServiceImpl-->",ex);
			svcSearchResult.setData(null);
			svcSearchResult.setOutcome(false);
			svcSearchResult.setMessage("Something Went Wrong, Please Try After Sometimes.");
		}
		return svcSearchResult;
	}


	@Override
	public ServiceOutcome<List<RoleHeadPermissionListDto>> getAllRolePermission() {
		
		ServiceOutcome<List<RoleHeadPermissionListDto>> svcSearchResult = new ServiceOutcome<List<RoleHeadPermissionListDto>>();
		List<RoleHeadPermissionListDto> roleHeadPermissionListDtoList=new ArrayList<RoleHeadPermissionListDto>();
		try {
			List<RolePermissionHead> rolePermissionheadList=rolePermissionHeadRepository.findAllByIsActiveTrue();
			if(rolePermissionheadList!=null) {
				for (RolePermissionHead rolePermissionHeadObj : rolePermissionheadList) {
					RoleHeadPermissionListDto roleHeadPermissionListDtoObj=new RoleHeadPermissionListDto();
					roleHeadPermissionListDtoObj.setRolePermissionHead(rolePermissionHeadObj);
					List<RolePermissionMaster> rolePermissionMasterListObj=rolePermissionMasterRepository.findAllByRolePermissionHeadPermissionHeadIdAndIsActiveTrue(rolePermissionHeadObj.getPermissionHeadId());
					roleHeadPermissionListDtoObj.setRolePermissionMaster(rolePermissionMasterListObj);
					roleHeadPermissionListDtoList.add(roleHeadPermissionListDtoObj);
				} 
			}
			if(!roleHeadPermissionListDtoList.isEmpty()) {
				svcSearchResult.setData(roleHeadPermissionListDtoList);
				svcSearchResult.setOutcome(true);
				svcSearchResult.setMessage("SUCCESS");
			}else {
				svcSearchResult.setData(null);
				svcSearchResult.setOutcome(false);
				svcSearchResult.setMessage("NO Role Permission FOUND");
			}
		}
		catch(Exception ex)
		{
			log.error("Exception occured in getAllRolePermission method in RoleServiceImpl-->",ex);
			svcSearchResult.setData(null);
			svcSearchResult.setOutcome(false);
			svcSearchResult.setMessage("Something Went Wrong, Please Try After Sometimes.");
		}
		return svcSearchResult;
	}

	@Transactional
	@Override
	public ServiceOutcome<RolePermissionDTO> rolePermission(RolePermissionDTO rolePermissionDTO) {
		ServiceOutcome<RolePermissionDTO> svcSearchResult = new ServiceOutcome<RolePermissionDTO>();
		List<UserRolePermissionMap> userRolePermissionMapList=null;
		RolePermissionDTO rolePermissionDTOObj=new RolePermissionDTO();
		try {
			UserRolePermissionMap userRolePermissionMapObj=null;
			User currentUser = SecurityHelper.getCurrentUser();
			if(rolePermissionDTO.getRoleId()!= null) {
				userRolePermissionMapList=userRolePermissionMapRepository.findAllByRoleRoleId(rolePermissionDTO.getRoleId());
				if(!userRolePermissionMapList.isEmpty()){
					userRolePermissionMapRepository.deleteByRoleRoleId(rolePermissionDTO.getRoleId());
				}
				if (rolePermissionDTO.getPermissionId().size() > 0) {
			    	for(int i=0;i<rolePermissionDTO.getPermissionId().size();i++)
			    	{
			    		userRolePermissionMapObj=new UserRolePermissionMap();
			    		userRolePermissionMapObj.setRole(roleRepository.findById(rolePermissionDTO.getRoleId()).get());
			    		userRolePermissionMapObj.setRolePermissionMaster(rolePermissionMasterRepository.findById(rolePermissionDTO.getPermissionId().get(i)).get());
			    		userRolePermissionMapObj.setCreatedBy(currentUser);
			    		userRolePermissionMapObj.setCreatedOn(new Date());
			    		userRolePermissionMapRepository.save(userRolePermissionMapObj);
			    	}
				}
				userRolePermissionMapList=userRolePermissionMapRepository.findAllByRoleRoleId(rolePermissionDTO.getRoleId());
		    	List<Long> collect = userRolePermissionMapList.stream().map(x -> x.getRolePermissionMaster().getPermissionId()).collect(Collectors.toList());
		    	rolePermissionDTOObj.setPermissionId(collect);
		    	rolePermissionDTOObj.setRoleId(rolePermissionDTO.getRoleId());
		    	svcSearchResult.setData(rolePermissionDTOObj);
		    	svcSearchResult.setOutcome(true);
		    	svcSearchResult.setMessage("Role permission saved successfully.");
			}
			else {
				svcSearchResult.setData(null);
		    	svcSearchResult.setOutcome(false);
		    	svcSearchResult.setMessage("NOT FOUND");
			}
			
		}catch (Exception ex) {
			log.error("Exception occured in rolePermission method in RoleServiceImpl-->" , ex);
			svcSearchResult.setData(null);
	    	svcSearchResult.setOutcome(false);
	    	svcSearchResult.setMessage("Something Went Wrong, Please Try After Sometimes.");
		}
		return svcSearchResult;
	}


	@Override
	public ServiceOutcome<RolePermissionDTO> getAllUserRolePerMissionMap(Long roleId) {
		ServiceOutcome<RolePermissionDTO> svcSearchResult = new ServiceOutcome<RolePermissionDTO>();
		try {
			RolePermissionDTO rolePermissionDTOObj=new RolePermissionDTO();
			List<UserRolePermissionMap> userRolePermissionMapList = userRolePermissionMapRepository.findAllByRoleRoleId(roleId);
			List<Long> collect = userRolePermissionMapList.stream().map(x -> x.getRolePermissionMaster().getPermissionId()).collect(Collectors.toList());
	    	rolePermissionDTOObj.setPermissionId(collect);
	    	rolePermissionDTOObj.setRoleId(roleId);;
			svcSearchResult.setData(rolePermissionDTOObj);
			svcSearchResult.setOutcome(true);
			svcSearchResult.setMessage("SUCCESS");
		}
		catch(Exception ex)
		{
			log.error("Exception occured in getAllUserRolePerMissionMap method in RoleServiceImpl-->",ex);
			svcSearchResult.setData(null);
			svcSearchResult.setOutcome(false);
			svcSearchResult.setMessage("Something Went Wrong, Please Try After Sometimes.");
		}
		return svcSearchResult;
	}


	@Override
	public ServiceOutcome<Role> saveNUpdateRole(Role role) {
		ServiceOutcome<Role> svcSearchResult = new ServiceOutcome<Role>();
		try {
			if(StringUtils.isNoneBlank(role.getRoleName())) {
				User currentUser = SecurityHelper.getCurrentUser();
				String[] splitted = role.getRoleName().trim().toUpperCase().split("_");
				Role roleCodeIsExists = roleRepository.findRoleByRoleCode("ROLE_" + StringUtils.join(splitted));
				if(roleCodeIsExists !=null) {
					svcSearchResult.setData(null);
					svcSearchResult.setOutcome(false);
					svcSearchResult.setMessage("Role Code Already Exist");
				}
				else {
					Role roleObj=null;
					role.setRoleCode("ROLE_" + StringUtils.join(splitted));
					role.setUpdatedTimestamp(new Date());
					role.setCreatedOn(new Date());
					role.setCreatedBy(currentUser);
					roleObj=roleRepository.save(role);
					svcSearchResult.setData(roleObj);
					svcSearchResult.setMessage("SUCCESS");
				}
			}
			else {
				svcSearchResult.setData(null);
				svcSearchResult.setOutcome(false);
				svcSearchResult.setMessage("Please Specify Role");
			}
		}
		catch(Exception ex)
		{
			log.error("Exception occured in saveNUpdateRole method in RoleServiceImpl-->",ex);
			svcSearchResult.setData(null);
			svcSearchResult.setOutcome(false);
			svcSearchResult.setMessage("Something Went Wrong, Please Try After Sometimes.");
		}
		return svcSearchResult;
	}


	@Override
	public ServiceOutcome<List<Role>> getRoleDropDownByUser() {
		ServiceOutcome<List<Role>> svcSearchResult = new ServiceOutcome<List<Role>>();
		try {
			User currentUser = SecurityHelper.getCurrentUser();
			Role roleObj=null;
			List<Role> roleList=new ArrayList<Role>();
			List<UserRolePermissionMap> userRolePermissionMapList=null;
			List<String> rolePermissionCodeList=null;
			if(currentUser.getRole().getRoleCode().equals("ROLE_PARTNERADMIN")||currentUser.getRole().getRoleCode().equals("ROLE_AGENTSUPERVISOR")) {
				userRolePermissionMapList=userRolePermissionMapRepository.findByCreatedByOrganizationOrganizationIdAndRoleRoleId(currentUser.getOrganization().getOrganizationId(),currentUser.getRole().getRoleId());
				rolePermissionCodeList = userRolePermissionMapList.parallelStream().map(x -> x.getRolePermissionMaster().getPermissionCode()).collect(Collectors.toList());
		        if(!rolePermissionCodeList.isEmpty()&&rolePermissionCodeList!=null) {
		        	if(currentUser.getRole().getRoleCode().equals("ROLE_PARTNERADMIN")) {
		        		for (String rolePermissionCode : rolePermissionCodeList) {
							if(rolePermissionCode.equals("CREATEAGENT")) {
								roleObj=roleRepository.findRoleByRoleCode("ROLE_AGENTHR");
								roleList.add(roleObj);
							}
							if(rolePermissionCode.equals("CREATEAGENTSUPERVISOR")) {
								roleObj=roleRepository.findRoleByRoleCode("ROLE_AGENTSUPERVISOR");
								roleList.add(roleObj);
							}
							
						}
		        	}
		        	if(currentUser.getRole().getRoleCode().equals("ROLE_AGENTSUPERVISOR")) {
		        		for (String rolePermissionCode : rolePermissionCodeList) {
							if(rolePermissionCode.equals("CREATEAGENT")) {
								roleObj=roleRepository.findRoleByRoleCode("ROLE_AGENTHR");
								roleList.add(roleObj);
							}
						}
		        	}
		        }
			}else {
	        	Role role= roleRepository.findById(currentUser.getRole().getRoleId()).get();
	        	if(role.getRoleAccess()!=null) {
	        		String[] arrOfStr = role.getRoleAccess().split(",");
	        		for (String a : arrOfStr) {
			        	roleObj= roleRepository.findById(Long.parseLong(a)).get();
			        	roleList.add(roleObj);
		        	}
	        	}
	        }
	    	svcSearchResult.setData(roleList);
			svcSearchResult.setOutcome(true);
			svcSearchResult.setMessage("Success");
		}
		catch(Exception ex)
		{
			log.error("Exception occured in getRoleByUser method in RoleServiceImpl-->",ex);
			svcSearchResult.setData(null);
			svcSearchResult.setOutcome(false);
			svcSearchResult.setMessage("Something Went Wrong, Please Try After Sometimes.");
		}
		return svcSearchResult;
	}


	@Override
	public ServiceOutcome<List<String>> getAllRolePerMissionCodeByRoleCode(String roleCode) {
		ServiceOutcome<List<String>> svcSearchResult = new ServiceOutcome<List<String>>();
		try {
			if(StringUtils.isNoneBlank(roleCode)) {
				roleCode=roleCode.replaceAll("\"", "");
				User user = SecurityHelper.getCurrentUser();
				Role role = roleRepository.findRoleByRoleCode(roleCode);
				List<UserRolePermissionMap> userRolePermissionMapList = userRolePermissionMapRepository.findByCreatedByOrganizationOrganizationIdAndRoleRoleId(user.getOrganization().getOrganizationId(),role.getRoleId());
				if(!userRolePermissionMapList.isEmpty()) {
					List<String> permissionCodeList = userRolePermissionMapList.stream().map(x -> x.getRolePermissionMaster().getPermissionCode()).collect(Collectors.toList());
					svcSearchResult.setData(permissionCodeList);
					svcSearchResult.setOutcome(true);
					svcSearchResult.setMessage("List of Permission Code.");
				}else {
					svcSearchResult.setData(null);
					svcSearchResult.setOutcome(true);
					svcSearchResult.setMessage("No Permission Code found.");
				}
			}
		}
		catch(Exception ex)
		{
			log.error("Exception occured in getAllRolePerMissionCodeByRoleCode method in RoleServiceImpl-->",ex);
			svcSearchResult.setData(null);
			svcSearchResult.setOutcome(false);
			svcSearchResult.setMessage("Something Went Wrong, Please Try After Sometimes.");
		}
		return svcSearchResult;
	}

}
