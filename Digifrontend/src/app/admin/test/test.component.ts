import {Component, OnInit} from '@angular/core';
import {OrgadminService} from 'src/app/services/orgadmin.service';
import Swal from 'sweetalert2';
import {Router} from '@angular/router';
import {FormGroup, FormControl, FormBuilder, Validators} from '@angular/forms';
import * as XLSX from 'xlsx';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.scss']
})
export class TestComponent implements OnInit {
  addrole = new FormGroup({
    roleName: new FormControl('', Validators.required),
    roleCode: new FormControl(),
    roleAccess: new FormControl(),

  });

  constructor(private orgadmin: OrgadminService, private router: Router) {
  }

  ngOnInit(): void {
  }

  onSubmit(addrole: FormGroup) {
    const formData = new FormData();
    console.log(addrole.value, "____________________________________________")
    return this.orgadmin.Addrole(addrole.value).subscribe((result: any) => {
      this.addrole.reset();
      Swal.fire({
        title: result.message,
        icon: 'success'
      });

    });

  }




}
