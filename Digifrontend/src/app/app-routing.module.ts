import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: 'admin', loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule) },
  { path: '', loadChildren: () => import('./user-auths/user-auths.module').then(m => m.UserAuthsModule) },
  { path: 'charts', loadChildren: () => import('./charts/charts.module').then(m => m.ChartsModule) },
  { path: '**', redirectTo: '', pathMatch: 'full'}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {useHash: true}),
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
