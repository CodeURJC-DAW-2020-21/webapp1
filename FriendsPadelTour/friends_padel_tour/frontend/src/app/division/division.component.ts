import { catchError } from 'rxjs/operators';
import { LoginService } from './../Service/login.service';
import { MatchesService } from './../Service/matches.service';
import { PadelMatch } from './../model/padelMatch.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Player } from '../model/player.model';
import { Observable } from 'rxjs/internal/Observable';

@Component({
  selector: 'app-division',
  templateUrl: './division.component.html',
})
export class DivisionComponent implements OnInit{
  matches: PadelMatch[] | undefined;
  top10: Player[] | undefined;
  num = 1;
  logged = false;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: MatchesService, public loginService: LoginService) {
   }

  ngOnInit(): void {
    this.service.getMatchesOfDivision(this.num).subscribe(
      matches => this.matches = matches,
      error => console.log(error)
    );
    this.service.getTop10(this.num).subscribe(
      ranking => this.top10 = ranking,
      error => console.log(error)
    );
    this.loginService.me().pipe().subscribe(
      response => this.logged = true,
      error => this.logged = false);
  }


}
