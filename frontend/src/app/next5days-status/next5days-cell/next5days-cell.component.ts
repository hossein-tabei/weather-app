import { Component, Input } from '@angular/core';
import { DayStatus } from 'src/app/model/day-status';
import { WeatherStatusEnum } from 'src/app/enum/weather-status-enum';

@Component({
  selector: 'app-next5days-cell',
  templateUrl: './next5days-cell.component.html',
  styleUrl: './next5days-cell.component.scss'
})
export class Next5daysCellComponent {
  @Input() dayStatus!: DayStatus;

  protected getIconSymbol(symbol: string): string|undefined {
    return WeatherStatusEnum.getInstanceBySymbol(symbol)?.getIconSymbol();
  }
}
