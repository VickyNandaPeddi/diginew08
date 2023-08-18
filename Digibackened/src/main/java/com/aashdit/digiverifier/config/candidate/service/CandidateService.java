package com.aashdit.digiverifier.config.candidate.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.aashdit.digiverifier.config.candidate.dto.*;
import com.aashdit.digiverifier.config.candidate.model.*;
import com.aashdit.digiverifier.vendorcheck.dto.FetchVendorConventionalCandidateDto;
import org.json.JSONArray;
import org.springframework.web.multipart.MultipartFile;

import com.aashdit.digiverifier.common.model.ServiceOutcome;
import com.aashdit.digiverifier.config.superadmin.dto.DashboardDto;
import com.aashdit.digiverifier.config.admin.dto.VendorUploadChecksDto;
import com.aashdit.digiverifier.common.model.Content;

public interface CandidateService {

    ServiceOutcome<List> saveCandidateInformation(MultipartFile file);

    ServiceOutcome<List> saveConventionalCandidateInformation(FetchVendorConventionalCandidateDto fetchVendorConventionalCandidateDto);

    ServiceOutcome<DashboardDto> getUploadDetailsStatusAndCountConventional(DashboardDto dashboardDto);

    ServiceOutcome<DashboardDto> findConvCandidateForInterimAndFinal(DashboardDto dashboardDto);

    ServiceOutcome<List> saveConventionalCandidateDrugInformation(FetchVendorConventionalCandidateDto fetchVendorConventionalCandidateDto);

    ServiceOutcome<List> saveConventionalCandidateReferenceInformation(FetchVendorConventionalCandidateDto fetchVendorConventionalCandidateDto);

    ServiceOutcome<List> saveConventionalCandidateExperienceInformation(FetchVendorConventionalCandidateDto fetchVendorConventionalCandidateDto);

    ServiceOutcome<List> saveConventionalCandidateEducationalInformation(FetchVendorConventionalCandidateDto fetchVendorConventionalCandidateDto);

    ServiceOutcome<List> saveConventionalCandidateAddressInformation(FetchVendorConventionalCandidateDto fetchVendorConventionalCandidateDto);

    ServiceOutcome<DashboardDto> getAllCandidateList(DashboardDto dashboardDto);

    ServiceOutcome<Boolean> invitationSent(CandidateInvitationSentDto candidateInvitationSentDto);

    ServiceOutcome<CandidateDetailsDto> updateCandidate(CandidateDetailsDto candidateDetails);

    ServiceOutcome<DashboardDto> getCandidateStatusAndCount(DashboardDto dashboardDto);

    ServiceOutcome<Boolean> cancelCandidate(String referenceNo);

    List<CandidateStatus> expireInvitationForCandidate();

    ServiceOutcome<CandidateStatus> getCandidateStatusByCandidateCode(String code);

    ServiceOutcome<DashboardDto> getReportDeliveryDetailsStatusAndCount(DashboardDto dashboardDto);

    ServiceOutcome<List<RemarkMaster>> getAllRemark(String remarkType);

    List<CandidateStatus> processDeclined();

    ServiceOutcome<CandidateDetailsDto> getCandidateByCandidateCode(String referenceNo);

    Candidate findCandidateByCandidateCode(String candidateCode);

    ServiceOutcome<Boolean> declineAuthLetter(String candidateCode);

    ServiceOutcome<Boolean> saveFakeCompanyDetails(MultipartFile file);

    ServiceOutcome<Boolean> saveFakeCollegeDetails(MultipartFile file);

    ServiceOutcome<List<QualificationMaster>> getQualificationList();

    ServiceOutcome<Boolean> saveNUpdateCandidateEducation(String candidateCafEducation, MultipartFile certificate);

    ServiceOutcome<CandidateCafEducationDto> getCandidateEducationById(Long candidateCafEducationId);

    ServiceOutcome<Boolean> saveNUpdateCandidateExperience(String candidateCafExperience, MultipartFile certificate);

    ServiceOutcome<CandidateCafExperienceDto> getCandidateExperienceById(Long candidateCafExperienceId);

