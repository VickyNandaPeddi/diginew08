import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidateMgmtComponent } from './candidate-mgmt.component';

describe('CandidateMgmtComponent', () => {
  let component: CandidateMgmtComponent;
  let fixture: ComponentFixture<CandidateMgmtComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CandidateMgmtComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CandidateMgmtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
