import { UserService } from '../Service/users.service';
import { LoginService } from '../Service/login.service';
import { PadelMatch } from '../model/padelMatch.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TournamentsService } from '../Service/tournaments.service';
import { Player } from '../model/player.model';
import { User } from '../model/user.model';
import { Tournament } from '../model/tournament.model';
import { Bussiness } from '../model/bussiness.model';

@Component({
  selector: 'app-accept-deny-tournament',
  templateUrl: './accept-deny-tournament.component.html'
})
export class AcceptDenyTournamentComponent implements OnInit {
  creator!: Bussiness;
  tournamentId: number;
  logged = false;
  bussiness: boolean | undefined;
  userName: string = '';

  tournaments: Tournament[] | undefined;

  pendingTournaments: Tournament[];
  //tournament: Tournament;

  constructor(private router: Router, activatedRoute: ActivatedRoute,
              public service: TournamentsService, public loginService: LoginService, public userService: UserService) {
    this.tournamentId = activatedRoute.snapshot.params['id'];
    this.pendingTournaments = []
    service.getNonAcceptedTournaments(3).subscribe(
      //tour => this.pendingTournaments = tour

    )
    /*const id = activatedRoute.snapshot.params['id'];
        service.getATournament(id).subscribe(
            tournament => this.tournament = this.tournament,
            error => console.error(error)
        );*/
   }

  ngOnInit(): void {
    this.loginService.me().subscribe(
      loggedUser => this.userService.getBussiness((loggedUser as User).username).subscribe(
        bussiness => this.creator = bussiness
      )
    )
  }

  acceptATournament(){
      this.service.acceptTournament(this.tournamentId).subscribe(
        tournament => {
          alert('Torneo aceptado con exito.');
          this.router.navigate(['/']);
        }
      );
  }

  goBussinessProfile(){
    if(this.bussiness){
      this.router.navigate(['/bussiness', this.userName]);
    }else{
      this.router.navigate(['/error']);
    }
   }

  declineTournament(){
    this.service.declineTournament(this.tournamentId).subscribe(
      tournament => {
        alert('Torneo rechazado con exito.');
        this.router.navigate(['/']);
      }
    );
}


}
