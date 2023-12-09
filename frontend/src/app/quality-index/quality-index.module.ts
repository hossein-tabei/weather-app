import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TodayStatusComponent } from '../today-status/today-status/today-status.component';
import { SharedModule } from '../shared/shared.module';
import { QualityIndexComponent } from './quality-index/quality-index.component';
import { AirElementsComponent } from './air-elements/air-elements.component';
import { AirElementItemComponent } from './air-element-item/air-element-item.component';

@NgModule({
  declarations: [
    QualityIndexComponent,
    AirElementsComponent,
    AirElementItemComponent
  ],
  imports: [
    CommonModule,
    SharedModule
  ],
  exports: [
    QualityIndexComponent
  ]
})
export class QualityIndexModule { }
