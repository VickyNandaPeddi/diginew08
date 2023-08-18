import {AmChartsLogo} from '@amcharts/amcharts4/.internal/core/elements/AmChartsLogo';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {CandidateService} from 'src/app/services/candidate.service';

@Component({
  selector: 'app-admin-final-report',
  templateUrl: './admin-final-report.component.html',
  styleUrls: ['./admin-final-report.component.scss']
})
export class AdminFinalReportComponent implements OnInit {
  pageTitle = 'Candidate Report';
  candidateCode: any;
  candidateName: any;
  reportStat: boolean = false;
  candidateAddressData: any = [];
  candidateEduData: any = [];
  candidateEXPData: any = [];
  candidateITRData: any = [];
  cApplicationFormDetails: any = [];
  candidateIdItems: any = [];
  executiveSummary: any = [];
  employmentDetails: any = [];
  dateOfBirth: any;
  contactNumber: any;
  ccEmailId: any;
  isFresher: any;
  organizationName: any;
  panNumber: any;
  executiveSummary_stat: any;
  candidateEXPData_stat: any;
  employmentDetails_stat: any;
  candidateIdItems_stat: any;
  candidateEduData_stat: any;
  candidateAddressData_stat: any;
  candidateFinalReport_stat: any;
  dateOfEmailInvite: any;
  submittedOn: any;
  emailId: any;
  getServiceConfigCodes: any = [];
  caseDetails: any = [];
  Criminal_stat: any;
  globalDatabaseCaseDetails: any = [];
  digiDoc: any;
  globalDatabaseCase_stat: any;
  applicantId: any;
  isCRIMINAL: boolean = false;
  isGLOBAL: boolean = false;
  epfoSkipped: boolean = false;

