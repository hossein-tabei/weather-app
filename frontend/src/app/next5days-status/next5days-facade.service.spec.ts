import { TestBed } from '@angular/core/testing';

import { Next5daysFacadeService } from './next5days-facade.service';

describe('Next5daysFacadeService', () => {
  let service: Next5daysFacadeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Next5daysFacadeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
