import {Component, NgZone, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {NgbCalendar, NgbDate, NgbModal,} from '@ng-bootstrap/ng-bootstrap';
import Swal from 'sweetalert2';
import {OrgadminService} from '../../services/orgadmin.service';
import {AuthenticationService} from '../../services/authentication.service';
import {OrgadminDashboardService} from '../../services/orgadmin-dashboard.service';
import {LoaderService} from '../../services/loader.service';
import {CustomerService} from '../../services/customer.service';
import {Router} from '@angular/router';
import * as am4core from '@amcharts/amcharts4/core';
import * as am4charts from '@amcharts/amcharts4/charts';
import {CandidateService} from "../../services/candidate.service";

@Component({
  selector: 'app-candidatesubmitted-conventional',
  templateUrl: './candidatesubmitted-conventional.component.html',
  styleUrls: ['./candidatesubmitted-conventional.component.scss'],
})
export class CandidatesubmittedConventionalComponent implements OnInit {
  pageTitle = 'Conventional Dashboard';
  closeModal: string | undefined;
  selectedFiles: any;
  currentFile: any;
  containerStat: boolean = false;
  fileInfos: any;
  getReportDeliveryStatCodes: any;
  getPendingDetailsStatCode: any;
  getStatCodes: any;
  isShowDiv: boolean = false;
  isCBadmin: boolean = false;
  getUserByOrganizationIdAndUserId: any = [];
  getRolePerMissionCodes: any = [];
  AGENTUPLOAD_stat: boolean = false;
  CANDIDATEUPLOAD_stat: boolean = false;
  fromDate: any;
  toDate: any;
  getToday: NgbDate;
  getMinDate: any;
  setfromDate: any;
  settoDate: any;
  initToday: any;
  Interim: boolean = false;
  Final: boolean = false;
  dashboardFilter = new FormGroup({
    fromDate: new FormControl('', Validators.required),
    toDate: new FormControl('', Validators.required),
  });

  candidateData: any;

  // added for chart
  getChartData: any = [];
  ChartDataListing: any = [];
  getuploadinfo: any = [];
  addcheckDisabled: boolean = false;

  constructor(
    private orgadmin: OrgadminService,
    private modalService: NgbModal,
    private navRouter: Router,
    public authService: AuthenticationService,
    private dashboardservice: OrgadminDashboardService,
    public calendar: NgbCalendar,
    private customer: CustomerService,
    private zone: NgZone,
    private loaderService: LoaderService,
    private candidateService: CandidateService
  ) {

    //adding Candidates based on vendor id without adding duplicates
    this.customer.updateCandidateStatusBasedOnLiCheckStatus().subscribe(data => {
      console.log(data)
    });
    this.getReportDeliveryStatCodes =
      this.dashboardservice.getReportDeliveryStatCode();
    this.getPendingDetailsStatCode =
      this.dashboardservice.getPendingDetailsStatCode();

    this.getStatCodes = this.dashboardservice.getStatusCode();
    this.dashboardservice
      .getUsersByRoleCode(localStorage.getItem('roles'))
      .subscribe((data: any) => {
        this.getUserByOrganizationIdAndUserId = data.data;

      });
    var userId: any = localStorage.getItem('userId');
    var fromDate: any = localStorage.getItem('dbFromDate');
    var toDate: any = localStorage.getItem('dbToDate');
    let filterData = {
      userId: userId,
      fromDate: fromDate,
      toDate: toDate,
      status: this.getStatCodes,
    };

    this.dashboardservice
      .getCandidatesSubmittedDetailsByDateRange(filterData)
      .subscribe((resp: any) => {
        // @ts-ignore
        this.candidateData = resp.data;
      });

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
  }

  VendorID: any;

  ngOnInit(): void {

    const isCBadminVal = localStorage.getItem('roles');
    if (this.getStatCodes) {
      $(".orgadmin_uploaddetails").addClass(this.getStatCodes);
      if (this.getStatCodes === "NEWUPLOAD") {
        $(".dbtabheading").text("New Upload-Conventional");
        this.Interim = true;
        this.Final = false;
      } else if (this.getStatCodes === "INPROGRESS") {
        $(".dbtabheading").text("Inprogress");
        this.Interim = true;
        this.Final = true;
      } else if (this.getStatCodes === "QCPending") {
        $(".dbtabheading").text("QC Pending");
        this.Interim = false;
        this.Final = false;
      }

      this.containerStat = true;
      //isCBadmin required for drilldown dashboard at Superadmin
    }
    //Role Management
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

  ngAfterViewInit() {
    setTimeout(() => {
      // this.ngOnDestroy();
      this.loadCharts();
    }, 50);
  }
  getStatcodeFromLocalStorage(): string {
    // Retrieve the statcode from local storage
    const statcode = localStorage.getItem('statCode');
    return statcode || ''; // Return an empty string if statcode is null or undefined
  }

  loadCharts() {
    //for left side chart
    this.zone.runOutsideAngular(() => {
      let chart = am4core.create('chartdiv', am4charts.PieChart);
      chart.innerRadius = am4core.percent(50);
      chart.legend = new am4charts.Legend();

      chart.legend.itemContainers.template.paddingTop = 4;
      chart.legend.itemContainers.template.paddingBottom = 4;
      chart.legend.fontSize = 13;
      chart.legend.useDefaultMarker = true;
      let marker: any = chart.legend.markers.template.children.getIndex(0);
      marker.cornerRadius(12, 12, 12, 12);
      marker.strokeWidth = 3;
      marker.strokeOpacity = 1;
      marker.stroke = am4core.color('#000');

      chart.legend.maxHeight = 210;
      chart.legend.scrollable = true;
      chart.legend.position = 'right';
      chart.logo.disabled = true;
      chart.padding(0, 0, 0, 0);
      chart.radius = am4core.percent(95);
      chart.paddingRight = 0;
      var userId: any = localStorage.getItem('userId');
      var fromDate: any = localStorage.getItem('dbFromDate');
      var toDate: any = localStorage.getItem('dbToDate');
      let filterData = {
        userId: userId,
        fromDate: fromDate,
        toDate: toDate,
      };
      this.dashboardservice
        .getConventionalUploadDetails(filterData)
        .subscribe((uploadinfo: any) => {

          this.getuploadinfo = uploadinfo.data.candidateStatusCountDto;
          //console.log(this.getuploadinfo);
          let data = [];
          for (let i = 0; i < this.getuploadinfo.length; i++) {
            data.push({
              name: this.getuploadinfo[i].statusName,
              value: this.getuploadinfo[i].count,
              statcode: this.getuploadinfo[i].statusCode,
            });
          }
          chart.data = data;
        });
      // Add and configure Series
      let pieSeries = chart.series.push(new am4charts.PieSeries());
      pieSeries.slices.template.tooltipText = '{category}: {value}';
      pieSeries.labels.template.disabled = true;
      pieSeries.dataFields.value = 'value';
      pieSeries.dataFields.category = 'name';
      pieSeries.slices.template.stroke = am4core.color('#fff');
      pieSeries.slices.template.strokeWidth = 2;
      pieSeries.slices.template.strokeOpacity = 1;
      // This creates initial animation
      pieSeries.hiddenState.properties.opacity = 1;
      pieSeries.hiddenState.properties.endAngle = -90;
      pieSeries.hiddenState.properties.startAngle = -90;
      pieSeries.legendSettings.itemValueText = '[bold]{value}[/bold]';
      pieSeries.colors.list = [
        am4core.color('#FF8E00'),
        am4core.color('#ffd400'),
        am4core.color('#fd352c'),
        am4core.color('#08e702'),
        am4core.color('#9c27b0'),
        am4core.color('#021aee'),
      ];

      pieSeries.slices.template.events.on('hit', (e) => {
        const getchartData = e.target._dataItem as any;
        const statuscodes = getchartData._dataContext.statcode;
        //console.log(statuscodes);
        this.dashboardservice.setStatusCode(statuscodes);
        window.location.reload();
      });

      chart.legend.itemContainers.template.events.on("hit", (ev) => {
        const getchartData = ev.target._dataItem as any;
        const statuscodes = getchartData._label._dataItem._dataContext.statcode;
        this.dashboardservice.setStatusCode(statuscodes);
        window.location.reload();
      });
      pieSeries.slices.template.cursorOverStyle = am4core.MouseCursorStyle.pointer;
    });
    //for right side chart

    this.zone.runOutsideAngular(() => {
      let chart = am4core.create('chartReportDelivery', am4charts.PieChart);
      chart.innerRadius = am4core.percent(50);
      chart.legend = new am4charts.Legend();

      chart.legend.itemContainers.template.paddingTop = 4;
      chart.legend.itemContainers.template.paddingBottom = 4;
      chart.legend.fontSize = 13;
      chart.legend.useDefaultMarker = true;
      let marker: any = chart.legend.markers.template.children.getIndex(0);
      marker.cornerRadius(12, 12, 12, 12);
      marker.strokeWidth = 3;
      marker.strokeOpacity = 1;
      marker.stroke = am4core.color('#000');

      chart.legend.maxHeight = 210;
      chart.legend.scrollable = true;
      chart.legend.position = 'right';
      chart.logo.disabled = true;
      chart.padding(0, 0, 0, 0);
      chart.radius = am4core.percent(95);
      chart.paddingRight = 0;
      var userId: any = localStorage.getItem('userId');
      var fromDate: any = localStorage.getItem('dbFromDate');
      var toDate: any = localStorage.getItem('dbToDate');
      let filterData = {
        userId: userId,
        fromDate: fromDate,
        toDate: toDate,
      };
      this.dashboardservice
        .findInterimAndFinalReportForDashboard(filterData)
        .subscribe((uploadinfo: any) => {
          this.getuploadinfo = uploadinfo.data.candidateStatusCountDto;
          //console.log(this.getuploadinfo);
          let data = [];
          for (let i = 0; i < this.getuploadinfo.length; i++) {
            data.push({
              name: this.getuploadinfo[i].statusName,
              value: this.getuploadinfo[i].count,
              statcode: this.getuploadinfo[i].statusCode,
            });
          }
          chart.data = data;
        });
      // Add and configure Series
      let pieSeries = chart.series.push(new am4charts.PieSeries());
      pieSeries.slices.template.tooltipText = '{category}: {value}';
      pieSeries.labels.template.disabled = true;
      pieSeries.dataFields.value = 'value';
      pieSeries.dataFields.category = 'name';
      pieSeries.slices.template.stroke = am4core.color('#fff');
      pieSeries.slices.template.strokeWidth = 2;
      pieSeries.slices.template.strokeOpacity = 1;
      // This creates initial animation
      pieSeries.hiddenState.properties.opacity = 1;
      pieSeries.hiddenState.properties.endAngle = -90;
      pieSeries.hiddenState.properties.startAngle = -90;
      pieSeries.legendSettings.itemValueText = '[bold]{value}[/bold]';
      pieSeries.colors.list = [
        am4core.color('#FF8E00'),
        am4core.color('#ffd400'),
        am4core.color('#fd352c'),
        am4core.color('#08e702'),
        am4core.color('#9c27b0'),
        am4core.color('#021aee'),
      ];

      pieSeries.slices.template.events.on('hit', (e) => {
        const getchartData = e.target._dataItem as any;
        const statuscodes = getchartData._dataContext.statcode;
        //console.log(statuscodes);
        this.dashboardservice.setStatusCode(statuscodes);
        window.location.reload();
      });

      chart.legend.itemContainers.template.events.on("hit", (ev) => {
        const getchartData = ev.target._dataItem as any;
        const statuscodes = getchartData._label._dataItem._dataContext.statcode;
        this.dashboardservice.setStatusCode(statuscodes);
        window.location.reload();
      });
      pieSeries.slices.template.cursorOverStyle = am4core.MouseCursorStyle.pointer;
    });
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

  isLoading = true;

  // conventionalvendor(candidateId: any) {
  //   this.loaderService.showLoader(true);
  //   alert('Licheck Fetch Starts')
  //
  //
  //   console.log(candidateId, '-----------------------------------------------');
  //   alert(candidateId + "start");
  //   this.customer.getConventionalCandidateByCandidateId(candidateId).subscribe((data: any) => {
  //     console.log(data)
  //     if (data.toString() != null) {
  //       this.loaderService.showLoader(false);
  //       alert('Licheck Fetch ends')
  //     }
  //
  //   });
  // }
  // async licheckByCandidateID(candidateId: any) {
  //
  //   try {
  //     this.loaderService.show();
  //     const licheckdata: any = await this.customer.getConventionalCandidateByCandidateId(candidateId).toPromise();
  //     console.log("responde data" + licheckdata);
  //
  //     if (licheckdata != null) {
  //       return;
  //     }
  //   } catch (error) {
  //     // Handle any errors that occur during the API call
  //     console.error(error);
  //   } finally {
  //     console.log("finally")
  //   }
  // }


  async licheckByCandidateID(candidateId: any) {
    return new Promise<void>((resolve) => {
      this.customer.getConventionalCandidateByCandidateId(candidateId).subscribe((LICDAE: any) => {
        console.log(LICDAE);
        if (LICDAE.toString() != null) {
          resolve();
        }
      });
    });
  }

  conventionalvendor(candidateId: any, status: any) {
    this.addcheckDisabled = true;
    // console.log(candidateId, '-----------------------------------------------');
    this.loaderService.show();
    this.licheckByCandidateID(candidateId).then((lickedata: any) => {
      if (lickedata != null) {
        this.loaderService.hide();
      }
      localStorage.setItem("requestid", candidateId);
      const navURL = 'admin/conventionalVendorcheck';
      this.navRouter.navigate([navURL]);
    });
      localStorage.setItem("finalReportStatus", status);
    }

  getInterimReport(requestId: any) {
    this.candidateService.generateReportWithReportType(requestId, "INTERIM", "DONT").subscribe(data =>
      // @ts-ignore
      window.open(data.data, "_blank")
    );

  }

  getFinalReport(requestId: any) {
    this.candidateService.generateReportWithReportType(requestId, "FINAL", "DONT").subscribe(data =>
      // @ts-ignore
      window.open(data.data, "_blank")
    );
  }
}
