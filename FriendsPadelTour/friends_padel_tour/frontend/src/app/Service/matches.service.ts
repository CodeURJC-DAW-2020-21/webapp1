import { PadelMatch } from './../model/padelMatch.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Player } from '../model/player.model';
import { catchError, map } from 'rxjs/operators';
import { throwError } from 'rxjs';

const BASE_URL = '/api/matches';

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

  getMatch(matchId: number): Observable<PadelMatch> {
    return this.http.get(BASE_URL + '/' + matchId.toString()).pipe(
    ) as Observable<PadelMatch>;
  }

  joinLonely(id: number, slot: number): Observable<PadelMatch> {
    return this.http.post(BASE_URL + '/' + id + '/player/' + slot.toString(), undefined).pipe(
    ) as Observable<PadelMatch>;
  }

  joinInDouble(id: number, slot: number, double: Player): Observable<PadelMatch> {
    return this.http.post(BASE_URL + '/' + id + '/double/' + slot.toString(), double).pipe(
    ) as Observable<PadelMatch>;
  }

  postMatch(newMatch: PadelMatch): Observable<PadelMatch> {
    return this.http.post(BASE_URL + '/', newMatch).pipe(
    )as Observable<PadelMatch>;
  }

  getPlayedMatchesOf(playerUserName: string): Observable<PadelMatch[]>{
    return this.http.get(BASE_URL + '/playedMatchesOf/' + playerUserName).pipe(
    ) as Observable<PadelMatch[]>;
  }
  getPendingMatchesOf(playerUserName: string): Observable<PadelMatch[]>{
    return this.http.get(BASE_URL + '/pendingMatchesOf/' + playerUserName).pipe(
      ) as Observable<PadelMatch[]>;
  }
  getCreatedMatchesOf(playerUserName: string): Observable<PadelMatch[]>{
    return this.http.get(BASE_URL + '/createdBy/' + playerUserName).pipe(
      ) as Observable<PadelMatch[]>;
  }

  selectWinner(match: PadelMatch, slot: number): Observable<PadelMatch> {
    return this.http.put(BASE_URL + '/' + match.id?.toString() + '/' + slot.toString(), match).pipe(
      )as Observable<PadelMatch>;
  }

  deleteMatch(id: number) {
    return this.http.delete(BASE_URL + '/' + id).pipe(
      )as Observable<PadelMatch>;
  }


}
