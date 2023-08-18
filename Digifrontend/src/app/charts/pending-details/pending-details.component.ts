import {Component, NgZone, AfterViewInit, OnDestroy, OnInit} from '@angular/core';
import * as am4core from "@amcharts/amcharts4/core";
import * as am4charts from "@amcharts/amcharts4/charts";
import am4themes_animated from "@amcharts/amcharts4/themes/animated";
import {OrgadminDashboardService} from 'src/app/services/orgadmin-dashboard.service';
import Swal from 'sweetalert2';
import {Router} from '@angular/router';
import {FormGroup, FormControl, FormBuilder, Validators} from '@angular/forms';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {AuthenticationService} from 'src/app/services/authentication.service';
import {OrgadminService} from 'src/app/services/orgadmin.service';

am4core.useTheme(am4themes_animated);

@Component({
  selector: 'app-pending-details',
  templateUrl: './pending-details.component.html',
  styleUrls: ['./pending-details.component.scss']
})
export class PendingDetailsComponent implements OnInit {
  private chart: am4charts.XYChart | undefined;
  ChartPendingDetails: any = [];
  containerStat: boolean = false;
  getPendingDetailsStatCode: any;
  CharPendingDetails: any = [];
  public stat_INVITATIONSENT = false;
  getRolePerMissionCodes: any = [];
  EDITCANDIDATE_stat: boolean = false;
  tmp: any = [];
  getCandidate: any = [];
  public stat_NEWUPLOAD = true;
  public stat_btn_ReInvite = false;
  formSendInvitation = new FormGroup({
    candidateReferenceNo: new FormControl('', Validators.required),
    statuscode: new FormControl('', Validators.required)
  });

  updateCandidate = new FormGroup({
    applicantId: new FormControl(''),
    candidateName: new FormControl('', Validators.required),
    createdByUserFirstName: new FormControl('', Validators.required),
    candidateCode: new FormControl('', Validators.required),
    contactNumber: new FormControl('', [Validators.minLength(10), Validators.maxLength(10), Validators.pattern('[6-9]\\d{9}')]),
    emailId: new FormControl('', [Validators.required, Validators.email])
  });

  patchUserValues() {
    this.formSendInvitation.patchValue({
      candidateReferenceNo: this.tmp,
      statuscode: "INVITATIONSENT",
    });
  }

  reInvitePatchValues() {
    this.formSendInvitation.patchValue({
      candidateReferenceNo: this.tmp,
      statuscode: "REINVITE",
    });
  }

  constructor(private zone: NgZone, private adminDashboard: OrgadminDashboardService,
              private router: Router, private modalService: NgbModal, public authService: AuthenticationService,
              private orgadminservice: OrgadminService) {
    this.getPendingDetailsStatCode = this.adminDashboard.getPendingDetailsStatCode();
    if (this.getPendingDetailsStatCode) {
      var userId: any = localStorage.getItem('userId');
      var fromDate: any = localStorage.getItem('dbFromDate');
      var toDate: any = localStorage.getItem('dbToDate');
      let filterData = {
        'userId': userId,
        'fromDate': fromDate,
        'toDate': toDate,
        'status': this.getPendingDetailsStatCode
      }
      this.adminDashboard.getChartDetails(filterData).subscribe((data: any) => {
        this.CharPendingDetails = data.data.candidateDtoList;
      });

    }
  }

  ngAfterViewInit() {
    setTimeout(() => {
      this.ngOnDestroy();
      this.loadCharts();
    }, 50);
  }

  ngOnInit(): void {
    if (this.getPendingDetailsStatCode) {
      if (this.getPendingDetailsStatCode === "INVITATIONSENT") {
        $(".dbtabheading").text("Invitation Sent");
        this.stat_INVITATIONSENT = true;
        this.stat_btn_ReInvite = true;
      }
      this.containerStat = true;
    }

    //Role Management
    this.orgadminservice.getRolePerMissionCodes(localStorage.getItem('roles')).subscribe(
      (result: any) => {
        this.getRolePerMissionCodes = result.data;
        //console.log(this.getRolePerMissionCodes);
        if (this.getRolePerMissionCodes) {
          if (this.getRolePerMissionCodes.includes("EDITCANDIDATE")) {
            this.EDITCANDIDATE_stat = true;
          }


        }
      });
  }

