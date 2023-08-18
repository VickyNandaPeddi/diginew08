import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrgadminUploaddetailsComponent } from './orgadmin-uploaddetails.component';

describe('OrgadminUploaddetailsComponent', () => {
  let component: OrgadminUploaddetailsComponent;
  let fixture: ComponentFixture<OrgadminUploaddetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrgadminUploaddetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrgadminUploaddetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
