import { Observable } from 'rxjs/internal/Observable';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Player } from '../model/player.model';
import { DoubleOfPlayers } from '../model/doubleOfPlayers.model';

const BASE_URL = '/api/doubles';

@Injectable({
  providedIn: 'root'
})
export class DoubleService {

  constructor(private http: HttpClient) { }

  makeDoubleWith(p1: Player, p2: Player):Observable<DoubleOfPlayers> {
    const newDouble = {player1: p1, player2: p2 };
    return this.http.post(BASE_URL, newDouble).pipe() as Observable<DoubleOfPlayers>;
  }

  getDoublesOf(user: string): Observable<Player[]>{
    return this.http.get(BASE_URL + '/of/' + user).pipe(
    ) as Observable<Player[]>;
  }

}
