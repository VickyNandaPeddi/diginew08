import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VendorMgmtComponent } from './vendor-mgmt.component';

describe('VendorMgmtComponent', () => {
  let component: VendorMgmtComponent;
  let fixture: ComponentFixture<VendorMgmtComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VendorMgmtComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VendorMgmtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
