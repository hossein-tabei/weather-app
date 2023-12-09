import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { CurrentStatus } from '../model/current-status';
import { Location } from '../model/location';
import { AirQualityIndex } from '../model/air-quality-index';
import { DayStatus } from '../model/day-status';
import { HourStatus } from '../model/hour-status';

@Injectable({
  providedIn: 'root'
})
export class StateService {

  private bsLocation: BehaviorSubject<Location> = new BehaviorSubject(<Location>{});
  private bsCurrentStatus: BehaviorSubject<CurrentStatus> = new BehaviorSubject(<CurrentStatus>{});
  private bsNext5DaysStatusList: BehaviorSubject<DayStatus[]> = new BehaviorSubject(<DayStatus[]>{});
  private bsNext24HoursStatusList: BehaviorSubject<HourStatus[]> = new BehaviorSubject(<HourStatus[]>{});
  private bsAirQualityIndex: BehaviorSubject<AirQualityIndex> = new BehaviorSubject(<AirQualityIndex>{});

  // location
  setLocation(location: Location): void {
    this.bsLocation.next(location);
  }
  getLocation(): Observable<Location> {
    return this.bsLocation.asObservable();
  }

  // currentStatus
  setCurrentStatus(currentStatus: CurrentStatus): void {
    this.bsCurrentStatus.next(currentStatus);
  }
  getCurrentStatus(): Observable<CurrentStatus> {
    return this.bsCurrentStatus.asObservable();
  }

  // dayStatusList
  setNext5DaysStatusList(dayStatusList: DayStatus[]): void {
    this.bsNext5DaysStatusList.next(dayStatusList);
  }
  getNext5DaysStatusList(): Observable<DayStatus[]> {
    return this.bsNext5DaysStatusList.asObservable();
  }

  // hourStatusList
  setNext24HoursStatusList(hourStatusList: HourStatus[]): void {
    this.bsNext24HoursStatusList.next(hourStatusList);
  }
  getNext24HoursStatusList(): Observable<HourStatus[]> {
    return this.bsNext24HoursStatusList.asObservable();
  }

  // airQualityIndex
  setAirQualityIndex(airQualityIndex: AirQualityIndex): void {
    this.bsAirQualityIndex.next(airQualityIndex);
  }
  getAirQualityIndex(): Observable<AirQualityIndex> {
    return this.bsAirQualityIndex.asObservable();
  }
}
