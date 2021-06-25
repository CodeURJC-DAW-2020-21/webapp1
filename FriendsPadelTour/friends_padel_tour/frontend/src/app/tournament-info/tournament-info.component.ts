import { Tournament } from '../model/tournament.model';
import { Component, OnInit } from '@angular/core';
import { TournamentsService } from '../Service/tournaments.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-tournament-info',
  templateUrl: './tournament-info.component.html'
})
export class TournamentInfoComponent implements OnInit {

  tournament: Tournament | undefined;
  tournamentId: number;



  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: TournamentsService) {
    this.tournamentId = activatedRoute.snapshot.params['id'];
   }

  ngOnInit(): void {
    this.service.tournamentInfo(this.tournamentId).subscribe(
        t => this.tournament = t
        )
  }


}




