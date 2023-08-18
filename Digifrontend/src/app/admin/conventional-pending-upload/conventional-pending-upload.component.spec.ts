import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConventionalPendingUploadComponent } from './conventional-pending-upload.component';

describe('ConventionalPendingUploadComponent', () => {
  let component: ConventionalPendingUploadComponent;
  let fixture: ComponentFixture<ConventionalPendingUploadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConventionalPendingUploadComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConventionalPendingUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
