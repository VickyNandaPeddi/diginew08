import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { CustomerService } from '../../services/customer.service';
import Swal from 'sweetalert2';
import { ActivatedRoute, Router } from '@angular/router';
import { ReturnStatement } from '@angular/compiler';


@Component({
  selector: 'app-customer-config',
  templateUrl: './customer-config.component.html',
  styleUrls: ['./customer-config.component.scss']
})
export class CustomerConfigComponent implements OnInit {
  pageTitle = "Service Configuration";
  exctive:any=[2,3,4,6];
  weight:any=[90,80,70,100];
  serviceConfig = new FormGroup({
    tenure: new FormControl('', [ Validators.required, Validators.minLength(1), Validators.maxLength(5), Validators.pattern("^[0-9]*$")]),
    dataRetentionPeriod: new FormControl('', [ Validators.required, Validators.minLength(1), Validators.maxLength(5), Validators.pattern("^[0-9]*$")]),
    anonymousDataRetentionPeriod: new FormControl('', [ Validators.required, Validators.minLength(1), Validators.maxLength(5), Validators.pattern("^[0-9]*$")]),
    //dualEmploymentTolerance: new FormControl('', Validators.required),
    dualEmployment: new FormControl('', [ Validators.required, Validators.minLength(1), Validators.maxLength(5), Validators.pattern("^[0-9]*$")]),
    colorId: new FormControl('', Validators.required),
    numberYrsOfExperience: new FormControl('',  [ Validators.required, Validators.minLength(1), Validators.maxLength(5)]),
    numberOfEmployment: new FormControl('', [ Validators.required, Validators.minLength(1), Validators.maxLength(5), Validators.pattern("^[0-9]*$")]),
    numberOfLatestEducation: new FormControl('', [ Validators.required, Validators.minLength(1), Validators.maxLength(5), Validators.pattern("^[0-9]*$")]),
    accessToRelativesBill: new FormControl('', Validators.required),
    sourceServiceId: new FormControl(''),
    executiveId:new FormControl(''),
    weight:new FormControl(''),
    organizationId: new FormControl(''),
    toleranceConfigId: new FormControl('')
  });
  orgID: any;

  patchUserValues() {
		this.serviceConfig.patchValue({
			organizationId: this.orgID,
      sourceServiceId: this.tmp,
      executiveId:this.exctive,
      weight:100,

		});
	}
  getColors: any=[];
  getCustConfigs: any=[];
  userstatcheck: any;
  constructor(private customers:CustomerService, private router: Router,
     private ActivatedRouter: ActivatedRoute  , private fb:FormBuilder) { 
    this.customers.getColors().subscribe((data: any)=>{
      this.getColors=data.data;
    });
    
    this.customers.getCustomersData(this.ActivatedRouter.snapshot.params.organizationId).subscribe((result: any)=>{
      this.orgID = result.data['organizationId'];
      this.customers.getCustConfigs(this.orgID).subscribe((data: any)=>{
        this.getCustConfigs=data.data;
      });
      this.customers.getCustconfigDetails(this.orgID).subscribe((userconfigObj: any)=>{
        
        this.userstatcheck = userconfigObj.data;
        console.log(this.userstatcheck);
        if(this.userstatcheck!=null){
          $("label.required").addClass("editMode");
          const RelativesBillstat = this.userstatcheck.toleranceConfig['accessToRelativesBill'];
          const sourceServicearray = this.userstatcheck['sourceServiceId'];
          
          setTimeout(() =>{
            sourceServicearray.forEach((element: any) =>{
              $(".childcboxinput"+element).attr('checked', 'true');
              this.childCheckselected(element);
             });
          },1500);
          
          
          this.serviceConfig = new FormGroup({
            tenure: new FormControl(this.userstatcheck.toleranceConfig['tenure'], Validators.required),
            dataRetentionPeriod: new FormControl(this.userstatcheck.toleranceConfig['dataRetentionPeriod'], Validators.required),
            anonymousDataRetentionPeriod: new FormControl(this.userstatcheck.toleranceConfig['tenure'], Validators.required),
            //dualEmploymentTolerance: new FormControl(this.userstatcheck.toleranceConfig['dualEmploymentTolerance'], Validators.required),
            dualEmployment: new FormControl(this.userstatcheck.toleranceConfig['dualEmployment'], Validators.required),
            colorId: new FormControl(this.userstatcheck.toleranceConfig.color['colorId'], Validators.required),
            numberYrsOfExperience: new FormControl(this.userstatcheck.toleranceConfig['numberYrsOfExperience'], Validators.required),
            numberOfEmployment: new FormControl(this.userstatcheck.toleranceConfig['numberOfEmployment'], Validators.required),
            numberOfLatestEducation: new FormControl(this.userstatcheck.toleranceConfig['numberOfLatestEducation'], Validators.required),
            accessToRelativesBill: new FormControl(RelativesBillstat, Validators.required),
            sourceServiceId: new FormControl(sourceServicearray, Validators.required),
            organizationId: new FormControl(this.userstatcheck.toleranceConfig.organization['organizationId'], Validators.required),
            toleranceConfigId: new FormControl(this.userstatcheck.toleranceConfig['toleranceMasterId'], Validators.required)
          })
        }
      });
    });
  }

  ngOnInit(): void {
  }

  onSubmit(){
    this.patchUserValues();
    let dict=this.serviceConfig.value  
    dict["executiveId"]=this.exctive,
    dict['weight']=this.weight
    console.log(dict)
    return this.customers.saveCustServiceConfig(dict).subscribe((result:any)=>{
      console.log(result);
      if(result.outcome === true){
        Swal.fire({
          title: result.message,
          icon: 'success'
        }).then((result) => {
          if (result.isConfirmed) {
            const navURL = 'admin/admindashboard/';
            this.router.navigate([navURL]);
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

  parentCheck(e:any){ 
    if (e.target.checked) {
      $(e.target).parent().parent().addClass("childCboxActive");
    }
    else{
      $(e.target).parent().parent().removeClass("childCboxActive");
    }
  }

  tmp: any=[];
  childCheck(e:any){
    var sid = e.target.id;
    if (e.target.checked) {
      if(!this.tmp.includes(sid)){
        this.tmp.push(sid);
      }
      
    } else {
        this.tmp = this.arrayRemove(this.tmp, sid);
    }
  }
  arrayRemove(arr:any, value:any) { 
    
    return arr.filter(function(ele:any){ 
        return ele != value; 
    });
}

  childCheckselected(sid:any){
    this.tmp.push(sid);
    this.selectCheckboxes(sid);
    
  }

  selectCheckboxes(element:any){
    //alert($(".childcboxinput"+element).is(':checked'));
    if($(".childcboxinput"+element).is(':checked')){
      $(".childcboxinput"+element).parent().parent().parent().addClass("childCboxActive");
      $(".childcboxinput"+element).parent().parent().parent().find("li").find(".pcboxinput").attr("checked", "true");
      }
  }

}

