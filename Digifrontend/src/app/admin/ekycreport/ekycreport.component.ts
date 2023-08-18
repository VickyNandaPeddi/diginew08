import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { CustomerService } from 'src/app/services/customer.service';
import Swal from 'sweetalert2';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-ekycreport',
  templateUrl: './ekycreport.component.html',
  styleUrls: ['./ekycreport.component.scss']
})
export class EkycreportComponent implements OnInit {
  pageTitle = 'e-KYC Report';
  getCustID: any=[];
  getAgentID: any=[];
  geteKycReport:any=[];
  custId:any=[0];
  agentId:any;
  ekycReportFilter = new FormGroup({
    organizationIds: new FormControl('', Validators.required),
    agentIds: new FormControl(''),
    userId: new FormControl('', Validators.required)
  });
  constructor(private customers: CustomerService, public authService: AuthenticationService) {
    if(authService.roleMatch(['ROLE_CBADMIN'])){
      this.customers.getCustomersBill().subscribe((data: any)=>{
        this.getCustID=data.data;
      });
    }
    if(authService.roleMatch(['ROLE_ADMIN'])){
      this.customers.getAgentList(localStorage.getItem('orgID')).subscribe((data: any)=>{
        this.getAgentID=data.data;
      });
      console.log(this.getAgentID);
    }
    let rportData = {
      'userId': localStorage.getItem('userId')
    }

    this.customers.posteKycReport(rportData).subscribe((data: any)=>{
      this.geteKycReport=data.data.candidateDetailsDto;
      console.log(this.geteKycReport);
    });

      

   }
  getagentId(id:any){
    this.agentId = id;
    let agentIdsArray: any=[];
    agentIdsArray.push(id);
    this.agentId = agentIdsArray;
  }
  getcustId(id:any){
    let custIdArray: any=[];
    custIdArray.push(id);
    this.custId = custIdArray;
    this.customers.getAgentList(id).subscribe((data: any)=>{
      this.getAgentID=data.data;
    });
  }
  
  ngOnInit(): void {
  }
  onSubmitFilter(utilizationReportFilter:FormGroup){
    var getuserId:any = localStorage.getItem('userId');
    this.ekycReportFilter.patchValue({
      organizationIds: this.custId,
      agentIds: this.agentId,
      userId: getuserId
     });
    this.customers.posteKycReport(this.ekycReportFilter.value).subscribe((data:any)=>{
      if(data.outcome === true){
        this.geteKycReport=data.data.candidateDetailsDto;
      }else{
        Swal.fire({
          title: data.message,
          icon: 'warning'
        })
      }
});
  }
}
