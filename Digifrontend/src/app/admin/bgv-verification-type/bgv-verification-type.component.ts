import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CustomerService} from '../../services/customer.service';
import {LoaderService} from "../../services/loader.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-bgv-verification-type',
  templateUrl: './bgv-verification-type.component.html',
  styleUrls: ['./bgv-verification-type.component.scss']
})
export class BGVVerificationTypeComponent implements OnInit {

  constructor(private router: ActivatedRoute, private navRouter: Router, private customer: CustomerService, private loaderService: LoaderService) {
  }

  isLoading = false;

  ngOnInit(): void {
  }


  onDashboardClick() {

    console.log("-----------------------------------------------");
    const navURL = 'admin/vendorinitiaste/';
    this.navRouter.navigate([navURL]);
  }

  onConventionalClick() {

    let vendorData = {
      VendorID: "2CDC7E3A"
    }
    console.log('----------------------candidate fetch starts-------------------------');
    this.addCandidateData(vendorData).then(() => {
      this.loaderService.hide();
      const navURL = 'admin/ConventionalDashboard/';
      this.navRouter.navigate([navURL]);

    });
    console.log('----------------------candidate fetch ends-------------------------');

  }

  async addCandidateData(vendorData: any) {
    return new Promise<void>((resolve) => {

      this.loaderService.show();
      this.customer.addAndUpdateCandidateData(vendorData).subscribe((data: any) => {
        // if (data.status !== null) {
        //   alert(data.status);
        // }
        if (data.outcome === false) {
          // const errorJsonStartIndex = errorMessage.indexOf('[');
          // const errorJson = errorMessage.substring(errorJsonStartIndex);
          // const errorObject = JSON.parse(errorJson);
          //
          // const errorMessageFromObject = errorObject[0].Message;
          Swal.fire({

            title: data.message,
            icon: 'warning'
          }).then((result) => {
            if (result.isConfirmed) {
            }
          });
        }
        if (data.toString() != null) {
          resolve();
        }
      });

    });
  }
}
