import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {
  logged : boolean = false;
  user : boolean = false;
  bussiness : boolean = false;
  userName : string = '';
  constructor() { }

  ngOnInit(): void {
  }

}
