import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadVendocheckComponent } from './upload-vendocheck.component';

describe('UploadVendocheckComponent', () => {
  let component: UploadVendocheckComponent;
  let fixture: ComponentFixture<UploadVendocheckComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UploadVendocheckComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadVendocheckComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
