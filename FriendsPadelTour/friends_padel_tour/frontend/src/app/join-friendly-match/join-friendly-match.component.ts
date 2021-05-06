import { DoubleService } from './../Service/double.service';
import { UserService } from './../Service/users.service';
import { LoginService } from './../Service/login.service';
import { Observable } from 'rxjs/internal/Observable';
import { PadelMatch } from './../model/padelMatch.model';
import { MatchesService } from './../Service/matches.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Player } from '../model/player.model';
import { User } from '../model/user.model';

@Component({
  selector: 'app-join-friendly-match',
  templateUrl: './join-friendly-match.component.html'
})
export class JoinFriendlyMatchComponent implements OnInit {
  matchToJoin: PadelMatch | undefined;

  double1Joined = false;
  double2Joined = false;
  player1Joined = false;
  player2Joined = false;
  player3Joined = false;
  player4Joined = false;
  player1: Player | undefined;
  player3: Player | undefined;
  player4: Player | undefined;
  player2: Player | undefined;
  matchId: number;

  playerLogged: Player | undefined;
  userDoubles: Player[] = [];

  doubleSelected: string = '';

  constructor(private router: Router, activatedRoute: ActivatedRoute,
              public matchService: MatchesService,public loginService: LoginService, public userService: UserService,
              public doubleService: DoubleService) {
    this.matchId = activatedRoute.snapshot.params['id'];
   }

  ngOnInit(): void {
    this.matchService.getMatch(this.matchId).subscribe(
      match => this.refresh(match)
    );
  }

  private refresh(match: PadelMatch) {
    this.matchToJoin = match;
    if (this.matchToJoin.double1 === null){
      this.double1Joined = false;
      this.player1Joined = false;
      this.player2Joined = false;
    }else{
      this.double1Joined = true;
      this.player1Joined = this.matchToJoin.double1.player1 !== null;
      this.player1 = this.matchToJoin.double1.player1;
      this.player2Joined = this.matchToJoin.double1.player2 !== null;
      this.player2 = this.matchToJoin.double1.player2;
    }
    if (this.matchToJoin.double2 === null){
      this.double2Joined = false;
      this.player3Joined = false;
      this.player4Joined = false;
    }else{
      this.double2Joined = true;
      this.player3Joined = this.matchToJoin.double2.player1 !== null;
      this.player3 = this.matchToJoin.double2.player1;
      this.player4Joined = this.matchToJoin.double2.player2 !== null;
      this.player4 = this.matchToJoin.double2.player2;
    }
    this.loginService.me().subscribe(
      user => this.userService.getPlayer((user as User).username).subscribe(
        player => {
          this.playerLogged = player;
          this.doubleService.getDoublesOf(player.username).subscribe(
            doubles => {
              this.userDoubles = doubles;
              this.doubleSelected = this.userDoubles[0].username;
            }
          );
        }
      )
    );
  }

  joinLonely(id: number, slot: number){
    this.matchService.joinLonely(id, slot).subscribe(
      match => this.refresh(match)
    );
  }

  joinInDouble(id: number, slot: number, double: string){
    this.userService.getPlayer(double).subscribe(
      playerDouble => {
        this.matchService.joinInDouble(id, slot, playerDouble).subscribe(
          match => this.refresh(match)
        );
      }
    );
  }
}
