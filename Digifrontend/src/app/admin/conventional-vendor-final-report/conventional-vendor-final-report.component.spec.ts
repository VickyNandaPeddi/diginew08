import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConventionalVendorFinalReportComponent } from './conventional-vendor-final-report.component';

describe('ConventionalVendorFinalReportComponent', () => {
  let component: ConventionalVendorFinalReportComponent;
  let fixture: ComponentFixture<ConventionalVendorFinalReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConventionalVendorFinalReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConventionalVendorFinalReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
