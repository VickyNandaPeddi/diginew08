import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VendorCApprovalComponent } from './vendor-c-approval.component';

describe('VendorCApprovalComponent', () => {
  let component: VendorCApprovalComponent;
  let fixture: ComponentFixture<VendorCApprovalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VendorCApprovalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VendorCApprovalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
