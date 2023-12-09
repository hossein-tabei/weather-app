import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DayStatus } from '../model/day-status';
import { StateService } from '../services/state.service';

@Injectable({
  providedIn: 'root'
})
export class Next5daysFacadeService {

  constructor(private stateService: StateService) { }

  getNext5DaysStatusList(): Observable<DayStatus[]> {
    return this.stateService.getNext5DaysStatusList();
  }
}
