import { DoubleService } from './../Service/double.service';
import { UserService } from './../Service/users.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Player } from '../model/player.model';
import { User } from '../model/user.model';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { PadelMatch } from '../model/padelMatch.model';

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

  player: Player;

  @ViewChild("file")
  file: any;


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

  //falta pasarle al template los partidos 


  ngOnInit(): void {
  }

  edit(divison: string, pass: string){
    let div = parseInt(divison)
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
  updateImage(player: Player): void {

    const image = this.file.nativeElement.files[0];
    if (image) {
      let formData = new FormData();
      formData.append("imageFile", image);
      this.service.updateImage(this.player, formData).subscribe()
      
    }
  }

}
