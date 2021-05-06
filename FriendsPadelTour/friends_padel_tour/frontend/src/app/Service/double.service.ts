import { Observable } from 'rxjs/internal/Observable';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Player } from '../model/player.model';

const BASE_URL = 'api/doubles';

@Injectable({
  providedIn: 'root'
})
export class DoubleService {

constructor(private http: HttpClient) { }

  getDoublesOf(user: string): Observable<Player[]>{
    return this.http.get(BASE_URL + '/of/' + user).pipe(
    ) as Observable<Player[]>;
  }

}
