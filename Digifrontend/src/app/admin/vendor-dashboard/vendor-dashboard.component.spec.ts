import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrgadminDashboardComponent } from './orgadmin-dashboard.component';

describe('OrgadminDashboardComponent', () => {
  let component: OrgadminDashboardComponent;
  let fixture: ComponentFixture<OrgadminDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrgadminDashboardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrgadminDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
