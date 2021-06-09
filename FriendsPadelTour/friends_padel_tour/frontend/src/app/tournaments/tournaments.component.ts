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

  tournament: Tournament | undefined;
  p = 1;
  bussiness = false;
  player = false;
  logged = false;
  tournaments: Tournament[] = [];
  loggedUser: User | undefined;
  userDoubles: Player[] = [];
  tournamentId: number;
  doubleSelected: number = 0;

  constructor(private router: Router, activatedRoute: ActivatedRoute,
              public service: TournamentsService, public loginService: LoginService) {
    this.tournamentId = activatedRoute.snapshot.params['id'];
    loginService.me().subscribe(//ver que clase de usuario esta loggeado
      userLogged => {
        switch (userLogged.roles[0]) {
          case 'ADMIN':
            this.logged = true;
            this.bussiness = false;
            this.player = false;
            break;
          case 'USER':
            this.logged = true;
            this.bussiness = false;
            this.player = true;
            break;
          case 'BUSSINESS':
            this.logged = true;
            this.bussiness = true;
            this.player = false;
            break;
          default:
            this.logged = false;
            this.bussiness = false;
            this.player = false;
            break;
        }
      }
    )
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
