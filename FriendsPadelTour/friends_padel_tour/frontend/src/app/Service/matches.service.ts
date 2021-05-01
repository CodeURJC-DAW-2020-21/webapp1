import { HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";

const BASE_URL = '/matches';

@Injectable({providedIn : 'root'})
export class MatchesService{

  constructor(private http : HttpClient){

  }
}
