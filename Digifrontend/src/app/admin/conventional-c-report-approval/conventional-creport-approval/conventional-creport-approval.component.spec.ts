import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConventionalCReportApprovalComponent } from './conventional-creport-approval.component';

describe('ConventionalCReportApprovalComponent', () => {
  let component: ConventionalCReportApprovalComponent;
  let fixture: ComponentFixture<ConventionalCReportApprovalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConventionalCReportApprovalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConventionalCReportApprovalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
