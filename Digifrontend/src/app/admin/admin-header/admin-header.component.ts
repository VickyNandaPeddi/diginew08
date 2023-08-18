import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from 'src/app/services/authentication.service';
import {OrgadminService} from 'src/app/services/orgadmin.service';
import {CandidateService} from "../../services/candidate.service";
import {dataLoader} from "@amcharts/amcharts4/core";
import * as buffer from "buffer";


import * as XLSX from 'xlsx';

@Component({
  selector: 'app-admin-header',
  templateUrl: './admin-header.component.html',
  styleUrls: ['./admin-header.component.scss']
})
export class AdminHeaderComponent implements OnInit {
  details: any;
  rolename: any;
  getRolePerMissionCodes: any = [];
  CustUtilizationReport_stat: boolean = false;
  eKycReport_stat: boolean = false;

  constructor(public authService: AuthenticationService,
              private router: Router, private orgadmin: OrgadminService, private candidateservice: CandidateService) {
  }

  ngOnInit(): void {
    this.details = this.authService.getuserName();
    this.rolename = this.authService.getroleName();

    this.orgadmin.getRolePerMissionCodes(localStorage.getItem('roles')).subscribe(
      (result: any) => {
        this.getRolePerMissionCodes = result.data;
        console.log("permissioncodes-->" + this.getRolePerMissionCodes);
        if (this.getRolePerMissionCodes) {
          if (this.getRolePerMissionCodes.includes('CUSTOMERUTILIZATIONREPORT')) {
            this.CustUtilizationReport_stat = true;
          }

          if (this.getRolePerMissionCodes.includes('E-KYCREPORT')) {
            this.eKycReport_stat = true;
          }

        }
      });
  }

  message: any;

  generateConventionalExcelReport() {
    this.candidateservice.generateDataForExcel()
      .subscribe((data:any) => {
        const link = document.createElement('a');
        link.href = 'data:application/vnd.ms-excel;base64,' + data.message;
        // @ts-ignore
        // link.href = 'data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64,' + encodeURIComponent(data.message);

        link.download = "ResponseExcel.xlsx";
        link.target = '_blank';
        link.click();

      });

  }

  openExcelFile(base64String: string, fileName: string) {

  }

  public isLoggedIn() {
    return this.authService.isLoggedIn();
  }

  public logout() {
    this.authService.logOut().subscribe(
      (response: any) => {
        if (response.outcome === true) {
          this.authService.clear();
          this.router.navigate(['./']);
        }
      }
    );
  }


}
