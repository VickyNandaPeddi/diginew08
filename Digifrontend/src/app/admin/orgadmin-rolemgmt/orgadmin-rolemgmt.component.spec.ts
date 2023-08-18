import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrgadminRolemgmtComponent } from './orgadmin-rolemgmt.component';

describe('OrgadminRolemgmtComponent', () => {
  let component: OrgadminRolemgmtComponent;
  let fixture: ComponentFixture<OrgadminRolemgmtComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrgadminRolemgmtComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrgadminRolemgmtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
