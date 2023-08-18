import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppComponent } from './app.component';
import { FormsModule ,ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AdminGuard } from './services/admin.guard';
import { AuthInterceptor } from './services/auth.interceptor';
import { AuthenticationService } from './services/authentication.service';
import { ErrorComponent } from './shared/error/error.component';
import { NgbDateParserFormatter, NgbDatepickerModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoaderComponent } from './shared/loader/loader.component';
import { LoaderService } from './services/loader.service';
import { LoaderInterceptor } from './services/loader.interceptor';
import { NgbDateCustomParserFormatter } from './services/ngb-date-parser-formatter.service';
import * as $ from 'jquery';

//import $ from 'jquery';
@NgModule({
  declarations: [
    AppComponent,
    ErrorComponent,
    LoaderComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    NgbModule,
    NgbDatepickerModule
  ],
  providers: [
    AdminGuard, LoaderService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }, AuthenticationService,
    LoaderService,
    {provide: HTTP_INTERCEPTORS, useClass: LoaderInterceptor, multi: true},
    {provide: NgbDateParserFormatter, useClass: NgbDateCustomParserFormatter}
  ],
  exports: [
    LoaderComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