  loadCharts() {
    this.zone.runOutsideAngular(() => {
      let chart = am4core.create("chartPendingDetails", am4charts.PieChart);
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
      marker.stroke = am4core.color("#000");
      //chart.legend.maxHeight = 210;
      chart.legend.scrollable = true;

      chart.legend.position = "right";
      chart.logo.disabled = true;
      chart.padding(0, 0, 0, 0);
      chart.radius = am4core.percent(95);
      chart.paddingRight = 0;

      var userId: any = localStorage.getItem('userId');
      var fromDate: any = localStorage.getItem('dbFromDate');
      var toDate: any = localStorage.getItem('dbToDate');
      let filterData = {
        'userId': userId,
        'fromDate': fromDate,
        'toDate': toDate
      }

      // this.adminDashboard.getPendingDetailsStatusAndCount(filterData).subscribe((uploadinfo: any) => {
      //   this.ChartPendingDetails = uploadinfo.data.candidateStatusCountDto;
      //   //console.log(this.ChartPendingDetails);
      //   let data = [];
      //   for (let i = 0; i < this.ChartPendingDetails.length; i++) {
      //     let obj = {};
      //     obj = this.ChartPendingDetails[i].statusName;
      //     data.push({
      //       name: this.ChartPendingDetails[i].statusName,
      //       value: this.ChartPendingDetails[i].count,
      //       statcode: this.ChartPendingDetails[i].statusCode
      //     });
      //   }
      //   chart.data = data;
      // });

// Add and configure Series
      let pieSeries = chart.series.push(new am4charts.PieSeries());

      pieSeries.slices.template.tooltipText = "{category}: {value}";
      pieSeries.labels.template.disabled = true;
      pieSeries.dataFields.value = "value";
      pieSeries.dataFields.category = "name";
      pieSeries.slices.template.stroke = am4core.color("#fff");
      pieSeries.slices.template.strokeWidth = 2;
      pieSeries.slices.template.strokeOpacity = 1;

// This creates initial animation
      pieSeries.hiddenState.properties.opacity = 1;
      pieSeries.hiddenState.properties.endAngle = -90;
      pieSeries.hiddenState.properties.startAngle = -90;
      pieSeries.legendSettings.itemValueText = "[bold]{value}[/bold]";
      pieSeries.colors.list = [
        am4core.color("#FF8E00"),
        am4core.color("#ffd400"),
        am4core.color("#fd352c"),
        am4core.color("#08e702"),
        am4core.color("#9c27b0"),
        am4core.color("#021aee"),
        am4core.color("#00bd77"),
        am4core.color("#ff0052"),
      ];

// var rgm = new am4core.RadialGradientModifier();
// rgm.brightnesses.push(-0.3, -0.3, -0.1, 0, - 0.1);
// pieSeries.slices.template.fillModifier = rgm;
// pieSeries.slices.template.strokeModifier = rgm;
// pieSeries.slices.template.strokeWidth = 0;

      pieSeries.slices.template.events.on('hit', (e) => {
        const getchartData = e.target._dataItem as any;
        const statuscodes = getchartData._dataContext.statcode;
        this.adminDashboard.setPendingDetailsStatCode(statuscodes);
        window.location.reload();
      });
      chart.legend.itemContainers.template.events.on("hit", (ev) => {
        const getchartData = ev.target._dataItem as any;
        const statuscodes = getchartData._label._dataItem._dataContext.statcode;
        this.adminDashboard.setPendingDetailsStatCode(statuscodes);
        window.location.reload();
      });
      pieSeries.slices.template.cursorOverStyle = am4core.MouseCursorStyle.pointer;
    });

  }

  ngOnDestroy() {
    this.zone.runOutsideAngular(() => {
      if (this.chart) {
        this.chart.dispose();
      }
    });
  }

  sendinvitation() {
    this.patchUserValues();
    return this.adminDashboard.saveInvitationSent(this.formSendInvitation.value).subscribe((result: any) => {
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

  reInvite() {
    this.reInvitePatchValues();
    return this.adminDashboard.saveInvitationSent(this.formSendInvitation.value).subscribe((result: any) => {
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


  activeInactive(referenceNo: any) {
    return this.adminDashboard.putAgentStat(referenceNo).subscribe((result: any) => {
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

  childCheck(e: any) {
    var sid = e.target.id;
    if (e.target.checked) {
      this.tmp.push(sid);
    } else {
      this.tmp.splice($.inArray(sid, this.tmp), 1);
    }
  }

  childCheckselected(sid: any) {
    this.tmp.push(sid);
  }

  selectAll(e: any) {
    if (e.target.checked) {
      $(".childCheck").prop('checked', true);
      var cboxRolesinput = $('.childCheck');
      var arrNumber: any = [];
      $.each(cboxRolesinput, function (idx, elem) {
        arrNumber.push($(this).val());
      });

      this.tmp = arrNumber;
      //console.log(this.tmp);
    } else {
      $(".childCheck").prop('checked', false);
    }

  }

//*****************UPDATE CANDIDATE*****************//
  openModal(modalData: any, userId: any) {
    this.modalService.open(modalData, {
      centered: true,
      backdrop: 'static'
    });
    this.adminDashboard.getCandidateDetails(userId).subscribe((result: any) => {
      this.getCandidate = result.data;
      this.updateCandidate.patchValue({
        candidateName: this.getCandidate.candidateName,
        applicantId: this.getCandidate.applicantId,
        createdByUserFirstName: this.getCandidate.createdByUserFirstName,
        candidateCode: this.getCandidate.candidateCode,
        contactNumber: this.getCandidate.contactNumber,
        emailId: this.getCandidate.emailId
      });
    });
  }

  onSubmit(updateCandidate: FormGroup) {
    if (this.updateCandidate.valid) {
      this.adminDashboard.putCandidateData(this.updateCandidate.value).subscribe((result: any) => {
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
        title: "Please enter the required information",
        icon: 'warning'
      })
    }
  }


}
