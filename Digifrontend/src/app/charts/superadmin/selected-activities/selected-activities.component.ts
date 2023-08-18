import { Component, NgZone, AfterViewInit, OnDestroy, OnInit } from '@angular/core';
import * as am4core from "@amcharts/amcharts4/core";
import * as am4charts from "@amcharts/amcharts4/charts";
import am4themes_animated from "@amcharts/amcharts4/themes/animated";
import { SuperadminDashboardService } from 'src/app/services/superadmin-dashboard.service';
import { CustomerService } from 'src/app/services/customer.service';
import { Subscription } from 'rxjs';
import { LoaderService } from 'src/app/services/loader.service';
am4core.useTheme(am4themes_animated);

@Component({
  selector: 'app-selected-activities',
  templateUrl: './selected-activities.component.html',
  styleUrls: ['./selected-activities.component.scss']
})
export class SelectedActivitiesComponent implements OnInit {
  private chart: any=am4charts.XYChart;
  selectedActivitiesData:any=[];
  getCustID:any=[];
  selectedCustId: number=0;
  SAduration: number=0;
  constructor(private zone: NgZone, private superadminDB: SuperadminDashboardService,
    private customers:CustomerService, public loaderService:LoaderService) {
      this.customers.getCustomersBill().subscribe((data: any)=>{
        this.getCustID=data.data;
      })
  }
  getCustomerData(custId:any){
    this.selectedCustId = custId;
    this.loadCharts();
    setTimeout(() =>{
      this.loaderService.hide();
    },1500);
  }
  saDurationFilter(duration:any){
    this.SAduration = duration;
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
      let chart = am4core.create("selectedActivities", am4charts.PieChart);
      chart.innerRadius = am4core.percent(50);
      chart.legend = new am4charts.Legend();
      chart.legend.maxHeight = 290;
      chart.legend.scrollable = true;
      chart.legend.itemContainers.template.paddingTop = 4;
      chart.legend.itemContainers.template.paddingBottom = 4;
      chart.legend.fontSize = 13;
      chart.legend.useDefaultMarker = true;
      let marker:any = chart.legend.markers.template.children.getIndex(0);
      marker.cornerRadius(12, 12, 12, 12);
      marker.strokeWidth = 3;
      marker.strokeOpacity = 1;
      marker.stroke = am4core.color("#000");
      
      chart.logo.disabled = true;
      chart.legend.position = "right";
      chart.padding(0, 0, 0, 0);
      chart.paddingRight = 0;

      var fromDate:any = localStorage.getItem('dbFromDate');
      var toDate:any = localStorage.getItem('dbToDate');
      let filterData = {
        'fromDate': fromDate,
        'toDate': toDate,
        'organizationId': this.selectedCustId
      }
     
      this.superadminDB.getActivityDetails(filterData).subscribe((uploadinfo: any)=>{
        this.selectedActivitiesData=uploadinfo.data.candidateStatusCountDto;
        let data = [];
        for (let i = 0; i < this.selectedActivitiesData.length; i++) {
          let obj={};
          obj=this.selectedActivitiesData[i].statusName;
          data.push({name: this.selectedActivitiesData[i].statusName, value: this.selectedActivitiesData[i].count});
        }
        chart.data = data;
      });
      
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
