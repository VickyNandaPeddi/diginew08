import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerUtilAgentComponent } from './customer-util-agent.component';

describe('CustomerUtilAgentComponent', () => {
  let component: CustomerUtilAgentComponent;
  let fixture: ComponentFixture<CustomerUtilAgentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerUtilAgentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerUtilAgentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
