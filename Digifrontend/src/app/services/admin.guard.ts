import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {
  constructor(private router: Router, private authService: AuthenticationService) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
      if(this.authService.getToken() !== null){
        const role = route.data['roles'] as Array<string>;
        //console.log("puinnn"+role);
        if(role){
          const match = this.authService.roleMatch(role);
          if(match){
            return true;
          }else {
            this.router.navigate(['/login']); //redirect to forbidden page
            return false;
          }
        }
      }
      this.router.navigate(['/login']); //redirect to forbidden page
      return false;
    }
  
}
