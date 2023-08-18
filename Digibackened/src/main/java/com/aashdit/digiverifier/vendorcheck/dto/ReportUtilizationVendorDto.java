package com.aashdit.digiverifier.vendorcheck.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public interface ReportUtilizationVendorDto {

    Long getVendorId();

//    void setVendorId(List<Long> vendorId);

    Long getCandidateId();

    //    void setCandidateId(List<Long> candidateId);
    List<String> getSourceId();

//    void setSourceId(List<Long> sourceId);

}
