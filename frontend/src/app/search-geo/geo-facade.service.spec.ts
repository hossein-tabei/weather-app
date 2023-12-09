import { TestBed } from '@angular/core/testing';

import { GeoFacadeService } from './geo-facade.service';

describe('GeoFacadeService', () => {
  let service: GeoFacadeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GeoFacadeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
