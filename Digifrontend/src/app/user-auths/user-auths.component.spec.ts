import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAuthsComponent } from './user-auths.component';

describe('UserAuthsComponent', () => {
  let component: UserAuthsComponent;
  let fixture: ComponentFixture<UserAuthsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserAuthsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserAuthsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
