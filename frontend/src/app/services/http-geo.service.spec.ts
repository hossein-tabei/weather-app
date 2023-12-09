import { TestBed } from '@angular/core/testing';

import { HttpGeoService } from './http-geo.service';

describe('HttpGeoService', () => {
  let service: HttpGeoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpGeoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
