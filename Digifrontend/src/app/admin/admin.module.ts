import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {AdminRoutingModule} from './admin-routing.module';
import {AdminComponent} from './admin.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {AdminHeaderComponent} from './admin-header/admin-header.component';
import {AdminFooterComponent} from './admin-footer/admin-footer.component';
import {AdminSidenavComponent} from './admin-sidenav/admin-sidenav.component';
import {TestComponent} from './test/test.component';
import {AddCustomerComponent} from './add-customer/add-customer.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CustomerListComponent} from './customer-list/customer-list.component';
import {HttpClientModule} from '@angular/common/http';
import {AdminSetupComponent} from './admin-setup/admin-setup.component';
import {CustomerEditComponent} from './customer-edit/customer-edit.component';
import {RouterModule} from '@angular/router';
import {OrgadminUsermgmtComponent} from './orgadmin-usermgmt/orgadmin-usermgmt.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {OrgadminDashboardComponent} from './orgadmin-dashboard/orgadmin-dashboard.component';
import {ChartsModule} from '../charts/charts.module';
import {AdminCReportApprovalComponent} from './admin-c-report-approval/admin-c-report-approval.component';
import {MyprofileComponent} from './myprofile/myprofile.component';
import {CustomerUtilizationComponent} from './customer-utilization/customer-utilization.component';
import {CustomerUtilAgentComponent} from './customer-util-agent/customer-util-agent.component';
import {CustomerUtilCandidatesComponent} from './customer-util-candidates/customer-util-candidates.component';
import {EkycreportComponent} from './ekycreport/ekycreport.component';
import {CandidateMgmtComponent} from './candidate-mgmt/candidate-mgmt.component';
import {AgGridModule} from 'ag-grid-angular';

import {AddVendorComponent} from './add-vendor/add-vendor.component';
import {VendorDashboardComponent} from './vendor-dashboard/vendor-dashboard.component';
import {UploadVendocheckComponent} from './upload-vendocheck/upload-vendocheck.component';
import {
  ConventionalVendorcheckDashboardComponent
} from './conventional-vendorcheck-dashboard/conventional-vendorcheck-dashboard.component';
import {PendingConventionalComponent} from './pending-conventional/pending-conventional.component';
import {BGVVerificationTypeComponent} from './bgv-verification-type/bgv-verification-type.component';
import {
  CandidatesubmittedConventionalComponent
} from './candidatesubmitted-conventional/candidatesubmitted-conventional.component';
import {VendorCApprovalComponent} from './vendor-c-approval/vendor-c-approval.component';
import {
  ConventionalVendorFinalReportComponent
} from './conventional-vendor-final-report/conventional-vendor-final-report.component';
import {ConventionalPendingUploadComponent} from './conventional-pending-upload/conventional-pending-upload.component';
import {
  ConventionalCReportApprovalComponent
} from './conventional-c-report-approval/conventional-creport-approval/conventional-creport-approval.component';

@NgModule({
  declarations: [
    AdminComponent,
    DashboardComponent,
    AdminHeaderComponent,
    AdminFooterComponent,
    AdminSidenavComponent,
    TestComponent,
    AddCustomerComponent,
    CustomerListComponent,
    AdminSetupComponent,
    CustomerEditComponent,
    OrgadminUsermgmtComponent,
    OrgadminDashboardComponent,

    // AdminFinalReportComponent,
    AdminCReportApprovalComponent,
    MyprofileComponent,
    CustomerUtilizationComponent,
    CustomerUtilAgentComponent,
    CustomerUtilCandidatesComponent,
    EkycreportComponent,
    CandidateMgmtComponent,
    // VendorMgmtComponent,
    AddVendorComponent,
    VendorDashboardComponent,
    UploadVendocheckComponent,
    ConventionalVendorcheckDashboardComponent,
    PendingConventionalComponent,
    BGVVerificationTypeComponent,
    CandidatesubmittedConventionalComponent,
    VendorCApprovalComponent,
    ConventionalVendorFinalReportComponent,
    ConventionalPendingUploadComponent,
    ConventionalCReportApprovalComponent,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    NgbModule,
    ChartsModule,
    AgGridModule.withComponents([])
  ]
})
export class AdminModule { }
