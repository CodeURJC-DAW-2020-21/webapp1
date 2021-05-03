import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {
  logged = false;
  user = false;
  bussiness = false;
  admin = false;
  userName = '';

  constructor() { }

  ngOnInit(): void {
  }

}
