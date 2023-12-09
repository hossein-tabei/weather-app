import { Component } from '@angular/core';
import { CurrentStatus } from 'src/app/model/current-status';
import { StateService } from 'src/app/services/state.service';
import { TodayFacadeService } from '../today-facade.service';

@Component({
  selector: 'app-tody-more-info',
  templateUrl: './tody-more-info.component.html',
  styleUrl: './tody-more-info.component.scss'
})
export class TodyMoreInfoComponent {

  protected currentStatus!: CurrentStatus;

  constructor(private todayFacadeService: TodayFacadeService) {
    todayFacadeService.getCurrentStatus().subscribe(currentStatus => {
      this.currentStatus = currentStatus;
    });
  }

}
