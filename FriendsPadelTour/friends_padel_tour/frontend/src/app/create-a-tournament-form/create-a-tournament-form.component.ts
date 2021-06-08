import { Player } from './../model/player.model';
import { UserService } from '../Service/users.service';
import { LoginService } from '../Service/login.service';
import { PadelMatch } from '../model/padelMatch.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TournamentsService } from '../Service/tournaments.service';
import { User } from '../model/user.model';
import { Tournament } from '../model/tournament.model';
import { DoubleOfPlayers } from '../model/doubleOfPlayers.model';
import { Bussiness } from '../model/bussiness.model';

@Component({
  selector: 'app-create-a-tournament-form',
  templateUrl: './create-a-tournament-form.component.html'
})
export class CreateATournamentFormComponent implements OnInit {
  creator!: Bussiness;
  tournament: Tournament | undefined;

  constructor(private router: Router, activatedRoute: ActivatedRoute,
              public tournamentService: TournamentsService, public loginService: LoginService, public userService: UserService) {
   }

   ngOnInit(): void {
    this.loginService.me().subscribe(
      loggedUser => this.userService.getBussiness((loggedUser as User).username).subscribe(
        bussiness => this.creator = bussiness
      )
    )
  }

//Cuantos parámetros tengo que meter




  createTournament(name: string, description: string, firstPrize: string, secondPrize:string, minCouples: string,
     maxCouples: string, facility: string, adress: string, city: string, localization: string,
     postalCode: string, inscriptionStartDate: string, tournamentStartDate: string, inscriptionFinishDate:string, tournamentFinishDate: string, category: string){
     let isFull = false;
     let registeredCouples = 0
     let accepted= false
     let finished = false
     let firstWinningCouple = null
     let secondWinningCouple = null
     let players: DoubleOfPlayers[] = []

      this.tournament = {name: name, desription: description, firstPrize: parseInt(firstPrize), secondPrize: parseInt(secondPrize), minCouples: parseInt(minCouples),
      maxCouples: parseInt(maxCouples), facility: facility, adress: adress, city: city, localization: localization,
      postalCode: parseInt(postalCode), inscriptionStartDate: inscriptionStartDate, tournamentStartDate: tournamentStartDate, inscriptionFinishDate: inscriptionFinishDate,
      tournamentFinishDate: tournamentFinishDate,registeredCouples: registeredCouples , isFull: isFull, accepted: accepted,
      finished: finished, players: players, category: parseInt(category), secondWinningCouple: secondWinningCouple, firstWinningCouple: firstWinningCouple }


      this.tournamentService.postTournament(this.tournament).subscribe(
        tournament => {
          alert('Torneo creado con exito.');
          this.router.navigate(['/']);
        }
      );
    }
  }



