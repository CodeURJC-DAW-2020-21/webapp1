import { DoubleService } from './../Service/double.service';
import { UserService } from './../Service/users.service';
import { Component, OnInit } from '@angular/core';
import { Player } from '../model/player.model';
import { User } from '../model/user.model';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-player-profile',
  templateUrl: './player-profile.component.html'
})
export class PlayerProfileComponent implements OnInit {
  loggedUser: User | undefined;
  usersProfile: Player | undefined;
  isExtern: boolean = true;
  principalDouble: Player | undefined;
  userDoubles: Player[] = [];
  efectivity: number = 0;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: UserService, private doubleService : DoubleService) {
    const playerUserName = activatedRoute.snapshot.params['userName'];
    service.getPlayer(playerUserName).subscribe(
      user => {
        this.usersProfile = user;
        this.doubleService.getDoublesOf(user.username).subscribe(
          doubles => {
            this.userDoubles = doubles;
            this.principalDouble = doubles.pop();
          }
        );
        if (this.usersProfile.mathesPlayed!==0){
          this.efectivity = this.usersProfile.mathcesWon / this.usersProfile.mathesPlayed;
        }else{this.efectivity == 0}
      },
      error => console.error('Bad request')
    );
    this.isExtern = this.loggedUser !== undefined && this.loggedUser.username !== playerUserName;
  }

  ngOnInit(): void {
  }

  generateChart(){
    this.router.navigate([])
  }

}
