import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';
import { TodayStatusComponent } from './today-status/today-status.component';
import { TodyMoreInfoComponent } from './tody-more-info/tody-more-info.component';



@NgModule({
  declarations: [
    TodayStatusComponent,
    TodyMoreInfoComponent
  ],
  imports: [
    CommonModule,
    SharedModule
  ],
  exports: [
    TodayStatusComponent,
    TodyMoreInfoComponent
  ]
})
export class TodayStatusModule { }
