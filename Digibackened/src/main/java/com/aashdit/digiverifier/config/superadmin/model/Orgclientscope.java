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
import javax.validation.constraints.NotBlank;
import lombok.ToString;
import org.hibernate.annotations.Type;
import com.aashdit.digiverifier.config.admin.model.User;
import lombok.Data;



@Data
@Entity
@Table(name = "t_dgv_orgclientscope")
public class Orgclientscope implements Serializable {

    // private static final long serialVersionUID = -4790328078476964299L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id")
	private Long clientid;

    // @NotBlank


	@Column(name = "client_name")
	private String clientName;

    // @NotBlank
	@Column(name = "education")
	private String education;

    // @NotBlank
	@Column(name = "employment")
	private String employment;

    @Column(name = "interim_report")
	private Boolean interimReport;

	@Column(name = "pan")
	private String pan;
	
	@Column(name = "pan_address")
	private String panAddress;

	@Column(name = "aadhaar")
	private String aadhaar;

	@Column(name = "aadhaar_address")
	private String aadhaarAddress;

	@Column(name = "uan")
	private String uan;

	@Column(name = "ekyc")
	private String eKyc;

	@Column(name = "passport")
	private String passport;
	
	@Column(name = "reference_check_1")
	private String referenceCheck1;

	
	@Column(name = "reference_check_2")
	private String referenceCheck2;

    // @NotBlank
	@Column(name = "criminal_check")
	private String criminalCheck;

    // @NotBlank
	@Column(name = "db_check")
	private String dbCheck;

    // @NotBlank
	@Column(name = "address_verification")
	private String addressVerification;

    // @NotBlank
    @Column(name = "ID_ENUM_PP_PAN_AADHAR_DL")
	private String ID_ENUM_PP_PAN_AADHAR_DL;

    
	@Column(name = "drug_test")
	private String drugTest;

    
	@Column(name = "credit_check")
	private String creditCheck;


    @Column(name = "Additional_Remarks")
	private String AdditionalRemarks;

    // @NotBlank
    // @Column(name"Customer_mandate_BV_for_allcoation_ENUM_Mandate")
    // private String CustomermandateBVforallcoationENUMMandate;

}





