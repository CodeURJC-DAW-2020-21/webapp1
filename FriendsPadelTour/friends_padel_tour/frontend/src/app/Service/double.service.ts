import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

const BASE_URL = '/doubles';

@Injectable({
  providedIn: 'root'
})
export class DoubleService {

constructor(private http: HttpClient) { }

}
