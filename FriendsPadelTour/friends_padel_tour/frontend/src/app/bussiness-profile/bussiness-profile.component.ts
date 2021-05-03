import { UserService } from './../Service/users.service';
import { Bussiness } from './../model/bussiness.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../model/user.model';

@Component({
  selector: 'app-bussiness-profile',
  templateUrl: './bussiness-profile.component.html'
})
export class BussinessProfileComponent implements OnInit {
  bussinessProfile: Bussiness | undefined;
  loggedUser: User | undefined;
  isExtern = true;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: UserService) {
    const bussinessUserName = activatedRoute.snapshot.params.userName;
    service.getBussiness(bussinessUserName).subscribe(
      bussiness => {
        this.bussinessProfile = bussiness;
      },
      error => console.error('Server error.')
    );
    this.isExtern = this.loggedUser !== undefined && this.loggedUser.userName !== bussinessUserName;
   }

  ngOnInit(): void {
  }

}
