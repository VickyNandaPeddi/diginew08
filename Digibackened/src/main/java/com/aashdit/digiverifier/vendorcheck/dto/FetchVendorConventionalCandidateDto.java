/**
 *
 */
package com.aashdit.digiverifier.vendorcheck.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author ${Nanda Kishore}
 */
@Data
@Getter
@Setter
@NoArgsConstructor
public class FetchVendorConventionalCandidateDto {

    private String requestId;
    private String candidateID;
    private String psno;
    private String vendorId;
    private String requestType;

    private String createdBy;

    private Date createdOn;


    private Long sourceId;

    private Long licheckId;

    private String vendorName;

    private String sourceName;

    public FetchVendorConventionalCandidateDto(String requestId, String candidateID, String psno, String vendorId, Date createdOn) {
        this.requestId = requestId;
        this.candidateID = candidateID;
        this.psno = psno;
        this.vendorId = vendorId;
        this.createdOn = createdOn;
    }

    public FetchVendorConventionalCandidateDto(String requestId, String candidateID, String psno, String vendorId, String requestType) {
        this.requestId = requestId;
        this.candidateID = candidateID;
        this.psno = psno;
        this.vendorId = vendorId;
        this.requestType = requestType;
    }

    public FetchVendorConventionalCandidateDto(String requestId, String candidateID, String psno, String vendorId) {
        this.requestId = requestId;
        this.candidateID = candidateID;
        this.psno = psno;
        this.vendorId = vendorId;
    }
}
