import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-info-message',
  templateUrl: './info-message.component.html'
})
export class InfoMessageComponent implements OnInit {

  message: string = '';

  constructor(private router: Router, activatedRoute: ActivatedRoute) {
    this.message = activatedRoute.snapshot.params['message'];
  }

  ngOnInit(): void {
  }

}
