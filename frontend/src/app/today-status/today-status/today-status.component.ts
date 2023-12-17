import { Component } from '@angular/core';
import { WeatherStatusEnum } from 'src/app/enum/weather-status-enum';
import { CurrentStatus } from 'src/app/model/current-status';
import { TodayFacadeService } from '../today-facade.service';

@Component({
  selector: 'app-today-status',
  templateUrl: './today-status.component.html',
  styleUrl: './today-status.component.scss'
})
export class TodayStatusComponent {

  protected currentStatus!: CurrentStatus;
  protected weatherStatus!: WeatherStatusEnum|undefined;

  constructor(private todayFacadeService: TodayFacadeService) {
    todayFacadeService.getCurrentStatus().subscribe(currentStatus => {
      this.currentStatus = currentStatus;
      this.weatherStatus = WeatherStatusEnum.getInstanceBySymbol(currentStatus.status);
    });
  }

}
