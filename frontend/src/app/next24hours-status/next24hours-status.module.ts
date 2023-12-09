import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';
import { Next24hoursCellComponent } from './next24hours-cell/next24hours-cell.component';
import { Next24hoursStatusComponent } from './next24hours-status/next24hours-status.component';



@NgModule({
  declarations: [
    Next24hoursStatusComponent,
    Next24hoursCellComponent
  ],
  imports: [
    CommonModule,
    SharedModule
  ],
  exports: [
    Next24hoursStatusComponent
  ]
})
export class Next24hoursStatusModule { }
