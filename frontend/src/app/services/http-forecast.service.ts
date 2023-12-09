import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CurrentStatus } from '../model/current-status';
import { DayStatus } from '../model/day-status';
import { HourStatus } from '../model/hour-status';


@Injectable({
  providedIn: 'root'
})
export class HttpForecastService {
  readonly PATH_FORECAST_NOW: string = '/api/forecast/now';
  readonly PATH_FORECAST_NEXT5DAYS: string = '/api/forecast/next5days';
  readonly PATH_FORECAST_NEXT24HOURS: string = '/api/forecast/next24hours';

  constructor(private http: HttpClient) {}

  currentStatus(latitude: number, longitude: number): Observable<CurrentStatus> {
    let queryParams = new HttpParams().append('lat', latitude).append('lon', longitude);
    return this.http.get<CurrentStatus>(this.PATH_FORECAST_NOW, {params: queryParams});
  }

  next5DaysForecast(latitude: number, longitude: number): Observable<DayStatus[]> {
    let queryParams = new HttpParams().append('lat', latitude).append('lon', longitude);
    return this.http.get<DayStatus[]>(this.PATH_FORECAST_NEXT5DAYS, {params: queryParams});
  }

  next24HoursForecast(latitude: number, longitude: number): Observable<HourStatus[]> {
    let queryParams = new HttpParams().append('lat', latitude).append('lon', longitude);
    return this.http.get<HourStatus[]>(this.PATH_FORECAST_NEXT24HOURS, {params: queryParams});
  }
}
