import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AdminGuard} from '../services/admin.guard';
import {ErrorComponent} from '../shared/error/error.component';
import {AddCustomerComponent} from './add-customer/add-customer.component';
import {AdminCReportApprovalComponent} from './admin-c-report-approval/admin-c-report-approval.component';
import {AdminSetupComponent} from './admin-setup/admin-setup.component';
import {AdminComponent} from './admin.component';
import {CandidateMgmtComponent} from './candidate-mgmt/candidate-mgmt.component';
import {CustomerEditComponent} from './customer-edit/customer-edit.component';
import {CustomerListComponent} from './customer-list/customer-list.component';
import {CustomerUtilAgentComponent} from './customer-util-agent/customer-util-agent.component';
import {CustomerUtilCandidatesComponent} from './customer-util-candidates/customer-util-candidates.component';
import {CustomerUtilizationComponent} from './customer-utilization/customer-utilization.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {EkycreportComponent} from './ekycreport/ekycreport.component';
import {MyprofileComponent} from './myprofile/myprofile.component';
import {OrgadminDashboardComponent} from './orgadmin-dashboard/orgadmin-dashboard.component';
import {OrgadminUsermgmtComponent} from './orgadmin-usermgmt/orgadmin-usermgmt.component';
import {TestComponent} from './test/test.component';
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
} from "./candidatesubmitted-conventional/candidatesubmitted-conventional.component";
import {VendorCApprovalComponent} from './vendor-c-approval/vendor-c-approval.component';
import {
  ConventionalVendorFinalReportComponent
} from './conventional-vendor-final-report/conventional-vendor-final-report.component';
import {
  ConventionalCReportApprovalComponent
} from './conventional-c-report-approval/conventional-creport-approval/conventional-creport-approval.component';

