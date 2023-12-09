import { Component, Input } from '@angular/core';
import { AirElementEnum } from 'src/app/enum/air-element-enum';

@Component({
  selector: 'app-air-element-item',
  templateUrl: './air-element-item.component.html',
  styleUrl: './air-element-item.component.scss'
})
export class AirElementItemComponent {
  @Input() elementName!: AirElementEnum|undefined;
  @Input() value!: number;
}
