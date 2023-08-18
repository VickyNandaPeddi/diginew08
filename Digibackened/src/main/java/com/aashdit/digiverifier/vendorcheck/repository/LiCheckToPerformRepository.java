/**
 *
 */
package com.aashdit.digiverifier.vendorcheck.repository;

import com.aashdit.digiverifier.vendorcheck.dto.LicheckRequiredResponseDto;
import com.aashdit.digiverifier.vendorcheck.dto.liChecksDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aashdit.digiverifier.vendorcheck.model.ConventionalVendorliChecksToPerform;

import java.util.List;

/**
 * @author ${Nanda Kishore}
 */
@Repository
public interface LiCheckToPerformRepository extends JpaRepository<ConventionalVendorliChecksToPerform, Long> {

    //    @Query("SELECT new com.aashdit.digiverifier.vendorcheck.dto.LicheckRequiredResponseDto(fm.checkCode,fm.checkName,fm.checkStatus,fm.checkRemarks,fm.modeOfVerificationRequired,fm.modeOfVerificationPerformed,fm.completedDate,fm.createdOn,fm.candidateId,fm.vendorChecks.vendorcheckId,fm.source.sourceId,fm.vendorName,fm.sourceName,fm.vendorChecks.documentname) FROM #{#entityName} fm")
    @Query("SELECT new com.aashdit.digiverifier.vendorcheck.dto.LicheckRequiredResponseDto(fm.id,fm.checkCode,fm.checkName,fm.checkStatus.checkStatusCode,fm.checkRemarks,fm.modeOfVerificationRequired,fm.modeOfVerificationPerformed,fm.completedDate,fm.createdOn,fm.candidateId,fm.vendorChecks.vendorcheckId,fm.source.sourceId) FROM #{#entityName} fm")
    public List<LicheckRequiredResponseDto> findAllLiCheckResponses();

    Boolean existsByCandidateId(String candidateId);

    Boolean existsByRequestId(String requestID);

    @Query("SELECT fm  FROM #{#entityName} fm where fm.candidateId=?1")
    public List<ConventionalVendorliChecksToPerform> findAllLiCheckResponseByCandidateId(String candidateId);

    @Query("SELECT new com.aashdit.digiverifier.vendorcheck.dto.liChecksDetails(fm.checkCode,fm.checkName,fm.checkStatus.vendorCheckStatusMasterId,fm.checkRemarks,fm.modeOfVerificationRequired,fm.modeOfVerificationPerformed,fm.completedDate) FROM #{#entityName} fm where fm.requestId=?1")
    public List<liChecksDetails> findAllUpdateLiCheckResponseByRequestId(String requestId);

    @Query("SELECT new com.aashdit.digiverifier.vendorcheck.dto.LicheckRequiredResponseDto(fm.id,fm.checkCode,fm.checkName,fm.checkStatus.checkStatusCode,fm.checkRemarks,fm.modeOfVerificationRequired,fm.modeOfVerificationPerformed,fm.completedDate,fm.createdOn,fm.candidateId,fm.vendorChecks.vendorcheckId,fm.source.sourceId) FROM #{#entityName} fm where fm.checkStatus.vendorCheckStatusMasterId=?1")
    public List<LicheckRequiredResponseDto> findAllLiCheckResponseByCheckStatus(Long checkStatus);

    @Query(value = "select  * from t_dgv_conventional_vendorchecks_to_perform fm where fm.vendor_check=?1", nativeQuery = true)
    public ConventionalVendorliChecksToPerform findByVendorChecksVendorcheckId(Long vendorCheckId);
//    public ConventionalVendorliChecksToPerform findByVendorChecksVendorcheckId(Long vendorCheckId);

    public List<ConventionalVendorliChecksToPerform> findByCandidateId(String canidateId);

    public ConventionalVendorliChecksToPerform findByCheckUniqueId(Long checkUniqueId);


    public List<ConventionalVendorliChecksToPerform> findByRequestId(String requestId);


}
