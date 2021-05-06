import { PadelMatch } from './../model/padelMatch.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Player } from '../model/player.model';
import { catchError, map } from 'rxjs/operators';
import { throwError } from 'rxjs';

const BASE_URL = 'api/matches';

@Injectable({providedIn : 'root'})
export class MatchesService{


  constructor(private http: HttpClient){

  }
  getTop10(num: number): Observable<Player[]> {
    return this.http.get(BASE_URL + '/division/' + num.toString() + '/ranking').pipe(
    ) as Observable<Player[]>;
  }

  getMatchesOfDivision(num: number): Observable<PadelMatch[]>{
    return this.http.get(BASE_URL + '/division/' + num.toString()).pipe(
    ) as Observable<PadelMatch[]>;
  }
}
