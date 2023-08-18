import { Component, NgZone, AfterViewInit, OnDestroy, OnInit } from '@angular/core';
import * as am4core from "@amcharts/amcharts4/core";
import { CustomerService } from '../../services/customer.service';
import * as am4charts from "@amcharts/amcharts4/charts";
import am4themes_animated from "@amcharts/amcharts4/themes/animated";
import { OrgadminDashboardService } from 'src/app/services/orgadmin-dashboard.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import * as  _ from 'lodash';
am4core.useTheme(am4themes_animated);

@Component({
  selector: 'app-conventional-pending-upload',
  templateUrl: './conventional-pending-upload.component.html',
  styleUrls: ['./conventional-pending-upload.component.scss']
})
export class ConventionalPendingUploadComponent implements OnInit {
  private chart: am4charts.XYChart | undefined;
  getReportDeliveryStatCodes: any;
  CharReportDelivery: any=[];
  candidateData: any;
  CharReportDeliveryData: any=[];
  containerStat:boolean = false;
  stat_linkAdminApproval:boolean = false;
  stat_linkCandidateReport:boolean = false;
  stat_linkConventional:boolean = false;
  finalreport:boolean=false;
  startdownload:boolean=false;
  startpredownload:boolean=false;
  isCBadmin:boolean = false;
  constructor(private zone: NgZone, private orgadmin:OrgadminDashboardService,private customers:CustomerService,
    private router: Router) {
    this.getReportDeliveryStatCodes = this.orgadmin.getReportDeliveryStatCode();
    if(this.getReportDeliveryStatCodes){
      var userId:any = localStorage.getItem('userId');
      var fromDate:any = localStorage.getItem('dbFromDate');
      var toDate:any = localStorage.getItem('dbToDate');
      let filterData = {
        'userId': userId,
        'fromDate': fromDate,
        'toDate': toDate,
        'status': this.getReportDeliveryStatCodes
      }
      this.orgadmin.getChartDetails(filterData).subscribe((data: any)=>{
        this.CharReportDelivery=data.data.candidateDtoList;
        console.log("Before : ", this.CharReportDelivery)
        for(let i=0; i<this.CharReportDelivery.length; i++) {
          // let index = _.findIndex(this.CharReportDelivery[i].contentDTOList, {contentSubCategory: 'PRE_APPROVAL'});
          // this.CharReportDelivery[i].pre_approval_content_id = (index != -1) ? this.CharReportDelivery[i].contentDTOList[index].contentId : -1;
          // console.log('Lavanyapre',index)
          let final = this.CharReportDelivery[i].contentDTOList;
          for (let i=0; i<final.length; i++){
            if(final[i].contentSubCategory=="FINAL"){
              this.finalreport = true
              console.log('Lavanyafinal',final[i].contentSubCategory)
            }
          }

        }


        console.log("After : ", this.CharReportDelivery)
        //console.log(data);
      });

    }
  }

  ngAfterViewInit() {
    setTimeout(() =>{
      this.ngOnDestroy();
      this.loadCharts();
    },50);
  }


