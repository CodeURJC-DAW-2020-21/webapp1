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
  p = 0;
  bussiness = false;
  player = false;
  logged = false;
  loggedUser: User | undefined;
  userDoubles: Player[] = [];
  tournamentId: number;
  doubleSelected: number = 0;

  constructor(private router: Router, activatedRoute: ActivatedRoute,public userService: UserService, public service: TournamentsService, public loginService: LoginService) {
    this.tournamentId = activatedRoute.snapshot.params['id'];
    loginService.me().subscribe(
      user => {this.loggedUser = user;
        this.bussiness = this.loggedUser.roles.includes("BUSSINESS");
        this.player = this.loggedUser.roles.includes("USER");
        this.logged = true;
      }
    );
  }

  ngOnInit(): void {
    this.service.getTournaments(this.p).subscribe(
      tournament => this.tournamentList = tournament
    );
  }

  //Añadir join dentro del popup porque el double selected se elige ahí con input
  joinATournament(tournamentId: number, doubleSelected:string){
    this.service.joinATournament(tournamentId, doubleSelected).subscribe(); //el double lo cojo del input del popup
  }

  //Pagination
  seeMore(){
    this.p = this.p + 1;
    this.service.getTournaments(this.p).subscribe(
      tournament => {
        for (let t of tournament){
            this.tournamentList?.push(t)}
        }
    );
  }

}
