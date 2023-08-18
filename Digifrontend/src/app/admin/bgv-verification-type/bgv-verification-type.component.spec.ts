import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BGVVerificationTypeComponent } from './bgv-verification-type.component';

describe('BGVVerificationTypeComponent', () => {
  let component: BGVVerificationTypeComponent;
  let fixture: ComponentFixture<BGVVerificationTypeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BGVVerificationTypeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BGVVerificationTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
