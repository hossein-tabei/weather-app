import { TestBed } from '@angular/core/testing';

import { TodayFacadeService } from './today-facade.service';

describe('TodayFacadeService', () => {
  let service: TodayFacadeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TodayFacadeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
