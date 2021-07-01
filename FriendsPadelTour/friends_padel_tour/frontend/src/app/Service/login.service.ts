import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, pipe } from 'rxjs';
import { User } from '../model/user.model';

const BASE_URL = '/api/auth';

@Injectable({providedIn : 'root'})
export class LoginService{

  me(): Observable<User> {
    return this.http.get('/api/users/me').pipe() as Observable<User>;
  }

  constructor(private http: HttpClient){
  }


  logIn(user: string, pass: string){
    return this.http.post('/api/auth/login', { username: user, password: pass }, { withCredentials: true }).pipe();
  }

  logOut() {
    return this.http.post('/api/auth/logout', { withCredentials: true }).pipe();
  }
}
