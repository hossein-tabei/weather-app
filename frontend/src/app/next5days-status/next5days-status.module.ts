import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Next5daysStatusComponent } from './next5days-status/next5days-status.component';
import { SharedModule } from '../shared/shared.module';
import { Next5daysCellComponent } from './next5days-cell/next5days-cell.component';



@NgModule({
  declarations: [
    Next5daysStatusComponent,
    Next5daysCellComponent
  ],
  imports: [
    CommonModule,
    SharedModule
  ],
  exports: [
    Next5daysStatusComponent
  ]
})
export class Next5daysStatusModule { }
