package com.aashdit.digiverifier.digilocker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aashdit.digiverifier.config.admin.model.User;

import lombok.Data;

@Entity
@Table(name="t_dgv_digilocker_issued_documents_type_master")
@Data
public class IssuedDocumentsType implements Serializable {

	private static final long serialVersionUID = 5240205336479056727L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "document_id")
	Long documentId;
	
	@Column(name = "document_name")
	String documentName;
	
	@Column(name = "is_active")
	Boolean isActive=true;
	
	@ManyToOne
	@JoinColumn(name = "created_by")
	private User createdBy;
	
	@Column(name = "created_on")
	private Date createdOn;
	
	@ManyToOne
	@JoinColumn(name = "last_updated_by")
	private User lastUpdatedBy;

	@Column(name = "last_updated_on")
	private Date lastUpdatedOn;
	
}