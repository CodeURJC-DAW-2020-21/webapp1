import { LoginService } from './../Service/login.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Player } from '../model/player.model';
import { playerRequest } from '../model/playerRequest.model';
import { User } from '../model/user.model';
import { SignUpService } from '../Service/signup.service';

@Component({
  selector: 'app-player-sign-up-form',
  templateUrl: './player-sign-up-form.component.html'
})
export class PlayerSignUpFormComponent implements OnInit {

  Category:number[] = [1,2,3,4,5,6]
  listProvinces:string[] = ["Álava","Albacete","Alicante","Almería","Ávila","Asturias"];

  player: Player | undefined;
  user: User;
  playerRequest: playerRequest | undefined;


  signUpPlayer(username:string,name :string,surname:string, email:string,password:string,location:string,division: string){
    this.user = {encodedPassword: password, username:username, roles: ['USER']}

    this.player = {username: username,name: name,surname:surname,email:email,  division: parseInt(division),hasImage:false,location:location,matchesLost: 0,mathesPlayed:0,mathcesWon:0,createdMatches:[],pendingMatches:[],playedMatches:[],user: this.user, score: 0,doubles:[],imagePath:''}
    this.playerRequest = {user: this.user, player: this.player}
    this.SignUpService.signUpPlayer(this.playerRequest).subscribe(
      data =>{
            window.alert("Usuario creado correctarmente");
            this.router.navigate(['/']);
      },
      error => console.error("Error al crear el jugador")
    )
  }


  constructor(private router: Router, private SignUpService: SignUpService, private loginService: LoginService) {
    this.user = {username: '', encodedPassword:'', roles:['']}
  }

  ngOnInit(): void {
  }

}
