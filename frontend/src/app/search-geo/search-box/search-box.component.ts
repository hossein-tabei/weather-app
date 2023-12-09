import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable, debounceTime } from 'rxjs';
import { Location } from 'src/app/model/location';
import { GeoFacadeService } from '../geo-facade.service';

@Component({
  selector: 'app-search-box',
  templateUrl: './search-box.component.html',
  styleUrls: ['./search-box.component.scss']
})
export class SearchBoxComponent {

  protected fcLocation = new FormControl<string>('');
  protected filteredLocations!: Observable<Location[]>;

  constructor(private geoFacadeService: GeoFacadeService) {

    this.fcLocation.valueChanges.pipe(debounceTime(300))
      .subscribe((searchClause: string|null) => {
        this.filteredLocations = searchClause?.trim()
                              ? this.geoFacadeService.searchGeo(searchClause)
                              : new Observable<Location[]>();
    });
  }

  protected onInputFocus(): void {
    if (typeof(this.fcLocation.value) !== 'string') {
      this.fcLocation.setValue('');
    }
  }

  protected onSelectedOption(selectedLocation: Location): void {
    this.geoFacadeService.saveLocationAndSummonForcasts(selectedLocation);
  }

}
