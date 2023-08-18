import {HttpClient, HttpRequest, HttpHeaders, HttpEvent} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CandidateService {

  constructor(private http: HttpClient) {
  }

  generateReportWithReportType(candidateCode: any, reportType: any, updated: any) {
    return this.http.get(`${environment.apiUrl}/api/vendorCheck/generateJsonByCandidateId/${candidateCode}/${reportType}/${updated}`);
  }

  generateDataForExcel() {
    return this.http.get(`${environment.apiUrl}/api/vendorCheck/generateConventionalUtilizationReport`);
  }

  generateReferenceDataForVendor(candidateId: any, checkId: any) {
    return this.http.get(`${environment.apiUrl}/api/vendorCheck/generateReferenceDataForVendor/${candidateId}/${checkId}`);
  }

  conventionalCandidateApplicationFormApproved(candidateCode: any) {
    return this.http.get(`${environment.apiUrl}/api/candidate/conventionalCandidateApplicationFormApproved/${candidateCode}`);
  }

  getDocumentNameAndUrl(candidateId: any) {
    return this.http.get(`${environment.apiUrl}/api/vendorCheck/findPrecisedUrl/${candidateId}`);
  }

  getAllVendorCheckStatus() {
    return this.http.get(`${environment.apiUrl}/api/organization/getAllVenorcheckStatus`);
  }

  getAllVendorCheckMasterStatus() {
    return this.http.get(`${environment.apiUrl}/api/vendorCheck/findAllVendorCheckStatus`);
  }

  generatePrecisedUrl(candidateID: any, docuementName: any) {

    return this.http.get(`${environment.apiUrl}/api/vendorCheck/generatePrecisedUrl/${candidateID}/${docuementName}`);

  }

  updateLiCheckIdWithVendorCheckId(vendorCheckId: any, liCheckId: any) {
    return this.http.get(`${environment.apiUrl}/api/vendorCheck/updateLicheckWithVendorcheck/${vendorCheckId}/${liCheckId}`);
  }

  saveInitiateVendorChecks(data: any) {
    return this.http.post(`${environment.apiUrl}/api/user/saveInitiateVendorChecks/`, data);
  }


  updateLiCheckStatusByVendorID(vendorCheckStatusMasterId: any, vendorCheckId: any) {
    return this.http.get(`${environment.apiUrl}/api/vendorCheck/updateLicheckStatusByVendor/${vendorCheckStatusMasterId}/${vendorCheckId}`);
  }

  updateCandidateStatusBasedOnLiCheckStatus() {
    return this.http.get(`${environment.apiUrl}/api/vendorCheck/updateCandidateStatus`);
  }

  saveLtrAccept(data: any) {
    return this.http.post(`${environment.apiUrl}/api/allowAll/createAccessCodeUriForSelf`, data);
  }

  saveLtrDecline(data: any) {
    return this.http.post(`${environment.apiUrl}/api/allowAll/declineAuthLetter`, data);
  }

  getCandidateFormData(candidateCode: number) {
    return this.http.get(`${environment.apiUrl}/api/allowAll/candidateApplicationFormDetails/${candidateCode}`);
  }

  getAllSuspectClgList() {
    return this.http.get(`${environment.apiUrl}/api/allowAll/getAllSuspectClgList/`);
  }

  getQualificationList() {
    return this.http.get(`${environment.apiUrl}/api/allowAll/getQualificationList`);
  }

  saveNUpdateEducation(formData: FormData): Observable<any> {
    return this.http.post(`${environment.apiUrl}/api/allowAll/saveNUpdateEducation`, formData);
  }

  saveCandidateApplicationForm(mainformData: FormData) {
    console.log(mainformData, "-------------------")
    return this.http.post(`${environment.apiUrl}/api/allowAll/saveCandidateApplicationForm`, mainformData);
  }

  getITRDetailsFromITRSite(data: any) {
    return this.http.post(`${environment.apiUrl}/api/allowAll/getITRDetailsFromITRSite`, data);
  }

  getepfoCaptcha(candidateCode: any) {
    return this.http.get(`${environment.apiUrl}/api/allowAll/epfoCaptcha/${candidateCode}`, candidateCode);
  }

  getEpfodetail(data: any) {
    return this.http.post(`${environment.apiUrl}/api/allowAll/getEpfodetail`, data);
  }

  getEpfodetailNew(data: any) {
    console.log("Callling new API")
    return this.http.post(`${environment.apiUrl}/api/allowAll/getEpfodetailNew`, data);
  }

  postIsFresher(data: any) {
    return this.http.post(`${environment.apiUrl}/api/allowAll/isFresher`, data);
  }

  getAllSuspectEmpList() {
    return this.http.get(`${environment.apiUrl}/api/allowAll/getAllSuspectEmpList/`);
  }

  saveNUpdateCandidateExperience(data: any) {
    return this.http.post(`${environment.apiUrl}/api/allowAll/saveNUpdateCandidateExperience/`, data);
  }

  relationshipAddressVerification(data: any) {
    return this.http.post(`${environment.apiUrl}/api/allowAll/relationshipAddressVerification`, data);
  }

  verifyRelation(data: any) {
    return this.http.post(`${environment.apiUrl}/api/allowAll/verifyRelation`, data);
  }

  getServiceConfigCodes(candidateCode: any) {
    return this.http.get(`${environment.apiUrl}/api/allowAll/getServiceConfigCodes/${candidateCode}`, candidateCode);
  }

  //admin_services//
  getColors() {
    return this.http.get(`${environment.apiUrl}/api/organization/getAllColor`);
  }

  getremarkType(remarkType: any) {
    return this.http.get(`${environment.apiUrl}/api/candidate/getAllRemark/${remarkType}`, remarkType);
  }

  updateCandidateEducationStatusAndRemark(data: any) {
    return this.http.put(`${environment.apiUrl}/api/candidate/updateCandidateEducationStatusAndRemark`, data);
  }

  updateCandidateExperienceStatusAndRemark(data: any) {
    return this.http.put(`${environment.apiUrl}/api/candidate/updateCandidateExperienceStatusAndRemark`, data);
  }

  updateCandidateAddressStatusAndRemark(data: any) {
    return this.http.put(`${environment.apiUrl}/api/candidate/updateCandidateAddressStatusAndRemark`, data);
  }

  candidateApplicationFormApproved(formData: FormData): Observable<any> {
    return this.http.put(`${environment.apiUrl}/api/candidate/candidateApplicationFormApproved`, formData);
  }

  getCandidateFormData_admin(candidateCode: number) {
    return this.http.get(`${environment.apiUrl}/api/candidate/candidateApplicationFormDetails/${candidateCode}`);
  }

  getCandidateConventional_admin(candidateCode: any) {
    return this.http.get(`${environment.apiUrl}/api/candidate/getVendorUploadChecksByCandidateCode/${candidateCode}`);
  }

  saveCandidateAddress(data: any) {
    return this.http.post(`${environment.apiUrl}/api/allowAll/saveCandidateAddress`, data);
  }

  updateExperience(data: any) {
    return this.http.post(`${environment.apiUrl}/api/allowAll/updateExperience`, data);
  }

  isUanSkipped(data: any) {
    return this.http.post(`${environment.apiUrl}/api/allowAll/isUanSkipped`, data);
  }

  getDigiTansactionid(candidateCode: any) {
    return this.http.get(`${environment.apiUrl}/api/allowAll/getDigiTansactionid/${candidateCode}`, candidateCode);
  }

  // constructor(private http:HttpClient) { }
  getDigiLockerAlldetail(data: any) {
    console.log("called api")
    return this.http.post(`${environment.apiUrl}/api/allowAll/getDigiLockerAlldetail`, data);
  }

  getDigiLockerdetail(data: any) {
    console.log("called api")
    return this.http.post(`${environment.apiUrl}/api/allowAll/getDigiLockerdetail`, data);
  }

  qcPendingstatus(candidateCode: any) {
    console.log(candidateCode, "calling ")
    return this.http.post(`${environment.apiUrl}/api/allowAll/qcPendingstatus/${candidateCode}`, candidateCode);
  }

  deletecandidateExpById(id: any) {
    console.log(".......................======================............", id)
    return this.http.put(`${environment.apiUrl}/api/candidate/deletecandidateExp/${id}`, id);
  }

  deletecandidateEducationById(id: any) {
    console.log(".......................======================............", id)
    return this.http.put(`${environment.apiUrl}/api/candidate/deletecandidateEducationById/${id}`, id);
  }

  getfinal(data: any) {
    var result = this.http.get(`${environment.flaskurl}/`);
    // return this.http.get(`${environment.flaskurl}/`);
    return this.http.post(`${environment.flaskurl}/`, data);
  }

  getCandidateDLdata(candidateCode: number) {
    console.log("now an candidate service");
    return this.http.get(`${environment.apiUrl}/api/allowAll/candidateDLdata/${candidateCode}`);
  }

  getuniveristy() {
    return this.http.get(`${environment.digiurl}/digilocker/get-issuers`);

  }

  getdocumenttype(org_id: any) {
    console.log(".......................======================............", org_id)
    return this.http.get(`${environment.digiurl}/digilocker/get-doctype/?orgid=${org_id}`, org_id);
  }

  getparameters(org_id: any, doctype: any) {
    console.log(".......................", org_id, "======================............", doctype)
    return this.http.get(`${environment.digiurl}/digilocker/get-parameters/?orgid=${org_id}&doctype=${doctype}`, org_id);

  }

  getDLEdudocument(data: any) {
    console.log(".......................", data, "======================............")
    return this.http.post(`${environment.apiUrl}/api/allowAll/getDLEdudocument`, data);
  }

  updateCandidateVendorProofColor(data: any) {
    return this.http.put(`${environment.apiUrl}/api/candidate/updateCandidateVendorProofColor`, data);
  }

  AddCommentsReports(data: any) {
    return this.http.put(`${environment.apiUrl}/api/candidate/AddCommentsReports`, data);
  }


}
