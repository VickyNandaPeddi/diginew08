import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, ValidationErrors, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {ModalDismissReasons, NgbCalendar, NgbDate, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {AuthenticationService} from 'src/app/services/authentication.service';
import {CandidateService} from 'src/app/services/candidate.service';
import Swal from 'sweetalert2';

import {CustomerService} from '../../services/customer.service';

@Component({
  selector: 'app-upload-vendocheck',
  templateUrl: './upload-vendocheck.component.html',
  styleUrls: ['./upload-vendocheck.component.scss']
})
export class UploadVendocheckComponent implements OnInit {
  pageTitle = 'Vendor Management';
  vendorchecksupload: any = [];
  vendoruser: any
  userID: any;
  getVendorID: any = [];
  candidateId: any;
  candidateCode: any;
  sourceId: any;
  candidateName: any = [];
  userName: any = [];
  sourceName: any = [];
  vendorId: any;
  getColors: any = [];
  colorid: any;
  fromDate: any;
  toDate: any;
  setfromDate: any;
  settoDate: any;
  getToday: NgbDate;
  getMinDate: any;
  start_date = "";
  end_date = "";
  proofDocumentNew: any;
  venderAttributeValue: any[] = [];
  venderAttributeCheckMapped: any[] = [];
  // public proofDocumentNew: any = File;
  closeModal: string | undefined;

  // selectedFiles: any;
  tep: any = [1];
  // vendorlist:any;
  tmp: any;
  orgID: any;

  vendorlist = new FormGroup({
    vendorcheckId: new FormControl(''),
    documentname: new FormControl(''),
    status: new FormControl('', [Validators.required]),
    remarks: new FormControl('', [Validators.required, Validators.minLength(15)]),
    colorid: new FormControl(''),
    value: new FormControl('')
  });

  utilizationReportFilter = new FormGroup({
    fromDate: new FormControl('', Validators.required),
    toDate: new FormControl('', Validators.required),
    // sourceId: new FormControl('', Validators.required)
  });


  patchUserValues() {
    this.vendorlist.patchValue({
      colorid: 2,
    });

  }

  vendorCheckStatus: any = []
  modeOfVerificationStatus: any = [];
  initToday: any;

  constructor(private candidateService: CandidateService, public authService: AuthenticationService, calendar: NgbCalendar, private customers: CustomerService, private _router: Router, private modalService: NgbModal,) {
    this.orgID = this.authService.getuserId();
    this.getToday = calendar.getToday();
    console.log(this.orgID)
    // if(localStorage.getItem('dbFromDate')==null && localStorage.getItem('dbToDate')==null){
    let inityear = this.getToday.year;
    let initmonth = this.getToday.month <= 9 ? '0' + this.getToday.month : this.getToday.month;
    ;
    let initday = this.getToday.day <= 9 ? '0' + this.getToday.day : this.getToday.day;
    let initfinalDate = initday + "/" + initmonth + "/" + inityear;
    this.initToday = initfinalDate;
    this.customers.setFromDate(this.initToday);
    this.customers.setToDate(this.initToday);
    this.fromDate = this.initToday;
    this.toDate = this.initToday;
    // }
    var checkfromDate: any = localStorage.getItem('dbFromDate');
    let getfromDate = checkfromDate.split('/');
    this.setfromDate = {day: +getfromDate[0], month: +getfromDate[1], year: +getfromDate[2]};

    var checktoDate: any = localStorage.getItem('dbToDate');
    let gettoDate = checktoDate.split('/');
    this.settoDate = {day: +gettoDate[0], month: +gettoDate[1], year: +gettoDate[2]};
    this.getMinDate = {day: +gettoDate[0], month: +gettoDate[1], year: +gettoDate[2]};
    this.utilizationReportFilter.patchValue({
      fromDate: this.setfromDate,
      toDate: this.settoDate
    });
    this.getUploadVendorCheckData();
    this.customers.getAllVendorCheckStatus().subscribe(
      data => {
        // @ts-ignore
        this.vendorCheckStatus = data.data;
      }
    )
    // this.customers.getallVendorCheckDetails(this.orgID).subscribe((data: any) => {
    //   console.log(data)
    //   this.vendorchecksupload = data.data;
    //
    //   let getfromDate = data.data.fromDate.split('/');
    //   this.setfromDate = {day: +getfromDate[0], month: +getfromDate[1], year: +getfromDate[2]};
    //   this.getMinDate = this.setfromDate;
    //
    //   let gettoDate = data.data.toDate.split('/');
    //   this.settoDate = {day: +gettoDate[0], month: +gettoDate[1], year: +gettoDate[2]};
    //   console.log("getfromDate, gettoDate", this.getMinDate, this.settoDate, this.fromDate, this.toDate);
    //
    //   this.start_date = 'No Date Filter';//data.data.fromDate!=null?data.data.fromDate.split('-').join('/'):''
    //   this.end_date = 'No Date Filter';//data.data.toDate!=null?data.data.toDate.split('-').join('/'):''
    //
    //   console.log("vendorchecksupload nenw fdasfdsa", this.vendorchecksupload);
    //
    // })


    // this.customers.getallVendorCheckDetailsByDateRange()
    this.candidateService.getColors().subscribe((data: any) => {
      this.getColors = data.data;
      console.log(this.getColors);
    });
  }

  ngOnInit(): void {

  }


  onfromDate(event: any) {
    let year = event.year;
    let month = event.month <= 9 ? '0' + event.month : event.month;
    ;
    let day = event.day <= 9 ? '0' + event.day : event.day;
    let finalDate = day + "/" + month + "/" + year;
    this.fromDate = finalDate;

    this.getMinDate = {day: +day, month: +month, year: +year};
  }

  ontoDate(event: any) {

    let year = event.year;
    let month = event.month <= 9 ? '0' + event.month : event.month;
    ;
    let day = event.day <= 9 ? '0' + event.day : event.day;
    ;
    let finalDate = day + "/" + month + "/" + year;
    this.toDate = finalDate;


  }

  get remarksControl() {
    return this.vendorlist.get('remarks') as FormControl;
  }

  getUploadVendorCheckData() {
    this.customers.setFromDate(this.fromDate);
    this.customers.setToDate(this.toDate);
    let filterData = {
      userId: this.orgID,
      fromDate: this.customers.getFromDate(),
      toDate: this.customers.getToDate(),
    };
    this.customers.getallVendorCheckDetailsByDateRange(filterData).subscribe((data: any) => {
      if (data.outcome === true) {
        this.vendorchecksupload = data.data;
      }
    });
  }

  // onSubmitFilter() {
  //
  //   this.fromDate = this.fromDate != null ? this.fromDate : '';
  //   this.toDate = this.toDate != null ? this.toDate : '';
  //   this.utilizationReportFilter.patchValue({
  //     fromDate: this.fromDate,
  //     toDate: this.toDate,
  //   });
  //   localStorage.setItem('dbFromDate', this.fromDate);
  //   localStorage.setItem('dbToDate', this.toDate);
  //   let filterData = {
  //     userId: this.orgID,
  //     fromDate: localStorage.getItem("dbFromDate"),
  //     toDate: localStorage.getItem("dbToDate"),
  //   };
  //
  //   console.log("filtered data  :" + filterData)
  //   this.customers.getallVendorCheckDetailsByDateRange(filterData).subscribe((data: any) => {
  //     if (data.outcome === true) {
  //       this.vendorchecksupload = data.data;
  //       console.log("this.vendorchecksupload", this.vendorchecksupload);
  //       // this.start_date = data.data.fromDate != null ? data.data.fromDate.split('-').join('/') : ''
  //       // this.end_date = data.data.toDate != null ? data.data.toDate.split('-').join('/') : ''
  //     }
  //   });
  // }
  onSubmitFilter() {

    const inputFromDate: any = $("#inputFromDateVendor").val();
    const finalInputFromDate = inputFromDate;
    const inputToDate: any = $("#inputToDateVendor").val();
    const finalInputToDate = inputToDate;

    if (this.fromDate == null) {
      this.fromDate = finalInputFromDate;
    }
    if (this.toDate == null) {
      this.toDate = finalInputToDate;
    }
    if (this.utilizationReportFilter.valid) {
      this.getUploadVendorCheckData();
    }
  }


  // uploadGlobalCase(event: any) {
  //   const file = event.target.files[0];
  //   const reader = new FileReader();
  //   reader.onload = function (e) {
  //     const data = new Uint8Array(e.target.result);
  //     const xhr = new XMLHttpRequest();
  //     xhr.open('POST', '/upload', true);
  //     xhr.setRequestHeader('Content-Type', 'application/octet-stream');
  //     xhr.send(data);
  //   };
  //   reader.readAsArrayBuffer(file);
  // }

  uploadGlobalCaseDetails(event: any) {
    const fileType = event.target.files[0].name.split('.').pop();

    if (fileType == 'pdf' || fileType == 'PDF' || fileType == 'png' || fileType == 'PNG' || fileType == 'jpg' || fileType == 'JPG' || fileType == ' ') {

      const file = event.target.files[0];
      this.proofDocumentNew = file;
    } else {
      event.target.value = null;
      Swal.fire({
        title: 'Please select .jpeg, .jpg, .png file type only.',
        icon: 'warning'
      });
    }
  }

  getcolor(event: any) {
    console.log(event.target.value)
    this.colorid = event.target.value
  }

  getModeOfVerificationPerformed(value: any) {

  }

  // patchAddIdValues() {
  //   this.vendorlist.patchValue({
  //     candidateId: this.candidateId,
  //     sourceId: this.sourceId,
  //     vendorId: this.vendorId,
  //   });
  // }
  isButtonDisabled: boolean = false;

  getRemarks(): any {
    return this.vendorlist.get('remarks');
  }

  triggerModal(content: any, documentname: any, vendorcheckId: any) {
    this.customers.getVendorReportAttributes(vendorcheckId).subscribe(data => {
      // @ts-ignore
      this.venderAttributeValue = data.data.checkAttibutes.map((attr: any) => {
        return {
          label: attr,
          value: ""
        }
      })
    });


    this.modalService.open(content).result.then((res) => {
      console.log(content, "........................")
      this.closeModal = `Closed with: ${res}`;
    }, (res) => {
      this.closeModal = `Dismissed ${this.getDismissReason(res)}`;
    });
    console.log(documentname, "........................"),
      this.vendorlist.patchValue({
        documentname: documentname,
        status: status,
        vendorcheckId: vendorcheckId,
        colorid: this.colorid,
      });
  }

  getVendorStatusID(vendorCheckStatusID: any) {
   }

  updateLiCheckStatus(status: any, vendorCheckId: any, remarks: any, modeOfVerificationStatus: any) {
    console.log("updating licheckdata")
    this.customers.updateLiCheckStatusByVendorID(status, vendorCheckId, remarks, modeOfVerificationStatus).subscribe(data => {
      console.log(data)
    });

  }

  udpateBgvStatusRowWise(data: any) {
    this.customers.updateBgvCheckStatusRowWise(data).subscribe(data => {
      console.log(data);
    })
  }

  isUploadButtonDisabled(item: any): boolean {
    return item.vendorCheckStatusMaster?.checkStatusCode === 'CLEAR';
  }

  onSubmit(vendorlist: FormGroup) {
    this.isButtonDisabled = true
    let rawValue = vendorlist.getRawValue();
    console.log("---------------------entryyyyyyyyyyyyyyyyy-------------------")
    this.patchUserValues();
    console.log(this.vendorlist.value, "----------------------------------------")
    const formData = new FormData();

    const venderAttributeValuesGloble = this.venderAttributeValue.reduce((obj, item) => {
      obj[item.label] = item.value;
      return obj;
    }, {});

    //  delete agentAttributeValues.value
    this.venderAttributeCheckMapped = {...venderAttributeValuesGloble}
    console.log(" onSubmit:::", this.venderAttributeCheckMapped);
    console.warn("venderAttributeValues===>", venderAttributeValuesGloble);
    const mergedData = {
      ...this.venderAttributeCheckMapped,
    };
    // @ts-ignore
    // formData.append('vendorchecks', JSON.stringify(this.vendorlist.value));
    formData.append('file', this.proofDocumentNew);
    formData.append('vendorchecks', JSON.stringify(this.vendorlist.value));
    formData.append('vendorRemarksReport', JSON.stringify(mergedData));
    // this.updateLiCheckStatus(rawValue.status, rawValue.vendorcheckId, rawValue.remarks, rawValue.modeofverificationperformed);
    this.udpateBgvStatusRowWise(formData);
    return this.customers.saveproofuploadVendorChecks(formData).subscribe((result: any) => {
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
    // }
    this.isButtonDisabled = false;
  }


  private getDismissReason(reason: any): string {
    window.location.reload();
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  getvendorid(id: any) {
    this.getvendorid = id;
    let agentIdsArray: any = [];
    agentIdsArray.push(id);
    this.getvendorid = agentIdsArray;

  }

  dashboardRedirect(id: any) {
    this.customers.getVendorList(Number(id)).subscribe((result: any) => {
      console.log(result);
      if (result.outcome === true) {
        localStorage.setItem('orgID', id);
        localStorage.setItem('userId', result.data.userId);
        this._router.navigate(['admin/orgadminDashboard']);
      } else {
        Swal.fire({
          title: result.message,
          icon: 'warning'
        })
      }
    });
  }

  downloadReferenceExcelData(candidateName: any, sourceName: any, candidateId: any, sourceId: any) {
    this.candidateService.generateReferenceDataForVendor(candidateId, sourceId).subscribe((data: any) => {
      const link = document.createElement('a');
      link.href = 'data:application/vnd.ms-excel;base64,' + data.message;
      // @ts-ignore
      // link.href = 'data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64,' + encodeURIComponent(data.message);

      link.download = candidateName + "_" + sourceName + ".xlsx";
      link.target = '_blank';
      link.click();
    });
  }

  downloadPdf(agentUploadedDocument: any) {
    console.log(agentUploadedDocument, "******************************");
    if (agentUploadedDocument == null || agentUploadedDocument == "") {
      console.log("No Document Found")
    }

    this.customers.generatePrecisedUrl(agentUploadedDocument).subscribe(data => {

      // specify the URL of the file
      // @ts-ignore
      // let precisedurl = data.data;
      //
      // var parts = precisedurl.split('.'); // split the URL by the "." character
      // var extension = parts[parts.length - 1]; // get the last part of the URL, which should be the file extension
      //
      // if (extension !== 'pdf') {
      //   precisedurl = precisedurl.replace('.' + extension, '.png'); // replace the extension with ".png"
      // }
      //
      // window.open(precisedurl, "_blank", "resizable=yes,scrollbars=yes,status=yes");

      //
      // // @ts-ignore
      // var mime_type = "image/png"; // specify the MIME type of the file
      // @ts-ignore
      window.open(data.data);

      // var mime_type = "image/png"; // specify the MIME type of the PNG image
      // //@ts-ignore
      // window.open(data.data).document.type = mime_type;

    })
    // if (agentUploadedDocument != null) {
    //   const linkSource = 'data:application;base64,' + agentUploadedDocument;
    //   const downloadLink = document.createElement("a");
    //   downloadLink.href = linkSource;
    //   downloadLink.download = "Download.pdf"
    //   downloadLink.click();
    // } else {
    //   Swal.fire({
    //     title: 'No Documents Uploaded',
    //     icon: 'warning'
    //   })
    // }
  }


}