  constructor(private candidateService: CandidateService, private router: ActivatedRoute, private modalService: NgbModal) {
    this.candidateCode = this.router.snapshot.paramMap.get('candidateCode');
    this.candidateService.getCandidateFormData_admin(this.candidateCode).subscribe((data: any) => {
      this.cApplicationFormDetails = data.data;
      console.log(this.cApplicationFormDetails);
      this.candidateName = this.cApplicationFormDetails.candidate.candidateName;
      this.dateOfBirth = this.cApplicationFormDetails.candidate.dateOfBirth;
      this.contactNumber = this.cApplicationFormDetails.candidate.contactNumber;
      this.ccEmailId = this.cApplicationFormDetails.candidate.ccEmailId;
      this.emailId = this.cApplicationFormDetails.candidate.emailId;
      this.applicantId = this.cApplicationFormDetails.candidate.applicantId;
      this.isFresher = this.cApplicationFormDetails.candidate.isFresher;
      this.organizationName = this.cApplicationFormDetails.candidate.organization.organizationName;
      this.panNumber = this.cApplicationFormDetails.candidate.panNumber;
      this.dateOfEmailInvite = this.cApplicationFormDetails.emailStatus.dateOfEmailInvite;
      this.submittedOn = this.cApplicationFormDetails.candidate.submittedOn;

      this.candidateAddressData = this.cApplicationFormDetails.candidateCafAddressDto;
      this.candidateEduData = this.cApplicationFormDetails.candidateCafEducationDto;
      this.candidateEXPData = this.cApplicationFormDetails.candidateCafExperienceDto;
      this.employmentDetails = this.cApplicationFormDetails.employmentDetails;
      this.candidateITRData = this.cApplicationFormDetails.itrdataFromApiDto;
      this.candidateIdItems = this.cApplicationFormDetails.candidateIdItems;
      console.log(this.candidateIdItems, "ids")
      this.executiveSummary = this.cApplicationFormDetails.executiveSummary;
      this.caseDetails = this.cApplicationFormDetails.caseDetails;
      if (this.caseDetails) {
        $("#viewcaseDetails").attr("src", 'data:application/pdf;base64,' + this.caseDetails.document);
        this.Criminal_stat = this.caseDetails.colorName;
      }
      this.globalDatabaseCaseDetails = this.cApplicationFormDetails.globalDatabaseCaseDetails;
      if (this.globalDatabaseCaseDetails) {
        $("#viewGlobalDatabase").attr("src", 'data:application/pdf;base64,' + this.globalDatabaseCaseDetails.document);
        this.globalDatabaseCase_stat = this.globalDatabaseCaseDetails.colorName;
      }
      this.digiDoc = this.cApplicationFormDetails.document;

      for (let index = 0; index < this.digiDoc.length; index++) {

        if (this.digiDoc[index].contentSubCategory == "PAN") {
          console.log(this.digiDoc[index].contentSubCategory);
          $("#digiDoc").attr("src", 'data:application/pdf;base64,' + this.digiDoc[index].document);
        }
        if (this.digiDoc[index].contentSubCategory == "UAN") {
          console.log(this.digiDoc[index].contentSubCategory);
          $("#uanDoc").attr("src", 'data:application/pdf;base64,' + this.digiDoc[index].document);
        }
        if (this.digiDoc[index].contentSubCategory == "DRIVING_LICENSE") {
          console.log(this.digiDoc[index].contentSubCategory);
          $("#drivingDoc").attr("src", 'data:application/pdf;base64,' + this.digiDoc[index].document);
        }
        if (this.digiDoc[index].contentSubCategory == "DEGREE_CERTIFICATE") {
          console.log(this.digiDoc[index].contentSubCategory);
          $("#degreeDoc").attr("src", 'data:application/pdf;base64,' + this.digiDoc[index].document);
        }

      }

      // if(this.digiDoc){
      //   $("#digiDoc").attr("src", 'data:application/pdf;base64,'+this.cApplicationFormDetails.document);

      // }

      const reportStat = this.cApplicationFormDetails.candidateStatus.statusMaster.statusCode;
      if (reportStat == "FINALREPORT") {
        this.reportStat = true;
      } else {
        this.reportStat = false;
      }
      if (this.executiveSummary) {
        var colorArray = [];
        for (let index = 0; index < this.executiveSummary.length; index++) {
          colorArray.push(this.executiveSummary[index].result);
        }
        if (colorArray.includes('Red')) {
          this.executiveSummary_stat = 'Red';
        } else if (colorArray.includes('Amber')) {
          this.executiveSummary_stat = 'Amber';
        } else {
          this.executiveSummary_stat = 'Green';
        }

      }
      if (this.candidateEXPData) {
        var colorArray = [];
        for (let index = 0; index < this.candidateEXPData.length; index++) {
          colorArray.push(this.candidateEXPData[index].colorColorName);

        }
        if (colorArray.includes('Red')) {
          this.candidateEXPData_stat = 'Red';
        } else if (colorArray.includes('Amber')) {
          this.candidateEXPData_stat = 'Amber';
        } else {
          this.candidateEXPData_stat = 'Green';
        }

      }

      if (this.candidateAddressData) {
        var colorArray = [];
        for (let index = 0; index < this.candidateAddressData.length; index++) {
          colorArray.push(this.candidateAddressData[index].colorColorName);

        }
        if (colorArray.includes('Red')) {
          this.candidateAddressData_stat = 'Red';
        } else if (colorArray.includes('Amber')) {
          this.candidateAddressData_stat = 'Amber';
        } else {
          this.candidateAddressData_stat = 'Green';
        }
      }

      if (this.candidateEduData) {
        var colorArray = [];
        for (let index = 0; index < this.candidateEduData.length; index++) {
          if (this.candidateEduData[index].colorColorName != "") {
            colorArray.push(this.candidateEduData[index].colorColorName);
          }


        }
        if (colorArray.includes('Red')) {
          this.candidateEduData_stat = 'Red';
        } else if (colorArray.includes('Amber')) {
          this.candidateEduData_stat = 'Amber';
        } else {
          this.candidateEduData_stat = 'Green';
        }

      }

      if (this.candidateIdItems) {
        var colorArray = [];
        for (let index = 0; index < this.candidateIdItems.length; index++) {
          colorArray.push(this.candidateIdItems[index].color.colorName);

        }
        if (colorArray.includes('Red')) {
          this.candidateIdItems_stat = 'Red';
        } else if (colorArray.includes('Amber')) {
          this.candidateIdItems_stat = 'Amber';
        } else {
          this.candidateIdItems_stat = 'Green';
        }

      }

      if (this.employmentDetails) {
        var colorArray = [];
        for (let index = 0; index < this.employmentDetails.length; index++) {
          colorArray.push(this.employmentDetails[index].result);

        }
        if (colorArray.includes('Red')) {
          this.employmentDetails_stat = 'Red';
        } else if (colorArray.includes('Amber')) {
          this.employmentDetails_stat = 'Amber';
        } else {
          this.employmentDetails_stat = 'Green';
        }
      }

      if (this.executiveSummary_stat == 'Red' || this.candidateEXPData_stat == 'Red' || this.candidateAddressData_stat == 'Red' || this.candidateEduData_stat == 'Red' || this.candidateIdItems_stat == 'Red' || this.employmentDetails_stat == 'Red' || this.globalDatabaseCase_stat == 'Red' || this.Criminal_stat == 'Red') {
        this.candidateFinalReport_stat = 'Red';
      } else if (this.executiveSummary_stat == 'Amber' || this.candidateEXPData_stat == 'Amber' || this.candidateAddressData_stat == 'Amber' || this.candidateEduData_stat == 'Amber' || this.candidateIdItems_stat == 'Amber' || this.employmentDetails_stat == 'Amber' || this.globalDatabaseCase_stat == 'Amber' || this.Criminal_stat == 'Amber') {
        this.candidateFinalReport_stat = 'Amber';
      } else if (this.executiveSummary_stat == 'Green' || this.candidateEXPData_stat == 'Green' || this.candidateAddressData_stat == 'Green' || this.candidateEduData_stat == 'Green' || this.candidateIdItems_stat == 'Green' || this.employmentDetails_stat == 'Green' || this.globalDatabaseCase_stat == 'Green' || this.Criminal_stat == 'Green') {
        this.candidateFinalReport_stat = 'Green';
      }

      if (this.cApplicationFormDetails.candidate.isUanSkipped == true) {
        this.epfoSkipped = true
      } else {
        this.epfoSkipped = false
      }

    });

    this.candidateService.getServiceConfigCodes(this.candidateCode).subscribe((result: any) => {
      this.getServiceConfigCodes = result.data;
      console.log(this.getServiceConfigCodes);
      if (this.getServiceConfigCodes.includes('CRIMINAL')) {
        this.isCRIMINAL = true;
      }
      if (this.getServiceConfigCodes.includes('GLOBAL')) {
        this.isGLOBAL = true;
      }
    });


  }

  ngOnInit(): void {

  }

  printDiv() {
    window.print();
  }

  //Document View
  openCertificate(modalCertificate: any, certificate: any) {
    this.modalService.open(modalCertificate, {
      centered: true,
      backdrop: 'static',
      size: 'lg'
    });
    if (certificate) {
      $("#viewcandidateCertificate").attr("src", 'data:application/pdf;base64,' + certificate);
    }
  }

  openLandlordAgreement(modalLandlordAgreement: any, document: any) {
    this.modalService.open(modalLandlordAgreement, {
      centered: true,
      backdrop: 'static',
      size: 'lg'
    });
    if (document) {
      $("#viewLandlordAgreement").attr("src", 'data:application/pdf;base64,' + document);
    }
  }

  calculateTotalGapsAndTenure() {
    var tenures = $(".outputTenures");
    var gaps = $(".gaps");
    var gap = 0;
    $.each(tenures, function (idx, elem) {

    });


    console.log("tgaps" + gap);
  }

}
