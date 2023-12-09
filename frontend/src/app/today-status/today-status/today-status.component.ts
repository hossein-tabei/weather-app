import { Component } from '@angular/core';
import { CurrentStatus } from 'src/app/model/current-status';
import { StateService } from 'src/app/services/state.service';
import { WeatherStatusEnum } from 'src/app/enum/weather-status-enum';
import { TodayFacadeService } from '../today-facade.service';

@Component({
  selector: 'app-today-status',
  templateUrl: './today-status.component.html',
  styleUrl: './today-status.component.scss'
})
export class TodayStatusComponent {

  protected currentStatus!: CurrentStatus;

  constructor(private todayFacadeService: TodayFacadeService) {
    todayFacadeService.getCurrentStatus().subscribe(currentStatus => {
      this.currentStatus = currentStatus;
    });
  }

  protected getIconSymbol(symbol: string): string|undefined {
    return WeatherStatusEnum.getInstanceBySymbol(symbol)?.getIconSymbol();
  }
}
