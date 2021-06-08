import { Tournament } from './../model/tournament.model';
import { Component, OnInit } from '@angular/core';
import { Player } from '../model/player.model';
import { User } from '../model/user.model';
import { TournamentsService } from '../Service/tournaments.service';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../Service/login.service';
import { UserService } from '../Service/users.service';

@Component({
  selector: 'app-tournaments',
  templateUrl: './tournaments.component.html'
})
export class TournamentsComponent {

  tournamentList: Tournament[] | undefined;
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

  constructor(private router: Router, activatedRoute: ActivatedRoute,public userService: UserService, public service: TournamentsService, public loginService: LoginService) {
    this.tournamentId = activatedRoute.snapshot.params['id'];
    const bussinessUserName = params['userName'];
    userService.getBussiness(bussinessUserName).subscribe(
        bussiness => {
          this.bussinessProfile = bussiness;
          this.login.me().subscribe(
            user => this.isExtern = user !== undefined && user.username !== bussinessUserName
          )
        },
        error => console.error('Server error.')
      );
   }
   }

   ngOnInit(): void {
    this.service.getTournaments(this.p).subscribe(
      tournaments => this.tournament = tournaments,
      error => console.log(error)
    );

   }

  joinATournament(tournamentId: number, doubleSelected: number){
    this.service.joinATournament(tournamentId, doubleSelected).subscribe(
        tournament => {
          alert('T con exito.');
          this.router.navigate(['/']);
         }
       );
      }

  tournamentInfo(tournamentId: number){
    this.service.tournamentInfo(tournamentId).subscribe(
      tournament => {
        alert('Info del torneo.');
        this.router.navigate(['/']);
       }
     );
    }

  seeMore(){
    this.p = this.p + 1;
  }

}
