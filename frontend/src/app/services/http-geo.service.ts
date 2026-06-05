import {HttpClient, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {ServerError} from '../model/server-error';
import {Location} from '../model/location';

@Injectable({
  providedIn: 'root'
})
export class HttpGeoService {
  readonly LOCATION_SEARCH_PATH: string = '/api/v1/weather/location/search';

  constructor(private http: HttpClient) {}

  searchGeo(searchTerm: string): Observable<Location[]> {
    let queryParams = new HttpParams().append('searchTerm', searchTerm);
    return this.http.get<Location[]>(this.LOCATION_SEARCH_PATH, {params: queryParams});
  }
}
