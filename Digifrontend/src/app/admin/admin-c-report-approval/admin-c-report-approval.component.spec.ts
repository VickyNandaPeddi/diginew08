import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminCReportApprovalComponent } from './admin-c-report-approval.component';

describe('AdminCReportApprovalComponent', () => {
  let component: AdminCReportApprovalComponent;
  let fixture: ComponentFixture<AdminCReportApprovalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminCReportApprovalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminCReportApprovalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
