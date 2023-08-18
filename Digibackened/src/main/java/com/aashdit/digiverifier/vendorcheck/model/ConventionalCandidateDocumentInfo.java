package com.aashdit.digiverifier.vendorcheck.model;

import com.aashdit.digiverifier.config.admin.model.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "t_dgv_conventional_candidate_document_info")
public class ConventionalCandidateDocumentInfo {
    @Id
    @Column(name = "doc_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long docId;
    @Column(name = "document_name")
    private String documentName;
    @Lob
    @Column(name = "document_url")
    private String documentUrl;
    @Column(name = "file_type")
    private String fileType;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "created_on")
    private Date createdOn;
    @Column(name = "candidate_id")
    private String candidateId;
    @Column(name = "resubmitted")
    private boolean resubmitted;
    @Column(name = "request_id")
    private String requestID;
}
