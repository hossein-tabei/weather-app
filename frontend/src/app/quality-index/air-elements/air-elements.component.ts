import { Component, Input, OnInit } from '@angular/core';
import { PolutionIndex } from 'src/app/model/polution-index';
import { AirElementEnum } from 'src/app/enum/air-element-enum';

interface AirElementItem {
  elementName: AirElementEnum|undefined;
  value: number;
}

@Component({
  selector: 'app-air-elements',
  templateUrl: './air-elements.component.html',
  styleUrl: './air-elements.component.scss'
})
export class AirElementsComponent implements OnInit {

  @Input() polutionIndexes: PolutionIndex[] = [];
  protected airElements: Array<AirElementItem> = []

  ngOnInit(): void {
    for (let item of this.polutionIndexes) {
      let airElementEnum = AirElementEnum.getInstanceBySymbol(item.chemicalName);
      this.airElements.push({elementName: airElementEnum, value: item.amount});
    }
  }

}
