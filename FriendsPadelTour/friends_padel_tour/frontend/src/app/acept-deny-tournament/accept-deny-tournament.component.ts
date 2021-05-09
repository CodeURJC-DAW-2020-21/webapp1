import { UserService } from '../Service/users.service';
import { LoginService } from '../Service/login.service';
import { PadelMatch } from '../model/padelMatch.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TournamentsService } from '../Service/tournaments.service';
import { Player } from '../model/player.model';
import { User } from '../model/user.model';
import { Tournament } from '../model/tournament.model';

@Component({
  selector: 'app-accept-deny-tournament',
  templateUrl: './accept-deny-tournament.component.html'
})
export class AcceptDenyTournamentComponent implements OnInit {
  creator!: Player;
  division: number;
  fmProvince = '';
  fmcity = '';
  fmfacility = '';
  fmdate = '';
  fmtime = '';

  constructor(private router: Router, activatedRoute: ActivatedRoute,
              public service: TournamentsService, public loginService: LoginService, public userService: UserService) {
    this.division = activatedRoute.snapshot.params['division'];
   }

  ngOnInit(): void {
    this.loginService.me().subscribe(
      loggedUser => this.userService.getPlayer((loggedUser as User).username).subscribe(
        player => this.creator = player
      )
    )
  }

  acceptATournament(){    
      this.service.acceptTournament().subscribe(
        tournament => {
          alert('Torneo aceptado con exito.');
          this.router.navigate(['/']);
        }
      );
    }
  }

}
