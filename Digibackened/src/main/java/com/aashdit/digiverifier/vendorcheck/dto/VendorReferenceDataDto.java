package com.aashdit.digiverifier.vendorcheck.dto;

import com.aashdit.digiverifier.config.candidate.model.ConventionalCafAddress;
import com.aashdit.digiverifier.config.candidate.model.ConventionalCandidateCafEducation;
import com.aashdit.digiverifier.config.candidate.model.ConventionalCandidateExperience;
import com.aashdit.digiverifier.config.candidate.model.ConventionalCandidateReferenceInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VendorReferenceDataDto<T> {

    private Long candidateId;
    private String CheckName;
//    private ConventionalCandidateReferenceInfo conventionalCandidateReferenceInfo;
//    private ConventionalCandidateCafEducation conventionalCandidateCafEducation;
//    private ConventionalCafAddress conventionalCafAddress;
//    private ConventionalCandidateExperience conventionalCandidateExperience;

    private List<T> vendorReferenceData;
//    private List<ConventionalCandidateCafEducation> conventionalCandidateCafEducations;
//    private List<ConventionalCafAddress> conventionalCafAddresses;
//    private List<ConventionalCandidateExperience> conventionalCandidateExperiences;


}