  loadCharts(){
    this.zone.runOutsideAngular(() => {
      let chart = am4core.create("chartReportDelivery", am4charts.PieChart);
      chart.innerRadius = am4core.percent(50);
      chart.legend = new am4charts.Legend();

      chart.legend.itemContainers.template.paddingTop = 4;
      chart.legend.itemContainers.template.paddingBottom = 4;
      chart.legend.fontSize = 13;
      chart.legend.useDefaultMarker = true;
      let marker:any = chart.legend.markers.template.children.getIndex(0);
      marker.cornerRadius(12, 12, 12, 12);
      marker.strokeWidth = 3;
      marker.strokeOpacity = 1;
      marker.stroke = am4core.color("#000");

      chart.legend.maxHeight = 210;
      chart.legend.scrollable = true;
      chart.legend.position = "right";
      chart.logo.disabled = true;
      chart.padding(10, 0, 0, 0);
      chart.radius = am4core.percent(95);
      chart.paddingRight = 0;

      var userId:any = localStorage.getItem('userId');
      var fromDate:any = localStorage.getItem('dbFromDate');
      var toDate:any = localStorage.getItem('dbToDate');
      let filterData = {
        'userId': userId,
        'fromDate': fromDate,
        'toDate': toDate
      }

      this.orgadmin.getReportDeliveryDetails(filterData).subscribe((uploadinfo: any)=>{
        this.CharReportDeliveryData=uploadinfo.data.candidateStatusCountDto;
        //console.log(this.CharReportDeliveryData);
        let data = [];
        for (let i = 0; i < this.CharReportDeliveryData.length; i++) {
          data.push({name: this.CharReportDeliveryData[i].statusName, value: this.CharReportDeliveryData[i].count, statcode: this.CharReportDeliveryData[i].statusCode });
        }
        chart.data = data;
      });

// Add and configure Series
let pieSeries = chart.series.push(new am4charts.PieSeries());
pieSeries.slices.template.stroke = am4core.color("#fff0");
pieSeries.slices.template.strokeWidth = 0;
pieSeries.slices.template.strokeOpacity = 0;

pieSeries.slices.template.tooltipText = "{category}: {value}";
pieSeries.labels.template.disabled = true;
pieSeries.dataFields.value = "value";
pieSeries.dataFields.category = "name";0

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
];

// var rgm = new am4core.RadialGradientModifier();
// rgm.brightnesses.push(-0.2, -0.2, -0.1, 0, - 0.1);
// pieSeries.slices.template.fillModifier = rgm;
// pieSeries.slices.template.strokeModifier = rgm;
// pieSeries.slices.template.strokeWidth = 0;


//pieSeries.slices.template.events.on("hit", myFunction, this);
pieSeries.slices.template.events.on('hit', (e) => {
  const getchartData = e.target._dataItem as any;
  const statuscodes = getchartData._dataContext.statcode;
  //console.log(statuscodes);
  this.orgadmin.setReportDeliveryStatCode(statuscodes);
  window.location.reload();
});
chart.legend.itemContainers.template.events.on("hit", (ev) => {
  const getchartData = ev.target._dataItem as any;
  const statuscodes = getchartData._label._dataItem._dataContext.statcode;
  this.orgadmin.setReportDeliveryStatCode(statuscodes);
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

  ngOnInit(): void {
    const isCBadminVal = localStorage.getItem('roles');
    if(this.getReportDeliveryStatCodes){
      if(this.getReportDeliveryStatCodes === "PENDINGAPPROVAL"){
        $(".dbtabheading").text("QC Pending");
        this.stat_linkAdminApproval = true;
        this.stat_linkCandidateReport = false;
      }else if(this.getReportDeliveryStatCodes === "INTERIMREPORT"){
        $(".dbtabheading").text("Interim Report");
        this.stat_linkAdminApproval = false;
        this.stat_linkCandidateReport = true;
      }else if(this.getReportDeliveryStatCodes === "FINALREPORT"){
        $(".dbtabheading").text("Final Report");
        this.stat_linkAdminApproval = false;
        this.stat_linkCandidateReport = true;
      }
      this.containerStat = true;
      //isCBadmin required for drilldown dashboard at Superadmin
      if(isCBadminVal == '"ROLE_CBADMIN"'){
        this.isCBadmin = true;
        this.stat_linkAdminApproval = false;
      }else{
        this.isCBadmin = false;
      }
    }
  }


  linkPendingApproval(candidateCode:any){
    console.log(candidateCode,"-----------------------------------------------");
    const billUrl = 'admin/C-Pending-Approval/'+candidateCode;
    this.router.navigate([billUrl]);
  }
  finalapproval(candidateCode:any){
    console.log(candidateCode,"-----------------------------------------------");
    const billUrl = 'admin/CV-Final-Approval/'+candidateCode;
    this.router.navigate([billUrl]);
  }
  downloadPreApprovalReport(candidate: any) {
    console.log(candidate);
    for(let i=0; i<this.CharReportDelivery.length; i++) {
      let index = _.findIndex(this.CharReportDelivery[i].contentDTOList, {contentSubCategory: 'PRE_APPROVAL'});
      this.CharReportDelivery[i].pre_approval_content_id = (index != -1) ? this.CharReportDelivery[i].contentDTOList[index].contentId : -1;
      console.log('Lavanyafinal',index)
      this.startpredownload=true

    }
    if(this.startpredownload==true){
      if(candidate.pre_approval_content_id != -1) {
        console.log(candidate,"-----if--------");
        this.orgadmin.getSignedURLForContent(candidate.pre_approval_content_id).subscribe((url: any)=>{
          // window.location.href = url;
          window.open(url.data, '_blank');
        });
      }
    }
  }

  downloadFinalReport(candidate: any) {
    console.log("final");
    for(let i=0; i<this.CharReportDelivery.length; i++) {
      let index = _.findIndex(this.CharReportDelivery[i].contentDTOList, {contentSubCategory: 'FINAL'});
      this.CharReportDelivery[i].pre_approval_content_id = (index != -1) ? this.CharReportDelivery[i].contentDTOList[index].contentId : -1;
      console.log('Lavanyafinal',index)
      this.startdownload=true

    }
    if(this.startdownload==true){
      if(candidate.pre_approval_content_id != -1) {
        console.log(candidate,"-----if--------");
        this.orgadmin.getSignedURLForContent(candidate.pre_approval_content_id).subscribe((url: any)=>{
          // window.location.href = url;
          window.open(url.data, '_blank');
        });
      }
    }
  }

}






