import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DTOResultWrapper } from '../model/dto-result-wrapper';
import { Location } from '../model/location';

@Injectable({
  providedIn: 'root'
})
export class HttpGeoService {
  readonly GEO_SEARCH_PATH: string = '/api/geo/search';

  constructor(private http: HttpClient) {}

  searchGeo(searchClause: string): Observable<DTOResultWrapper<Location[]>> {
    let queryParams = new HttpParams().append('searchClause', searchClause);
    return this.http.get<DTOResultWrapper<Location[]>>(this.GEO_SEARCH_PATH, {params: queryParams});
  }
}
