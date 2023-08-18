import { Component, OnInit } from '@angular/core';
import { OrgadminService } from 'src/app/services/orgadmin.service';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-orgadmin-rolemgmt',
  templateUrl: './orgadmin-rolemgmt.component.html',
  styleUrls: ['./orgadmin-rolemgmt.component.scss']
})
export class OrgadminRolemgmtComponent implements OnInit {
  pageTitle = 'Role Management';
  getRoleDropdown: any=[];
  orgID: any;
  getAllRolePermission: any=[];
  getRoleMgmtStat: any=[];
  orgRoleMgmt = new FormGroup({
    permissionId: new FormControl('', Validators.required),
    roleId: new FormControl('', Validators.required)
  });
  patchUserValues() {
		this.orgRoleMgmt.patchValue({
			permissionId: this.tmp,
      roleId: this.getroleId
		});
	}
  constructor(private orgadmin:OrgadminService) { 
    this.orgadmin.getRoleDropdown().subscribe((data: any)=>{
      this.getRoleDropdown=data.data;
      console.log(this.getRoleDropdown);
    });

    this.orgadmin.getAllRolePermission().subscribe((data: any)=>{
      this.getAllRolePermission=data.data;
      console.log(this.getAllRolePermission);
    });
    
  }

  ngOnInit(): void {
  }

  onSubmit(){
    this.patchUserValues();
    if (this.orgRoleMgmt.valid) {
      this.orgadmin.saveRoleMgmt(this.orgRoleMgmt.value).subscribe((result:any)=>{
        if(result.outcome === true){
          Swal.fire({
            title: result.message,
            icon: 'success'
          }).then((result) => {
            if (result.isConfirmed) {
              window.location.reload();
            }
          });
        }else{
          Swal.fire({
            title: result.message,
            icon: 'warning'
          })
        }
      });
    }else{
        Swal.fire({
          title: "Please enter the required information",
          icon: 'warning'
        })
      }
  }

  tmp: any=[];
  roleCboxes(e:any){
    var sid = e.target.id;
    if (e.target.checked) {
      this.tmp.push(sid);
    } else {
      this.tmp.splice($.inArray(sid, this.tmp),1);
    }
  }

  childCheckselected(sid:any){
    this.tmp.push(sid);
  }
  
  selectAll(e:any){
    if (e.target.checked) {
      $(e.target).parent().siblings().find(".rolecboxbtn").prop('checked', true);
     var  cboxRolesinput = $('.cboxRoles input');
      var arrNumber:any = [];
      $.each(cboxRolesinput,function(idx,elem){
        var inputValues:any  = $(elem).val();
        console.log(inputValues);
        arrNumber.push($(this).val());
      });
      
      this.tmp = arrNumber;
      console.log(this.tmp);
    } else {
      $(e.target).parent().siblings().find(".rolecboxbtn").prop('checked', false);
    }
    
  }
  
  getroleId:  any=[];
  roleDropdown(e:any){
    this.tmp = [];
    this.getroleId = e.target.id;
    $(".rolecboxbtn").prop('checked', false);
    this.orgadmin.getRoleMgmtStat(this.getroleId).subscribe((getRoleMgmtStat: any)=>{
      console.log(getRoleMgmtStat);
      const rolepermissionarray = getRoleMgmtStat.data['permissionId'];
      rolepermissionarray.forEach((element: any) =>{
        this.childCheckselected(element);
       $(".rolecboxbtn"+element).prop('checked', true);
      });
    });
  }
  

}


