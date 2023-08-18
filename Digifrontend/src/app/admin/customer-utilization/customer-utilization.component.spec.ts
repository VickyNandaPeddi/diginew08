import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerUtilizationComponent } from './customer-utilization.component';

describe('CustomerUtilizationComponent', () => {
  let component: CustomerUtilizationComponent;
  let fixture: ComponentFixture<CustomerUtilizationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerUtilizationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerUtilizationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
