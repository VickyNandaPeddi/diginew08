import {Component, NgZone, OnInit} from '@angular/core';
import {OrgadminService} from 'src/app/services/orgadmin.service';
import {
  ModalDismissReasons,
  NgbModal,
  NgbCalendar,
  NgbDate,
} from '@ng-bootstrap/ng-bootstrap';
import {
  FormGroup,
  FormControl,
  FormBuilder,
  Validators,
} from '@angular/forms';
import {HttpEventType, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import Swal from 'sweetalert2';
import {AuthenticationService} from 'src/app/services/authentication.service';
import {OrgadminDashboardService} from 'src/app/services/orgadmin-dashboard.service';
import {LoaderService} from 'src/app/services/loader.service';
import {CustomerService} from 'src/app/services/customer.service';
import {Router} from '@angular/router';

import * as am4core from '@amcharts/amcharts4/core';
import * as am4charts from '@amcharts/amcharts4/charts';

@Component({
  selector: 'app-vendor-dashboard',
  templateUrl: './vendor-dashboard.component.html',
  styleUrls: ['./vendor-dashboard.component.scss'],
})
export class VendorDashboardComponent implements OnInit {
  pageTitle = 'Conventional Dashboard';
  closeModal: string | undefined;
  selectedFiles: any;
  currentFile: any;
  containerStat: boolean = false;
  fileInfos: any;
  getReportDeliveryStatCodes: any;
  getPendingDetailsStatCode: any;
  getStatCodes: any;
  candidateId: any;
  isShowDiv: boolean = false;
  isCBadmin: boolean = false;
  getUserByOrganizationIdAndUserId: any = [];
  getRolePerMissionCodes: any = [];
  AGENTUPLOAD_stat: boolean = false;
  CANDIDATEUPLOAD_stat: boolean = false;
  fromDate: any;
  toDate: any;
  getToday: NgbDate = new NgbDate(2023, 1, 1);
  getMinDate: any;
  setfromDate: any;
  settoDate: any;
  initToday: any;
  dashboardFilter = new FormGroup({
    fromDate: new FormControl('', Validators.required),
    toDate: new FormControl('', Validators.required),
  });

  // added for chart
  getChartData: any = [];
  ChartDataListing: any = [];
  getuploadinfo: any = [];

  ngOnInit(): void {

    if (this.getPendingDetailsStatCode) {
      this.isShowDiv = true;
    } else if (this.getReportDeliveryStatCodes) {
      this.isShowDiv = false;
    }

    if (
      this.getStatCodes ||
      this.getPendingDetailsStatCode ||
      this.getReportDeliveryStatCodes
    ) {
      this.containerStat = true;
    }
    setTimeout(() => {
      this.loaderService.hide();
    }, 7000);
    //isCBadmin required for drilldown dashboard at Superadmin
    const isCBadminVal = localStorage.getItem('roles');
    if (isCBadminVal == '"ROLE_CBADMIN"') {
      this.isCBadmin = true;
    } else {
      this.isCBadmin = false;
    }

    this.orgadmin
      .getRolePerMissionCodes(localStorage.getItem('roles'))
      .subscribe((result: any) => {
        this.getRolePerMissionCodes = result.data;
        //console.log(this.getRolePerMissionCodes);
        if (this.getRolePerMissionCodes) {
          if (this.getRolePerMissionCodes.includes('AGENTUPLOAD')) {
            this.AGENTUPLOAD_stat = true;
          }

          if (this.getRolePerMissionCodes.includes('CANDIDATEUPLOAD')) {
            this.CANDIDATEUPLOAD_stat = true;
          }
        }
      });
  }

  candidateData: any;

  constructor(
    private orgadmin: OrgadminService,
    private modalService: NgbModal,
    private navRouter: Router,
    public authService: AuthenticationService,
    private dashboardservice: OrgadminDashboardService,
    public loaderService: LoaderService,
    public calendar: NgbCalendar,
    private customer: CustomerService,
    private zone: NgZone
  ) {
    var userId: any = localStorage.getItem('userId');
    this.getToday = calendar.getToday();
    if (
      localStorage.getItem('dbFromDate') == null &&
      localStorage.getItem('dbToDate') == null
    ) {
      let inityear = this.getToday.year;
      let initmonth =
        this.getToday.month <= 9
          ? '0' + this.getToday.month
          : this.getToday.month;
      let initday =
        this.getToday.day <= 9 ? '0' + this.getToday.day : this.getToday.day;
      let initfinalDate = initday + '/' + initmonth + '/' + inityear;
      this.initToday = initfinalDate;
      this.customer.setFromDate(this.initToday);
      this.customer.setToDate(this.initToday);
      this.fromDate = this.initToday;
      this.toDate = this.initToday;
    }


    this.getReportDeliveryStatCodes =
      this.dashboardservice.getReportDeliveryStatCode();
    this.getPendingDetailsStatCode =
      this.dashboardservice.getPendingDetailsStatCode();
    //this.getStatCodes = this.dashboardservice.getStatusCode();
    this.dashboardservice
      .getUsersByRoleCode(localStorage.getItem('roles'))
      .subscribe((data: any) => {
        this.getUserByOrganizationIdAndUserId = data.data;
        //console.log(this.getUserByOrganizationIdAndUserId)
      });
    let filterData = {
      userId: userId,
      fromDate: localStorage.getItem('dbFromDate'),
      toDate: localStorage.getItem('dbToDate'),
      status: this.getStatCodes,
    };

    this.updateChartDetails(filterData);

    var checkfromDate: any = localStorage.getItem('dbFromDate');
    let getfromDate = checkfromDate.split('/');
    this.setfromDate = {
      day: +getfromDate[0],
      month: +getfromDate[1],
      year: +getfromDate[2],
    };

    var checktoDate: any = localStorage.getItem('dbToDate');
    let gettoDate = checktoDate.split('/');
    this.settoDate = {
      day: +gettoDate[0],
      month: +gettoDate[1],
      year: +gettoDate[2],
    };
    this.getMinDate = {
      day: +gettoDate[0],
      month: +gettoDate[1],
      year: +gettoDate[2],
    };

    this.dashboardFilter.patchValue({
      fromDate: this.setfromDate,
      toDate: this.settoDate,
    });

    this.getStatCodes = this.dashboardservice.getStatusCode();
    if (this.getStatCodes) {
      var userId: any = localStorage.getItem('userId');
      var fromDate: any = localStorage.getItem('dbFromDate');
      var toDate: any = localStorage.getItem('dbToDate');
      let filterData = {
        userId: userId,
        fromDate: localStorage.getItem(""),
        toDate: toDate,
        status: this.getStatCodes,
      };
      this.dashboardservice
        .getChartDetails(filterData)
        .subscribe((data: any) => {
          this.ChartDataListing = data.data.candidateDtoList;

        });

    }
  }

  updateChartDetails(filterData: any) {
    this.dashboardservice.getConventionalUploadDetails(filterData).subscribe((resp) => {
      // @ts-ignore
      this.candidateData = resp.data;
    });

  }
  toggleDisplayDiv() {
    this.isShowDiv = !this.isShowDiv;
  }

  getuserId(userId: any) {
    if (userId != 'null') {
      localStorage.setItem('userId', userId);
      window.location.reload();
    } else {
      Swal.fire({
        title: 'Please select the user.',
        icon: 'success',
      });
    }
  }

  onfromDate(event: any) {
    let year = event.year;
    let month = event.month <= 9 ? '0' + event.month : event.month;
    let day = event.day <= 9 ? '0' + event.day : event.day;
    let finalDate = day + '/' + month + '/' + year;
    this.fromDate = finalDate;
    this.getMinDate = {day: +day, month: +month, year: +year};
  }

  ontoDate(event: any) {
    let year = event.year;
    let month = event.month <= 9 ? '0' + event.month : event.month;
    let day = event.day <= 9 ? '0' + event.day : event.day;
    let finalDate = day + '/' + month + '/' + year;
    this.toDate = finalDate;
  }

  onSubmitFilter(dashboardFilter: FormGroup) {
    let inputFromDate: any = $('#inputFromDate').val();
    //let getInputFromDate:any = inputFromDate.split('-');
    let finalInputFromDate = inputFromDate;

    let inputToDate: any = $('#inputToDate').val();
    //let getInputToDate:any = inputToDate.split('-');
    let finalInputToDate = inputToDate;

    if (this.fromDate == null) {
      this.fromDate = finalInputFromDate;
    }
    if (this.toDate == null) {
      this.toDate = finalInputToDate;
    }
    if (this.dashboardFilter.valid) {
      this.customer.setFromDate(this.fromDate);
      this.customer.setToDate(this.toDate);
      window.location.reload();

    } else {
      Swal.fire({
        title: 'Please select the valid dates.',
        icon: 'warning',
      });
    }
  }
}
