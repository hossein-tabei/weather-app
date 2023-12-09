import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HourStatus } from '../model/hour-status';
import { StateService } from '../services/state.service';

@Injectable({
  providedIn: 'root'
})
export class Next24hoursFacadeService {

  constructor(private stateService: StateService) { }

  getNext24HoursStatusList(): Observable<HourStatus[]> {
    return this.stateService.getNext24HoursStatusList();
  }
}
