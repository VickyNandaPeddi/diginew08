import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ChartsRoutingModule } from './charts-routing.module';
import { ChartsComponent } from './charts.component';
import { OrgadminUploaddetailsComponent } from './orgadmin-uploaddetails/orgadmin-uploaddetails.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ReportDeliveryDetailsComponent } from './report-delivery-details/report-delivery-details.component';
import { PendingDetailsComponent } from './pending-details/pending-details.component';
import { AgentDetailsComponent } from './superadmin/agent-details/agent-details.component';
import { SelectedActivitiesComponent } from './superadmin/selected-activities/selected-activities.component';
import { CustomerActivitiesComponent } from './superadmin/customer-activities/customer-activities.component';
import { RatePerItemComponent } from './superadmin/rate-per-item/rate-per-item.component';
import { RateperreportComponent } from './superadmin/rateperreport/rateperreport.component';
import { ActivityComparisionComponent } from './superadmin/activity-comparision/activity-comparision.component';


@NgModule({
  declarations: [
    ChartsComponent,
    OrgadminUploaddetailsComponent,
    ReportDeliveryDetailsComponent,
    PendingDetailsComponent,
    AgentDetailsComponent,
    SelectedActivitiesComponent,
    CustomerActivitiesComponent,
    RatePerItemComponent,
    RateperreportComponent,
    ActivityComparisionComponent
  ],
  imports: [
    CommonModule,
    ChartsRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [OrgadminUploaddetailsComponent, ReportDeliveryDetailsComponent, PendingDetailsComponent, 
    AgentDetailsComponent, SelectedActivitiesComponent, CustomerActivitiesComponent, RatePerItemComponent,
    RateperreportComponent, ActivityComparisionComponent]
})
export class ChartsModule { }
