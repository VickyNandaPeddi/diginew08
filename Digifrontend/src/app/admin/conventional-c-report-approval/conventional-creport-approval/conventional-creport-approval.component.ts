import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {CandidateService} from 'src/app/services/candidate.service';
import Swal from 'sweetalert2';
import * as XLSX from 'xlsx';
import {LoaderService} from "../../../services/loader.service";
import {CustomerService} from "../../../services/customer.service";

@Component({
  selector: 'app-conventional-creport-approval',
  templateUrl: './conventional-creport-approval.component.html',
  styleUrls: ['./conventional-creport-approval.component.scss']
})
export class ConventionalCReportApprovalComponent implements OnInit {
  pageTitle = 'Pending Approval';
  candidateCode: any = '';

  formReportApproval = new FormGroup({
    criminalVerificationColorId: new FormControl(''),
    globalDatabseCaseDetailsColorId: new FormControl('')
  });
  formAddcomment = new FormGroup({
    addComments: new FormControl('', Validators.required),
    id: new FormControl(''),
    candidateCode: new FormControl(''),
  });
  cApplicationFormDetails: any;
  candidateName: any;
  candidateResume: string = '';

  formEditDOC = new FormGroup({
    colorId: new FormControl(''),
    vendorChecks: new FormControl(''),
  });
  vendorChecks: any;
  getColors: any = [];
  employ: any = [];
  getServiceConfigCodes: any = [];
  public CaseDetailsDoc: any = File;
  public globalCaseDoc: any = File;
  closeModal: string | undefined;
  jsonData: any;
  finalReportDisabled: number | undefined;
  approveenable: any;

  constructor(private modalService: NgbModal, private customer: CustomerService, private candidateService: CandidateService, private router: ActivatedRoute, private navRouter: Router, private loaderService: LoaderService) {
    // this.candidateCode = this.router.snapshot.paramMap.get('candidateCode');
    const candidateid = localStorage.getItem('capprequestid');
    console.log("candidate id cproval" + this.candidateCode);
    this.candidateCode = candidateid;
    this.approveenable = localStorage.getItem('approveenable');
    this.candidateService.getCandidateConventional_admin(this.candidateCode).subscribe((data: any) => {
      this.cApplicationFormDetails = data.data;
      console.log(this.cApplicationFormDetails, "------------candidate-----------");
      // @ts-ignore
      this.candidateName = this.cApplicationFormDetails.candidateName;
      this.employ = this.cApplicationFormDetails.vendorProofDetails;
      this.finalReportDisabled = this.cApplicationFormDetails.finalReportStatus;
      if (this.cApplicationFormDetails.candidateResume) {
        this.candidateResume = 'data:application/pdf;base64,' + this.cApplicationFormDetails.candidateResume.document;
      }
    });

    this.candidateService.getColors().subscribe((data: any) => {
      this.getColors = data.data;
      console.log(this.getColors);
    });

    // this.candidateService.getServiceConfigCodes(this.candidateCode).subscribe((result: any) => {
    //   this.getServiceConfigCodes = result.data;
    //   console.log(this.getServiceConfigCodes);
    // });
  }

  ngOnInit(): void {
  }

  //Document View
  openResume(modalResume: any) {
    this.modalService.open(modalResume, {
      centered: true,
      backdrop: 'static',
      size: 'lg'
    });
    if (this.candidateResume) {
      $("#viewcandidateResume").attr("src", this.candidateResume);
    }
  }

  submitEditDOC() {
    this.patchAdddocValues();
    if (this.formEditDOC.valid) {
      console.log("..........................employeeeeee..........", this.formEditDOC.value)
      this.candidateService.updateCandidateVendorProofColor(this.formEditDOC.value).subscribe((result: any) => {
        if (result.outcome === true) {
          Swal.fire({
            title: result.message,
            icon: 'success'
          }).then((result) => {
            if (result.isConfirmed) {
              window.location.reload();
            }
          });
        } else {
          Swal.fire({
            title: result.message,
            icon: 'warning'
          })
        }
      });
    } else {
      Swal.fire({
        title: 'Please enter the required details.',
        icon: 'warning'
      })
    }
  }

  patchAdddocValues() {
    this.formEditDOC.patchValue({

      vendorChecks: this.vendorChecks
    });
  }

  openCertificate(modalCertificate: any, certificate: any) {
    this.modalService.open(modalCertificate, {
      centered: true,
      backdrop: 'static',
      size: 'lg'
    });
    // 'data:application/pdf;base64,' + certificate
    if (certificate) {
      $("#viewcandidateCertificate").attr("src", 'data:application/pdf;base64,' + certificate);
    }
  }

  openVendorModal(modalExperience: any, vendorChecks: any,
  ) {
    console.log(vendorChecks)
    this.modalService.open(modalExperience, {
      centered: true,
      backdrop: 'static'
    });
    this.vendorChecks = vendorChecks

  }

  async generateInterimReport() {
    this.loaderService.show();
    try {
      const resp: any = await this.candidateService.generateReportWithReportType(this.candidateCode, "FINAL", "UPDATE").toPromise();
      console.log("resp  of report" + resp);


      if (resp.message != null) {

      }
      if (resp != null) {
        window.open(resp.data, "_blank");
      }
    } catch (error) {
      // Handle any errors that occur during the API call
      console.error(error);
    } finally {
      this.loaderService.hide();
    }
  }

