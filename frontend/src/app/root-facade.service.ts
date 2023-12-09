import { Injectable } from '@angular/core';
import { StateService } from './services/state.service';
import { CurrentStatus } from './model/current-status';
import { Observable } from 'rxjs';
import { DayStatus } from './model/day-status';
import { HourStatus } from './model/hour-status';
import { AirQualityIndex } from './model/air-quality-index';

@Injectable({
  providedIn: 'root'
})
export class RootFacadeService {

  constructor(private stateService: StateService) { }

  getCurrentStatus(): Observable<CurrentStatus> {
    return this.stateService.getCurrentStatus();
  }

  getNext5DaysStatusList(): Observable<DayStatus[]> {
    return this.stateService.getNext5DaysStatusList();
  }

  getNext24HoursStatusList(): Observable<HourStatus[]> {
    return this.stateService.getNext24HoursStatusList();
  }

  getAirQualityIndex(): Observable<AirQualityIndex> {
    return this.stateService.getAirQualityIndex();
  }
}
