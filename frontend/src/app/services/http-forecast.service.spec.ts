import { TestBed } from '@angular/core/testing';

import { HttpForecastService } from './http-forecast.service';

describe('HttpForecastService', () => {
  let service: HttpForecastService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpForecastService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
