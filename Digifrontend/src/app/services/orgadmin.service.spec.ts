import { TestBed } from '@angular/core/testing';

import { OrgadminService } from './orgadmin.service';

describe('OrgadminService', () => {
  let service: OrgadminService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OrgadminService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
