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

  searchGeo(searchClause: string): void {
    this.httpGeoService.searchGeo(searchClause)
    .subscribe((locationList) => this.stateService.setLocationList(locationList.info));
  }

  getLocationList(): Observable<Location[]> {
    return this.stateService.getLocationList();
  }

  saveLocationAndSummonForcasts(location: Location) {
    this.stateService.setSelectedLocation(location);

    this.httpForecastService.currentStatus(location.lat, location.lon)
    .subscribe((currentStatus) => this.stateService.setCurrentStatus(currentStatus.info));

    this.httpForecastService.next5DaysForecast(location.lat, location.lon)
    .subscribe((dayStatusList) => this.stateService.setNext5DaysStatusList(dayStatusList.info));

    this.httpForecastService.next24HoursForecast(location.lat, location.lon)
    .subscribe((hourStatusList) => this.stateService.setNext24HoursStatusList(hourStatusList.info));

    this.httpForecastService.airQualityIndex(location.lat, location.lon)
    .subscribe((airQualityIndex) => this.stateService.setAirQualityIndex(airQualityIndex.info));
  }
}
