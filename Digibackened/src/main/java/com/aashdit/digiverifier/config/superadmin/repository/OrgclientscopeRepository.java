package com.aashdit.digiverifier.config.superadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aashdit.digiverifier.config.superadmin.model.Orgclientscope;

@Repository
public interface OrgclientscopeRepository extends JpaRepository<Orgclientscope, Long> {

}
