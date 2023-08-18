import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {AuthenticationService} from 'src/app/services/authentication.service';
import {CustomerService} from 'src/app/services/customer.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-conventional-vendorcheck-dashboard',
  templateUrl: './conventional-vendorcheck-dashboard.component.html',
  styleUrls: ['./conventional-vendorcheck-dashboard.component.scss']
})
export class ConventionalVendorcheckDashboardComponent implements OnInit {
  pageTitle = 'Conventional Vendorcheck';
  vendoruser: any
  userID: any;
  getbgv: any = [];
  getBillValues: any = [];
  VendorData_stat: boolean = false;
  getVendorID: any = [];
  candidateId: any;
  changedCandidateId: string | undefined;
  sourceid: any;
  vendorid: any;
  getCustomerBillData: any;
  closeModal: string | undefined;
  Employments: Boolean = false;
  education: Boolean = false;
  GlobalDatabasecheck: Boolean = false;
  Address: Boolean = false;
  IDItems: Boolean = true;
  crimnal: Boolean = false;
  DrugTest: Boolean = false;
  public proofDocumentNew: any = File;
  public empexirdocument: any = File;
  vendorchecksupload: any = [];
  liIndex: any;

  vendorName: any;
  sourceName: any;
  completeData: any = []

  uploadedVendor: any = [];

  documentUrl: any;
  documentData: any = [];

  // added
  vendorId = new FormControl();
  sourceId = new FormControl();
  newUploadList: any;
  inProgressList: any;
  qcPendingList: any;
  checkStatusList: any;

  candidateCode: any;
  vendorCheckId: any;
  finalReportDisabled: string | undefined;
  isButtonDisabled: boolean = true;
  checkStatusArray: any = [];
  documentName: any;

  constructor(private customers: CustomerService, private router: ActivatedRoute, private fb: FormBuilder, authService: AuthenticationService,
              private modalService: NgbModal, private navRouter: Router) {
    this.userID = this.router.snapshot.paramMap.get('userId');
    const requestID = localStorage.getItem("requestid");
    this.candidateId = requestID;
    // @ts-ignore
    this.finalReportDisabled = localStorage.getItem("finalReportStatus");
    if (authService.roleMatch(['ROLE_ADMIN'])) {

      this.customers.getAllNewUploadLiChecks(this.candidateId).subscribe((data: any) => {
        this.newUploadList = data.data;
      });
      //adding lichecks based on candidate id without adding duplicates
      //by request id
      this.customers.getAllLiChecks(this.candidateId).subscribe((data: any) => {
        console.warn("lichecs " + data.data);
        if (data.data.length > 0) {
          this.completeData.lichecksData = data.data;
          this.checkStatusList = Object.assign(this.completeData.lichecksData).filter((temp: any) => temp.checkStatus);

          this.checkStatusList.map((datafadsfas: any) => {
            this.checkStatusArray.push(datafadsfas.checkStatus);
            console.log("check statuses data " + this.checkStatusArray)
          });
          const allClear = this.checkStatusArray.every((status: any) => status === 'CLEAR');
          localStorage.setItem("approveenable", String(allClear));
          if (allClear === true) {
            localStorage.setItem("approveenable", String(allClear));
          }

          // this.newUploadList = Object.assign(this.completeData.lichecksData).filter((temp: any) => temp.checkStatus == "NEWUPLOAD");
          this.inProgressList = Object.assign(this.completeData.lichecksData).filter((temp: any) => temp.checkStatus == "INPROGRESS");
          this.qcPendingList = Object.assign(this.completeData.lichecksData).filter((temp: any) => temp.checkStatus != "NEWUPLOAD" && temp.checkStatus != "INPROGRESS");
          this.candidateCode = this.candidateId;
        }
      })


      this.customers.getVendorList(localStorage.getItem('orgID')).subscribe((data: any) => {
        // this.getVendorID = data.data;
        this.completeData.getVendorData = data.data;

        // @ts-ignore
        //alert(this.completeData.getVendorData.map(dsa=>dsa.userFirstName));

        // console.log(this.getVendorID, "-------------vendoy----------------");
      });
      // @ts-ignore
      this.customers.getCandidateIdByConventionalId(this.candidateId).subscribe(data => {
        // @ts-ignore
        this.changedCandidateId = data.data;
        this.candidateId = this.changedCandidateId
      })
      // console.log(this.vendorlist.value, "-------------vend----------------");
    }
    let rportData = {
      'userId': localStorage.getItem('userId')
    }

    this.customers.getSources().subscribe((data: any) => {
      // this.getbgv = data.data;
      this.completeData.sourceData = data.data;
      console.log(this.completeData.sourceData);
    });
    if (this.userID) {
      this.customers.getAllVendorServices(this.userID).subscribe((data: any) => {
        console.log("--------------------calling service--------------")
        this.completeData.billValues = data.data;
        //console.log(this.getBillValues, "--------------------")
      });
    }

  }


