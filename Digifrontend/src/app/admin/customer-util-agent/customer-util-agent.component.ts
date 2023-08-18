import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { CustomerService } from 'src/app/services/customer.service';
import Swal from 'sweetalert2';
import { NgbCalendar, NgbDate } from '@ng-bootstrap/ng-bootstrap';
import * as XLSX from 'xlsx';
import { AuthenticationService } from 'src/app/services/authentication.service';
@Component({
  selector: 'app-customer-util-agent',
  templateUrl: './customer-util-agent.component.html',
  styleUrls: ['./customer-util-agent.component.scss']
})
export class CustomerUtilAgentComponent implements OnInit {
  pageTitle = 'Customer Utilization Report (Agent)';
  getAgentUtilizationReport: any=[];
  fromDate:any;
  toDate:any;
  setfromDate:any;
  settoDate:any;
  custId:any=0;
  agentId:any;
  getAgentID:any=[];
  getToday: NgbDate;
  getMinDate: any;
  utilizationReportClick = new FormGroup({
    fromDate: new FormControl('', Validators.required),
    toDate: new FormControl('', Validators.required),
    organizationIds: new FormControl('', Validators.required),
    statusCode: new FormControl('', Validators.required),
  });
  utilizationReportFilter = new FormGroup({
    fromDate: new FormControl('', Validators.required),
    toDate: new FormControl('', Validators.required),
    organizationIds: new FormControl('', Validators.required),
    agentIds: new FormControl('', Validators.required)
  });
  /*name of the excel-file which will be downloaded. */ 
  fileName= 'export.xlsx';  
  exportexcel(): void 
    {
       /* table id is passed over here */   
       let element = document.getElementById('excel-table'); 
       const ws: XLSX.WorkSheet =XLSX.utils.table_to_sheet(element);
       /* generate workbook and add the worksheet */
       const wb: XLSX.WorkBook = XLSX.utils.book_new();
       XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
       /* save to file */
       XLSX.writeFile(wb, this.fileName);
  } 
  constructor(private route: ActivatedRoute, private customers: CustomerService,
    private navrouter: Router,  calendar: NgbCalendar, public authService: AuthenticationService) {
      this.getToday = calendar.getToday();
     }

  ngOnInit(): void {
    let fromDate = this.route.snapshot.queryParamMap.get('fromDate');
    let toDate = this.route.snapshot.queryParamMap.get('toDate');
    const organizationIds = this.route.snapshot.queryParamMap.get('organizationIds');
    let organizationIdArray: any=[];
    organizationIdArray.push(organizationIds);
    this.custId = organizationIdArray;
    fromDate = fromDate!=null?fromDate:'';
    toDate = toDate!=null?toDate:'';

    this.utilizationReportClick.patchValue({
      fromDate: fromDate,
      toDate: toDate,
      organizationIds: organizationIdArray
     });

    this.customers.getCustomerUtilizationReportByAgent(this.utilizationReportClick.value).subscribe((data: any)=>{
      this.getAgentUtilizationReport=data.data.reportResponseDtoList;
      console.log(data);
      let getfromDate = data.data.fromDate.split('/');
      this.setfromDate = { day:+getfromDate[0],month:+getfromDate[1],year:+getfromDate[2]};
      this.getMinDate = this.setfromDate;

      let gettoDate = data.data.toDate.split('/');
      this.settoDate = { day:+gettoDate[0],month:+gettoDate[1],year:+gettoDate[2]};

      this.utilizationReportFilter.patchValue({
        fromDate: this.setfromDate,
        toDate: this.settoDate,
       });
      this.fromDate= data.data.fromDate!=null?data.data.fromDate:'';
      this.toDate=  data.data.toDate!=null?data.data.toDate:'';

    });

    this.customers.getAgentList(organizationIds).subscribe((data: any)=>{
      this.getAgentID=data.data;
    });

  }
  onfromDate(event:any) {
    let year = event.year;
    let month = event.month <= 9 ? '0' + event.month : event.month;;
    let day = event.day <= 9 ? '0' + event.day : event.day;;
    let finalDate = day + "/" + month + "/" + year;
    this.fromDate = finalDate;
    this.getMinDate = { day:+day,month:+month,year:+year};
   }
   ontoDate(event:any) {
    let year = event.year;
    let month = event.month <= 9 ? '0' + event.month : event.month;;
    let day = event.day <= 9 ? '0' + event.day : event.day;;
    let finalDate = day + "/" + month + "/" + year;
    this.toDate = finalDate;
   }

  getagentId(id:any){
    this.agentId = id;
  }

  getData(agentId:any, statusCode:any){
    let organizationIds: any=[];
    organizationIds.push(this.custId);

    let agentIdsArray: any=[];
    agentIdsArray.push(agentId);

    this.utilizationReportClick.patchValue({
      fromDate: this.fromDate,
      toDate: this.toDate,
      organizationIds: organizationIds,
      statusCode: statusCode,
      agentIds: agentIdsArray
     });
     const navURL = 'admin/customerUtilizationCandidate/';
      this.navrouter.navigate([navURL],{ queryParams: 
        { fromDate: this.fromDate,
          toDate: this.toDate,
          organizationIds: organizationIds,
          statusCode: statusCode,
          agentIds: agentIdsArray,
          isAgent: 'true'
         }
      });
  }

  onSubmitFilter(utilizationReportFilter:FormGroup){
    this.fromDate= this.fromDate!=null?this.fromDate:'';
    this.toDate=  this.toDate!=null?this.toDate:'';

    let agentIdsArray: any=[];
    agentIdsArray.push(this.agentId);

    this.utilizationReportFilter.patchValue({
      fromDate: this.fromDate,
      toDate: this.toDate,
      organizationIds: this.custId,
      agentIds: agentIdsArray
     });
      this.customers.getCustomerUtilizationReportByAgent(this.utilizationReportFilter.value).subscribe((data:any)=>{
        if(data.outcome === true){
          this.getAgentUtilizationReport=data.data.reportResponseDtoList;
          let getfromDate = data.data.fromDate.split('/');
          this.setfromDate = { day:+getfromDate[0],month:+getfromDate[1],year:+getfromDate[2]};
          let gettoDate = data.data.toDate.split('/');
          this.settoDate = { day:+gettoDate[0],month:+gettoDate[1],year:+gettoDate[2]};
          this.utilizationReportFilter.patchValue({
            fromDate: this.setfromDate,
            toDate: this.settoDate
           });
        }else{
          Swal.fire({
            title: data.message,
            icon: 'warning'
          })
        }
  });
  }

}
