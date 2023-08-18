import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { CustomerService } from '../../services/customer.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { timer } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

// import * as FileSaver from 'file-saver';

import { NgbCalendar, NgbDate } from '@ng-bootstrap/ng-bootstrap';
import * as XLSX from 'xlsx';
import { AuthenticationService } from 'src/app/services/authentication.service';

const EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';

@Component({
  selector: 'app-customer-utilization',
  templateUrl: './customer-utilization.component.html',
  styleUrls: ['./customer-utilization.component.scss']
})
export class CustomerUtilizationComponent implements OnInit {
  pageTitle = 'Customer Utilization Report';
  getCustomerUtilizationReport: any=[];
  //merge excel start
  getCandidateUtilizationReport: any=[];
  company = new Map<string, {}>();
  company_name: any=[];
  excel_data: any=[];
  geteKycReport:any=[];
  orgKycReport:any=[];
  getAgentUtilizationReport: any=[];
  init_agent_details: any=[];
  agent_details: any=[];
  start_date="";
  end_date="";
  //merge excel end
  getCustomerUtilizationReportByAgent: any=[];
  getCanididateDetailsByStatus: any=[];
  getCustID: any=[];
  custId:any=0;
  getAgentList: any=[];
  fromDate:any;
  toDate:any;
  setfromDate:any;
  settoDate:any;
  getToday: NgbDate;
  getMinDate: any;
  kyc:Boolean=false;
  utilizationReportClick = new FormGroup({
    fromDate: new FormControl('', Validators.required),
    toDate: new FormControl('', Validators.required),
    organizationIds: new FormControl('', Validators.required),
    statusCode: new FormControl('', Validators.required),
  });
  utilizationReportFilter = new FormGroup({
    fromDate: new FormControl('', Validators.required),
    toDate: new FormControl('', Validators.required),
    organizationIds: new FormControl('', Validators.required)
  });
  /*name of the excel-file which will be downloaded. */ 

  
  // public exportAsExcelFile(json: any[], excelFileName: string): void {
  //   const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(json);
  //   const workbook: XLSX.WorkBook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
  //   const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
  //   this.saveAsExcelFile(excelBuffer, excelFileName);
  // }
  // private saveAsExcelFile(buffer: any, fileName: string): void {
  //    const data: Blob = new Blob([buffer], {type: EXCEL_TYPE});
  //    FileSaver.saveAs(data, fileName + ".xlsx");
  // }
  excelBuffer: any;
  fileName= 'export.xlsx';  
  
