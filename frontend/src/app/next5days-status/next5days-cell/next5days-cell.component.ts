import { Component, Input, OnInit } from '@angular/core';
import { DayStatus } from 'src/app/model/day-status';
import { WeatherStatusEnum } from 'src/app/enum/weather-status-enum';

@Component({
  selector: 'app-next5days-cell',
  templateUrl: './next5days-cell.component.html',
  styleUrl: './next5days-cell.component.scss'
})
export class Next5daysCellComponent implements OnInit {
  @Input() dayStatus!: DayStatus;
  protected minWeatherStatus!: WeatherStatusEnum|undefined;
  protected maxWeatherStatus!: WeatherStatusEnum|undefined;

  ngOnInit(): void {
    this.minWeatherStatus = WeatherStatusEnum.getInstanceBySymbol(this.dayStatus.minStatus);
    this.maxWeatherStatus = WeatherStatusEnum.getInstanceBySymbol(this.dayStatus.maxStatus);
  }

}