    ServiceOutcome<CandidationApplicationFormDto> candidateApplicationFormDetails(String candidateCode);

    ServiceOutcome<Boolean> saveCandidateApplicationForm(String candidateCafEducation,
                                                         JSONArray candidateCafAddress, MultipartFile resume, String candidateCode);

    ServiceOutcome<Boolean> updateCandidateEducationStatusAndRemark(ApprovalStatusRemarkDto approvalStatusRemarkDto);

    ServiceOutcome<Boolean> updateCandidateExperienceStatusAndRemark(ApprovalStatusRemarkDto approvalStatusRemarkDto);

    ServiceOutcome<Boolean> updateCandidateAddressStatusAndRemark(ApprovalStatusRemarkDto approvalStatusRemarkDto);

    ServiceOutcome<Boolean> candidateApplicationFormApproved(String candidateCode, MultipartFile criminalVerificationDocument, Long criminalVerificationColorId, MultipartFile globalDatabseCaseDetailsDocument, Long globalDatabseCaseDetailsColorId);

    ServiceOutcome<List<SuspectClgMaster>> getAllSuspectClgList();

    ServiceOutcome<List<SuspectEmpMaster>> getAllSuspectEmpList();

    ServiceOutcome<Boolean> relationshipAddressVerification(String candidateCafRealation, MultipartFile document);

    ServiceOutcome<DashboardDto> getPendingDetailsStatusAndCount(DashboardDto dashboardDto);

    ServiceOutcome<Candidate> saveIsFresher(String candidateCode, Boolean isFresher);

    ServiceOutcome<CandidationApplicationFormDto> candidateApplicationFormDetailsExceptCandidate(String candidateCode);

    ServiceOutcome<CandidateCafExperience> updateCandidateExperience(CandidateCafExperienceDto candidateCafExperienceDto);

    ServiceOutcome<CandidateCafAddress> saveCandidateAddress(CandidateCafAddressDto candidateCafAddressDto);

    ServiceOutcome<List<String>> getServiceConfigCodes(String candidateCode, Long orgId);

    ServiceOutcome<Candidate> setIsLoaAccepted(String candidateCode);

    CandidateStatusHistory createCandidateStatusHistory(CandidateStatus candidateStatus, String who);

    ServiceOutcome<List<StatusMaster>> getAllStatus();

    ServiceOutcome<String> generateInterimReport(String candidateCode) throws FileNotFoundException, IOException;

    ServiceOutcome<Candidate> saveIsUanSkipped(String candidateCode, String isUanSkipped);

    List<CandidateCafExperience> getCandidateExperienceFromItrAndEpfoByCandidateId(Long candidateId, Boolean formatEpfoDate);

    List<CandidateCafExperience> getCandidateExperienceByCandidateId(Long candidateId);

    CandidateVerificationState getCandidateVerificationStateByCandidateId(Long candidateId);

    CandidateVerificationState addOrUpdateCandidateVerificationStateByCandidateId(Long candidateId, CandidateVerificationState candidateVerificationState);

    List<CandidateCafEducationDto> getAllCandidateEducationByCandidateId(Long candidateId);

    List<CandidateCafAddressDto> getCandidateAddress(Candidate candidate);

    ServiceOutcome<Boolean> qcPendingstatus(String candidateCode);

    ServiceOutcome<CandidateCafExperience> deletecandidateExpById(Long id);

    ServiceOutcome<CandidateCafEducation> deletecandidateEducationById(Long id);

    ServiceOutcome<CandidateDetailsDto> candidateDLdata(String candidateCode);

    ServiceOutcome<Boolean> updateCandidateVendorProofColor(VendorUploadChecksDto vendorUploadChecksDto);

    ServiceOutcome<Long> getContentById(String CandidateCode);

    ServiceOutcome<Long> getCandidateIdByConventionalCandidateId(Long canidateId);

    ServiceOutcome<Boolean> AddCommentsReports(CandidateCaseDetailsDTO candidateCaseDetailsDTO);


    ServiceOutcome<ConventionalCReportApprovalDto> getVendorUploadChecksByCandidateId(String candidateCode);
}