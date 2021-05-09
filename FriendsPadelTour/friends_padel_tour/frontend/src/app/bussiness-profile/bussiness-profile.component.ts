import { UserService } from './../Service/users.service';
import { Bussiness } from './../model/bussiness.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../model/user.model';

@Component({
  selector: 'app-bussiness-profile',
  templateUrl: './bussiness-profile.component.html'
})
export class BussinessProfileComponent {
  bussinessProfile: Bussiness | undefined;
  loggedUser: User | undefined;
  isExtern = true;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: UserService) {
    activatedRoute.params.subscribe(params =>{
      const bussinessUserName = params['userName'];
      service.getBussiness(bussinessUserName).subscribe(
        bussiness => {
          this.bussinessProfile = bussiness;
        },
        error => console.error('Server error.')
      );
      this.isExtern = this.loggedUser !== undefined && this.loggedUser.username !== bussinessUserName;
    });
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
