/**
 *
 */
package com.aashdit.digiverifier.vendorcheck.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;

import com.aashdit.digiverifier.config.admin.model.User;
import com.aashdit.digiverifier.config.admin.model.VendorChecks;
import com.aashdit.digiverifier.config.superadmin.model.Source;
import com.aashdit.digiverifier.config.superadmin.model.VendorCheckStatusMaster;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author ${Nanda Kishore}
 */

@Getter
@Setter
@Entity
@Table(name = "t_dgv_conventional_vendorchecks_to_perform")
public class ConventionalVendorliChecksToPerform {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Id;
    @Column(name = "check_code")
    private Long checkCode;
    @Column(name = "check_name")
    private String checkName;
    @Column(name = "check_unique_id")
    private Long checkUniqueId;

    @Column(name = "request_id")
    private String requestId;


    /*
     * enum of checkstatus
     * */
    @OneToOne
    @JoinColumn(name = "check_status")
    private VendorCheckStatusMaster checkStatus;

    @Column(name = "check_remarks")
    private String checkRemarks;
    @Column(name = "mode_of_verification_required")
    private String modeOfVerificationRequired;
    @Column(name = "mode_of_verification_performed")
    private String modeOfVerificationPerformed;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "completed_date")
    private String completedDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;
    @Column(name = "created_on")
    private Date createdOn;
    @Column(name = "candidate_id")
    private String candidateId;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "vendor_check")
    private VendorChecks vendorChecks;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "source")
    private Source source;

    @Column(name = "vendor_name")
    private String vendorName;

    @Column(name = "source_name")
    private String sourceName;

//    @Column(name = "resubmitted")
//    private String resubmitted;


}
