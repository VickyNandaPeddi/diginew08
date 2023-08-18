import { TestBed } from '@angular/core/testing';

import { OrgadminDashboardService } from './orgadmin-dashboard.service';

describe('OrgadminDashboardService', () => {
  let service: OrgadminDashboardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OrgadminDashboardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
