import { Tournament } from './../model/tournament.model';
import { Component, OnInit } from '@angular/core';
import { Player } from '../model/player.model';
import { User } from '../model/user.model';

@Component({
  selector: 'app-tournaments',
  templateUrl: './tournaments.component.html'
})
export class TournamentsComponent implements OnInit {
  p = 1;
  bussiness = false;
  player = false;
  logged = false;
  tournaments: Tournament[] = [];
  loggedUser: User | undefined;
  userDoubles: Player[] = [];

  constructor() { }

  ngOnInit(): void {
  }

  seeMore(){
    this.p = this.p + 1;
  }

}