  exportexcel(): void 
    {
      
       /* table id is passed over here */   
      //  let element = document.getElementById('excel-table'); 
      //  const ws: XLSX.WorkSheet =XLSX.utils.table_to_sheet(element);
      //  /* generate workbook and add the worksheet */
      //  const wb: XLSX.WorkBook = XLSX.utils.book_new();
      //  XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
      //  /* save to file */
      //  XLSX.writeFile(wb, this.fileName);
      console.log("Inside excel",this.company);

      

      const fileName = "Customer Utilization Report.xlsx";
      const sheetName = ["sheet1", "sheet2", "sheet3"];

    //   let wb = XLSX.utils.book_new();
    //   for (var i = 0; i < sheetName.length; i++) {
    //     let ws = XLSX.utils.json_to_sheet(arr[i]);
    //     XLSX.utils.book_append_sheet(wb, ws, sheetName[i]);
    //   }
    //   XLSX.writeFile(wb, fileName);

    let wb = XLSX.utils.book_new(); 
    let ws_newupload: any; let ws_PENDINGNOW: any; let ws_FINALREPORT: any; let ws_PENDINGAPPROVAL: any; let ws_INVITATIONEXPIRED: any; let ws_REINVITE: any;
    let NEWUPLOAD = 0; let PENDINGNOW = 0; let FINALREPORT = 0; let PENDINGAPPROVAL = 0; let INVITATIONEXPIRED = 0; let REINVITE = 0;
    let OLD_NEWUPLOAD_LEN = 0; let OLD_PENDINGNOW_LEN = 0; let OLD_FINALREPORT_LEN = 0; let OLD_PENDINGAPPROVAL_LEN = 0; let OLD_INVITATIONEXPIRED_LEN = 0; let OLD_REINVITE_LEN = 0;
    
    // let ws_OVERALLSummary = XLSX.utils.json_to_sheet(this.getCustomerUtilizationReport);
    let element = document.getElementById('excel-table'); 
    let ws_OVERALLSummary =XLSX.utils.table_to_sheet(element);
   
      let ws_ekycReport = XLSX.utils.json_to_sheet(this.geteKycReport)
    

    this.company.forEach((value: any=[], key: string) => {
        const filter_key = key.slice(0,30);
        if (key.includes('NEWUPLOAD')){
            console.log("Outside",key, value.length, value)
            if (NEWUPLOAD==0){
                console.log("Inside zero",key, value.length, value)
                ws_newupload = XLSX.utils.json_to_sheet(value);
                NEWUPLOAD = 1
                OLD_NEWUPLOAD_LEN = OLD_NEWUPLOAD_LEN + value.length;
            }else{                
                console.log("Inside Non zero",key, OLD_NEWUPLOAD_LEN, value)
                XLSX.utils.sheet_add_json(ws_newupload, value, 
                    {skipHeader: false,origin: `A${OLD_NEWUPLOAD_LEN+2}`})
                OLD_NEWUPLOAD_LEN = OLD_NEWUPLOAD_LEN + value.length;
                console.log("OLD_NEWUPLOAD_LEN",OLD_NEWUPLOAD_LEN);
            }   
        }else if (key.includes('FINALREPORT')){
            // console.log("Outside",key, value.length, value)
            if (FINALREPORT==0){
                console.log("Inside zero",key, value.length, value)
                ws_FINALREPORT = XLSX.utils.json_to_sheet(value);
                FINALREPORT = 1
                OLD_FINALREPORT_LEN = OLD_FINALREPORT_LEN + value.length;
            }else{                
                console.log("Inside Non zero",key, OLD_FINALREPORT_LEN, value)
                XLSX.utils.sheet_add_json(ws_FINALREPORT, value, 
                    {skipHeader: false,origin: `A${OLD_FINALREPORT_LEN+2}`})
                OLD_FINALREPORT_LEN = OLD_FINALREPORT_LEN + value.length;
                // console.log("OLD_FINALREPORT_LEN",OLD_FINALREPORT_LEN);
            }   
        }else if (key.includes('PENDINGNOW')){
            // console.log("Outside",key, value.length, value)
            if (PENDINGNOW==0){
                console.log("Inside zero",key, value.length, value)
                ws_PENDINGNOW = XLSX.utils.json_to_sheet(value);
                PENDINGNOW = 1
                OLD_PENDINGNOW_LEN = OLD_PENDINGNOW_LEN + value.length;
            }else{                
                console.log("Inside Non zero",key, OLD_PENDINGNOW_LEN, value)
                XLSX.utils.sheet_add_json(ws_PENDINGNOW, value, 
                    {skipHeader: false,origin: `A${OLD_PENDINGNOW_LEN+2}`})
                OLD_PENDINGNOW_LEN = OLD_PENDINGNOW_LEN + value.length;
                console.log("OLD_PENDINGNOW_LEN",OLD_PENDINGNOW_LEN);
            }   
        }else if (key.includes('PENDINGAPPROVAL')){
            // console.log("Outside",key, value.length, value)
            if (PENDINGAPPROVAL==0){
                console.log("Inside zero",key, value.length, value)
                ws_PENDINGAPPROVAL = XLSX.utils.json_to_sheet(value);
                PENDINGAPPROVAL = 1
                OLD_PENDINGAPPROVAL_LEN = OLD_PENDINGAPPROVAL_LEN + value.length;
            }else{                
                console.log("Inside Non zero",key, OLD_PENDINGAPPROVAL_LEN, value)
                XLSX.utils.sheet_add_json(ws_PENDINGAPPROVAL, value, 
                    {skipHeader: false,origin: `A${OLD_PENDINGAPPROVAL_LEN+2}`})
                OLD_PENDINGAPPROVAL_LEN = OLD_PENDINGAPPROVAL_LEN + value.length;
                console.log("OLD_PENDINGAPPROVAL_LEN",OLD_PENDINGAPPROVAL_LEN);
            }   
        }else if (key.includes('INVITATIONEXPIRED')){
          // console.log("Outside",key, value.length, value)
          if (INVITATIONEXPIRED==0){
              console.log("Inside zero",key, value.length, value)
              ws_INVITATIONEXPIRED = XLSX.utils.json_to_sheet(value);
              INVITATIONEXPIRED = 1
              OLD_INVITATIONEXPIRED_LEN = OLD_INVITATIONEXPIRED_LEN + value.length;
          }else{                
              console.log("Inside Non zero",key, OLD_INVITATIONEXPIRED_LEN, value)
              XLSX.utils.sheet_add_json(ws_INVITATIONEXPIRED, value, 
                  {skipHeader: false,origin: `A${OLD_INVITATIONEXPIRED_LEN+2}`})
              OLD_INVITATIONEXPIRED_LEN = OLD_INVITATIONEXPIRED_LEN + value.length;
              console.log("OLD_INVITATIONEXPIRED_LEN",OLD_INVITATIONEXPIRED_LEN);
          }   
      }else if (key.includes('REINVITE')){
        // console.log("Outside",key, value.length, value)
        if (REINVITE==0){
            console.log("Inside zero",key, value.length, value)
            ws_REINVITE = XLSX.utils.json_to_sheet(value);
            REINVITE = 1
            OLD_REINVITE_LEN = OLD_REINVITE_LEN + value.length;
        }else{                
            console.log("Inside Non zero",key, OLD_REINVITE_LEN, value)
            XLSX.utils.sheet_add_json(ws_REINVITE, value, 
                {skipHeader: false,origin: `A${OLD_REINVITE_LEN+2}`})
            OLD_REINVITE_LEN = OLD_REINVITE_LEN + value.length;
            console.log("OLD_REINVITE_LEN",OLD_REINVITE_LEN);
        }   
    }                            
    })
    XLSX.utils.book_append_sheet(wb, ws_OVERALLSummary, "OVERALLSUMMARY");
    XLSX.utils.book_append_sheet(wb, ws_newupload, "NEWUPLOAD");
    XLSX.utils.book_append_sheet(wb, ws_FINALREPORT, "FINALREPORT");
    XLSX.utils.book_append_sheet(wb, ws_PENDINGNOW, "PENDINGNOW");
    XLSX.utils.book_append_sheet(wb, ws_PENDINGAPPROVAL, "INTERIMREPORT");
    XLSX.utils.book_append_sheet(wb, ws_INVITATIONEXPIRED, "INVITATIONEXPIRED");
    XLSX.utils.book_append_sheet(wb, ws_REINVITE, "REINVITE");
    XLSX.utils.book_append_sheet(wb, ws_ekycReport, "EKYCREPORT");
    XLSX.writeFile(wb, fileName);

    //   const ws: XLSX.WorkSheet =XLSX.utils.json_to_sheet(this.getCustomerUtilizationReport);
    //   const wb: XLSX.WorkBook = XLSX.utils.book_new();
    //   XLSX.utils.book_append_sheet(wb, ws, 'Overallsummary');
    //   var count = 0;
      
    //   this.company.forEach((value: any=[], key: string) => {
    //     const filter_key = key.slice(0,30);
    //     this.company_name.push(filter_key);
    //     console.log("#########################");
    //     if (key.includes('NEWUPLOAD')){
    //       console.log(key, typeof(key), this.company_name, value);
    //     //   const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(value);
    //     //   const workbook: XLSX.WorkBook = { Sheets: { filter_key : worksheet }, SheetNames: this.company_name };
    //     //   const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
    //     //   const data: Blob = new Blob([excelBuffer], {type: EXCEL_TYPE});
    //     //   FileSaver.saveAs(data, this.fileName);
          

    //     //   this.exportAsExcelFile(value, key);
    //       const Newuploads_worksheet = XLSX.utils.json_to_sheet(value,{origin: -1});
    //       console.log("Newuploads_worksheet",Newuploads_worksheet);
    //       // XLSX.utils.sheet_add_json(Newuploads_worksheet,value, {skipHeader: false,origin: -1} );
    //       XLSX.utils.book_append_sheet(wb, Newuploads_worksheet,key);
    //     }
        
    // );
    

    // XLSX.writeFile(wb, this.fileName);
  } 

