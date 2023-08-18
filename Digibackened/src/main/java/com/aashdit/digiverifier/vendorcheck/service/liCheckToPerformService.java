/**
 *
 */
package com.aashdit.digiverifier.vendorcheck.service;

import com.aashdit.digiverifier.common.model.ServiceOutcome;
import com.aashdit.digiverifier.config.superadmin.Enum.ReportType;
import com.aashdit.digiverifier.config.superadmin.dto.DashboardDto;
import com.aashdit.digiverifier.config.superadmin.model.VendorCheckStatusMaster;
import com.aashdit.digiverifier.vendorcheck.dto.*;
import com.aashdit.digiverifier.vendorcheck.model.ConventionalAttributesMaster;
import com.aashdit.digiverifier.vendorcheck.model.ConventionalVendorCandidatesSubmitted;
import com.aashdit.digiverifier.vendorcheck.model.ModeOfVerificationStatusMaster;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author ${Nanda Kishore}
 */
@Service
public interface liCheckToPerformService {
    public ServiceOutcome<List<ConventionalVendorCandidatesSubmitted>> findAllConventionalVendorSubmittedCandidatesByDateRange(DashboardDto dashboardDto) throws Exception;

    public ServiceOutcome<List<ConventionalVendorCandidatesSubmitted>> findAllSubmittedCandidatesByDateRangeOnInterimAndFinal(DashboardDto dashboardDto) throws Exception;

    public ServiceOutcome<LicheckRequiredResponseDto> addUpdateLiCheckToPerformData(FetchVendorConventionalCandidateDto licheckDto) throws Exception;

    public ServiceOutcome<List<LicheckRequiredResponseDto>> findAllLiChecksRequired() throws Exception;

    public ServiceOutcome<List<LicheckRequiredResponseDto>> findAllLiChecksRequiredbyCandidateId(String candidateId) throws Exception;

    public ServiceOutcome<List<LicheckRequiredResponseDto>> findAllNewUploadLiChecksRequiredbyCandidateId(String candidateId) throws Exception;

    public ServiceOutcome<List<LicheckRequiredResponseDto>> findAllLiChecksRequiredbyCheckStatus(String checkStatus) throws Exception;

    public ServiceOutcome<SubmittedCandidates> saveConventionalVendorSubmittedCandidates(String vendorId) throws Exception;

    public ServiceOutcome<List<SubmittedCandidates>> findAllConventionalVendorSubmittedCandidates() throws Exception;

    public String UpdateBGVCheckStatusRowwise(String vendorChecksString, MultipartFile proofDocumentNew, String modeOfVerificationPerformed);

    public ServiceOutcome<ConventionalCandidateDocDto> saveConventionalCandidateDocumentInfo(FetchVendorConventionalCandidateDto fetchVendorConventionalCandidateDto) throws Exception;

    public ServiceOutcome<List<ConventionalCandidateDocDto>> findAllConventionalCandidateDocumentInfo() throws Exception;

    public ServiceOutcome<CandidateuploadS3Documents> findAllfilesUploadedurls(String candidateId, String checkName) throws Exception;

    public ServiceOutcome<String> findUpdateLicheckWithVendorCheck(String vendorCheckId, String liCheckId) throws Exception;

    public ServiceOutcome<String> updateLiCheckStatusByVendor(String vendorCheckStatusMasterId, String vendorCheckId, String remarks, String modeOfVericationPerformed) throws Exception;

    public ServiceOutcome<String> updateCandidateStatusByLicheckStatus();

    public ServiceOutcome<String> updateCandidateVerificationStatus(String requestId);

    public ServiceOutcome<List<VendorCheckStatusMaster>> findAllVendorCheckStatus();

    public ServiceOutcome<List<liReportDetails>> generateDocumentConventional(String candidateId, String reportType);

    public ServiceOutcome<String> generateJsonRepsonseByConventionalCandidateId(String candidateId, ReportType reportType, String update);

    public ServiceOutcome<String> generateConventionalCandidateReport(Long candidateCode, ReportType reportType);


    public ServiceOutcome<ConventionalVendorCandidatesSubmitted> findConventionalCandidateByCandidateId(Long candiateId);

    public ServiceOutcome<List<ReportUtilizationDto>> generateJsonResponse3() throws Exception;

    public ServiceOutcome<List<VendorReferenceDataDto>> generateReferenceDataToVendor(Long candidateId, Long checkName) throws Exception;


    public ServiceOutcome<List<ModeOfVerificationStatusMaster>> findAllModeOfVerifcationPerformed() throws Exception;


    //new code
    ServiceOutcome<ConventionalAttributesMaster> saveConventionalAttributesMaster(ConventionalAttributesMaster conventionalAttributesMaster);

    ServiceOutcome<ConventionalAttributesMaster> getConventionalAttributesMasterById(Long Id);


}
