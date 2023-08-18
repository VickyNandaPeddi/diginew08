import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidatesubmittedConventionalComponent } from './candidatesubmitted-conventional.component';

describe('CandidatesubmittedConventionalComponent', () => {
  let component: CandidatesubmittedConventionalComponent;
  let fixture: ComponentFixture<CandidatesubmittedConventionalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CandidatesubmittedConventionalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CandidatesubmittedConventionalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
