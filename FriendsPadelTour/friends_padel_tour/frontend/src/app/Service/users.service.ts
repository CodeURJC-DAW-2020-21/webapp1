import { Observable } from 'rxjs/internal/Observable';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Player } from '../model/player.model';
import { Bussiness } from '../model/bussiness.model';

const BASE_URL = 'api/users';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  getPlayer(userName: string): Observable<Player> {
    return this.http.get(BASE_URL + '/player/' + userName).pipe() as Observable<Player>;
  }

  getBussiness(userName: string): Observable<Bussiness>{
    return this.http.get(BASE_URL + '/bussiness/' + userName).pipe() as Observable<Bussiness>;
  }

  updatePlayer(divison: number, password: string, id: number){
    return this.http.put(BASE_URL + '/player/' + id ,{password: password, division: divison}).pipe() as Observable<Player>;
  }

  updateBussiness(password: string, id: number){
    return this.http.put(BASE_URL + '/bussiness/' + id , {password: password}).pipe() as Observable<Bussiness>;
  }

  constructor(private http: HttpClient) { }
}
