import { TestBed } from '@angular/core/testing';

import { AqiFacadeService } from './aqi-facade.service';

describe('AqiFacadeService', () => {
  let service: AqiFacadeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AqiFacadeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
