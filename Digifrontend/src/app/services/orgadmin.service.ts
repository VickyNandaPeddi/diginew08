import { HttpClient, HttpRequest, HttpHeaders, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class OrgadminService {

  constructor(private http:HttpClient) { }
  
  getOrgusers(organizationId:number){
    return this.http.get(`${environment.apiUrl}/api/user/getUserByOrganizationId/` + organizationId);
  }
  getOrgroles(){
    
    return this.http.get(`${environment.apiUrl}/api/role/getRoleDropDownByUser`);
  }
  getOrgVendor(organizationId:number){

    return this.http.get(`${environment.apiUrl}/api/user/getVendorList/` + organizationId);

  }
  Addrole(data:any){
    console.log("______________________calling api_________________________")
    return this.http.post(`${environment.apiUrl}/api/role/saveNUpdateRole`,data);
  }
  getSupervisor(organizationId:number){
    return this.http.get(`${environment.apiUrl}/api/user/getAgentSupervisorList/` + organizationId);
  }
  saveOrgusers(data: any){
    return this.http.post(`${environment.apiUrl}/api/user/saveNUpdateUser`, data);
  }
  orguserStat(userId : any, isActive: any){
    return this.http.put(`${environment.apiUrl}/api/user/activeNInAtiveUser/${userId}/${isActive}`, userId );
  }
  getUserbyId(userId:number){
    return this.http.get(`${environment.apiUrl}/api/user/getUserById/` + userId);
  }


  uploadAgent(file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    const req = new HttpRequest('POST', `${environment.apiUrl}/api/user/uploadAgent`, formData, {
      reportProgress: true,
      responseType: 'json'
    });
    return this.http.request(req);
  }

  uploadCandidate(file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    const req = new HttpRequest('POST', `${environment.apiUrl}/api/candidate/uploadCandidate`, formData, {
      reportProgress: true,
      responseType: 'json'
    });
    return this.http.request(req);
  }

  uploadClientscope(file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    const req = new HttpRequest('POST', `${environment.apiUrl}/api/organization/uploadClientscope`, formData, {
      reportProgress: true,
      responseType: 'json'
    });
    return this.http.request(req);
  }


  getRoleDropdown(){
    return this.http.get(`${environment.apiUrl}/api/role/getRoleDropDownByUser`);
  }

  getAllRolePermission(){
    return this.http.get(`${environment.apiUrl}/api/role/getAllRolePermission`);
  }
  saveRoleMgmt(data: any){
    return this.http.post(`${environment.apiUrl}/api/role/rolePermission`, data);
  }

  getRoleMgmtStat(roleId:number){
    return this.http.get(`${environment.apiUrl}/api/role/getAllUserRolePerMissionMap/${roleId}`);
  }

  getRolePerMissionCodes(roleCode:any){
    return this.http.get(`${environment.apiUrl}/api/role/getRolePerMissionCodes/${roleCode}`);
  }

}
