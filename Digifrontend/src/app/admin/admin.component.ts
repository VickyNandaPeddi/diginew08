import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {
  clearStat:any;
  constructor(private authService: AuthenticationService) { 
    var timeoutInMiliseconds = 25200000;
    var timeoutId; 
    this.clearStat = localStorage.getItem("jwtToken");
    if(this.clearStat){
      timeoutId = window.setTimeout(doInactive, timeoutInMiliseconds);
    }
    function doInactive() {
      authService.forceLogout();
    }
  }

  ngOnInit(): void {
    
  }

}
