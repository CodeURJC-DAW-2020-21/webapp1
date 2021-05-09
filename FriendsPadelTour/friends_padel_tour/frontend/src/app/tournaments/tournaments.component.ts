import { Tournament } from './../model/tournament.model';
import { Component, OnInit } from '@angular/core';
import { Player } from '../model/player.model';
import { User } from '../model/user.model';
import { TournamentsService } from '../Service/tournaments.service';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../Service/login.service';

@Component({
  selector: 'app-tournaments',
  templateUrl: './tournaments.component.html'
})
export class TournamentsComponent {

  tournamentToJoin: Tournament | undefined;
  p = 1;
  bussiness = false;
  player = false;
  logged = false;
  tournaments: Tournament[] = [];
  loggedUser: User | undefined;
  userDoubles: Player[] = [];
  tournamentId: number;
  doubleSelected: string = '';

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: TournamentsService, public loginService: LoginService) {
    this.tournamentId = activatedRoute.snapshot.params['id'];
   }

  joinATournament(tournamentId: number, doubleSelected: number){
    this.service.joinATournament(tournamentId, doubleSelected).subscribe(
        tournament => {
          alert('T con exito.');
          this.router.navigate(['/']);
         }
       );
      }

  seeMore(){
    this.p = this.p + 1;
  }

}
