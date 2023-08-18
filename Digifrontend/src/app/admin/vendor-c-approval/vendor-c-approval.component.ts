import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CandidateService } from 'src/app/services/candidate.service';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import { OrgadminDashboardService } from 'src/app/services/orgadmin-dashboard.service';
import { NgbCalendar, NgbDate } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-vendor-c-approval',
  templateUrl: './vendor-c-approval.component.html',
  styleUrls: ['./vendor-c-approval.component.scss']
})
export class VendorCApprovalComponent implements OnInit {
   pageTitle= 'Pending Approval';
   candidateName: any;
   candidateId : any;
   candidateCode: any;
   cApplicationFormDetails: any=[];
   getToday: NgbDate;
   employ:any=[];
   getColors: any=[];
   vendorChecks:any;
   getServiceConfigCodes: any=[];
   public CaseDetailsDoc: any = File;
  public globalCaseDoc: any = File;

   formEditDOC = new FormGroup({
    colorId: new FormControl(''),
    vendorChecks: new FormControl(''),
  });

  formReportApproval = new FormGroup({
    criminalVerificationColorId: new FormControl(''),
    globalDatabseCaseDetailsColorId: new FormControl('')
  });

  constructor(private candidateService: CandidateService, private router: ActivatedRoute, 
    private modalService: NgbModal,  private navRouter:Router,calendar: NgbCalendar,) {
      this.candidateId = this.router.snapshot.paramMap.get('candidateId');
      this.getToday = calendar.getToday();
    this.candidateCode = this.router.snapshot.paramMap.get('candidateCode');
    // this.candidateCode='47CF5AF631QI';
    this.candidateService.getCandidateFormData_admin(this.candidateCode).subscribe((data: any)=>{
      this.cApplicationFormDetails=data.data;
      console.log(this.cApplicationFormDetails,"------------candidate-----------")
      this.candidateName=this.cApplicationFormDetails.candidate.candidateName;
      this.candidateId=this.cApplicationFormDetails.candidate.candidateId;
      this.employ=this.cApplicationFormDetails.vendorProofDetails 
    });

    this.candidateService.getColors().subscribe((data: any)=>{
      this.getColors=data.data;
      console.log(this.getColors);
    });
  }

  ngOnInit(): void {
  }

  patchAdddocValues() {
		this.formEditDOC.patchValue({
			
      vendorChecks:this.vendorChecks
		});
	}

  openCertificate(modalCertificate:any, certificate:any){
    this.modalService.open(modalCertificate, {
     centered: true,
     backdrop: 'static',
     size: 'lg'
    });
    if(certificate){
      $("#viewcandidateCertificate").attr("src", 'data:application/pdf;base64,'+certificate);
    }
  }

  openVendorModal(modalExperience:any, vendorChecks:any,){
    console.log(vendorChecks)
    this.modalService.open(modalExperience, {
     centered: true,
     backdrop: 'static'
    });
   this.vendorChecks=vendorChecks   
  }

  submitEditDOC(){
    this.patchAdddocValues();
    if(this.formEditDOC.valid){
      console.log("..........................employeeeeee..........",this.formEditDOC.value)
     this.candidateService.updateCandidateVendorProofColor(this.formEditDOC.value).subscribe((result:any)=>{
        if(result.outcome === true){
          Swal.fire({
            title: result.message,
            icon: 'success'
          }).then((result) => {
            if (result.isConfirmed) {
              window.location.reload();
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
        title: 'Please enter the required details.',
        icon: 'warning'
      })
    }
  }
  
  InterimReport(){
    console.log(this.candidateCode,"-----------------------------------------------");
    const navURL = 'admin/CV-Final-Approval/'+'PNNUA30G17TV';
    this.navRouter.navigate([navURL]);
  }

  FinalReportApproval(formReportApproval: FormGroup){
    if(this.getServiceConfigCodes.includes('CRIMINAL')){
      this.formReportApproval.controls["criminalVerificationColorId"].clearValidators();
      this.formReportApproval.controls["criminalVerificationColorId"].setValidators(Validators.required);
      this.formReportApproval.controls["criminalVerificationColorId"].updateValueAndValidity();
      if(this.CaseDetailsDoc.size == null){
        formReportApproval.setErrors({ 'invalid': true });
      }
    }

    if(this.getServiceConfigCodes.includes('GLOBAL')){
      this.formReportApproval.controls["globalDatabseCaseDetailsColorId"].clearValidators();
      this.formReportApproval.controls["globalDatabseCaseDetailsColorId"].setValidators(Validators.required);
      this.formReportApproval.controls["globalDatabseCaseDetailsColorId"].updateValueAndValidity();
      if(this.globalCaseDoc.size == null){
        formReportApproval.setErrors({ 'invalid': true });
      }
    }

    const candidateReportApproval = formReportApproval.value;
    const formData = new FormData();
    formData.append('candidateReportApproval', JSON.stringify(candidateReportApproval));
    formData.append('criminalVerificationDocument', this.CaseDetailsDoc);
    formData.append('globalDatabseCaseDetailsDocument', this.globalCaseDoc);
    formData.append('candidateCode', this.candidateCode);
      this.candidateService.candidateApplicationFormApproved(formData).subscribe(
        (result:any) => {
            if(result.outcome === true){
              Swal.fire({
                title: result.message,
                icon: 'success'
              }).then((result) => {
                if (result.isConfirmed) {
                  const navURL = 'admin/CV-Final-Approval/'+'PNNUA30G17TV';
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



