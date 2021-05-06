import { UserService } from './../Service/users.service';
import { LoginService } from './../Service/login.service';
import { PadelMatch } from './../model/padelMatch.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatchesService } from '../Service/matches.service';
import { Player } from '../model/player.model';
import { User } from '../model/user.model';

@Component({
  selector: 'app-create-afriendly-match-form',
  templateUrl: './create-afriendly-match-form.component.html'
})
export class CreateAFriendlyMatchFormComponent implements OnInit {
  creator!: Player;
  division: number;
  fmProvince = '';
  fmcity = '';
  fmfacility = '';
  fmdate = '';
  fmtime = '';

  constructor(private router: Router, activatedRoute: ActivatedRoute,
              public service: MatchesService, public loginService: LoginService, public userService: UserService) {
    this.division = activatedRoute.snapshot.params['division'];
   }

  ngOnInit(): void {
    this.loginService.me().subscribe(
      loggedUser => this.userService.getPlayer((loggedUser as User).username).subscribe(
        player => this.creator = player
      )
    )
  }

  createMatch(){
    const anyFieldEmpty = this.fmProvince === '' || this.fmcity === '' || this.fmdate === ''
                          || this.fmfacility === '' || this.fmtime === '';
    if (anyFieldEmpty){
      alert('Rellene todos los campos por favor');
    }else{
      const newMatch: PadelMatch = {division: this.division, time: this.fmtime, date: this.fmdate,
         city: this.fmcity, province: this.fmProvince, facility: this.fmfacility, nPlayers: 0,
         hasWinner: false, playerCreator: this.creator, double1: null, double2: null, tournament: null,
         doubleWinner: null};
      this.service.postMatch(newMatch).subscribe(
        match => {
          alert('Partido creado con exito.');
          this.router.navigate(['/']);
        }
      );
    }
  }

}
