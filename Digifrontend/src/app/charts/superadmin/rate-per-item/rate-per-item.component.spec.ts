import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RatePerItemComponent } from './rate-per-item.component';

describe('RatePerItemComponent', () => {
  let component: RatePerItemComponent;
  let fixture: ComponentFixture<RatePerItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RatePerItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RatePerItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
