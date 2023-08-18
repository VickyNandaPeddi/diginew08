package com.aashdit.digiverifier.config.superadmin.model;

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
import javax.validation.constraints.NotNull;
import com.aashdit.digiverifier.config.admin.model.User;
import lombok.Data;

@Data
@Entity
@Table(name = "t_dgv_vendor_master_new")
public class VendorMasterNew implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1760822105264530177L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vendor_id")
	private Long vendorId;
	
	// @NotNull
	
	@Column(name = "user_id")
	private Long userId;
	
	// @NotNull
	@ManyToOne
	@JoinColumn(name = "source_id")
	private Source source;


	@Column(name = "rate_per_report")
    private Double ratePerReport;
	
	@Column(name = "rate_per_item")
    private Double ratePerItem;

    @Column(name = "tat_per_item")
    private Double tatPerItem;

    @Column(name = "is_active")
    private Boolean isActive;

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