  ngOnInit(): void {

  }


  patchUserValues() {
    this.vendorlist.patchValue({
      sourceId: this.tmp,
      candidateId: this.candidateId,
    });
  }

  formEditEdu = new FormGroup({
    documentname: new FormControl(''),
    vendorId: new FormControl(''),
    sourceId: new FormControl('', Validators.required),
    candidateId: new FormControl(''),

  });


  foremployements = new FormGroup({
    candidateName: new FormControl(''),
    documentname: new FormControl(''),
    vendorId: new FormControl(''),
    sourceId: new FormControl(''),
    candidateId: new FormControl(''),
  });

  //
  patcheduValuesemp() {
    this.foremployements.patchValue({
      sourceId: this.sourceid,
      candidateId: this.candidateId,
      vendorId: this.vendorid
    });

  }

  //
  forAddressCrimnalGlobal = new FormGroup({
    candidateName: new FormControl(''),
    dateOfBirth: new FormControl('', Validators.required),
    contactNo: new FormControl('', Validators.required),
    fatherName: new FormControl('', Validators.required),
    address: new FormControl('', Validators.required),
    vendorId: new FormControl(''),
    sourceId: new FormControl(''),
    candidateId: new FormControl(''),
  });

  formpassport = new FormGroup({
    vendorId: new FormControl(''),
    sourceId: new FormControl('', Validators.required),
    candidateId: new FormControl(''),
    documentname: new FormControl(''),
    documentUrl: new FormControl(''),
    licheckId: new FormControl(''),
    vendorName: new FormControl(''),
    sourceName: new FormControl('')
  });

  vendorlist = new FormGroup({
    // organizationIds: new FormControl('', Validators.required),
    vendorId: new FormControl(''),
    userId: new FormControl('', Validators.required),
    sourceId: new FormControl('', Validators.required),
    candidateId: new FormControl(''),
    documentname: new FormControl(''),
    document: new FormControl('', Validators.required),
  });

  forDrugTest = new FormGroup({
    candidateName: new FormControl(''),
    documentname: new FormControl(''),
    dateOfBirth: new FormControl('', Validators.required),
    contactNo: new FormControl('', Validators.required),
    fatherName: new FormControl('', Validators.required),
    address: new FormControl('', Validators.required),
    alternateContactNo: new FormControl('', Validators.required),
    typeOfPanel: new FormControl('', Validators.required),
    vendorId: new FormControl(''),
    sourceId: new FormControl(''),
    candidateId: new FormControl(''),
  });

  candidateName: any;


  patchpassport() {
    this.formpassport.patchValue({
      sourceId: this.sourceid,
      candidateId: this.candidateId,
      vendorId: this.vendorid,
    });
  }


  patcheduValuesAddress() {
    this.forAddressCrimnalGlobal.patchValue({
      sourceId: this.sourceid,
      candidateId: this.candidateId,
      vendorId: this.vendorid
    });

  }

  patcheduValuesDrugTest() {
    this.forDrugTest.patchValue({
      sourceId: this.sourceid,
      candidateId: this.candidateId,
      vendorId: this.vendorid
    });

  }

  callingFunction() {
    console.log("fdasfadfad" + this.formpassport.value);
  }

  patcheduValues() {
    this.formEditEdu.patchValue({
      sourceId: this.sourceid,
      candidateId: this.changedCandidateId,
      vendorId: this.vendorid,
      doumenturl: this.documentUrl,
      cname: this.candidateName,
      dname: this.documentName
    });

  }

  getSourceValue(checkName: string): string {
    const sourceItem = this.completeData.sourceData.find((item: any) => item.sourceName === checkName);
    if (sourceItem) {
      return sourceItem.sourceName + ' ' + sourceItem.sourceId;
    }
    return ''; // Default value if no matching sourceItem is found
  }

