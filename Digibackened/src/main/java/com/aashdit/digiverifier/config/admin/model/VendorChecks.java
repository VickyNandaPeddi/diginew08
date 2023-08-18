package com.aashdit.digiverifier.config.admin.model;

// import java.io.Serializable;
// import java.util.Date;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.Table;
// import javax.validation.constraints.NotNull;
// import lombok.Data;

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
import com.aashdit.digiverifier.config.superadmin.model.Source;
import com.aashdit.digiverifier.config.candidate.model.Candidate;
// import com.aashdit.digiverifier.config.admin.model.VendorMasterNew;
import lombok.Data;
import org.hibernate.annotations.Type;
import com.aashdit.digiverifier.config.superadmin.model.VendorCheckStatusMaster;

@Data
@Entity
@Table(name = "t_dgv_vendor_checks")
public class VendorChecks implements Serializable {

    /**
     *
     */
    // private static final long serialVersionUID = 5043587024437591514L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_check_id")
    private Long vendorcheckId;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @Column(name = "vendor_id")
    private Long vendorId;

    @ManyToOne
    @JoinColumn(name = "source_id")
    private Source source;

    @Column(name = "tat")
    private Double tat;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "expires_on")
    private Boolean expireson;


    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "agent_Uploaded_Document", columnDefinition = "LONGBLOB")
    private byte[] agentUploadedDocument;


    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "created_at")
    private Date createdOn;

    @Column(name = "Is_proof_uploaded")
    private Boolean Isproofuploaded;

    @Column(name = "document_name")
    private String documentname;

    @Column(name = "candidate_name")
    private String candidateName;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "address")
    private String address;

    @Column(name = "alternate_contact_no")
    private String alternateContactNo;

    @Column(name = "type_of_panel")
    private String typeOfPanel;

    @ManyToOne
    @JoinColumn(name = "vendor_checkstatus_master_id")
    private VendorCheckStatusMaster vendorCheckStatusMaster;


    @Column(name = "pathkey")
    private String pathKey;


    @Column(name = "licheckid")
    private Long licheckId;

}
