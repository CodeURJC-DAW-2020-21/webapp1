import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { PadelMatch } from '../model/padelMatch.model';
import { Player } from '../model/player.model';
import { LoginService } from '../Service/login.service';
import { MatchesService } from '../Service/matches.service';

@Component({
  selector: 'app-division2',
  templateUrl: './division2.component.html'
})
export class Division2Component implements OnInit {
  matches: PadelMatch[] | undefined;
  top10: Player[] | undefined;
  num = 2;
  logged = false;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: MatchesService, private loginService: LoginService) {
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
      error => alert('Error searching user'));
  }


}
