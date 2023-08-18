import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ForgotpwdComponent } from './forgotpwd/forgotpwd.component';
import { LoginComponent } from './login/login.component';
import { UserAuthsComponent } from './user-auths.component';

const routes: Routes = [
  { path: '', component: UserAuthsComponent,
  children:[
    { path: '', component: LoginComponent},
    { path: 'forgotpwd', component: ForgotpwdComponent}
    ] 
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserAuthsRoutingModule { }
