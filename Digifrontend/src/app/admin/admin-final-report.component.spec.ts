import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminFinalReportComponent } from './admin-final-report.component';

describe('AdminFinalReportComponent', () => {
  let component: AdminFinalReportComponent;
  let fixture: ComponentFixture<AdminFinalReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminFinalReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminFinalReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
