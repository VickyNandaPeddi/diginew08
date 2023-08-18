import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerActivitiesComponent } from './customer-activities.component';

describe('CustomerActivitiesComponent', () => {
  let component: CustomerActivitiesComponent;
  let fixture: ComponentFixture<CustomerActivitiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerActivitiesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerActivitiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
