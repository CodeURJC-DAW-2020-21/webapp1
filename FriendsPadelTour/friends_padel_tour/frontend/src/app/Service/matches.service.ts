import { PadelMatch } from './../model/padelMatch.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { catchError } from 'rxjs/operators';
import { Player } from '../model/player.model';
import { throwError } from 'rxjs';

const BASE_URL = '/matches';

@Injectable({providedIn : 'root'})
export class MatchesService{


  constructor(private http: HttpClient){

  }
  getTop10(num: number): Observable<Player[]> {
    return this.http.get(BASE_URL + '/division/' + num.toString() + '/ranking').pipe(
      catchError(error => this.handleError(error))
    ) as Observable<Player[]>;
  }

  getMatchesOfDivision(num: number): Observable<PadelMatch[]>{
    return this.http.get(BASE_URL + '/division/' + num.toString()).pipe(
      catchError(error => this.handleError(error))
    ) as Observable<PadelMatch[]>;
  }

  handleError(err: Error){
    console.error(err.message);
    return throwError('Server error');
  }
}
