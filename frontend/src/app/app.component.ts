import { Component } from '@angular/core';
import { RootFacadeService } from './root-facade.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  isTodayStatusReady: boolean = false;
  isTodayMoreInfoReady: boolean = false;
  isNext5daysReady: boolean = false;
  isNext24hoursReady: boolean = false;
  isQualityIndexReady: boolean = false;

  constructor(private facadeService: RootFacadeService) {
    facadeService.getCurrentStatus()
    .subscribe(result => {
      if (Object.keys(result).length > 0) {
        this.isTodayStatusReady = true;
        this.isTodayMoreInfoReady = true;
      }
    });

    facadeService.getNext5DaysStatusList().subscribe(result => this.isNext5daysReady = result.length > 0);
    facadeService.getNext24HoursStatusList().subscribe(result => this.isNext24hoursReady = result.length > 0);
    facadeService.getAirQualityIndex().subscribe(result => this.isQualityIndexReady = Object.keys(result).length > 0);
  }
}
