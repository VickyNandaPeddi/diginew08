import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivityComparisionComponent } from './activity-comparision.component';

describe('ActivityComparisionComponent', () => {
  let component: ActivityComparisionComponent;
  let fixture: ComponentFixture<ActivityComparisionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActivityComparisionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivityComparisionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
