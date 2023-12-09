import { TestBed } from '@angular/core/testing';

import { RootFacadeService } from './root-facade.service';

describe('RootFacadeService', () => {
  let service: RootFacadeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RootFacadeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
