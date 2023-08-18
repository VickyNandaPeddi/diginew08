import { Component, NgZone, AfterViewInit, OnDestroy, OnInit } from '@angular/core';
import * as am4core from "@amcharts/amcharts4/core";
import * as am4charts from "@amcharts/amcharts4/charts";
import am4themes_animated from "@amcharts/amcharts4/themes/animated";
import { SuperadminDashboardService } from 'src/app/services/superadmin-dashboard.service';
am4core.useTheme(am4themes_animated);
@Component({
  selector: 'app-agent-details',
  templateUrl: './agent-details.component.html',
  styleUrls: ['./agent-details.component.scss']
})
export class AgentDetailsComponent implements OnInit {
  private chart: am4charts.XYChart | undefined;
  CharReportDelivery:any=[];
  constructor(private zone: NgZone, private superadminDB: SuperadminDashboardService) { }
  ngAfterViewInit() {
    setTimeout(() =>{
      this.ngOnDestroy();
      this.loadCharts();
    },50);
  }

  loadCharts(){
    this.zone.runOutsideAngular(() => {
      let chart = am4core.create("agentDetails", am4charts.XYChart);
      chart.logo.disabled = true;
      chart.padding(0, 0, 0, 0);
      chart.paddingRight = 0;
      this.superadminDB.getTestData().subscribe((uploadinfo: any)=>{
        this.CharReportDelivery=uploadinfo;
        //console.log(this.CharReportDelivery);
        let data = [];
        for (let i = 0; i < this.CharReportDelivery.length; i++) {
          let obj={};
          obj=this.CharReportDelivery[i].statusName;
          data.push({name: this.CharReportDelivery[i].id, value: this.CharReportDelivery[i].id});
        }
        chart.data = data;
      });
      
// Add and configure Series
var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
categoryAxis.dataFields.category = "name";
categoryAxis.renderer.minGridDistance = 30;
categoryAxis.renderer.labels.template.fill = am4core.color("#000");
categoryAxis.renderer.labels.template.fontSize = 13;

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
 chart.colors.list = [
  am4core.color("#fde05d"),
  am4core.color("#2E59D9"),
  am4core.color("#21a990"),
  am4core.color("#6ba6fe"),
  am4core.color("#F72525"),
];

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
    //am4core.options.autoDispose = true;

  }

}
