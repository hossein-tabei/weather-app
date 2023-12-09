import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Location } from '../model/location';
import { HttpForecastService } from '../services/http-forecast.service';
import { HttpGeoService } from '../services/http-geo.service';
import { StateService } from '../services/state.service';

@Injectable({
  providedIn: 'root'
})
export class GeoFacadeService {

  constructor(private stateService: StateService,
    private httpGeoService: HttpGeoService,
    private httpForecastService: HttpForecastService) {}

  searchGeo(searchClause: string): Observable<Location[]> {
    return this.httpGeoService.searchGeo(searchClause);
  }

  saveLocationAndSummonForcasts(location: Location) {
    this.stateService.setLocation(location);

    this.httpForecastService.currentStatus(location.lat, location.lon)
    .subscribe((currentStatus) => {
      this.stateService.setCurrentStatus(currentStatus);
      this.stateService.setAirQualityIndex(currentStatus.airQualityIndex);
    });

    this.httpForecastService.next5DaysForecast(location.lat, location.lon)
    .subscribe((dayStatusList) => this.stateService.setNext5DaysStatusList(dayStatusList));

    this.httpForecastService.next24HoursForecast(location.lat, location.lon)
    .subscribe((hourStatusList) => this.stateService.setNext24HoursStatusList(hourStatusList));
  }
}
