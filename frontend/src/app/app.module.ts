import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { Next24hoursStatusModule } from './next24hours-status/next24hours-status.module';
import { Next5daysStatusModule } from './next5days-status/next5days-status.module';
import { QualityIndexModule } from './quality-index/quality-index.module';
import { SearchGeoModule } from './search-geo/search-geo.module';
import { TodayStatusModule } from './today-status/today-status.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    CoreModule,
    SearchGeoModule,
    TodayStatusModule,
    Next5daysStatusModule,
    Next24hoursStatusModule,
    QualityIndexModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
