package com.aashdit.digiverifier.config.superadmin.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_dgv_organization_executive")
public class OrganizationExecutive {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "organization_executive_id")
	private Long organizationExecutiveId;
	
	@Column(name = "organization_id")
	private Long organizationId;
	
	@OneToOne
	@JoinColumn(name = "executive_id",insertable = false,updatable = false)
	private Executive executive;
	
	@Column(name = "executive_id")
	private Long executiveId;
	
	@Column(name = "weight")
	private Long weight;
}
