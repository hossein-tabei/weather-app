import { Component } from '@angular/core';
import { DayStatus } from 'src/app/model/day-status';
import { Next5daysFacadeService } from '../next5days-facade.service';

@Component({
  selector: 'app-next5days-status',
  templateUrl: './next5days-status.component.html',
  styleUrl: './next5days-status.component.scss'
})
export class Next5daysStatusComponent {

  protected next5DaysStatuses!: DayStatus[];

  constructor(private facadeService: Next5daysFacadeService) {
    facadeService.getNext5DaysStatusList()
    .subscribe(next5DaysStatuses => this.next5DaysStatuses = next5DaysStatuses);
  }
}
