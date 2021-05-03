import { MatchesService } from './../Service/matches.service';
import { PadelMatch } from './../model/padelMatch.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Player } from '../model/player.model';
import { Observable } from 'rxjs/internal/Observable';

@Component({
  selector: 'app-division',
  templateUrl: './division.component.html'
})
export class DivisionComponent implements OnInit {
  $matches: Observable<PadelMatch[]>;
  $top10: Observable<Player[]>;
  num: number;
  logged = false;

  constructor(private router: Router, activatedRoute: ActivatedRoute, service: MatchesService) {
    this.num = activatedRoute.snapshot.params['num'];
    this.$matches = service.getMatchesOfDivision(this.num);
    this.$top10 = service.getTop10(this.num);
   }

  ngOnInit(): void {
  }

}
