import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { Tournament } from '../model/tournament.model';

//Preguntar lo del page

const BASE_URL = '/api/tournaments';

@Injectable({
  providedIn: 'root'
})
export class TournamentsService {

  constructor(private http: HttpClient) { }

    getTournaments(pageNumber: number): Observable<Tournament[]>{
      return this.http.get(BASE_URL + '/acceptedTournaments?pageNumber=' + pageNumber).pipe(map(response => this.extractTournaments(response as any))
        ) as Observable<Tournament[]>;
    }

    private extractTournaments(response: any) {
      return response.content;
    }

    getAllAccepted():Observable<Tournament[]>{
      return this.http.get(BASE_URL + '/allAccepted').pipe(
      ) as Observable<Tournament[]>;
      }

    getAllNonAccepted():Observable<Tournament[]>{
      return this.http.get(BASE_URL + '/allNonAccepted').pipe(
      ) as Observable<Tournament[]>;
      }

    getNonAcceptedTournaments(pageNumber: number): Observable<Tournament[]>{
      return this.http.get(BASE_URL + '/nonAcceptedTournaments?pageNumber=' + pageNumber).pipe(map(response => (response as any))
        ) as Observable<Tournament[]>;
    }

    postTournament(tournament: Tournament): Observable<Tournament>{
      return this.http.post(BASE_URL + '/', tournament).pipe(
        )as Observable<Tournament>;
    }

    acceptTournament(id: number):Observable<Tournament>{
      return this.http.put(BASE_URL + '/acceptedTournament/' + id, id).pipe(
        ) as Observable<Tournament>;
    }

    declineTournament(id: number): Observable<Tournament>{
      return this.http.put(BASE_URL + '/delTournament/' + id, id).pipe(
        ) as Observable<Tournament>;
    }

    tournamentInfo(id: number): Observable<Tournament>{
      return this.http.get(BASE_URL + '/' + id).pipe(
        ) as Observable<Tournament>;
    }

    joinATournament(id: number, doubleSelected: string): Observable<Tournament>{
      return this.http.post(BASE_URL + '/acceptedTournament/' + id + '/double/',  doubleSelected).pipe(
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

    getAcceptedTournametsOf(bussiness:string): Observable<Tournament[]>{
        return this.http.get(BASE_URL + '/acceptedTournamentsOf/' + bussiness).pipe() as Observable<Tournament[]>;
    }



    getNotAcceptedTournamentsOf(bussiness:string): Observable<Tournament[]>{
      return this.http.get(BASE_URL + '/notAcceptedTournamentsOf/' + bussiness).pipe() as Observable<Tournament[]>;
  }
}
