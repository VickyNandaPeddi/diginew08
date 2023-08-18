import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { AuthenticationService } from './authentication.service';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthenticationService, private router: Router) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.authService.getToken();
    req = this.addToken(req, token);
    if (req.headers.get("No-Auth") === 'True'){
      return next.handle(req.clone());
    }
    
    return next.handle(req).pipe(
      catchError(
        (err:HttpErrorResponse)=>{
          console.log(err.status)
          if(err.status === 401){
            this.router.navigate(['/'])
          }else if(err.status === 403){
            this.router.navigate(['/']);
            this.authService.forceLogout();
            localStorage.clear();
            window.location.reload();
          }
          return throwError("Something Went Wrong!");
        }
      )
    );
  }

  private addToken(request:HttpRequest<any>, token:string){
    return request.clone(
      {
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      }
    );
  }


}
