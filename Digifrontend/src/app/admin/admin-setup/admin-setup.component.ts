import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { CustomerService } from '../../services/customer.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
//import { ServiceSetup } from 'src/app/models/service-setup';

@Component({
  selector: 'app-admin-setup',
  templateUrl: './admin-setup.component.html',
  styleUrls: ['./admin-setup.component.scss']
})
export class AdminSetupComponent implements OnInit {
  pageTitle = "Customer Admin Setup";
  
  //public setupModel: ServiceSetup = new ServiceSetup;
  adminSetup = this.fb.group({
    roleId: ['2', Validators.required],
    userId: [''],
    organizationId: ['', Validators.required],
    employeeId: ['', Validators.required],
    userFirstName: ['', Validators.required],
    userMobileNum: ['', [Validators.minLength(10), Validators.maxLength(10), Validators.pattern('[6-9]\\d{9}')]],
    userEmailId: ['', [Validators.required,Validators.email]],
    location: ['', Validators.required],
    password: ['', Validators.required],
    isActive: ['']
  });
  
  orgID: any;
  getCustID: any=[];
  getCustAdmin: any=[];
  useridcheck: any=[];
  constructor(private customers:CustomerService, private router: Router, private fb: FormBuilder) { 
    this.customers.getCustomersBill().subscribe((data: any)=>{
      this.getCustID=data.data;
    })
  }

  getCustomerData(organizationId:String){
    this.customers.getCustAdminDetails(Number(organizationId)).subscribe((userObj: any)=>{
      this.getCustAdmin=userObj;
      this.useridcheck = userObj.data;
      if(this.useridcheck!=null){
        $(".isactivestat").removeClass("d-none");
        $("label.required").addClass("editMode");
        $(".empid").attr("readonly", "true");
        this.adminSetup = new FormGroup({
          roleId: new FormControl('2', Validators.required),
          userId: new FormControl(this.getCustAdmin.data['userId'], Validators.required),
          organizationId: new FormControl(this.getCustAdmin.data.organization.organizationId, Validators.required),
          employeeId: new FormControl(this.getCustAdmin.data['employeeId'], Validators.required),
          userFirstName: new FormControl(this.getCustAdmin.data['userFirstName'], Validators.required),
          userMobileNum: new FormControl(this.getCustAdmin.data['userMobileNum'], Validators.required),
          userEmailId: new FormControl(this.getCustAdmin.data['userEmailId'], Validators.required),
          location: new FormControl(this.getCustAdmin.data['location'], Validators.required),
          password: new FormControl(''),
          isActive: new FormControl(this.getCustAdmin.data.isActive, Validators.required),
        })
      }else{
        $(".isactivestat").addClass("d-none");
        $("label.required").removeClass("editMode");
        $(".empid").removeAttr("readonly");
        this.adminSetup.reset();
        this.adminSetup = new FormGroup({
          roleId: new FormControl('2', Validators.required),
          organizationId: new FormControl(organizationId, Validators.required),
          employeeId: new FormControl('', Validators.required),
          userFirstName: new FormControl('', Validators.required),
          userMobileNum:  new FormControl('', [Validators.minLength(10),Validators.required, Validators.maxLength(10), Validators.pattern('[6-9]\\d{9}')]),
          userEmailId: new FormControl('',  [Validators.email,Validators.required]),
          location: new FormControl('', Validators.required),
          password: new FormControl('', Validators.required),
          isActive: new FormControl(''),
        })
      }
      



    })
  }
  

  
  ngOnInit(): void { 
    
  }

  onSubmit() {
    return this.customers.saveAdminSetup(this.adminSetup.value).subscribe((result:any)=>{
      if(result.outcome === true){
        Swal.fire({
          title: result.message,
          icon: 'success'
        })
        const sconfigURL = 'admin/custconfig/'+result.data['organizationId'];
        this.router.navigate([sconfigURL]);
      }else{
        Swal.fire({
          title: result.message,
          icon: 'warning'
        });
      }


    });
    
  }

}
