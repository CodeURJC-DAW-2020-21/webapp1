import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { catchError } from "rxjs/operators";
import { Bussiness } from "../model/bussiness.model";
import { Player } from "../model/player.model";



const BASE_URL = '/api/users'


@Injectable({providedIn: 'root'})
export class SignUpService{



    constructor(private http: HttpClient){

    }

    signUpBussiness(Bussiness: Bussiness){
        
        return this.http.post(BASE_URL + '/bussiness/' , Bussiness).pipe(
            //catchError(error => this.handleError)
        ) as Observable<Bussiness>
    }

    signUpPlayer(Player: Player){
        return this.http.post(BASE_URL + '/player',Player).pipe(
            //catchError(error => this.handleError)
        )   as Observable<Player>;

    }







}