import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from 'src/environments/environment';
import {data} from 'jquery';
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {


  constructor(private http: HttpClient) {
  }

  public sharedData: any;

  setSharedData(data: any) {
    this.sharedData = data;
  }

  getSharedData() {
    return this.sharedData;
  }

  getVendorReportAttributes(vendorCheckID: any) {
    return this.http.get(`${environment.apiUrl}/api/vendorCheck/getConventionalAttributesMaster/${vendorCheckID}`);
  }

  addAndUpdateLicheckByCandidateID({candidateId}: any) {
    return this.http.post(`${environment.apiUrl}/api/vendorCheck/liCheck`, candidateId);
  }

  getConventionalCandidateByCandidateId(requestID: any) {
    return this.http.get(`${environment.apiUrl}/api/vendorCheck/findConventionalCandidate/${requestID}`);
  }


  getAllModeOfVerificationPerformed() {
    return this.http.get(`${environment.apiUrl}/api/vendorCheck/findAllModeOfVerificationPerformed`);
  }

  addAndUpdateCandidateData(VendorID: any) {
    return this.http.post(`${environment.apiUrl}/api/vendorCheck/saveSubmittedCandidates`, VendorID);
  }

  updateCandidateStatusBasedOnLiCheckStatus() {
    return this.http.get(`${environment.apiUrl}/api/vendorCheck/updateCandidateStatus`);
  }

  getAllVendorCheckStatus() {
    return this.http.get(`${environment.apiUrl}/api/organization/getAllVenorcheckStatusForVendor`);
  }

  getAllVendorCheckMasterStatus() {
    return this.http.get(`${environment.apiUrl}/api/vendorCheck/findAllVendorCheckStatus`);
  }


  updateLiCheckStatusByVendorID(vendorCheckStatusMasterId: any, vendorCheckId: any, remarks: any, modeOfVerificationStatus: any) {
    return this.http.get(`${environment.apiUrl}/api/vendorCheck/updateLicheckStatusByVendor/${vendorCheckStatusMasterId}/${vendorCheckId}/${remarks}/${modeOfVerificationStatus}`);
  }

  getCustomers() {
    return this.http.get(`${environment.apiUrl}/api/organization/getAllOrganization`);
  }

  saveCustomers(data: any) {
    return this.http.post(`${environment.apiUrl}/api/organization/saveOrganization`, data);
  }

  getCustomersData(organizationId: any) {
    return this.http.get(`${environment.apiUrl}/api/organization/getOrganizationById/${organizationId}`, organizationId);
  }

  customerStat(organizationId: any, isActive: any) {
    return this.http.put(`${environment.apiUrl}/api/organization/activeNInAtiveOrganization/${organizationId}/${isActive}`, organizationId);
  }

  getSources() {
    return this.http.get(`${environment.apiUrl}/api/organization/getAllSource`);
  }

  saveCustomersBill(data: any, organizationId: any) {
    return this.http.post(`${environment.apiUrl}/api/organization/saveOrganizationBilling/` + organizationId, data);
  }

  saveVendorChecks(data: any, userId: any) {
    return this.http.post(`${environment.apiUrl}/api/organization/saveVendorChecks/` + userId, data);
  }

  generatePrecisedUrl(docuementName: any) {

    return this.http.post
    (`${environment.apiUrl}/api/vendorCheck/generatePrecisedUrl`, docuementName);

  }

  getAllVendorServices(userId: any) {
    return this.http.get(`${environment.apiUrl}/api/organization/getAllVendorServices /${userId}`, userId);
  }

  updateLiCheckIdWithVendorCheckId(vendorCheckId: any, liCheckId: any) {
    return this.http.get(`${environment.apiUrl}/api/vendorCheck/updateLicheckWithVendorcheck/${vendorCheckId}/${liCheckId}`);
  }


  saveInitiateVendorChecks(data: any) {
    return this.http.post(`${environment.apiUrl}/api/user/saveInitiateVendorChecks/`, data);
  }

  getCustomersBill() {
    return this.http.get(`${environment.apiUrl}/api/organization/getOrganizationListAfterBilling/`);
  }

  getCustAdminDetails(organizationId: number) {
    return this.http.get(`${environment.apiUrl}/api/user/getAdminDetailsForOrganization/` + organizationId);
  }

  saveAdminSetup(data: any) {
    return this.http.post(`${environment.apiUrl}/api/user/saveNUpdateUser`, data);
  }

  getColors() {
    return this.http.get(`${environment.apiUrl}/api/organization/getAllColor`);
  }

  getCustConfigs(organizationId: number) {
    return this.http.get(`${environment.apiUrl}/api/organization/getAllServicesForConfiguration/` + organizationId);
  }

  saveCustServiceConfig(data: any) {
    return this.http.post(`${environment.apiUrl}/api/organization/saveOrganizationServiceConfiguration`, data);
  }

  getCustconfigDetails(organizationId: number) {
    return this.http.get(`${environment.apiUrl}/api/organization/getServiceTypeConfigByOrgId/` + organizationId);
  }

  getAllServices(organizationId: any) {
    return this.http.get(`${environment.apiUrl}/api/organization/getAllServices/${organizationId}`, organizationId);
  }

  getUserById() {
    return this.http.get(`${environment.apiUrl}/api/user/getUserProfile`);
  }

  getCustomerUtilizationReport() {
    return this.http.get(`${environment.apiUrl}/api/report/getCustomerUtilizationReport`);
  }

  postCustomerUtilizationReport(data: any) {
    return this.http.post(`${environment.apiUrl}/api/report/getCustomerUtilizationReport`, data);
  }

  getCustomerUtilizationReportByAgent(data: any) {
    return this.http.post(`${environment.apiUrl}/api/report/getCustomerUtilizationReportByAgent`, data);
  }

  getCanididateDetailsByStatus(data: any) {
    return this.http.post(`${environment.apiUrl}/api/report/getCanididateDetailsByStatus`, data);
  }

  getAgentList(organizationId: any) {
    return this.http.get(`${environment.apiUrl}/api/user/getAgentList/${organizationId}`);
  }

  getVendorList(organizationId: any) {
    return this.http.get(`${environment.apiUrl}/api/user/getVendorList/${organizationId}`);
  }

  eKycReport(data: any) {
    return this.http.get(`${environment.apiUrl}/api/report/eKycReport/`, data);
  }

  posteKycReport(data: any) {
    return this.http.post(`${environment.apiUrl}/api/report/eKycReport/`, data);
  }

  getAllStatus() {
    return this.http.get(`${environment.apiUrl}/api/candidate/getAllStatus`);
  }

  getShowvalidation(organizationId: any) {
    console.log("---------------calling api-------------", organizationId)
    return this.http.get(`${environment.apiUrl}/api/organization/getShowvalid/${organizationId}`);

  }

  getVendorCheckDetails(candidateId: any) {
    return this.http.get(`${environment.apiUrl}/api/user/getVendorCheckDetails/${candidateId}`);
  }

  getCandidateIdByConventionalId(candidateId: any) {
    return this.http.get(`${environment.apiUrl}/api/candidate/conventionalCandidateId/${candidateId}`);
  }

  getDocumentNameAndUrl(candidateId: any,checkName:any) {
    return this.http.get(`${environment.apiUrl}/api/vendorCheck/findPrecisedUrl/${candidateId}/${checkName}`);
  }

  saveConventionalVendorCheckWithVendorData(data: any) {
    return this.http.post(`${environment.apiUrl}/api/vendorCheck/liCheck`, data);
  }

  updateBgvCheckStatusRowWise(candidateId: any) {
    return this.http.post(`${environment.apiUrl}/api/vendorCheck/udpdateBgvCheckStatusRowwise`, data);
  }

  getAllLiChecks(requestId: any) {
    return this.http.get(`${environment.apiUrl}/api/vendorCheck/findAllLiChecks/${requestId}`);
  }
  getAllNewUploadLiChecks(requestId: any) {
    return this.http.get(`${environment.apiUrl}/api/vendorCheck/findAllNewUploadLiChecks/${requestId}`);
  }

  getAllLiChecksAll() {
    return this.http.get(`${environment.apiUrl}/api/vendorCheck/findAllLiChecks`);
  }

  saveproofuploadVendorChecks(data: any) {
    return this.http.post(`${environment.apiUrl}/api/user/saveproofuploadVendorChecks/`, data);
  }


  public setFromDate(statCode: string) {
    localStorage.setItem('dbFromDate', statCode);
  }

  public getFromDate() {
    return localStorage.getItem('dbFromDate');
  }

  public setToDate(statCode: string) {
    localStorage.setItem('dbToDate', statCode);
  }

  public getToDate() {
    return localStorage.getItem('dbToDate');
  }


  getallVendorCheckDetails(vendorId: any) {
    return this.http.get(`${environment.apiUrl}/api/user/getVendorCheck/${vendorId}`);
  }

  getallVendorCheckDetailsByDateRange(data: any) {
    return this.http.post(`${environment.apiUrl}/api/user/getVendorCheck`, data);
  }


}
