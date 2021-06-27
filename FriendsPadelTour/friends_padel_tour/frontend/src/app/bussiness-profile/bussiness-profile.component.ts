import { UserService } from './../Service/users.service';
import { Bussiness } from './../model/bussiness.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../model/user.model';
import { TournamentsService } from '../Service/tournaments.service';
import { Tournament } from '../model/tournament.model';
import { LoginService } from '../Service/login.service';


@Component({
  selector: 'app-bussiness-profile',
  templateUrl: './bussiness-profile.component.html'
})
export class BussinessProfileComponent {
  bussinessProfile: Bussiness | undefined;
  loggedUser: User | undefined;
  isExtern = true;
  tournamentId: number;
  tournamentsNoAccepted: Tournament[] | undefined;
  tournamentsAccepted: Tournament[] | undefined;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: UserService, public tournamentService: TournamentsService, public login: LoginService) {
    this.tournamentId = activatedRoute.snapshot.params['id'];
    this.tournamentService.getAllNonAccepted().subscribe(
      tournament => this.tournamentsNoAccepted = tournament
    );
    this.tournamentService.getAllAccepted().subscribe(
      tournament => this.tournamentsAccepted = tournament
    );
    activatedRoute.params.subscribe(params =>{
      const bussinessUserName = params['userName'];
      service.getBussiness(bussinessUserName).subscribe(
        bussiness => {
          this.bussinessProfile = bussiness;
          this.login.me().subscribe(
            user => this.isExtern = user !== undefined && user.username !== bussinessUserName
          )
        },
        error => console.error('Server error.')
      );
    });
   }

  deleteATournament(id: number|undefined){
      if(id){
        this.tournamentService.deleteTournament(id).subscribe(
          _ => {
            window.location.reload()
            }
        );
      }
  }

   edit(pass: string){
    let id = this.bussinessProfile?.id
    if (id !== undefined)
    this.service.updateBussiness(pass,id).subscribe(
      player => {
        alert('Se ha editado correctamente sus datos')
        this.router.navigate(['/'])
      }

    )
   }

}
