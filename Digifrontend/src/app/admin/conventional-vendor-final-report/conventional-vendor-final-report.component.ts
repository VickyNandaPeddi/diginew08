import { AmChartsLogo } from '@amcharts/amcharts4/.internal/core/elements/AmChartsLogo';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CandidateService } from 'src/app/services/candidate.service';
import { jsPDF } from "jspdf";
import html2canvas from 'html2canvas';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-conventional-vendor-final-report',
  templateUrl: './conventional-vendor-final-report.component.html',
  styleUrls: ['./conventional-vendor-final-report.component.scss']
})
export class ConventionalVendorFinalReportComponent implements OnInit {
  companyname: any;
  companyaddrs: any;
  candidateCode: any;
  candidateName: any;
  organizationLogo: any;
  organizationLocation: any;
  organizationName:any;
  reportStat:boolean=false;
  cApplicationFormDetails: any=[];
  candidateIdItems: any=[];
  executiveSummary: any=[];
  employmentDetails: any=[];
  employ:any=[];
  dateOfBirth:any;
  contactNumber:any;
  isFresher:any;
  panNumber:any;
  uanNumber:any;
  executiveSummary_stat: any;
  candidateEXPData_stat:any;
  employmentDetails_stat:any;
  candidateIdItems_stat:any;
  candidateEduData_stat:any;
  candidateAddressData_stat:any;
  candidateFinalReport_stat:any;
  dateOfEmailInvite:any;
  submittedOn:any;
  emailId:any;
  getServiceConfigCodes: any=[];
  caseDetails: any=[];
  Criminal_stat:any;
  globalDatabaseCaseDetails: any=[];
  digiDoc:any;
  vendorproof:any=[];
  globalDatabaseCase_stat:any;
  applicantId:any;
  isCRIMINAL:boolean=false;
  isGLOBAL:boolean=false;
  epfoSkipped:boolean=false;
  result:any;
  isVendor:boolean=false;
  isDOC1:boolean=false;
  isDOC2:boolean=false;
  isDOC3:boolean=false;
  isDOC4:boolean=false;
  isDOC5:boolean=false;
  isDOC6:boolean=false;
  isDOC7:boolean=false;
  isDOC8:boolean=false;
  isDOC9:boolean=false;
  isDOC0:boolean=false;
  docname0:any;
  docname1:any;
  docname2:any;
  docname3:any;
  docname4:any;
  docname5:any;
  docname6:any;
  docname7:any;
  docname8:any;
  docname9:any;
  comment:any;
 
  
  constructor( private candidateService: CandidateService, private router: ActivatedRoute, private modalService: NgbModal,private sanitizer:DomSanitizer) { 
    this.candidateCode = this.router.snapshot.paramMap.get('candidateCode');
    this.candidateService.getCandidateConventional_admin(this.candidateCode).subscribe((data: any)=>{
      this.cApplicationFormDetails=data.data;
      console.log(this.cApplicationFormDetails);
      this.candidateName=this.cApplicationFormDetails.candidateName;
      this.dateOfBirth=this.cApplicationFormDetails.dateOfBirth;
      this.contactNumber=this.cApplicationFormDetails.contactNumber;
      this.emailId=this.cApplicationFormDetails.emailId;
      this.applicantId=this.cApplicationFormDetails.applicantId;
      this.isFresher=this.cApplicationFormDetails.isFresher;
      this.panNumber=this.cApplicationFormDetails.panNumber;
      this.dateOfEmailInvite=this.cApplicationFormDetails.dateOfEmailInvite;
      this.submittedOn=this.cApplicationFormDetails.submittedOn;
      this.comment=this.cApplicationFormDetails.candidateAddComments;
      this.employ=this.cApplicationFormDetails.vendorProofDetails
      console.log(this.comment)
      this.organizationName=this.cApplicationFormDetails.candidate.organization.organizationName;
      this.organizationLogo=this.cApplicationFormDetails.candidate.organization.organizationLogo;
      this.organizationLocation=this.cApplicationFormDetails.candidate.organization.organizationLocation;
      // console.log("================logo=========",this.organizationLogo); 
      this.caseDetails=this.cApplicationFormDetails.caseDetails;
      if(this.caseDetails){
        $("#viewcaseDetails").attr("src", 'data:application/pdf;base64,'+this.caseDetails.document);
        this.Criminal_stat = this.caseDetails.colorName;
      }
      this.globalDatabaseCaseDetails=this.cApplicationFormDetails.globalDatabaseCaseDetails;
      if(this.globalDatabaseCaseDetails){
        $("#viewGlobalDatabase").attr("src", 'data:application/pdf;base64,'+this.globalDatabaseCaseDetails.document);
        this.globalDatabaseCase_stat = this.globalDatabaseCaseDetails.colorName;
      }
      this.digiDoc=this.cApplicationFormDetails.document;
      this.vendorproof=this.cApplicationFormDetails.vendorProofDetails;
      // console.log(this.vendorproof.length,"2345678990")
     
      if(this.vendorproof){
        this.isVendor=true
        $("#doc0").attr("src", 'data:application/pdf;base64,'+this.vendorproof[0].document);
        this.isDOC0=true
        this.docname0=this.vendorproof[0].documentname
        if(this.vendorproof[1]){
          this.isDOC1=true
          this.docname1=this.vendorproof[1].documentname
          $("#doc1").attr("src", 'data:application/pdf;base64,'+this.vendorproof[1].document);
        }
        if(this.vendorproof[2]){
          this.isDOC2=true
          this.docname2=this.vendorproof[2].documentname
          $("#doc2").attr("src", 'data:application/pdf;base64,'+this.vendorproof[2].document);
        }
        if(this.vendorproof[3]){
          this.isDOC3=true
          this.docname3=this.vendorproof[3].documentname
          $("#doc3").attr("src", 'data:application/pdf;base64,'+this.vendorproof[3].document);
        }
        if(this.vendorproof[4]){
          this.isDOC4=true
          this.docname4=this.vendorproof[4].documentname
            $("#doc4").attr("src", 'data:application/pdf;base64,'+this.vendorproof[4].document);
        }
        if(this.vendorproof[5]){ 
          this.isDOC5=true
          this.docname5=this.vendorproof[5].documentname
            $("#doc5").attr("src", 'data:application/pdf;base64,'+this.vendorproof[5].document);
        }
        if(this.vendorproof[6]){
          this.isDOC6=true
          this.docname6=this.vendorproof[6].documentname
          $("#doc6").attr("src", 'data:application/pdf;base64,'+this.vendorproof[6].document);
        }
        if(this.vendorproof[7]){
          this.isDOC7=true
          this.docname7=this.vendorproof[7].documentname
         $("#doc7").attr("src", 'data:application/pdf;base64,'+this.vendorproof[7].document);
        }
        if(this.vendorproof[8]){
          this.isDOC8=true
          this.docname8=this.vendorproof[8].documentname
          $("#doc8").attr("src", 'data:application/pdf;base64,'+this.vendorproof[8].document);
        }
        if(this.vendorproof[9]){
          this.isDOC9=true
          this.docname9=this.vendorproof[9].documentname
           $("#doc9").attr("src", 'data:application/pdf;base64,'+this.vendorproof[9].document);
        }
        console.log($( $("#doc3").attr("src", 'data:application/pdf;base64,'))) 
      }      
     
      const reportStat =this.cApplicationFormDetails.candidateStatus.statusMaster.statusCode;
      if(reportStat == "FINALREPORT"){
        this.reportStat = true; 
      }else {
        this.reportStat = false; 
      }  
    });      
  } 

  ngOnInit(): void { }

//Document View
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

}
