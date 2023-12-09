import { Injectable } from '@angular/core';
import { StateService } from '../services/state.service';
import { CurrentStatus } from '../model/current-status';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TodayFacadeService {

  constructor(private stateService: StateService) { }

  getCurrentStatus(): Observable<CurrentStatus> {
    return this.stateService.getCurrentStatus();
  }
}
