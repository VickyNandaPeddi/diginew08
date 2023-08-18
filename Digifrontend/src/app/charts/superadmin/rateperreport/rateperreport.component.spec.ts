import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RateperreportComponent } from './rateperreport.component';

describe('RateperreportComponent', () => {
  let component: RateperreportComponent;
  let fixture: ComponentFixture<RateperreportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RateperreportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RateperreportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
