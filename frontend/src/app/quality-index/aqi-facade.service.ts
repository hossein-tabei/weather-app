import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AirQualityIndex } from '../model/air-quality-index';
import { StateService } from '../services/state.service';

@Injectable({
  providedIn: 'root'
})
export class AqiFacadeService {

  constructor(private stateService: StateService) { }

  getAirQualityIndex(): Observable<AirQualityIndex> {
    return this.stateService.getAirQualityIndex();
  }
}
