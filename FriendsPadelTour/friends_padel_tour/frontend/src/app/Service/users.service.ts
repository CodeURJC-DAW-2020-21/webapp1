import { Observable } from 'rxjs/internal/Observable';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Player } from '../model/player.model';
import { Bussiness } from '../model/bussiness.model';

const BASE_URL = '/users';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  getBussiness(bussinessUserName: string): Observable<Bussiness> {
    throw new Error('Method not implemented.');
  }

  getPlayer(userName: string): Observable<Player> {
    return this.http.get(BASE_URL + '/player/' + userName).pipe() as Observable<Player>;
  }


  constructor(private http: HttpClient) { }
}
