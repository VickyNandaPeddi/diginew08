import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportDeliveryDetailsComponent } from './report-delivery-details.component';

describe('ReportDeliveryDetailsComponent', () => {
  let component: ReportDeliveryDetailsComponent;
  let fixture: ComponentFixture<ReportDeliveryDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReportDeliveryDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportDeliveryDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
