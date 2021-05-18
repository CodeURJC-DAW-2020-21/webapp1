import { MatchesService } from './../Service/matches.service';
import { DoubleService } from './../Service/double.service';
import { UserService } from './../Service/users.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Player } from '../model/player.model';
import { User } from '../model/user.model';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { PadelMatch } from '../model/padelMatch.model';
import { LoginService } from '../Service/login.service';
import { error } from '@angular/compiler/src/util';

@Component({
  selector: 'app-player-profile',
  templateUrl: './player-profile.component.html'
})
export class PlayerProfileComponent implements OnInit {
  playerUserName: string;
  loggedUser: User | undefined;
  usersProfile: Player | undefined;
  isExtern: boolean = true;
  principalDouble: Player | undefined;
  userDoubles: Player[] = [];
  efectivity: number = 0;
  division: number= 0;
  usersImage: any;

  createdMatches: PadelMatch[] = [];
  pendingMatches: PadelMatch[] = [];
  playedMatches: PadelMatch[] = [];

  player: Player | undefined;

  @ViewChild("file")
  file: any;


  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: UserService, private doubleService: DoubleService,
              private login: LoginService, private matchesService: MatchesService) {
    this.playerUserName = activatedRoute.snapshot.params['userName'];
  }

  //falta pasarle al template los partidos


  ngOnInit(): void {
    this.service.getPlayer(this.playerUserName).subscribe(
      user => {
        this.player = user;
        this.usersProfile = user;
        this.login.me().subscribe(
          user => this.isExtern = user !== undefined && user.username !== this.playerUserName
        );
        this.doubleService.getDoublesOf(user.username).subscribe(
          doubles => {
            this.userDoubles = doubles;
            this.principalDouble = doubles.pop();
          }
        );
        this.matchesService.getCreatedMatchesOf(this.playerUserName).subscribe(
          matches => {
            this.createdMatches = matches;
          }
        );
        this.matchesService.getPendingMatchesOf(this.playerUserName).subscribe(
          matches => {
            this.pendingMatches = matches;
          }
        );
        this.matchesService.getPlayedMatchesOf(this.playerUserName).subscribe(
          matches => {
            this.playedMatches = matches;
          }
        );
        if (this.usersProfile.mathesPlayed!==0){
          this.efectivity = this.usersProfile.mathcesWon / this.usersProfile.mathesPlayed;
        }else{this.efectivity == 0}
      },
      error => console.error('Bad request')
    );
  }

  edit(pass: string){
    let div = this.division
    let id = this.usersProfile?.id
    if (id !== undefined)
    this.service.updatePlayer(div,pass,id).subscribe(
      player => {
        alert('Se ha editado correctamente sus datos')
        this.router.navigate(['/'])
      }

    )

  }
/*
  updateImage(image: any){
    //const profilePicture = this.file.nativeElement.files[0];
    console.log(image.files[0])
    const file: File = image.files[0];
    const reader = new FileReader()



    let id = this.usersProfile?.id


      reader.addEventListener('load', (event: any) => {
        if (id !== undefined /*&& profilePicture*/ /*){
        this.service.updateImage(id,file).subscribe(
          (res) => {alert('Se ha editado correctamente su imagen')
          this.router.navigate(['/'])},
          (err) => alert('Error uploading user image: ' + err)
          )
        }
      });
    reader.readAsDataURL(file);
  }
  */
  updateImage():void {

    const image = this.file.nativeElement.files[0];
    if (image) {
      let formData = new FormData();
      formData.append("profilePicture", image);
      if(this.player)
        this.service.updateImage(this.player, formData).subscribe(
          _ => alert('Imagen subida de forma correcta'),
          error => alert('Error uploading user image')
        );

    }
  }

  downloadImage(){
    if(this.player?.id){
      this.service.getImage(this.player.id).subscribe(
        image => {
          this.usersImage = image;
          return this.player?.imagePath;
        });
    }
  }

  selectMatchWinner(match: PadelMatch, slot: number){
      this.matchesService.selectWinner(match, slot).subscribe(
        _ => {
          this.router.navigate(['success', 'Exito eligiendo ganadores del partido']);
        }
      )
  }

  deleteMatch(id: number|undefined){
    if(id){
      this.matchesService.deleteMatch(id).subscribe(
        _ => {
          this.router.navigate(['success', 'Exito eliminando el partido']);
        }
      )
    }
  }

}
