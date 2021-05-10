import { UserService } from './../Service/users.service';
import { Bussiness } from './../model/bussiness.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../model/user.model';
import { LoginService } from '../Service/login.service';

@Component({
  selector: 'app-bussiness-profile',
  templateUrl: './bussiness-profile.component.html'
})
export class BussinessProfileComponent {
  bussinessProfile: Bussiness | undefined;
  loggedUser: User | undefined;
  isExtern = true;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: UserService, private login: LoginService) {
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
