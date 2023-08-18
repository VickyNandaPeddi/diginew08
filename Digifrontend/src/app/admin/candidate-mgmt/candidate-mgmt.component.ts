import { Component, OnInit } from '@angular/core';
import { OrgadminDashboardService } from 'src/app/services/orgadmin-dashboard.service';
import { ColDef } from 'ag-grid-community';
import {ModalDismissReasons, NgbModal, NgbCalendar, NgbDate} from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { CustomerService } from 'src/app/services/customer.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-candidate-mgmt',
  templateUrl: './candidate-mgmt.component.html',
  styleUrls: ['./candidate-mgmt.component.scss']
})
export class CandidateMgmtComponent implements OnInit {
  pageTitle = 'Candidate Management';
  defaultColDef:any;
  columnDefs: any=[];
  candidateData: any=[];
  gridApi:any;
  fromDate:any;
  toDate:any;
  getToday: NgbDate;
  getMinDate: any;
  setfromDate:any;
  settoDate:any;
  initToday:any;
  dashboardFilter = new FormGroup({
    fromDate: new FormControl('', Validators.required),
    toDate: new FormControl('', Validators.required)
  });
  constructor(private orgadmin: OrgadminDashboardService, public calendar: NgbCalendar, 
    private customer: CustomerService) {
    var userId:any = localStorage.getItem('userId');
      var fromDate:any = localStorage.getItem('dbFromDate');
      var toDate:any = localStorage.getItem('dbToDate');
      let filterData = {
        'userId': userId,
        'fromDate': fromDate,
        'toDate': toDate,
        'status': 'NEWUPLOAD'
      }
      this.orgadmin.getChartDetails(filterData).subscribe((data: any)=>{
        this.candidateData=data.data.candidateDtoList;
        console.log(this.candidateData);
      });
      this.defaultColDef = {
        sortable: true,
        resizable: true,
        filter: true,
        flex: 1,
        minWidth: 80,
       //floatingFilter: true,
      };
      this.columnDefs= [
        { headerName: 'Agent Name', field: 'createdByUserFirstName',...this.defaultColDef },
        { headerName: 'Candidate Name', field: 'candidateName',...this.defaultColDef },
        { headerName: 'Email ID', field: 'emailId',...this.defaultColDef },
        { headerName: 'Contact Number', field: 'contactNumber',...this.defaultColDef },
        { headerName: 'Applicant ID', field: 'applicantId',...this.defaultColDef },
        { headerName: 'Data uploaded', field: 'createdOn',...this.defaultColDef },
        { headerName: 'Status', field: 'candidateStatusName',...this.defaultColDef, filter: 'agSetColumnFilter' }
      ];

      this.getToday = calendar.getToday(); 
      if(localStorage.getItem('dbFromDate')==null && localStorage.getItem('dbToDate')==null){
        let inityear = this.getToday.year;
        let initmonth = this.getToday.month <= 9 ? '0' + this.getToday.month : this.getToday.month;;
        let initday = this.getToday.day <= 9 ? '0' + this.getToday.day : this.getToday.day;
        let initfinalDate = initday + "/" + initmonth + "/" + inityear;
        this.initToday = initfinalDate;
        this.customer.setFromDate(this.initToday);
        this.customer.setToDate(this.initToday);
        this.fromDate = this.initToday;
        this.toDate = this.initToday;
      }
      
      var checkfromDate:any = localStorage.getItem('dbFromDate');
      let getfromDate = checkfromDate.split('/');
      this.setfromDate = { day:+getfromDate[0],month:+getfromDate[1],year:+getfromDate[2]};

      var checktoDate:any = localStorage.getItem('dbToDate');
      let gettoDate =checktoDate.split('/');
      this.settoDate = { day:+gettoDate[0],month:+gettoDate[1],year:+gettoDate[2]};
      this.getMinDate = { day:+gettoDate[0],month:+gettoDate[1],year:+gettoDate[2]};

      this.dashboardFilter.patchValue({
        fromDate: this.setfromDate,
        toDate: this.settoDate
       });

      
   }

   onfromDate(event:any) {
    let year = event.year;
    let month = event.month <= 9 ? '0' + event.month : event.month;;
    let day = event.day <= 9 ? '0' + event.day : event.day;
    let finalDate = day + "/" + month + "/" + year;
    this.fromDate = finalDate;
    this.getMinDate = { day:+day,month:+month,year:+year};
   }
   ontoDate(event:any) {
    let year = event.year;
    let month = event.month <= 9 ? '0' + event.month : event.month;;
    let day = event.day <= 9 ? '0' + event.day : event.day;;
    let finalDate = day + "/" + month + "/" + year;
    this.toDate = finalDate;
   }
   onSubmitFilter(dashboardFilter:FormGroup){
     let inputFromDate:any = $("#inputFromDate").val();
     //let getInputFromDate:any = inputFromDate.split('-');
     let finalInputFromDate = inputFromDate;

     let inputToDate:any = $("#inputToDate").val();
     //let getInputToDate:any = inputToDate.split('-');
     let finalInputToDate = inputToDate;

    if(this.fromDate==null){
        this.fromDate = finalInputFromDate;
    }
    if(this.toDate==null){
      this.toDate = finalInputToDate;
    }
    if (this.dashboardFilter.valid) {
      this.customer.setFromDate(this.fromDate);
      this.customer.setToDate(this.toDate);
      window.location.reload();
    }else{
      Swal.fire({
        title: 'Please select the valid dates.',
        icon: 'warning'
      });
    }
   }

   onBtExport() {
    this.gridApi.exportDataAsCsv();
  }
  onGridReady(params:any) {
    this.gridApi = params.api;
  }
  
  ngOnInit(): void {
  }

}
