import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { first, map } from 'rxjs/operators';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginPage!: FormGroup;
  
  constructor(
      private formBuilder: FormBuilder,
      private route: ActivatedRoute,
      private authService: AuthenticationService,
      private router: Router
  ) { }

  ngOnInit(): void {
    if(this.authService.isLoggedIn()!==null && this.authService.getRoles() == '"ROLE_CBADMIN"'){
      this.router.navigate(['admin']);
    }else if(this.authService.isLoggedIn()!==null && this.authService.getRoles() !== '"ROLE_CBADMIN"'){
      this.router.navigate(['/admin/BGVverification']);
    }else{
      this.router.navigate(['login']);
    }
    this.loginPage = new FormGroup({
      password: new FormControl('', Validators.required),
      userName: new FormControl('', Validators.required)
    });

  }

  onSubmit() {

    return this.authService.login(this.loginPage.value).subscribe(
      (response:any)=>{
        //console.log(response);
        if(response.outcome != true){
          Swal.fire({
            title: response.message,
            icon: 'warning'
          });
        }
        
        
        this.authService.setRoles(response.data.roleCode);
        this.authService.setToken(response.data.jwtToken);
        this.authService.setuserName(response.data.userFirstName);
        this.authService.setroleName(response.data.roleName);
        this.authService.setuserId(response.data.userId);
        if(response.data.organizationId){
          this.authService.setOrgID(response.data.organizationId);
        }
        const role = response.data.roleCode;
        if(role === "ROLE_CBADMIN"){
          this.router.navigate(['/admin']);
        }else if(role === "ROLE_ADMIN"){
          this.router.navigate(['/admin/BGVverification']);
        }else if(role === "ROLE_PARTNERADMIN"){
          this.router.navigate(['/admin/orgadminDashboard']);
        }else if(role === "ROLE_AGENTSUPERVISOR"){
          this.router.navigate(['/admin/orgadminDashboard']);
        }else if(role === "ROLE_AGENTHR"){
          this.router.navigate(['/admin/orgadminDashboard']);
        }else if(role === "ROLE_VENDOR"){
          this.router.navigate(['/admin/orgadminDashboard']);
        }else{
          this.router.navigate(['/login']);
        }
        
      },
      (error)=>{
        console.log(error);
      }
    )
    
  }

}
