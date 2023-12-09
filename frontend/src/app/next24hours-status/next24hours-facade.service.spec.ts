import { TestBed } from '@angular/core/testing';

import { Next24hoursFacadeService } from './next24hours-facade.service';

describe('Next24hoursFacadeService', () => {
  let service: Next24hoursFacadeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Next24hoursFacadeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
