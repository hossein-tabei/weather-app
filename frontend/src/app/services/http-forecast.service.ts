import {HttpClient, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {CurrentStatus} from '../model/current-status';
import {DayStatus} from '../model/day-status';
import {HourStatus} from '../model/hour-status';
import {AirQualityIndex} from '../model/air-quality-index';
import {DTOResultWrapper} from '../model/dto-result-wrapper';


@Injectable({
  providedIn: 'root'
})
export class HttpForecastService {
  readonly PATH_WEATHER_NOW: string = '/api/v1/weather/now';
  readonly PATH_FORECAST_NEXT5DAYS: string = '/api/v1/weather/forecast/next5days';
  readonly PATH_FORECAST_NEXT24HOURS: string = '/api/v1/weather/forecast/next24hours';
  readonly PATH_WEATHER_AQI: string = '/api/v1/weather/aqi';

  constructor(private http: HttpClient) {}

  currentStatus(latitude: number, longitude: number): Observable<DTOResultWrapper<CurrentStatus>> {
    let queryParams = new HttpParams().append('lat', latitude).append('lon', longitude);
    return this.http.get<DTOResultWrapper<CurrentStatus>>(this.PATH_WEATHER_NOW, {params: queryParams});
  }

  next5DaysForecast(latitude: number, longitude: number): Observable<DTOResultWrapper<DayStatus[]>> {
    let queryParams = new HttpParams().append('lat', latitude).append('lon', longitude);
    return this.http.get<DTOResultWrapper<DayStatus[]>>(this.PATH_FORECAST_NEXT5DAYS, {params: queryParams});
  }

  next24HoursForecast(latitude: number, longitude: number): Observable<DTOResultWrapper<HourStatus[]>> {
    let queryParams = new HttpParams().append('lat', latitude).append('lon', longitude);
    return this.http.get<DTOResultWrapper<HourStatus[]>>(this.PATH_FORECAST_NEXT24HOURS, {params: queryParams});
  }

  airQualityIndex(latitude: number, longitude: number): Observable<DTOResultWrapper<AirQualityIndex>> {
    let queryParams = new HttpParams().append('lat', latitude).append('lon', longitude);
    return this.http.get<DTOResultWrapper<AirQualityIndex>>(this.PATH_WEATHER_AQI, {params: queryParams});
  }
}
