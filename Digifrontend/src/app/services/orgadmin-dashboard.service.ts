import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class OrgadminDashboardService {

  constructor(private http: HttpClient) {
  }

  getUploadDetails(data: any) {
    return this.http.post(`${environment.apiUrl}/api/candidate/getCandidateStatusAndCount`, data);
  }

  getChartDetails(data: any) {
    return this.http.post(`${environment.apiUrl}/api/candidate/candidateList`, data);
  }

  getCandidatesSubmittedDetails() {
    return this.http.get(`${environment.apiUrl}/api/vendorCheck/findAllSubmittedCandidates`);
  }

  saveInvitationSent(data: any) {
    return this.http.post(`${environment.apiUrl}/api/candidate/invitationSent`, data);
  }

  putAgentStat(referenceNo: any) {
    return this.http.put(`${environment.apiUrl}/api/candidate/cancelCandidate/${referenceNo}`, referenceNo);
  }

  getCandidateDetails(referenceNo: any) {
    return this.http.get(`${environment.apiUrl}/api/candidate/getCandidate/${referenceNo}`, referenceNo);
  }

  putCandidateData(referenceNo: any) {
    return this.http.put(`${environment.apiUrl}/api/candidate/updateCandidate`, referenceNo);
  }

  public setStatusCode(statCode: string) {
    localStorage.setItem('statCode', statCode);
    localStorage.removeItem('reportDeliverystatCode');
    localStorage.removeItem('PendingDetailsStatCode');
  }

  public getStatusCode() {
    return localStorage.getItem('statCode');
  }

  public setReportDeliveryStatCode(reportDeliverystatCode: string) {
    localStorage.setItem('reportDeliverystatCode', reportDeliverystatCode);
    localStorage.removeItem('statCode');
    localStorage.removeItem('PendingDetailsStatCode');
  }

  public getReportDeliveryStatCode() {
    return localStorage.getItem('reportDeliverystatCode');
  }

  public setPendingDetailsStatCode(PendingDetailsStatCode: string) {
    localStorage.setItem('PendingDetailsStatCode', PendingDetailsStatCode);
    localStorage.removeItem('statCode');
    localStorage.removeItem('reportDeliverystatCode');
  }

  public getPendingDetailsStatCode() {
    return localStorage.getItem('PendingDetailsStatCode');
  }

  public setPendingConventional(PendingConventionalStatCode: string) {
    localStorage.setItem('PendingConventionalStatCode', PendingConventionalStatCode);
    localStorage.removeItem('PendingDetailsStatCode');
    localStorage.removeItem('reportDeliverystatCode');
  }

  public getPendingConventional() {
    return localStorage.getItem('PendingConventionalStatCode');
  }

  getReportDeliveryDetails(data: any) {
    return this.http.post(`${environment.apiUrl}/api/candidate/getReportDeliveryDetailsStatusAndCount`, data);
  }

  getPendingDetailsStatusAndCount(data: any) {
    return this.http.post(`${environment.apiUrl}/api/candidate/getPendingDetailsStatusAndCount`, data);
  }


  getCandidatesSubmittedDetailsByDateRange(data: any) {
    return this.http.post(`${environment.apiUrl}/api/vendorCheck/findAllSubmittedCandidatesByDateRange`, data);
  }

  getConventionalUploadDetails(data: any) {
    return this.http.post(`${environment.apiUrl}/api/candidate/getUploadDetailsStatusAndCountConventional`, data);
  }

//interim and final for charts
  findInterimAndFinalReportForDashboard(data: any) {
    return this.http.post(`${environment.apiUrl}/api/candidate/getConvCandInterimAndFinal`, data);
  }

//inrim and final for candidte conventional
  findInterimAndFinalReportForCandidateDetails(data: any) {
    return this.http.post(`${environment.apiUrl}/api/vendorCheck/findCandidateSubmittedForInterimandFinal`, data);
  }

  getUserByOrganizationIdAndUserId(organizationId: any, userId: any) {
    return this.http.get(`${environment.apiUrl}/api/user/getUserByOrganizationIdAndUserId/${organizationId}/${userId}`);
  }


  getUsersByRoleCode(organizationId: any) {
    return this.http.get(`${environment.apiUrl}/api/user/getUsersByRoleCode/${organizationId}`);
  }

  getSignedURLForContent(contentId: any) {
    return this.http.get(`${environment.apiUrl}/api/candidate/content?contentId=${contentId}&type=VIEW`);
  }


}
