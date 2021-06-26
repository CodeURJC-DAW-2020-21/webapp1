import { DoubleService } from './../Service/double.service';
import { Player } from './../model/player.model';
import { Component, OnInit } from '@angular/core';
import { User } from '../model/user.model';
import { ActivatedRoute, Router } from '@angular/router';
import { TournamentsService } from '../Service/tournaments.service';
import { LoginService } from '../Service/login.service';

@Component({
  selector: 'app-selectADoubleForATournament',
  templateUrl: './selectADoubleForATournament.component.html'
})
export class SelectADoubleForATournamentComponent implements OnInit {

  tournamentId : number = 1;
  user : User | undefined;
  userDoubles : Player[] = [];
  doubleSelected : string = '';

  constructor(private router: Router, activatedRoute: ActivatedRoute,
              public tournamentsService: TournamentsService, public loginService: LoginService, public doublesService: DoubleService) {
    this.tournamentId = activatedRoute.snapshot.params['tournamentId'];
   }

  ngOnInit() {
    this.loginService.me().subscribe(
      u => {
        this.user = u;
        this.doublesService.getDoublesOf(this.user.username).subscribe(
          doubles => this.userDoubles = doubles
        )
      }
    )
  }

  joinATournament(){
    if(this.tournamentId === undefined) this.tournamentId = 1;
    this.tournamentsService.joinATournament(this.tournamentId, this.doubleSelected).subscribe(
       response => this.router.navigate(["success","Exito al unirse al torneo"]),
       error => this.router.navigate(["**"])
    );

  }

}
