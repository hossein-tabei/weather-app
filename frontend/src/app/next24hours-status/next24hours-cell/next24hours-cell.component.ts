import { Component, Input } from '@angular/core';
import { HourStatus } from 'src/app/model/hour-status';
import { WeatherStatusEnum } from 'src/app/enum/weather-status-enum';

@Component({
  selector: 'app-next24hours-cell',
  templateUrl: './next24hours-cell.component.html',
  styleUrl: './next24hours-cell.component.scss'
})
export class Next24hoursCellComponent {

  @Input() hourStatus!: HourStatus;

  protected getIconSymbol(symbol: string): string|undefined {
    return WeatherStatusEnum.getInstanceBySymbol(symbol)?.getIconSymbol();
  }

}
