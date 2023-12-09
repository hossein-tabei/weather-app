import { Component } from '@angular/core';
import { AirQualityIndex } from 'src/app/model/air-quality-index';
import { AqiFacadeService } from '../aqi-facade.service';

@Component({
  selector: 'app-quality-index',
  templateUrl: './quality-index.component.html',
  styleUrl: './quality-index.component.scss'
})
export class QualityIndexComponent {

  protected airQualityIndex!: AirQualityIndex;

  constructor(private aqiFacadeService: AqiFacadeService) {
    aqiFacadeService.getAirQualityIndex().subscribe(airQualityIndex => {
      this.airQualityIndex = airQualityIndex;
    });
  }

}