  uploadGlobalCaseDetails(event: any) {
    const fileType = event.target.files[0].name.split('.').pop();
    const file = event.target.files[0];
    if (fileType == 'pdf' || fileType == 'PDF' || fileType == 'png' || fileType == 'PNG' || fileType == 'jpg' || fileType == 'JPG') {
      this.proofDocumentNew = file;
      this.empexirdocument = file;
    } else {
      event.target.value = null;
      Swal.fire({
        title: 'Please select .jpeg, .jpg, .png file type only.',
        icon: 'warning'
      });
    }
  }

  tmp: any = [];

  roleCboxes(e: any) {
    var sid = e.target.id;
    console.log("checked======================", sid)
    if (e.target.checked) {
      // console.log("value************",value)
      this.tmp.push(sid);
    } else {
      this.tmp.splice($.inArray(sid, this.tmp), 1);
    }
    console.log("checked==============================", this.tmp)
  }

  selectAll(e: any) {
    if (e.target.checked) {
      $(e.target).parent().siblings().find(".billServiceId").prop('checked', true);
      var iteminput = $('.item input');
      var arrNumber: any = [];
      $.each(iteminput, function (idx, elem) {
        var inputValues: any = $(elem).val();
        console.log(inputValues);
        arrNumber.push($(this).val());
      });

      this.tmp = arrNumber;
      console.log(this.tmp);
    } else {
      $(e.target).parent().siblings().find(".billServiceId").prop('checked', false);
    }

  }

  getvendorid(id: any) {
    this.isButtonDisabled = id === null;
    this.vendorid = id.slice(-2)
    this.vendorName = id.substring(0, id.length - 2);
  }

  onKeyUp() {
    this.VendorData_stat = false;
  }