const routes: Routes = [
  {
    path: '', component: AdminComponent,
    children: [
      {
        path: 'cReportApprovalConventional',
        component: ConventionalCReportApprovalComponent,
        canActivate: [AdminGuard],
        data: {roles: ['ROLE_CBADMIN', 'ROLE_PARTNERADMIN', 'ROLE_ADMIN', 'ROLE_AGENTSUPERVISOR', 'ROLE_AGENTHR']}
      },
      {
        path: '',
        redirectTo: 'admindashboard',
        pathMatch: "full",
        component: DashboardComponent,
        canActivate: [AdminGuard],
        data: {roles: ['ROLE_CBADMIN']}
      },
      {
        path: 'admindashboard',
        component: DashboardComponent,
        canActivate: [AdminGuard],
        data: {roles: ['ROLE_CBADMIN']}
      },
      {path: 'addcust', component: AddCustomerComponent, canActivate: [AdminGuard], data: {roles: ['ROLE_CBADMIN']}},
      // {
      //   path: 'vendormgmt/:userId',
      //   component: VendorMgmtComponent,
      //   canActivate: [AdminGuard],
      //   data: {roles: ['ROLE_ADMIN']}
      // },
      // {path: 'vendormgmt', component: VendorMgmtComponent, canActivate: [AdminGuard], data: {roles: ['ROLE_ADMIN']}},
      {path: 'addvendor', component: AddVendorComponent, canActivate: [AdminGuard], data: {roles: ['ROLE_ADMIN']}},
      {
        path: 'customerUtilization',
        component: CustomerUtilizationComponent,
        canActivate: [AdminGuard],
        data: {roles: ['ROLE_CBADMIN', 'ROLE_ADMIN', 'ROLE_PARTNERADMIN', 'ROLE_AGENTSUPERVISOR', 'ROLE_AGENTHR']}
      },
      {
        path: 'customerUtilizationAgent',
        component: CustomerUtilAgentComponent,
        canActivate: [AdminGuard],
        data: {roles: ['ROLE_CBADMIN', 'ROLE_ADMIN', 'ROLE_PARTNERADMIN', 'ROLE_AGENTSUPERVISOR', 'ROLE_AGENTHR']}
      },
      {
        path: 'customerUtilizationCandidate',
        component: CustomerUtilCandidatesComponent,
        canActivate: [AdminGuard],
        data: {roles: ['ROLE_CBADMIN', 'ROLE_ADMIN', 'ROLE_PARTNERADMIN', 'ROLE_AGENTSUPERVISOR', 'ROLE_AGENTHR']}
      },
      {
        path: 'ekycReport',
        component: EkycreportComponent,
        canActivate: [AdminGuard],
        data: {roles: ['ROLE_CBADMIN', 'ROLE_ADMIN', 'ROLE_PARTNERADMIN', 'ROLE_AGENTSUPERVISOR', 'ROLE_AGENTHR']}
      },
      {path: 'custlist', component: CustomerListComponent, canActivate: [AdminGuard], data: {roles: ['ROLE_CBADMIN']}},
      {
        path: 'custedit/:organizationId',
        component: CustomerEditComponent,
        canActivate: [AdminGuard],
        data: {roles: ['ROLE_CBADMIN']}
      },
      {path: 'adminsetup', component: AdminSetupComponent, canActivate: [AdminGuard], data: {roles: ['ROLE_CBADMIN']}},
      {path: 'test', component: TestComponent},
      {
        path: 'orgadminDashboard',
        component: OrgadminDashboardComponent,
        canActivate: [AdminGuard],
        data: {roles: ['ROLE_CBADMIN', 'ROLE_ADMIN', 'ROLE_PARTNERADMIN', 'ROLE_AGENTSUPERVISOR', 'ROLE_AGENTHR', 'ROLE_VENDOR']}
      },
      {
        path: 'usermgmt',
        component: OrgadminUsermgmtComponent,
        canActivate: [AdminGuard],
        data: {roles: ['ROLE_ADMIN', 'ROLE_PARTNERADMIN', 'ROLE_AGENTSUPERVISOR']}
      },
      {
        path: 'candidateMgmt',
        component: CandidateMgmtComponent,
        canActivate: [AdminGuard],
        data: {roles: ['ROLE_CBADMIN', 'ROLE_ADMIN', 'ROLE_PARTNERADMIN', 'ROLE_AGENTSUPERVISOR', 'ROLE_AGENTHR']}
      },
      // {
      //   path: 'cFinalReport/:candidateCode',
      //   component: AdminFinalReportComponent,
      //   canActivate: [AdminGuard],
      //   data: {roles: ['ROLE_CBADMIN', 'ROLE_PARTNERADMIN', 'ROLE_ADMIN', 'ROLE_AGENTSUPERVISOR', 'ROLE_AGENTHR']}
      // },
      {
        path: 'cReportApproval/:candidateCode',
        component: AdminCReportApprovalComponent,
        canActivate: [AdminGuard],
        data: {roles: ['ROLE_CBADMIN', 'ROLE_PARTNERADMIN', 'ROLE_ADMIN', 'ROLE_AGENTSUPERVISOR', 'ROLE_AGENTHR']}
      },
      {
        path: 'myProfile',
        component: MyprofileComponent,
        canActivate: [AdminGuard],
        data: {roles: ['ROLE_CBADMIN', 'ROLE_PARTNERADMIN', 'ROLE_ADMIN', 'ROLE_AGENTSUPERVISOR', 'ROLE_AGENTHR', 'ROLE_VENDOR']}
      },
      {
        path: 'ConventionalDashboard',
        component: VendorDashboardComponent,
        canActivate: [AdminGuard],
        data: {roles: ['ROLE_ADMIN', 'ROLE_VENDOR']}
      },

      {
        path: 'uploadvendorcheck',
        component: UploadVendocheckComponent,
        canActivate: [AdminGuard],
        data: {roles: ['ROLE_VENDOR']}
      },
      {
        path: 'conventionalVendorcheck',
        component: ConventionalVendorcheckDashboardComponent,
        canActivate: [AdminGuard],
        data: {roles: ['ROLE_ADMIN']}
      },
      {
        path: 'pendingconventional',
        component: PendingConventionalComponent,
        canActivate: [AdminGuard],
        data: {roles: ['ROLE_ADMIN']}
      },
      {
        path: 'BGVverification',
        component: BGVVerificationTypeComponent,
        canActivate: [AdminGuard],
        data: {roles: ['ROLE_ADMIN']}
      },
      {
        path: 'C-Pending-Approval/:candidateCode',
        component: VendorCApprovalComponent,
        canActivate: [AdminGuard],
        data: {roles: ['ROLE_ADMIN']}
      },
      {
        path: 'CV-Final-Approval/:candidateCode',
        component: ConventionalVendorFinalReportComponent,
        canActivate: [AdminGuard],
        data: {roles: ['ROLE_ADMIN']}
      },
      {
        path: 'Dashboard',
        component: CandidatesubmittedConventionalComponent,
        canActivate: [AdminGuard],
        data: {roles: ['ROLE_ADMIN']}
      },
      {path: 'error', component: ErrorComponent},
      {path: '**', redirectTo: 'error'}
    ]
  }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {
}
