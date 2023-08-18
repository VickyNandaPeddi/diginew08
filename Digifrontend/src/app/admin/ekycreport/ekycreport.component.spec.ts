import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EkycreportComponent } from './ekycreport.component';

describe('EkycreportComponent', () => {
  let component: EkycreportComponent;
  let fixture: ComponentFixture<EkycreportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EkycreportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EkycreportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
