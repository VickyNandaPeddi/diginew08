import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectedActivitiesComponent } from './selected-activities.component';

describe('SelectedActivitiesComponent', () => {
  let component: SelectedActivitiesComponent;
  let fixture: ComponentFixture<SelectedActivitiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SelectedActivitiesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectedActivitiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
