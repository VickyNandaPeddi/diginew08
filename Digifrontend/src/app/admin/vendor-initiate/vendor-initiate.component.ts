import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators, FormArray } from '@angular/forms';
import { CustomerService } from '../../services/customer.service';
import Swal from 'sweetalert2';
import { ActivatedRoute, Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-vendor-initiate',
  templateUrl: './vendor-initiate.component.html',
  styleUrls: ['./vendor-initiate.component.scss']
})


export class VendorInitiateComponent implements OnInit {
  pageTitle = 'Initiate Vendor Check';
  vendoruser: any
  userID: any;
  getbgv: any = [];
  getBillValues: any = [];
  VendorData_stat: boolean = false;
  getVendorID: any = [];
  candidateId: any;
  sourceid: any;
  vendorid: any;
  getCustomerBillData: any;
  closeModal: string | undefined;
  Employments: Boolean = false;
  education: Boolean = false;
  GlobalDatabasecheck: Boolean = false;
  Address: Boolean = false;
  IDItems: Boolean = false;
  crimnal: Boolean = false;
  DrugTest: Boolean = false;
  public proofDocumentNew: any = File;
  public empexirdocument: any = File;
  vendorchecksupload: any = [];

  vendorlist = new FormGroup({
    // organizationIds: new FormControl('', Validators.required),
    vendorId: new FormControl(''),
    userId: new FormControl('', Validators.required),
    sourceId: new FormControl('', Validators.required),
    candidateId: new FormControl(''),
    documentName: new FormControl('', Validators.required),
    document: new FormControl('', Validators.required),
  });
  patchUserValues() {
    this.vendorlist.patchValue({
      sourceId: this.tmp,
      candidateId: this.candidateId,
    });
  }

  formEditEdu = new FormGroup({
    documentname: new FormControl('', Validators.required),
    vendorId: new FormControl(''),
    sourceId: new FormControl('', Validators.required),
    candidateId: new FormControl(''),

  });
  patcheduValues() {
    this.formEditEdu.patchValue({
      sourceId: this.sourceid,
      candidateId: this.candidateId,
      vendorId: this.vendorid
    });

  }

  foremployements = new FormGroup({
    candidateName: new FormControl('', Validators.required),
    documentname: new FormControl('', Validators.required),
    vendorId: new FormControl(''),
    sourceId: new FormControl(''),
    candidateId: new FormControl(''),
  });
  patcheduValuesemp() {
    this.foremployements.patchValue({
      sourceId: this.sourceid,
      candidateId: this.candidateId,
      vendorId: this.vendorid
    });

  }

  forAddressCrimnalGlobal = new FormGroup({
    candidateName: new FormControl('', Validators.required),
    dateOfBirth: new FormControl('', Validators.required),
    contactNo: new FormControl('', Validators.required),
    fatherName: new FormControl('', Validators.required),
    address: new FormControl('', Validators.required),
    vendorId: new FormControl(''),
    sourceId: new FormControl(''),
    candidateId: new FormControl(''),
  });
  patcheduValuesAddress() {
    this.forAddressCrimnalGlobal.patchValue({
      sourceId: this.sourceid,
      candidateId: this.candidateId,
      vendorId: this.vendorid
    });

  }

