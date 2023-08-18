import { TestBed } from '@angular/core/testing';

import { NgbDateParserFormatterService } from './ngb-date-parser-formatter.service';

describe('NgbDateParserFormatterService', () => {
  let service: NgbDateParserFormatterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NgbDateParserFormatterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
