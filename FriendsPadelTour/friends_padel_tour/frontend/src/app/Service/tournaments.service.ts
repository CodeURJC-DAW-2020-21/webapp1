import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { Tournament } from '../model/tournament.model';

//Preguntar lo del page

const BASE_URL = '/tournaments'

@Injectable({
  providedIn: 'root'
})
export class TournamentsService {

  constructor(private http: HttpClient) { }

    getTournaments(pageNumber: number): Observable<Page<Tournament>>{
      return this.http.get(BASE_URL + '/AcceptedTournaments' + pageNumber).pipe(
        ) as Observable<Page<Tournament>>;
    }

    getNonAcceptedTournaments(pageNumber: number): Observable<Page<Tournament>>{
      return this.http.get(BASE_URL + '/nonAcceptedTournaments' + pageNumber).pipe(
        ) as Observable<Page<Tournament>>;
    }

    postTournament(tournament: Tournament): Observable<Tournament>{
      return this.http.post(BASE_URL + '/', tournament).pipe(
        )as Observable<Tournament>;   
    }

    acceptTournament(id: number): Observable<Tournament>{
      return this.http.put(BASE_URL + '/acceptedTournament', id).pipe(
        )as Observable<Tournament>;
    }

    declineTournament(id: number): Observable<Tournament>{
      return this.http.delete(BASE_URL + '/delTournament' + id).pipe(
        ) as Observable<Tournament>;
    }

    tournamentInfo(id: number): Observable<Tournament>{
      return this.http.get(BASE_URL + '/' + id).pipe(
        ) as Observable<Tournament>;
    }

    joinATournament(id: number, doubleSelected: number): Observable<Tournament>{
      return this.http.post(BASE_URL + '/acceptedTournament' + id + '/double/' + doubleSelected.toString(), undefined).pipe(
        ) as Observable<Tournament>;
    }

    deleteTournament(id:number): Observable<Tournament>{
      return this.http.delete(BASE_URL + '/' + id).pipe(
        ) as Observable<Tournament>;
    }

    tournamentWinner(id: number, winner: number): Observable<Tournament>{
      return this.http.put(BASE_URL + '/acceptedTournament/' + id + '/winner', winner).pipe(
        ) as Observable<Tournament>;
    }

    getATournament(id: number) {
      return this.http.get(BASE_URL + '/tournament' + id).pipe(
        ) as Observable<Tournament>;
    }

    handleError(err: Error){
        console.error(err.message);
        return throwError('Server error');
    }
}
