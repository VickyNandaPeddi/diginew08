package com.aashdit.digiverifier.config.candidate.model;

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
import com.aashdit.digiverifier.config.superadmin.model.Color;
import com.aashdit.digiverifier.config.superadmin.model.ServiceMaster;
import com.aashdit.digiverifier.config.superadmin.model.ServiceSourceMaster;
import com.aashdit.digiverifier.config.superadmin.model.Source;

import lombok.Data;

@Data
@Entity
@Table(name = "t_dgv_candidate_caf_address")
public class CandidateCafAddress implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7932023734763559209L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_caf_address_id")
    private Long candidateCafAddressId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "source_service_id")
    private ServiceSourceMaster serviceSourceMaster;
    //multiple fields
    @Column(name = "candidate_address")
    private String candidateAddress;

    @Column(name = "pin_code")
    private Integer pinCode;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    //addresstype
    @Column(name = "is_present_address")
    private Boolean isPresentAddress;

    @Column(name = "is_permanent_address")
    private Boolean isPermanentAddress;
    //
    @Column(name = "is_asset_delivery_address")
    private Boolean isAssetDeliveryAddress;

    @ManyToOne
    @JoinColumn(name = "remark_id")
    private RemarkMaster remarkMaster;

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

    @Column(name = "holder_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "address_verification_id")
    private CandidateAdressVerification addressVerification;

}
