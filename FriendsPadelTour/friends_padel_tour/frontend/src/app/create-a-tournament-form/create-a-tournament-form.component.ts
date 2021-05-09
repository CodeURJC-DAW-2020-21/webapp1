import { UserService } from '../Service/users.service';
import { LoginService } from '../Service/login.service';
import { PadelMatch } from '../model/padelMatch.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TournamentsService } from '../Service/tournaments.service';
import { Player } from '../model/player.model';
import { User } from '../model/user.model';
import { Tournament } from '../model/tournament.model';
import { DoubleOfPlayers } from '../model/doubleOfPlayers.model';

@Component({
  selector: 'app-create-a-tournament-form',
  templateUrl: './create-a-tournament-form.component.html'
})
export class CreateATournamentFormComponent implements OnInit {
    
  tournament: Tournament | undefined;   

  constructor(private router: Router, activatedRoute: ActivatedRoute,
              public tournamentService: TournamentsService, public loginService: LoginService, public userService: UserService) {
   }

  ngOnInit(): void {
    this.loginService.me().pipe().subscribe(
      response => this.logged = false,
      error => alert('Error searching user'));
  }

//Cuantos parámetros tengo que meter


  createTournament(name: string, description: string, firstPrize: number, secondPrize:number, minCouples: number,
     maxCouples: number, facility: string, adress: string, city: string, localization: string,
     postalCode: number, inscriptionStartDate: string, tournamentStartDate: string, inscriptionFinishDate:string, tournamentFinishDate: string, category: number){
    const double: DoubleOfPlayers;
      this.tournament = {name: name, desription: description, firstPrize: firstPrize, secondPrize: secondPrize, minCouples: minCouples, 
      maxCouples: maxCouples, facility: facility, adress: adress, city: city, localization: localization,
      postalCode: postalCode, inscriptionStartDate: inscriptionStartDate, tournamentStartDate: tournamentStartDate, inscriptionFinishDate: inscriptionFinishDate,
      tournamentFinishDate: tournamentFinishDate, registeredCouples:0, isFull: false, accepted: false,
    finished: false, players: [], category: category, secondWinningCouple: double }
  
      this.tournamentService.postTournament(this.tournament).subscribe(
        tournament => {
          alert('Torneo creado con exito.');
          this.router.navigate(['/']);
        }
      );
    }
  }
  

}
