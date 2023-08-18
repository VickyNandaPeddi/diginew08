package com.aashdit.digiverifier.config.admin.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.aashdit.digiverifier.config.admin.model.User;
import com.aashdit.digiverifier.config.admin.model.VendorChecks;
import com.aashdit.digiverifier.config.superadmin.model.Source;
import com.aashdit.digiverifier.config.candidate.model.Candidate;
import com.aashdit.digiverifier.config.superadmin.model.Color;
// import com.aashdit.digiverifier.config.admin.model.VendorMasterNew;
import lombok.Data;
import org.hibernate.annotations.Type;

@Data
@Entity
@Table(name = "t_dgv_vendor_uploaded_checks")

/**
 *
 */ public class VendorUploadChecks implements Serializable {
    private static final long serialVersionUID = 5043587024437591514L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_upload_check_id")
    private Long vendoruploadcheckId;


    @NotNull
    @OneToOne
    @JoinColumn(name = "vendor_check_id")
    private VendorChecks vendorChecks;


    @Column(name = "document_name")
    private String documentname;

    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "vendor_Uploaded_Document", columnDefinition = "LONGBLOB")
    private byte[] vendorUploadedDocument;

    @OneToOne
    @JoinColumn(name = "agent_color")
    private Color AgentColor;


    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "created_at")
    private Date createdOn;
    @Lob
    @Column(name = "vendor_attribute_values")
    private ArrayList<String> vendorAttirbuteValue;


}
