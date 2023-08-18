import { Component, OnInit } from '@angular/core';
import { LoaderService } from 'src/app/services/loader.service';
import { NgbCalendar, NgbDate } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { CustomerService } from 'src/app/services/customer.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  pageTitle = 'Dashboard';
  fromDate: any;
  toDate: any;
  getToday: NgbDate;
  getMinDate: any;
  setfromDate: any;
  settoDate: any;
  initToday: any;
  filterForm_valid: boolean = false;
  dashboardFilter = new FormGroup({
    fromDate: new FormControl('', Validators.required),
    toDate: new FormControl('', Validators.required)
  });
  constructor(public loaderService: LoaderService, public calendar: NgbCalendar, private customer: CustomerService) {
    this.getToday = calendar.getToday();
    let inityear = this.getToday.year;
    let initmonth = this.getToday.month <= 9 ? '0' + this.getToday.month : this.getToday.month;;
    let initday = this.getToday.day <= 9 ? '0' + this.getToday.day : this.getToday.day;
    let initfinalDate = initday + "/" + initmonth + "/" + inityear;
    this.initToday = initfinalDate;
    if (localStorage.getItem('dbFromDate') == null && localStorage.getItem('dbToDate') == null) {
      this.customer.setFromDate(this.initToday);
      this.customer.setToDate(this.initToday);
      this.fromDate = this.initToday;
      this.toDate = this.initToday;
    }

    var checkfromDate: any = localStorage.getItem('dbFromDate');
    let getfromDate = checkfromDate.split('/');
    this.setfromDate = { day: +getfromDate[0], month: +getfromDate[1], year: +getfromDate[2] };

    var checktoDate: any = localStorage.getItem('dbToDate');
    let gettoDate = checktoDate.split('/');
    this.settoDate = { day: +gettoDate[0], month: +gettoDate[1], year: +gettoDate[2] };
    this.getMinDate = { day: +gettoDate[0], month: +gettoDate[1], year: +gettoDate[2] };
    this.dashboardFilter.patchValue({
      fromDate: this.setfromDate,
      toDate: this.settoDate
    });
    //Test

  }
  onfromDate(event: any) {
    let year = event.year;
    let month = event.month <= 9 ? '0' + event.month : event.month;
    let day = event.day <= 9 ? '0' + event.day : event.day;
    let finalDate = day + "/" + month + "/" + year;
    this.fromDate = finalDate;
    this.getMinDate = { day: +day, month: +month, year: +year };
  }
  ontoDate(event: any) {
    let year = event.year;
    let month = event.month <= 9 ? '0' + event.month : event.month;
    let day = event.day <= 9 ? '0' + event.day : event.day;
    let finalDate = day + "/" + month + "/" + year;
    this.toDate = finalDate;
  }
  onSubmitFilter(dashboardFilter: FormGroup) {
    let inputFromDate: any = $("#inputFromDate").val();
    //let getInputFromDate:any = inputFromDate.split('-');
    let finalInputFromDate = inputFromDate;

    let inputToDate: any = $("#inputToDate").val();
    //let getInputToDate:any = inputToDate.split('-');
    let finalInputToDate = inputToDate;

    if (this.fromDate == null) {
      this.fromDate = finalInputFromDate;
    }
    if (this.toDate == null) {
      this.toDate = finalInputToDate;
    }
    if (this.dashboardFilter.valid) {
      this.customer.setFromDate(this.fromDate);
      this.customer.setToDate(this.toDate);
      window.location.reload();
    } else {
      Swal.fire({
        title: 'Please select the valid dates.',
        icon: 'warning'
      });
    }

  }

  filterToday() {
    this.customer.setFromDate(this.initToday);
    this.customer.setToDate(this.initToday);
    window.location.reload();
  }

  filterLast7days() {
    var date = new Date();
    date.setDate(date.getDate() - 7);
    var dateString = date.toISOString().split('T')[0];
    let getInputFromDate: any = dateString.split('-');
    let finalInputFromDate = getInputFromDate[2] + "/" + getInputFromDate[1] + "/" + getInputFromDate[0];
    this.customer.setFromDate(finalInputFromDate);
    this.customer.setToDate(this.initToday);
    window.location.reload();
  }

  filterLast30days() {
    var date = new Date();
    date.setDate(date.getDate() - 30);
    var dateString = date.toISOString().split('T')[0];
    let getInputFromDate: any = dateString.split('-');
    let finalInputFromDate = getInputFromDate[2] + "/" + getInputFromDate[1] + "/" + getInputFromDate[0];
    this.customer.setFromDate(finalInputFromDate);
    this.customer.setToDate(this.initToday);
    window.location.reload();
  }


  ngOnInit(): void {
    setTimeout(() => {
      this.loaderService.hide();
    }, 1000);
  }

}
