import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SuperadminDashboardService {

  constructor( private http:HttpClient) { }

  getTestData(){
    return this.http.get("https://jsonplaceholder.typicode.com/posts/1/comments");
  }
  getActivityDetails(data:any){
    return this.http.post(`${environment.apiUrl}/api/organization/getActivityDetails`, data);
  }

  getPendingDetails(data:any){
    return this.http.post(`${environment.apiUrl}/api/organization/getPendingDetails`, data);
  }

  getUtilizationRatePerItem(data:any){
    return this.http.post(`${environment.apiUrl}/api/organization/getUtilizationRatePerItem`, data);
  }

  getUtilizationRatePerReport(data:any){
    return this.http.post(`${environment.apiUrl}/api/organization/getUtilizationRatePerReport`, data);
  }

  getCompanyCountByActivity(data:any){
    return this.http.post(`${environment.apiUrl}/api/organization/getCompanyCountByActivity`, data);
  }

  postCompanyCountByActivity(data:any){
    return this.http.post(`${environment.apiUrl}/api/organization/getCompanyCountByActivity`, data);
  }

}
