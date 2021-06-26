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
  //tournamentId: number;
  logged = false;
  bussiness: boolean | undefined;
  userName: string = '';
  loggedUser: User | undefined;

  tournaments: Tournament[] | undefined;
  tournament: Tournament | undefined;

  //tournament: Tournament;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: TournamentsService) {
    //this.tournamentId = activatedRoute.snapshot.params['id'];
    this.service.getAllNonAccepted().subscribe(
      tournament => this.tournaments = tournament
    );
   }

  ngOnInit(): void {}

  acceptATournament(id: number|undefined){
    if(id){
      this.service.acceptTournament(id).subscribe(
      tournament => {
        alert('Torneo aceptado con exito.');
        this.router.navigate(['/']);
      });
    }
  }

  goBussinessProfile(){
    if(this.bussiness){
      this.router.navigate(['/bussiness', this.userName]);
    }else{
      this.router.navigate(['/error']);
    }
   }

  declineATournament(id: number|undefined){
    if(id){
      this.service.declineTournament(id).subscribe(
      tournament => {
        alert('Torneo rechazado con exito.');
        this.router.navigate(['/']);
      });
    }
  }


}
