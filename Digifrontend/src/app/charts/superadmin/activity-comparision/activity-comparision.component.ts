import { Component, NgZone, AfterViewInit, OnDestroy, OnInit } from '@angular/core';
import * as am4core from "@amcharts/amcharts4/core";
import * as am4charts from "@amcharts/amcharts4/charts";
import am4themes_animated from "@amcharts/amcharts4/themes/animated";
import { SuperadminDashboardService } from 'src/app/services/superadmin-dashboard.service';
import { CustomerService } from 'src/app/services/customer.service';
import { LoaderService } from 'src/app/services/loader.service';
am4core.useTheme(am4themes_animated);

@Component({
  selector: 'app-activity-comparision',
  templateUrl: './activity-comparision.component.html',
  styleUrls: ['./activity-comparision.component.scss']
})
export class ActivityComparisionComponent implements OnInit {
  private chart: any=am4charts.XYChart;
  activityComparisionData:any=[];
  getCustID:any=[];
  getStatus:any=[];
  selectedCustId:any=[0];
  SAactivityFilter:any=['NEWUPLOAD'];
  constructor(private zone: NgZone, private superadminDB: SuperadminDashboardService,
    private customers:CustomerService, public loaderService:LoaderService) { 
      this.customers.getCustomersBill().subscribe((data: any)=>{
        this.getCustID=data.data;
      });
      this.customers.getAllStatus().subscribe((data: any)=>{
        this.getStatus=data.data;
        //console.log(this.getStatus);
      })
    }
    getCustomerData(custId:any){
      this.selectedCustId = [];
      this.selectedCustId.push(custId);
      this.loadCharts();
      setTimeout(() =>{
        this.loaderService.hide();
      },1500);
    }
    activityFilter(activity:any){
      this.SAactivityFilter = [];
      this.SAactivityFilter.push(activity);
      this.loadCharts();
      setTimeout(() =>{
        this.loaderService.hide();
      },1500);
    }
    ngAfterViewInit() {
      setTimeout(() =>{
        this.ngOnDestroy();
        this.loadCharts();
      },50);
    }
    loadCharts(){
      this.zone.runOutsideAngular(() => {
        let chart = am4core.create("activityComparision", am4charts.XYChart);
        chart.colors.list = [
          am4core.color("#3177eb"),
        ];
        chart.logo.disabled = true;
        chart.padding(10, 0, 0, 0);
        chart.paddingRight = 0;

        var fromDate:any = localStorage.getItem('dbFromDate');
        var toDate:any = localStorage.getItem('dbToDate');
        let filterData = {
          'fromDate': fromDate,
          'toDate': toDate,
          'organizationIds': this.selectedCustId,
          'activityCode': this.SAactivityFilter
        }
        
        this.superadminDB.getCompanyCountByActivity(filterData).subscribe((result: any)=>{
          this.activityComparisionData=result.data.candidateStatusCountDto;
          console.log(result);
          let data = [];
          for (let i = 0; i < this.activityComparisionData.length; i++) {
            data.push({
              name: this.activityComparisionData[i].organizationName, 
              value: this.activityComparisionData[i].count
            });
          }
          chart.data = data;
        });
        
  // Add and configure Series
  var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
  categoryAxis.dataFields.category = "name";
  categoryAxis.renderer.minGridDistance = 30;
  categoryAxis.renderer.labels.template.fill = am4core.color("#000");
  categoryAxis.renderer.labels.template.fontSize = 13;
  categoryAxis.renderer.labels.template.horizontalCenter = "right";
  categoryAxis.renderer.labels.template.verticalCenter = "middle";
  categoryAxis.renderer.labels.template.rotation = 270;
  
  let label = categoryAxis.renderer.labels.template;
  label.wrap = false;
  label.truncate = true;
  label.maxWidth = 90;
  
  var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
  valueAxis.renderer.grid.template.strokeDasharray = "4,4";
  
  // This creates initial animation
   // Create series
   var series = chart.series.push(new am4charts.ColumnSeries());
   series.dataFields.valueY = "value";
   series.dataFields.categoryX = "name";
   series.columns.template.propertyFields.fill = "color";
   series.columns.template.propertyFields.stroke = "color";
   series.columns.template.column.cornerRadiusTopLeft = 15;
   series.columns.template.column.cornerRadiusTopRight = 15;
   series.columns.template.tooltipText = "{categoryX}: [bold]{valueY}[/b]";
  
      });
     
  }
  
    ngOnDestroy() {
      this.zone.runOutsideAngular(() => {
        if (this.chart) {
          //this.chart.dispose();
        }
      });
    }
  ngOnInit(): void {
  }

}
