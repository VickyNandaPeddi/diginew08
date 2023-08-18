import { TestBed } from '@angular/core/testing';

import { SuperadminDashboardService } from './superadmin-dashboard.service';

describe('SuperadminDashboardService', () => {
  let service: SuperadminDashboardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SuperadminDashboardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
