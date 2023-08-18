import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  requestHeader = new HttpHeaders(
    {"No-Auth":"True"}
  );

  constructor(private http:HttpClient, private router: Router) { }
  
  login(loginData: any){
    return this.http.post(`${environment.apiUrl}/api/login/authenticate`, loginData, {headers: this.requestHeader})
  }
  public logOut(){
    return this.http.post(`${environment.apiUrl}/api/login/sign-off`, {headers: this.requestHeader})
  }
  public setRoles(roles: []){
    localStorage.setItem('roles', JSON.stringify(roles));
  }
  public getRoles(){ 
    return localStorage.getItem('roles'); //needs to return as Array
  }

  public setToken(jwtToken: string){
    localStorage.setItem('jwtToken', JSON.stringify(jwtToken));
  }
  public getToken(){ 
    return JSON.parse(localStorage.getItem('jwtToken') || '{}'); //localStorage.getItem('jwtToken'); 
  }

  public setuserName(userName: string){
    localStorage.setItem('userName', userName);
  }
  public getuserName(){ 
    return localStorage.getItem('userName'); 
  }

  public setroleName(roleName: string){
    localStorage.setItem('roleName', roleName);
  }
  public getroleName(){ 
    return localStorage.getItem('roleName'); 
  }

  public setOrgID(orgID: string){
    localStorage.setItem('orgID', orgID);
  }
  public getOrgID(){ 
    return localStorage.getItem('orgID'); 
  }

  public setuserId(userId: string){
    localStorage.setItem('userId', userId);
  }
  public getuserId(){ 
    return localStorage.getItem('userId'); 
  }

  public clear(){ 
    return localStorage.clear(); 
  }
  public forceLogout(){
    this.logOut().subscribe(
      (response:any)=>{
        if (response.outcome === true)
        {
          this.clear();
          this.router.navigate(['./']);
        }
      }
    ); 
  }

  public isLoggedIn(){ 
    return this.getRoles() && this.getToken();
  }
  
  public roleMatch(allowedRoles:any[]) :boolean{
    let isMatch= false;
    const roles:any = this.getRoles();
    const userRoles  =[];
    userRoles.push(roles);
    if(userRoles != null){
      for(let i=0; i< allowedRoles.length; i++){
        for(let j=0; j< userRoles.length; j++){
          if(userRoles[j] === '"'+allowedRoles[i]+'"'){
            isMatch = true;
            return isMatch;
          }else{
            break;
          }
        }
      }
    }
    return isMatch;
  }




}
