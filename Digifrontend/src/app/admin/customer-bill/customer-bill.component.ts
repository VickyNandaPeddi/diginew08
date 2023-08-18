import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators, FormArray } from '@angular/forms';
import { CustomerService } from '../../services/customer.service';
import Swal from 'sweetalert2';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-customer-bill',
  templateUrl: './customer-bill.component.html',
  styleUrls: ['./customer-bill.component.scss']
})

export class CustomerBillComponent implements OnInit {
  pageTitle = 'Customers Bill';
  
  orgID: any;
  getbgv: any=[];
  getBillValues: any=[];
  getCustomerBillData:any;
  BillData_stat:boolean=false;
  constructor( private customers:CustomerService, private router:ActivatedRoute, private fb: FormBuilder, private navRouter: Router) {
    this.orgID = this.router.snapshot.paramMap.get('organizationId');
    this.customers.getSources().subscribe((data: any)=>{
      this.getbgv=data.data;
      this.getbgv.forEach((element:any) => {
        element.serviceId = '';
        element.ratePerReport = '';
        element.ratePerItem = '';
      });
    });

    this.customers.getAllServices(this.orgID).subscribe((data: any)=>{
      this.getBillValues=data.data;
      if(this.getBillValues){
        this.getBillValues.forEach((element:any) => {
          $(".billrpp"+element.source.sourceId).val(element.ratePerReport);
          $(".billrpi"+element.source.sourceId).val(element.ratePerItem);
          $(".billServiceId"+element.source.sourceId).val(element.serviceId);
          
        });
      }

    });
    this.customers.getCustomersData(this.orgID).subscribe((data: any)=>{
      console.log(data.data);
      this.getCustomerBillData=data.data.total;
      if(this.getCustomerBillData){
        this.BillData_stat = true;
      }
    });
    
  }
  onKeyUp(){
   this.BillData_stat = false;
  }
  ngOnInit(): void {
     
  }
  billsubmit(){
    var billValue = $(".x-billcomponents");
    var i=0;
    $.each(billValue,function(idx,elem){
      if($(elem).val()!=""){
        i++;
      }
    })
    if(i>0){
      this.onSubmit()
    }
  }
  onSubmit() {
    return this.customers.saveCustomersBill(this.getbgv,this.orgID ).subscribe((result:any)=>{
      console.log(result);
      if(result.outcome === true){
        Swal.fire({
          title: result.message,
          icon: 'success'
        }).then((result) => {
          if (result.isConfirmed) {
            const navURL = 'admin/admindashboard';
            this.navRouter.navigate([navURL]);
          }
        });
      }else{
        Swal.fire({
          title: result.message,
          icon: 'warning'
        })
      }
    });
  }
    billUpdate() {
      this.getBillValues.forEach((element:any) => {
        element.ratePerReport = $(".billrpp"+element.source.sourceId).val();
        element.ratePerItem = $(".billrpi"+element.source.sourceId).val();
        element.serviceId = $(".billServiceId"+element.source.sourceId).val();

      });
      return this.customers.saveCustomersBill(this.getBillValues,this.orgID ).subscribe((result:any)=>{
        console.log(result);
        if(result.outcome === true){
          Swal.fire({
            title: result.message,
            icon: 'success'
          }).then((result) => {
            if (result.isConfirmed) {
              const navURL = 'admin/admindashboard';
              this.navRouter.navigate([navURL]);
            }
          });
        }else{
          Swal.fire({
            title: result.message,
            icon: 'warning'
          })
        }
      });
    }

}

