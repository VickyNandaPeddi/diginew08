package com.aashdit.digiverifier.config.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.digiverifier.config.admin.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByRoleName(String string);

	Role findRoleByRoleCode(String roleCode);

}