  forDrugTest = new FormGroup({
    candidateName: new FormControl('', Validators.required),
    documentname: new FormControl('', Validators.required),
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
  patcheduValuesDrugTest() {
    this.forDrugTest.patchValue({
      sourceId: this.sourceid,
      candidateId: this.candidateId,
      vendorId: this.vendorid
    });

  }

  formpassport = new FormGroup({
    candidateName: new FormControl('', Validators.required),
    documentname: new FormControl('', Validators.required),
    vendorId: new FormControl(''),
    sourceId: new FormControl('', Validators.required),
    candidateId: new FormControl(''),

  });
  patchpassport() {
    this.formpassport.patchValue({
      sourceId: this.sourceid,
      candidateId: this.candidateId,
      vendorId: this.vendorid
    });

  }



  constructor(private customers: CustomerService, private router: ActivatedRoute, private fb: FormBuilder, authService: AuthenticationService,
    private modalService: NgbModal, private navRouter: Router) {
    this.userID = this.router.snapshot.paramMap.get('userId');
    this.candidateId = this.router.snapshot.paramMap.get('candidateId');
    console.log(this.candidateId, "-----------------------------------")
    this.customers.getVendorCheckDetails(this.candidateId).subscribe((data: any) => {

      this.vendorchecksupload = data.data;
      console.log(this.vendorchecksupload[0])
      if (this.getVendorID) {
        for (var index in this.vendorchecksupload) {
          for (var index1 in this.getVendorID) {
            if (this.vendorchecksupload[index]["vendorId"] == this.getVendorID[index1]["userId"]) {
              console.log(this.getVendorID[index1]["userFirstName"], "conuttt")
              this.vendorchecksupload[index]['username'] = this.getVendorID[index1]["userFirstName"]
            }
          }
        }
      }
      console.log(this.vendorchecksupload)
    })
    if (authService.roleMatch(['ROLE_ADMIN'])) {
      console.log(localStorage.getItem('orgID'), "------------------org id")
      this.customers.getVendorList(localStorage.getItem('orgID')).subscribe((data: any) => {
        this.getVendorID = data.data;
        console.log(this.getVendorID, "-------------vendoy----------------");
        if (this.userID) {
          for (var index in this.getVendorID) {
            console.log(this.getVendorID[index]["userId"], "indexxxxxxxxxxxxxxxxxxxx");
            if (this.userID == this.getVendorID[index]["userId"]) {
              console.log(this.userID, "final")
              this.vendoruser = this.getVendorID[index]["userFirstName"]
              console.log(this.vendoruser, "finaluser")
            }

          }
        }
        // if(this.userID){
        //     if (this.userID== item.)
        // }
      });
      console.log(this.vendorlist.value, "-------------vend----------------");

    }
    let rportData = {
      'userId': localStorage.getItem('userId')
    }

    this.customers.getSources().subscribe((data: any) => {
      this.getbgv = data.data;
      console.log(this.getbgv, "-------------getbgv----------------");
      this.getbgv.forEach((element: any) => {
        element.serviceId = '';
        element.ratePerItem = '';
        element.ratePerItem = '';

      });
    });
    if (this.userID) {

      this.customers.getAllVendorServices(this.userID).subscribe((data: any) => {
        console.log("--------------------calling service--------------")
        this.getBillValues = data.data;
        console.log(this.getBillValues, "--------------------")
        if (this.getBillValues) {
          this.getBillValues.forEach((element: any) => {
            $(".billrpp" + element.source.sourceId).val(element.ratePerItem);
            $(".billrpi" + element.source.sourceId).val(element.tatPerItem);
            $(".billServiceId" + element.source.sourceId).val(element.userId);

          });
        }

      });
    }

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
    this.vendorid = id;
  }

  onKeyUp() {
    this.VendorData_stat = false;
  }
  ngOnInit(): void {

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

  submitpassport(formpassport: FormGroup) {
    this.patchpassport()
    console.log("....................", this.formpassport.value)
    const formData = new FormData();
    formData.append('vendorchecks', JSON.stringify(this.formpassport.value));
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



  getsourceid(id: any) {
    this.sourceid = id;
    console.log(this.sourceid, "**********************")
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

  }

  opentemplate(id: any) {

    this.modalService.open(id, { ariaLabelledBy: 'modal-basic-title' }).result.then((res) => {
      this.closeModal = `Closed with: ${res}`;
    }, (res) => {
      this.closeModal = `Dismissed ${this.getDismissReason(res)}`;
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

  billUpdate() {
    console.log("______________inside button ------------------")
    this.getBillValues.forEach((element: any) => {
      element.ratePerItem = $(".billrpp" + element.source.sourceId).val();
      element.tatPerItem = $(".billrpi" + element.source.sourceId).val();
      element.serviceId = $(".billServiceId" + element.source.userId).val();

    });
    return this.customers.saveVendorChecks(this.getBillValues, this.userID).subscribe((result: any) => {
      console.log(result, '--------------------return---------------');
      if (result.outcome === true) {
        Swal.fire({
          title: result.message,
          icon: 'success'
        }).then((result) => {
          if (result.isConfirmed) {
            const navURL = 'admin/addvendor';
            this.navRouter.navigate([navURL]);
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

