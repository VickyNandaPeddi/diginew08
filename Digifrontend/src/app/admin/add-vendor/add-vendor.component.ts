import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { OrgadminService } from 'src/app/services/orgadmin.service';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
@Component({
  selector: 'app-add-vendor',
  templateUrl: './add-vendor.component.html',
  styleUrls: ['./add-vendor.component.scss']
})
export class AddVendorComponent implements OnInit {
  pageTitle = 'Vendor Management';
  orgID:any;
  // getOrgUsers: any=[];
  getOrgVendor: any=[];
  getOrgroles: any=[];
  getSupervisor: any=[];
  closeModal: string | undefined;
  getUserData: any=[];
  stat_roleId:boolean=false;
  getRolePerMissionCodes:any=[];
  ACTIVEANDINACTIVEAGENT_stat:boolean=false;
  EDITAGENT_stat:boolean=false;
  ACTIVEANDINACTIVEAGENTSUPERVISOR_stat:boolean=false;
  EDITAGENTSUPERVISOR_stat:boolean=false;
  admin_active:boolean=false;
  addOrgUser = new FormGroup({
    employeeId: new FormControl('', Validators.required),
    userFirstName: new FormControl('', Validators.required),
    userLastName: new FormControl('', Validators.required),
    userEmailId: new FormControl('', [Validators.required,Validators.email]),
    userMobileNum: new FormControl('', [ Validators.required, Validators.minLength(10), Validators.maxLength(10), Validators.pattern('[6-9]\\d{9}')]),
    userLandlineNum: new FormControl('', [Validators.minLength(8), Validators.maxLength(8)]),
    location: new FormControl('', Validators.required),
    roleId: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    organizationId: new FormControl('', Validators.required),
    userId: new FormControl(''),
    agentSupervisorId: new FormControl('')
  });
  patchUserValues() {
		this.addOrgUser.patchValue({
			organizationId: this.orgID
		});
	}
  constructor(private orgadmin:OrgadminService, public authService: AuthenticationService, private modalService: NgbModal,private router: Router) {
    this.orgID = this.authService.getOrgID();
    this.orgadmin.getOrgVendor(this.orgID).subscribe((data: any)=>{
      this.getOrgVendor=data.data;
      if(this.authService.roleMatch(['ROLE_ADMIN'])){
        this.admin_active = true;
      }
    });
    this.orgadmin.getOrgroles().subscribe((data: any)=>{
      this.getOrgroles=data.data;
    });
    this.orgadmin.getSupervisor(this.orgID).subscribe((supervisorList: any)=>{
      this.getSupervisor=supervisorList.data;
      console.log(this.getSupervisor);
    });
    
   }

  ngOnInit(): void {
    this.orgadmin.getRolePerMissionCodes(localStorage.getItem('roles')).subscribe(
      (result:any) => {
      this.getRolePerMissionCodes = result.data;
        if(this.getRolePerMissionCodes){
          for (let index = 0; index < this.getOrgVendor.length; index++) {
            if((this.getOrgVendor[index].roleName === 'Agent' && this.getRolePerMissionCodes.includes('EDITAGENT')) || this.authService.roleMatch(['ROLE_ADMIN'])){
              $("#edit"+index).removeClass('d-none');
             }
            if(this.getOrgVendor[index].roleName === 'Agent Supervisor' && this.getRolePerMissionCodes.includes('EDITAGENTSUPERVISOR') || this.authService.roleMatch(["ROLE_ADMIN"])){
              $("#edit"+index).removeClass('d-none');
            }

            if(this.getOrgVendor[index].roleName === 'Agent' && this.getRolePerMissionCodes.includes('ACTIVEANDINACTIVEAGENT') || this.authService.roleMatch(["ROLE_ADMIN"])){
              $("#inactiveCust_d"+index).removeClass('d-none');
              $("#inactiveCust_p"+index).removeClass('d-none');
             }
             if(this.getOrgVendor[index].roleName === 'Agent Supervisor' && this.getRolePerMissionCodes.includes('ACTIVEANDINACTIVEAGENTSUPERVISOR') || this.authService.roleMatch(["ROLE_ADMIN"])){
              $("#inactiveCust_d"+index).removeClass('d-none');
              $("#inactiveCust_p"+index).removeClass('d-none');
             }

            
          }

        }
    });
    
  }


  onSubmit() {
    return this.orgadmin.saveOrgusers(this.addOrgUser.value).subscribe((data:any)=>{
     
      if(data.outcome === true){
        Swal.fire({
          title: data.message,
          icon: 'success'
        })
       
        .then((data) => {
          if (data.isConfirmed) {
            window.location.reload();
          }
        });
      }else{
        Swal.fire({
          title: data.message,
          icon: 'warning'
        })
      } 
    });
    
  }


addvendor() {
  console.log("---------------inside-----btn")
  const billUrl = 'admin/vendormgmt';
  this.router.navigate([billUrl]);
    
  }
  
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }

  inactiveCust(userId: any, isActive: any){
    $(this).hide();
     this.orgadmin.orguserStat(userId, !isActive).subscribe((data: any)=>{
       for (let index = 0; index < this.getOrgVendor.length; index++) {
        if(this.getOrgVendor[index].userId === data.data.userId){
          this.getOrgVendor[index] = data.data;
         }
       }
       if(data.outcome === true){
        Swal.fire({
          title: data.message,
          icon: 'success'
        }).then((data) => {
          if (data.isConfirmed) {
            window.location.reload();
          }
        });
      }else{
        Swal.fire({
          title: data.message,
          icon: 'warning'
        })
      } 
       
     })
  }
  selectroleId(event:any){
    if(event.target.value == 3){
      this.stat_roleId = true;
      this.addOrgUser.controls["agentSupervisorId"].clearValidators();
      this.addOrgUser.controls["agentSupervisorId"].setValidators(Validators.required);
      this.addOrgUser.controls["agentSupervisorId"].updateValueAndValidity();
    }else{
      this.stat_roleId = false;
      this.addOrgUser.controls["agentSupervisorId"].clearValidators();
      this.addOrgUser.controls["agentSupervisorId"].updateValueAndValidity();
    }
  }
  openModal(userId:any){

    const billUrl = 'admin/vendormgmt/'+userId;
    this.router.navigate([billUrl]);
    
  }

}
