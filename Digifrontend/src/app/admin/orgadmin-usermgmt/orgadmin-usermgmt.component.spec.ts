import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrgadminUsermgmtComponent } from './orgadmin-usermgmt.component';

describe('OrgadminUsermgmtComponent', () => {
  let component: OrgadminUsermgmtComponent;
  let fixture: ComponentFixture<OrgadminUsermgmtComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrgadminUsermgmtComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrgadminUsermgmtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