  onfromDate(event:any) {
    let year = event.year;
    let month = event.month <= 9 ? '0' + event.month : event.month;;
    let day = event.day <= 9 ? '0' + event.day : event.day;
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

  constructor( private customers:CustomerService, private navrouter: Router, calendar: NgbCalendar, private route: ActivatedRoute,
    public authService: AuthenticationService) {
    this.getToday = calendar.getToday(); 
    this.customers.getCustomersBill().subscribe((data: any)=>{
      this.getCustID=data.data;
    });

    let rportData = {
      'userId': localStorage.getItem('userId')
    }

    this.customers.posteKycReport(rportData).subscribe((data: any)=>{
      this.geteKycReport=data.data.candidateDetailsDto;
      this.orgKycReport=data.data.candidateDetailsDto;
      console.log(data,"------------------------------------"); 
      let no=0;            
      for (let item in this.geteKycReport){
        delete this.geteKycReport[item]['candidateCode'];
        delete this.geteKycReport[item]['createdByUserFirstName'];
        delete this.geteKycReport[item]['candidateId'];
        delete this.geteKycReport[item]['createdByUserLastName'];
        delete this.geteKycReport[item]['candidateName'];
        delete this.geteKycReport[item]['dateOfBirth'];
        delete this.geteKycReport[item]['contactNumber'];                               
        delete this.geteKycReport[item]['emailId'];
        this.geteKycReport[item]['SNo'] = item;
        this.geteKycReport[item]['Applicant ID'] = this.geteKycReport[item]["applicantId"];
        delete this.geteKycReport[item]['applicantId'];
        delete this.geteKycReport[item]['experienceInMonth'];
        delete this.geteKycReport[item]['dateOfEmailInvite'];
        delete this.geteKycReport[item]['statusName'];
        this.geteKycReport[item]['Status Date'] = this.geteKycReport[item]["createdOn"];
        delete this.geteKycReport[item]['statusDate'];
        delete this.geteKycReport[item]['createdOn'];
        this.geteKycReport[item]['PAN'] = this.geteKycReport[item]['panNumber'];
        delete this.geteKycReport[item]['panNumber'];
        this.geteKycReport[item]["Name as per PAN"] = this.geteKycReport[item]["panName"];
        delete this.geteKycReport[item]['panName'];
        this.geteKycReport[item]["PAN DOB"] = this.geteKycReport[item]["panDob"];
        delete this.geteKycReport[item]['panDob'];
        delete this.geteKycReport[item]['statusDate'];
        delete this.geteKycReport[item]['currentStatusDate'];
        delete this.geteKycReport[item]['colorName'];
        delete this.geteKycReport[item]['numberofexpiredCount'];
        delete this.geteKycReport[item]['reinviteCount'];
        this.geteKycReport[item].UAN = this.geteKycReport[item]["candidateUan"];
        delete this.geteKycReport[item]['candidateUan'];
        delete this.geteKycReport[item]['organizationOrganizationName'];  
        this.geteKycReport[item]["Name as per UAN"] = this.geteKycReport[item]["candidateUanName"];
        delete this.geteKycReport[item]['candidateUanName'];
        this.geteKycReport[item]["Full Aadhaar No"] = this.geteKycReport[item]["aadharNumber"]
        delete this.geteKycReport[item]['aadharNumber'];
        this.geteKycReport[item]['Name as per Aadhaar'] = this.geteKycReport[item]["aadharName"];
        delete this.geteKycReport[item]['aadharName'];
        this.geteKycReport[item]["Aadhar DOB"] = this.geteKycReport[item]["aadharDob"];
        delete this.geteKycReport[item]['aadharDob'];
        delete this.geteKycReport[item]['relationName'];
        delete this.geteKycReport[item]['relationship'];
        if(this.geteKycReport[item]["aadharFatherName"]!=null){
          var str = this.geteKycReport[item]["aadharFatherName"]
          var splitted = str.split(" ", 3); 
          console.log(splitted.length,"length")
          if (splitted.length==3){
            this.geteKycReport[item]["Relative"] = splitted[2]
            delete this.geteKycReport[item]['aadharFatherName'];
            if (splitted[0]==="S/O"){
              this.geteKycReport[item]["Relationship"] = "Father"
               
            }
            else if(splitted[0]==="W/O"){
               
              this.geteKycReport[item]["Relationship"] = "Husband"
            }
            else if(splitted[0]==="D/O:"){
               
              this.geteKycReport[item]["Relationship"] = "Father"
            }
            else if(splitted[0]==="S/O:"){
               
              this.geteKycReport[item]["Relationship"] = "Father"
            }
            else if(splitted[0]==="D/O"){
               
              this.geteKycReport[item]["Relationship"] = "Father"
            }
            else{
              this.geteKycReport[item]["Relationship"] = ""
            }
          }
          else{
            if(splitted[1].length!=1){
              this.geteKycReport[item]["Relative"] = splitted[1]
              delete this.geteKycReport[item]['aadharFatherName'];
              if (splitted[0]==="S/O"){
                this.geteKycReport[item]["Relationship"] = "Father"
                
              }
              else if(splitted[0]==="W/O"){
                
                this.geteKycReport[item]["Relationship"] = "Husband"
              }
              else if(splitted[0]==="D/O:"){
                this.geteKycReport[item]["Relationship"] = "Father"
              }
              else if(splitted[0]==="D/O"){
                this.geteKycReport[item]["Relationship"] = "Father"
              }
              else if(splitted[0]==="S/O:"){
                this.geteKycReport[item]["Relationship"] = "Father"
              }
              else{
                this.geteKycReport[item]["Relationship"] = ""
              }
            }
            else{
              this.geteKycReport[item]["Relative"] = splitted[0]
              delete this.geteKycReport[item]['aadharFatherName'];
              this.geteKycReport[item]["Relationship"] = ""
            }
          }
        }
        else{
          delete this.geteKycReport[item]['aadharFatherName'];
        }
        
        if(this.geteKycReport[item]["aadharGender"]!=null){
          var str = this.geteKycReport[item]["aadharGender"]
          if(str === "F"){
            this.geteKycReport[item].Gender = "Female"
            delete this.geteKycReport[item]['aadharGender'];
          }
          else if(str === "M"){
            this.geteKycReport[item].Gender = "Male"
            delete this.geteKycReport[item]['aadharGender'];
          }
          else{
            this.geteKycReport[item].Gender = this.geteKycReport[item]["aadharGender"];
            delete this.geteKycReport[item]['aadharGender'];
          }
        }
        else{
          this.geteKycReport[item].Gender="";
          delete this.geteKycReport[item]['aadharGender'];
        }
        this.geteKycReport[item]["Address"] = this.geteKycReport[item]["address"]
        delete this.geteKycReport[item]['address'];
       this.kyc=true;

      }
     
      console.log("newKycreport",this.geteKycReport);
    });
    
    
   
    
    
    this.customers.getCustomerUtilizationReport().subscribe((data: any)=>{
      this.getCustomerUtilizationReport=data.data.reportResponseDtoList;
      let getfromDate = data.data.fromDate.split('/');
      this.setfromDate = { day:+getfromDate[0],month:+getfromDate[1],year:+getfromDate[2]};
      this.getMinDate = this.setfromDate;

      let gettoDate = data.data.toDate.split('/');
      this.settoDate = { day:+gettoDate[0],month:+gettoDate[1],year:+gettoDate[2]};
      console.log("getfromDate, gettoDate",this.getMinDate, this.settoDate, this.fromDate, this.toDate);

      this.start_date = 'No Date Filter';//data.data.fromDate!=null?data.data.fromDate.split('-').join('/'):''
      this.end_date = 'No Date Filter';//data.data.toDate!=null?data.data.toDate.split('-').join('/'):''
      
      console.log("getCustomerUtilizationReport",this.getCustomerUtilizationReport);
      let company = new Map<string, {}>();
      for (let item in this.getCustomerUtilizationReport){
        var features : any={};
        console.log("item",this.getCustomerUtilizationReport[item].name);
        
        Object.keys(this.getCustomerUtilizationReport[item]).find((key)=>{
          if (key.includes('Code')){
            const statusCode = this.getCustomerUtilizationReport[item][key];
            // console.log("statusCode type *****",typeof(statusCode), statusCode);
            const agentIds = this.route.snapshot.queryParamMap.get('agentIds');
            const isAgent = this.route.snapshot.queryParamMap.get('isAgent');
            // console.log("agentIds isAgent",agentIds,isAgent);
            let agentIdsArray: any=[];
            agentIdsArray.push(agentIds);

            if(isAgent == 'true'){
              this.utilizationReportClick.patchValue({
                fromDate: data.data.fromDate!=null?data.data.fromDate.split('-').join('/'):'',
                toDate: data.data.toDate!=null?data.data.toDate.split('-').join('/'):'',
                organizationIds: [this.getCustomerUtilizationReport[item].id],
                statusCode: statusCode,
                agentIds: agentIdsArray,
              });
            }else{
              this.utilizationReportClick.patchValue({
                fromDate: data.data.fromDate!=null?data.data.fromDate.split('-').join('/'):'',
                toDate: data.data.toDate!=null?data.data.toDate.split('-').join('/'):'',
                organizationIds: [this.getCustomerUtilizationReport[item].id],
                statusCode: statusCode,
                agentIds:[]
              });
            }
            // console.log("this.customers.getCanididateDetailsByStatus",this.utilizationReportClick);
            this.customers.getCanididateDetailsByStatus(this.utilizationReportClick.value).subscribe((result: any)=>{
              if (result['data']['organizationName']!=null && result.data.candidateDetailsDto.length>1){
                // console.log("organizationName",result['data']['organizationName']);
                // console.log("Fetaure name",result['data']['statusCode']);
                // console.log("Data",result, result.data.candidateDetailsDto.length);

                this.getCandidateUtilizationReport=result.data.candidateDetailsDto;
                features[result['data']['statusCode']] = result.data.candidateDetailsDto;
                this.company.set(result['data']['organizationName'] +", "+ result['data']['statusCode'],result.data.candidateDetailsDto);
              }
            });
          }else if(key.includes('agentCount')){
            const statusCode = "agent";
            console.log("statusCode type *****",typeof(statusCode), statusCode);
            const agentIds = this.route.snapshot.queryParamMap.get('agentIds');
            const isAgent = "true";
            console.log("agentIds isAgent",agentIds,isAgent);
            let agentIdsArray: any=[];
            agentIdsArray.push(agentIds);

            if(isAgent == 'true'){
              this.utilizationReportClick.patchValue({
                fromDate: data.data.fromDate!=null?data.data.fromDate.split('-').join('/'):'',
                toDate: data.data.toDate!=null?data.data.toDate.split('-').join('/'):'',
                organizationIds: [this.getCustomerUtilizationReport[item].id],
                statusCode: statusCode,
                agentIds: agentIdsArray,
              });
            }else{
              this.utilizationReportClick.patchValue({
                fromDate: data.data.fromDate!=null?data.data.fromDate.split('-').join('/'):'',
                toDate: data.data.toDate!=null?data.data.toDate.split('-').join('/'):'',
                organizationIds: [this.getCustomerUtilizationReport[item].id],
                statusCode: statusCode,
                agentIds:[]
              });
            }
            // console.log("this.customers.getCanididateDetailsByStatus",this.utilizationReportClick);
            this.customers.getCustomerUtilizationReportByAgent(this.utilizationReportClick.value).subscribe((data: any)=>{
              if (data.data.reportResponseDtoList!=null){
                console.log("Agent result",data);
                this.getAgentUtilizationReport = data.data.reportResponseDtoList
                this.company.set(this.getCustomerUtilizationReport[item].name +", "+ "AGENT",data.data.reportResponseDtoList);
              }
              
            });

          }
        });  
      }
      
      timer(3000).subscribe(x => { console.log("Initial",this.company); 
            this.agent_details = [];
            this.company.forEach((value: any=[], key: string) => {
              var agent_dict : any={};
              if (key.includes('AGENT')){
                agent_dict['key'] = key;
                agent_dict['value'] = value;
                this.agent_details.push(agent_dict);
              }
              
              
            })
            console.log("this.agent_details",this.agent_details);
          })
      //Excel end

      this.utilizationReportFilter.patchValue({
        fromDate: this.setfromDate,
        toDate: this.settoDate
       });
      this.fromDate= data.data.fromDate!=null?data.data.fromDate:'';
      this.toDate=  data.data.toDate!=null?data.data.toDate:'';

      if(authService.roleMatch(['ROLE_AGENTHR'])){
        const navURL = 'admin/customerUtilizationAgent/';
        this.navrouter.navigate([navURL],{ queryParams: 
          { fromDate: this.fromDate,
            toDate: this.toDate,
            organizationIds: localStorage.getItem('orgID'),
            statusCode: 'agent'
           }
        });
      }
      
    });

   }
   

 

  getData(custId:any, statusCode:any){
    let organizationIds: any=[];
    organizationIds.push(custId);
    console.log("this.fromDate, this.toDate",this.fromDate, this.toDate);
    this.utilizationReportClick.patchValue({
      fromDate: this.fromDate,
      toDate: this.toDate,
      organizationIds: organizationIds,
      statusCode: statusCode
     });
     if(statusCode == 'agent'){
      const navURL = 'admin/customerUtilizationAgent/';
      this.navrouter.navigate([navURL],{ queryParams: 
        { fromDate: this.fromDate,
          toDate: this.toDate,
          organizationIds: organizationIds,
          statusCode: statusCode
         }
      });
     }else{
      const navURL = 'admin/customerUtilizationCandidate/';
      this.navrouter.navigate([navURL],{ queryParams: 
        { fromDate: this.fromDate,
          toDate: this.toDate,
          organizationIds: organizationIds,
          statusCode: statusCode
         }
      });
     } 
  }

  getcustId(id:any){
    this.custId = id;
  }
  onSubmitFilter(utilizationReportFilter:FormGroup){
    this.fromDate= this.fromDate!=null?this.fromDate:'';
    this.toDate=  this.toDate!=null?this.toDate:'';
    let organizationIds: any=[];
    organizationIds.push(this.custId);
    this.utilizationReportFilter.patchValue({
      fromDate: this.fromDate,
      toDate: this.toDate,
      organizationIds: organizationIds
     });
        this.customers.postCustomerUtilizationReport(this.utilizationReportFilter.value).subscribe((data:any)=>{
          if(data.outcome === true){
            this.getCustomerUtilizationReport=data.data.reportResponseDtoList;
            console.log("this.getCustomerUtilizationReport",this.getCustomerUtilizationReport);
            this.start_date = data.data.fromDate!=null?data.data.fromDate.split('-').join('/'):''
            this.end_date = data.data.toDate!=null?data.data.toDate.split('-').join('/'):''

            //merge excel start
            this.company = new Map<string, {}>();
            for (let item in this.getCustomerUtilizationReport){
                var features : any={};
                // console.log("item",this.getCustomerUtilizationReport[item].name);
                
                Object.keys(this.getCustomerUtilizationReport[item]).find((key)=>{
                  if (key.includes('Code')){
                    const statusCode = this.getCustomerUtilizationReport[item][key];
                    // console.log("statusCode type *****",typeof(statusCode), statusCode);
                    const agentIds = this.route.snapshot.queryParamMap.get('agentIds');
                    const isAgent = this.route.snapshot.queryParamMap.get('isAgent');
                    // console.log("agentIds isAgent",agentIds,isAgent);
                    let agentIdsArray: any=[];
                    agentIdsArray.push(agentIds);

                    if(isAgent == 'true'){
                      this.utilizationReportClick.patchValue({
                        fromDate: this.fromDate!=null?this.fromDate.split('-').join('/'):'',
                        toDate: this.toDate!=null?this.toDate.split('-').join('/'):'',
                        organizationIds: [this.getCustomerUtilizationReport[item].id],
                        statusCode: statusCode,
                        agentIds: agentIdsArray,
                      });
                    }else{
                      this.utilizationReportClick.patchValue({
                        fromDate: this.fromDate!=null?this.fromDate.split('-').join('/'):'',
                        toDate: this.toDate!=null?this.toDate.split('-').join('/'):'',
                        organizationIds: [this.getCustomerUtilizationReport[item].id],
                        statusCode: statusCode,
                        agentIds:[]
                      });
                    }
                    // console.log("this.customers.getCanididateDetailsByStatus",this.utilizationReportClick);
                    this.customers.getCanididateDetailsByStatus(this.utilizationReportClick.value).subscribe((result: any)=>{
                      if (result['data']['organizationName']!=null && result.data.candidateDetailsDto.length>1){
                        // console.log("*************************************");
                        // console.log("organizationName",result['data']['organizationName']);
                        // console.log("Fetaure name",result['data']['statusCode']);
                        // console.log("Data",result, result.data.candidateDetailsDto.length);

                        this.getCandidateUtilizationReport=result.data.candidateDetailsDto;
                        features[result['data']['statusCode']] = result.data.candidateDetailsDto;
                        // console.log("features",features);
                        this.company.set(result['data']['organizationName'] +", "+ result['data']['statusCode'],result.data.candidateDetailsDto);
                      }
                      
                    });
                    
                  }else if(key.includes('agentCount')){
                    const statusCode = "agent";
                    // console.log("statusCode type *****",typeof(statusCode), statusCode);
                    const agentIds = this.route.snapshot.queryParamMap.get('agentIds');
                    const isAgent = "true";
                    // console.log("agentIds isAgent",agentIds,isAgent);
                    let agentIdsArray: any=[];
                    agentIdsArray.push(agentIds);

                    if(isAgent == 'true'){
                      this.utilizationReportClick.patchValue({
                        fromDate: this.fromDate!=null?this.fromDate.split('-').join('/'):'',
                        toDate: this.toDate!=null?this.toDate.split('-').join('/'):'',
                        organizationIds: [this.getCustomerUtilizationReport[item].id],
                        statusCode: statusCode,
                        agentIds: agentIdsArray,
                      });
                    }else{
                      this.utilizationReportClick.patchValue({
                        fromDate: this.fromDate!=null?this.fromDate.split('-').join('/'):'',
                        toDate: this.toDate!=null?this.toDate.split('-').join('/'):'',
                        organizationIds: [this.getCustomerUtilizationReport[item].id],
                        statusCode: statusCode,
                        agentIds:[]
                      });
                    }
                    
                    this.customers.getCustomerUtilizationReportByAgent(this.utilizationReportClick.value).subscribe((data: any)=>{
                      if (data.data.reportResponseDtoList!=null){
                        // console.log("Agent result",data);
                        this.getAgentUtilizationReport = data.data.reportResponseDtoList
                        this.company.set(this.getCustomerUtilizationReport[item].name +", "+ "AGENT",data.data.reportResponseDtoList);
                      }
                      
                    });
                  }
                })
                // console.log("this.company name",this.getCustomerUtilizationReport[item].name);
                // timer(10000).subscribe(x => { console.log("your_action_code_here",this.company) })
                // break;
            }
            
            console.log("filter this.company",this.company);
            timer(5000).subscribe(x => { console.log("your_action_code_here",this.company); 

            let count=0;
            this.company.forEach((value: any=[], key: string) => {
              var agent_dict : any={};
              if (key.includes('AGENT') && count!=0){
                agent_dict['key'] = key;
                agent_dict['value'] = value;
                this.agent_details.push(agent_dict);
              }
              count+=1;
              
            })
            console.log("this.agent_details",this.agent_details);
          })
          
            //merge excel end

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

  ngOnInit(): void {
    
  }

}
