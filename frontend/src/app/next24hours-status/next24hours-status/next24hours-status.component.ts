import { Component } from '@angular/core';
import { Next24hoursFacadeService } from '../next24hours-facade.service';
import { HourStatus } from 'src/app/model/hour-status';

interface Next24hoursCell {
  temperature: number;
  weatherIcon: string;
  windSpeed: number;
  hourOfDay: string;
}
@Component({
  selector: 'app-next24hours-status',
  templateUrl: './next24hours-status.component.html',
  styleUrl: './next24hours-status.component.scss'
})
export class Next24hoursStatusComponent {

  protected next24HoursStatuses!: HourStatus[];

  constructor(private facadeService: Next24hoursFacadeService) {
    facadeService.getNext24HoursStatusList()
    .subscribe(next24HoursStatuses => this.next24HoursStatuses = next24HoursStatuses);
  }

  next24HoursArray: Array<Next24hoursCell> = [
    {temperature: 14, weatherIcon: 'cloud', windSpeed: 7.4, hourOfDay: 'Now'},
    {temperature: 14, weatherIcon: 'cloud', windSpeed: 7.4, hourOfDay: '23:00'},
    {temperature: 14, weatherIcon: 'cloud', windSpeed: 7.4, hourOfDay: '01:00'},
    {temperature: 14, weatherIcon: 'cloud', windSpeed: 7.4, hourOfDay: '02:00'},
    {temperature: 14, weatherIcon: 'cloud', windSpeed: 7.4, hourOfDay: '03:00'},
    {temperature: 14, weatherIcon: 'cloud', windSpeed: 7.4, hourOfDay: '04:00'},
    {temperature: 14, weatherIcon: 'cloud', windSpeed: 7.4, hourOfDay: '05:00'},
    {temperature: 14, weatherIcon: 'cloud', windSpeed: 7.4, hourOfDay: '06:00'},
    {temperature: 14, weatherIcon: 'sunny', windSpeed: 7.4, hourOfDay: '07:00'},
    {temperature: 14, weatherIcon: 'sunny', windSpeed: 7.4, hourOfDay: '08:00'},
    {temperature: 14, weatherIcon: 'sunny', windSpeed: 7.4, hourOfDay: '09:00'},
    {temperature: 14, weatherIcon: 'sunny', windSpeed: 7.4, hourOfDay: '10:00'},
    {temperature: 14, weatherIcon: 'sunny', windSpeed: 7.4, hourOfDay: '11:00'},
    {temperature: 14, weatherIcon: 'sunny', windSpeed: 7.4, hourOfDay: '12:00'},
    {temperature: 14, weatherIcon: 'sunny', windSpeed: 7.4, hourOfDay: '13:00'},
    {temperature: 14, weatherIcon: 'sunny', windSpeed: 7.4, hourOfDay: '14:00'},
    {temperature: 14, weatherIcon: 'sunny', windSpeed: 7.4, hourOfDay: '15:00'},
    {temperature: 14, weatherIcon: 'sunny', windSpeed: 7.4, hourOfDay: '16:00'},
    {temperature: 14, weatherIcon: 'sunny', windSpeed: 7.4, hourOfDay: '17:00'},
    {temperature: 14, weatherIcon: 'sunny', windSpeed: 7.4, hourOfDay: '18:00'},
    {temperature: 14, weatherIcon: 'sunny', windSpeed: 7.4, hourOfDay: '19:00'},
    {temperature: 14, weatherIcon: 'sunny', windSpeed: 7.4, hourOfDay: '20:00'},
    {temperature: 14, weatherIcon: 'sunny', windSpeed: 7.4, hourOfDay: '21:00'},
    {temperature: 14, weatherIcon: 'sunny', windSpeed: 7.4, hourOfDay: '22:00'},
  ];
}
