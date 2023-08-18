import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConventionalVendorcheckDashboardComponent } from './conventional-vendorcheck-dashboard.component';

describe('ConventionalVendorcheckDashboardComponent', () => {
  let component: ConventionalVendorcheckDashboardComponent;
  let fixture: ComponentFixture<ConventionalVendorcheckDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConventionalVendorcheckDashboardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConventionalVendorcheckDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
