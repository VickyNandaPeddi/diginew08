package com.aashdit.digiverifier.config.admin.model;

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
import javax.validation.constraints.NotNull;

import com.aashdit.digiverifier.config.superadmin.model.Organization;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_dgv_user_master")
public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3513694544081413484L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "orgainzation_id")
    private Organization organization;

    @NotBlank
    @Column(name = "employee_id")
    private String employeeId;

    @NotBlank
    @Column(name = "user_first_name")
    private String userFirstName;

    @Column(name = "user_last_name")
    private String userLastName;

    @NotBlank
    @Column(name = "user_email_id")
    private String userEmailId;

    @NotBlank
    @Column(name = "location")
    private String location;

    @NotBlank
    @Column(name = "user_mobile_num")
    private String userMobileNum;

    @Column(name = "user_landline_num")
    private String userLandlineNum;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "is_user_active")
    private Boolean isActive;

    @Column(name = "is_user_blocked")
    private Boolean isUserBlocked;

    @NotBlank
    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "agent_supervisor_id")
    private User agentSupervisor;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "created_on")
    private Date createdOn;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "last_updated_by")
    private User lastUpdatedBy;

    @Column(name = "last_updated_on")
    private Date lastUpdatedOn;

    @Column(name = "username")
    private String userName;

    @Column(name = "is_logged_in")
    private Boolean isLoggedIn;

    @Column(name = "wrong_login_count")
    private Integer wrongLoginCount;

    @Column(name = "is_locked")
    private Boolean isLocked;

    @Column(name = "addl_password")
    private String addlPassword;

    @Column(name = "reporting_email_id")
    private String reportingEmailId;

    public User(@NotBlank String employeeId, @NotBlank String userFirstName, String userLastName,
                @NotBlank String userEmailId, @NotBlank String location, @NotBlank String userMobileNum,
                String userLandlineNum, String reportingEmailId) {
        super();
        this.employeeId = employeeId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmailId = userEmailId;
        this.location = location;
        this.userMobileNum = userMobileNum;
        this.userLandlineNum = userLandlineNum;
        this.reportingEmailId = reportingEmailId;
    }

}
