import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { pipe } from 'rxjs';
import { User } from '../model/user.model';

const BASE_URL = '/api/auth';

@Injectable({providedIn : 'root'})
export class LoginService{

  me() {
    return this.http.get('/api/users/me').pipe();
  }

  constructor(private http: HttpClient){
  }


  logIn(user: string, pass: string){
    return this.http.post(BASE_URL + '/login', { username: user, password: pass }, { withCredentials: true }).pipe();
  }

  logOut() {
    return this.http.post(BASE_URL + '/logout', { withCredentials: true }).pipe();
  }
}