  submitEditEdu(formEditEdu: FormGroup) {
    this.patcheduValues()
    console.log("....................", this.formEditEdu.value)
    const formData = new FormData();
    formData.append('vendorchecks', JSON.stringify(this.formEditEdu.value));
    formData.append('file', this.proofDocumentNew);
    console.log(".........formData...........", formData)


    if (this.formEditEdu.valid) {
      console.log(".........valid...........")
      this.customers.saveInitiateVendorChecks(formData).subscribe((result: any) => {


        console.log(result, "=========result");
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

  submitEmploye(foremployements: FormGroup) {
    this.patcheduValuesemp()
    console.log("....................", this.foremployements.value)
    const formData = new FormData();
    formData.append('vendorchecks', JSON.stringify(this.foremployements.value));
    formData.append('file', this.empexirdocument);
    if (this.foremployements.valid) {
      this.customers.saveInitiateVendorChecks(formData).subscribe((result: any) => {
        console.log(result);
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

  submitCrimnalGlobal(forAddressCrimnalGlobal: FormGroup) {
    this.patcheduValuesAddress()
    console.log("....................", this.forAddressCrimnalGlobal.value)
    const formData = new FormData();
    formData.append('vendorchecks', JSON.stringify(this.forAddressCrimnalGlobal.value));
    formData.append('file', this.proofDocumentNew);
    if (this.forAddressCrimnalGlobal.valid) {
      console.log(".........valid...........")
      this.customers.saveInitiateVendorChecks(formData).subscribe((result: any) => {

        console.log(result);
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

  submitDrugTest(formEditEdu: FormGroup) {
    this.patcheduValuesDrugTest()
    console.log("....................", this.forDrugTest.value)
    const formData = new FormData();
    formData.append('vendorchecks', JSON.stringify(this.forDrugTest.value));
    formData.append('file', this.proofDocumentNew);
    return this.customers.saveInitiateVendorChecks(formData).subscribe((result: any) => {

      console.log(result);
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
  }

  getsourceid(item: any) {
    debugger
    this.sourceName = item.sourceName;
    this.sourceid = item.sourceId;
    this.liIndex = item.id;
    if (item.candidateuploadS3Documents !== null) {
      this.documentUrl = item.candidateuploadS3Documents.pathkey;
    }
    if (item.documentName !== null) {
      this.documentName = item.documentName;
    } else {
      this.documentName = "NA";
    }
    if ((this.sourceid == "1") || (this.sourceid == "9")) {
      this.Employments = true;
      this.education = false;
      this.GlobalDatabasecheck = false;
      this.Address = false;
      this.IDItems = false;
      this.crimnal = false;
      this.DrugTest = false;
    }
    if (this.sourceid == "2") {
      this.education = true;
      this.Employments = false;
      this.GlobalDatabasecheck = false;
      this.Address = false;
      this.IDItems = false;
      this.crimnal = false;
      this.DrugTest = false;
    }
    // if(this.sourceid == "3"){
    //   this.GlobalDatabasecheck=true;
    //   this.Employments=false;
    //   this.education=false;
    //   this.Address=false;
    //   this.IDItems=false;
    //   this.crimnal=false;
    //   this.DrugTest=false;
    // }
    // if(this.sourceid == "4"){
    //   this.Address=true;
    //   this.Employments=false;
    //   this.education=false;
    //   this.GlobalDatabasecheck=false;
    //   this.IDItems=false;
    //   this.crimnal=false;
    //   this.DrugTest=false;
    // }
    if (this.sourceid == "5") {
      this.IDItems = true;
      this.Employments = false;
      this.education = false;
      this.GlobalDatabasecheck = false;
      this.Address = false;
      this.crimnal = false;
      this.DrugTest = false;
    }
    if ((this.sourceid == "6") || (this.sourceid == "3") || (this.sourceid == "4")) {
      this.crimnal = true;
      this.Employments = false;
      this.education = false;
      this.GlobalDatabasecheck = false;
      this.Address = false;
      this.IDItems = false;
      this.DrugTest = false;
    }
    if (this.sourceid == "10") {
      this.DrugTest = true;
      this.Employments = false;
      this.education = false;
      this.GlobalDatabasecheck = false;
      this.Address = false;
      this.IDItems = false;
      this.crimnal = false;
    }
    this.submitpassport()
  }

  licheckVendor: any = {

    requestId: "",
    candidateID: "",
    psno: "",
    vendorId: "",
    vendorBasicId: "",
    sourceId: "",
    licheckId: "",
    vendorName: "",
    sourceName: ""
  }

  onDropdownChange(selectedValue: any) {
    // Check if the selected value is not null (i.e., an option other than "--Select--" is chosen)

  }

  saveVendorIdToLicheck(vendorId: any, licheckId: any) {
    this.customers.updateLiCheckIdWithVendorCheckId(vendorId, licheckId).subscribe(
      (data: any) => console.log(data.data)
    )
  }

  docudata: string = "";

  submitpassport() {
    this.patchpassport();
    const formData = new FormData();
    // this.licheckVendor.licheckId = this.liIndex;
    // this.licheckVendor.vendorBasicId = this.formpassport.value.vendorId;
    // this.licheckVendor.sourceId = this.formpassport.value.sourceId;
    // this.licheckVendor.vendorName = this.vendorName
    // this.licheckVendor.sourceName = this.sourceName
    // this.licheckVendor.documentName = this.documentUrl;
    // this.licheckVendor.candidateID = this.changedCandidateId;
    this.formpassport.patchValue({
      documentUrl: this.documentUrl,
      documentname: this.documentName,
      licheckId: this.liIndex,
      vendorName: this.vendorName,
      sourceName: this.sourceName
    });
    formData.append('vendorchecks', JSON.stringify(this.formpassport.value));
    formData.append('documentUrl', JSON.stringify(this.documentUrl));
    console.log(this.formpassport.getRawValue())
    // @ts-ignore
    if (this.vendorCheckId != null) {
      alert("not null")
    }
    // this.customers.saveConventionalVendorCheckWithVendorData(this.licheckVendor).subscribe((data: any) => {
    //   console.log(data.data);
    // });

    console.log(formData)
    return this.customers.saveInitiateVendorChecks(formData).subscribe((result: any) => {
      this.vendorCheckId = result.status;

      this.saveVendorIdToLicheck(this.vendorCheckId, this.liIndex);
      if (result.outcome === true) {

        Swal.fire({
          title: result.message,
          icon: 'success',

          allowOutsideClick: false,
          allowEscapeKey: false
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
  }


  opentemplate(id: any, checkname: any) {
    this.customers.getDocumentNameAndUrl(this.candidateId, checkname)
      .subscribe((response: any) => {
        this.documentData = response.data;
      })

    this.modalService.open(id, {ariaLabelledBy: 'modal-basic-title'}).result.then((res) => {
      this.closeModal = `Closed with: ${res}`;
    }, (res) => {
      this.closeModal = `Dismissed ${this.getDismissReason(res)}`;
    });

  }

  closeStatusModal(modal: any) {
    modal.dismiss('Cross click');
    window.location.reload();
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

  linkAdminApproval(candidateCode: any) {
    console.log(candidateCode, '-----------------------------------------------');
    localStorage.setItem("capprequestid", candidateCode);
    const navURL = 'admin/cReportApprovalConventional';
    this.navRouter.navigate([navURL]);
  }

}

