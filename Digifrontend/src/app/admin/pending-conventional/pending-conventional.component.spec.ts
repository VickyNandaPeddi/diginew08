import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PendingConventionalComponent } from './pending-conventional.component';

describe('PendingConventionalComponent', () => {
  let component: PendingConventionalComponent;
  let fixture: ComponentFixture<PendingConventionalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PendingConventionalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PendingConventionalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
