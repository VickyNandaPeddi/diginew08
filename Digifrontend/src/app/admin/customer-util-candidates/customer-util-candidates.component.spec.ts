import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerUtilCandidatesComponent } from './customer-util-candidates.component';

describe('CustomerUtilCandidatesComponent', () => {
  let component: CustomerUtilCandidatesComponent;
  let fixture: ComponentFixture<CustomerUtilCandidatesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerUtilCandidatesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerUtilCandidatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