  submitReportApproval(formReportApproval: FormGroup) {
    // this.candidateService.generateReportWithReportType(this.candidateCode, "FINAL", "UPDATE").subscribe(
    //   (data: any) => {
    //     // @ts-ignore
    //     window.open(data.data, "_blank");
    //     if (data.message != null) {
    //       alert(data.message)
    //     }
    //   });
    this.generateInterimReport().then(() => {
      const navURL = 'admin/ConventionalDashboard/';
      this.navRouter.navigate([navURL]);
    });

  }

  submitAddcomment(formAddcomment: FormGroup) {
    console.log("================================ ***** formAddcomment", this.formAddcomment.value)
    if (this.formAddcomment.valid) {
      this.candidateService.AddCommentsReports(this.formAddcomment.value).subscribe((result: any) => {
        window.open(result.data, "_blank");
        if (result.outcome === true) {
          Swal.fire({
            title: result.data.data.message,
            icon: 'success'
          }).then((result) => {
            if (result.isConfirmed) {
              window.location.reload();
            }
          });
        } else {
          Swal.fire({
            title: result.message,
            icon: 'warning'
          })
        }
      });
    } else {
      Swal.fire({
        title: 'Please enter the required details.',
        icon: 'warning'
      })
    }

  }

  openAddcommentModal(content: any) {
    this.formAddcomment.reset();
    this.patchAddcomentValues();
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((res) => {
      this.closeModal = `Closed with: ${res}`;
    }, (res) => {
      this.closeModal = `Dismissed ${this.getDismissReason(res)}`;
    });
  }

  patchAddcomentValues() {
    this.formAddcomment.patchValue({
      candidateCode: this.candidateCode
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  saveAsExcelFile(buffer: any, fileName: string): void {

    const data: Blob = new Blob([buffer], {type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
    const a: HTMLAnchorElement = document.createElement('a');
    const url: string = window.URL.createObjectURL(data);
    a.href = url;
    a.download = `${fileName}.xlsx`;
    a.click();
    window.URL.revokeObjectURL(url);
    a.remove();
  }

  reportData: any;

  generateExcel(): void {

    this.candidateService.generateDataForExcel().subscribe(data => {
      // @ts-ignore
      this.reportData = data.data;
    });


    const
      worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(this.reportData);
    const
      workbook: XLSX.WorkBook = {Sheets: {'data': worksheet}, SheetNames: ['data']};
    // @ts-ignore
    const headingCellStyle: XLSX.Style = {font: {bold: true}, fill: {fgColor: {rgb: 'FFFF00'}}};
    XLSX.utils.sheet_add_json(worksheet, this.reportData, {skipHeader: true, origin: 'A2'});
    worksheet['A1'].s = headingCellStyle;
    worksheet['B1'].s = headingCellStyle;
    worksheet['C1'].s = headingCellStyle;
    worksheet['D1'].s = headingCellStyle;
    worksheet['E1'].s = headingCellStyle;
    worksheet['F1'].s = headingCellStyle;

    const excelBuffer: any = XLSX.write(workbook, {bookType: 'xlsx', type: 'array'});
    this
      .saveAsExcelFile(excelBuffer,
        'reportExcel'
      );
  }

  convertToExcel(jsonData: any): void {

    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(jsonData);
    const workbook: XLSX.WorkBook = {Sheets: {'data': worksheet}, SheetNames: ['data']};

    const excelBuffer: any = XLSX.write(workbook, {bookType: 'xlsx', type: 'array'});
    this.saveAsExcelFile(excelBuffer, 'data'); // Provide a filename for the Excel sheet
  }

  saveAsExcel(buffer: any, fileName: string):
    void {
    const data: Blob = new Blob([buffer], {type: 'application/octet-stream'});
    const url: string = window.URL.createObjectURL(data);
    const link: HTMLAnchorElement = document.createElement('a');
    link.href = url;
    link.download = `${fileName}.xlsx`; // Add the file extension
    link.click();
  }

  generateExcelReportTwo() {

    this.candidateService.generateReferenceDataForVendor(954, 30).subscribe(data => {

      // @ts-ignore
      this.jsonData = data.data;
      this.convertToExcel(this.jsonData);
    })
    // Replace with your actual JSON response


  }


  InterimReport() {
    // console.log(this.candidateCode, "-----------------------------------------------");
    // const navURL = 'admin/CV-Final-Approval/' + this.candidateCode;
    // this.navRouter.navigate([navURL]);
    this.candidateService.generateReportWithReportType(this.candidateCode, "INTERIM", "UPDATE").subscribe(
      (result: any) => {
        if (result.outcome === true) {
          Swal.fire({
            title: result.message,
            icon: 'success'
          }).then((result) => {
            if (result.isConfirmed) {
              // const navURL = 'admin/CV-Final-Approval/' + this.candidateCode;
              // this.navRouter.navigate([navURL]);
            }
          });
        } else {
          Swal.fire({
            title: result.message,
            icon: 'warning'
          })
        }
      });
  }
}
