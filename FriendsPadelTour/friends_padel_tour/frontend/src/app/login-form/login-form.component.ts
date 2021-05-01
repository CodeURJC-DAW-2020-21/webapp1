import { Component, OnInit } from '@angular/core';
import { LoginService } from '../Service/login.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html'
})
export class LoginFormComponent implements OnInit {

  loginError : boolean = false;

  constructor(public loginService: LoginService) { }

  ngOnInit(): void {
  }

  logIn(event : any, user : string, pass : string){
    event.preventDefault();

    this.loginService.logIn(user, pass);
  }

}
