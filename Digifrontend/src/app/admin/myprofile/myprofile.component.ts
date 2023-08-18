import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { CustomerService } from '../../services/customer.service';
import Swal from 'sweetalert2';
import { Customer } from 'src/app/models/customer';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-myprofile',
  templateUrl: './myprofile.component.html',
  styleUrls: ['./myprofile.component.scss']
})
export class MyprofileComponent implements OnInit {
  pageTitle = 'My Profile';
  userId:any;
  formMyProfile = new FormGroup({
    employeeId: new FormControl('', Validators.required),
    roleId: new FormControl('', Validators.required),
    location: new FormControl('', Validators.required),
    userId: new FormControl('', Validators.required),
    userFirstName: new FormControl('', Validators.required),
    userEmailId: new FormControl('', [Validators.required,Validators.email]),
    userMobileNum: new FormControl('', [Validators.required,Validators.minLength(10), Validators.maxLength(10), Validators.pattern('[6-9]\\d{9}')]),
    password: new FormControl(''),
  });
  constructor(private authService: AuthenticationService, private customers:CustomerService) { 
    this.customers.getUserById().subscribe((data: any)=>{
      console.log(data);
      this.formMyProfile = new FormGroup({
        employeeId: new FormControl(data.data['employeeId'], Validators.required),
        location: new FormControl(data.data['location'], Validators.required),
        roleId: new FormControl(data.data['roleId'], Validators.required),
        userId: new FormControl(data.data['userId'], Validators.required),
        userFirstName: new FormControl(data.data['userFirstName'], Validators.required),
        userEmailId: new FormControl(data.data['userEmailId'], [Validators.required,Validators.email]),
        userMobileNum: new FormControl(data.data['userMobileNum'], [Validators.required,Validators.minLength(10), Validators.maxLength(10), Validators.pattern('[6-9]\\d{9}')]),
        password: new FormControl(''),
      });
    });
  }

  ngOnInit(): void {
  }

  onSubmit(formMyProfile: FormGroup) {
    if(this.formMyProfile.valid){
      this.customers.saveAdminSetup(this.formMyProfile.value).subscribe((result:any)=>{
         if(result.outcome === true){
           Swal.fire({
             title: result.message,
             icon: 'success'
           }).then((result) => {
             if (result.isConfirmed) {
               //window.location.reload();
               this.authService.forceLogout();
             }
           });
         }else{
           Swal.fire({
             title: result.message,
             icon: 'warning'
           })
         }
   });
   }else{
     Swal.fire({
       title: "Please enter the required information",
       icon: 'warning'
     })
   }
  }

}
