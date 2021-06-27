import { LoginService } from './../Service/login.service';
import { UserService } from './../Service/users.service';
import { Component, OnInit } from '@angular/core';
import { User } from '../model/user.model';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit{
  logged: boolean = false;
  player: boolean | undefined;
  bussiness: boolean | undefined;
  admin: boolean | undefined;
  userName: string = '';
  password: string = '';
  user: User | undefined;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public loginService: LoginService) {
    this.loginService.me().subscribe(
      r => this.refresh(r)
    );
  }

  ngOnInit(): void {
    this.loginService.me().subscribe(
      r => this.refresh(r)
    );
  }

  refresh(response: any){
    this.user = response as User;
    if (this.user !== undefined) {
      this.logged = true;
    }
    else {
      this.logged = false;
    }
    this.player = this.isPlayer();
    this.bussiness = this.isBussiness();
    this.admin = this.isAdmin();
  }

  reset(){
    this.user = undefined;
    this.logged = false;
    this.player = false;
    this.bussiness = false;
    this.admin = false;
  }

   logout(){
     this.loginService.logOut().subscribe(
       response => {
         this.reset();
         this.password = '';
         this.router.navigate(['/']);
       },
       error => {
         this.reset();
         this.password = '';
         this.router.navigate(['/']);
       }
     );
   }

   goPlayerProfile(){
    if (this.player){
      this.router.navigate(['player/', this.userName]);
    }else{
      this.router.navigate(['/error']);
    }
   }

   goBussinessProfile(){
    if (this.bussiness){
      this.router.navigate(['/bussiness', this.userName]);
    }else{
      this.router.navigate(['/error']);
    }
   }

   goTournamentManagment(){
    if (this.admin){
      this.router.navigate(['/tournamentManagment']);
    }else{
      this.router.navigate(['/error']);
    }
  }

  login(user: string, pass: string){
    this.loginService.logIn(user, pass).subscribe(
      response => {
        this.loginService.me().subscribe(
          response2 => {
            this.refresh(response2);
          }
        );
      },
      error => {
        this.reset();
        alert('Wrong credentials');
        this.password = '';
      }
    );
  }


  isAdmin() {
      return this.user && this.user.roles.indexOf('ADMIN') !== -1;
  }

  isPlayer(){
    return this.user && this.user.roles.indexOf('USER') !== -1;
  }

  isBussiness(){
    return this.user && this.user.roles.indexOf('BUSSINESS') !== -1;
  }

}
