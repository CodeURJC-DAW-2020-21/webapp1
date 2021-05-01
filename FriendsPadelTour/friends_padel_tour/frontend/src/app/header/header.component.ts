import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {
  logged: boolean = false;
  user: boolean = false;
  bussiness : boolean = false;
  admin : boolean = false;
  userName : string = '';

  constructor() { }

  ngOnInit(): void {
  }

}
