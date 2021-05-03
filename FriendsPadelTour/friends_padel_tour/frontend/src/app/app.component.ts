import { UserService } from './Service/users.service';
import { TournamentsService } from './Service/tournaments.service';
import { PdfGeneratorService } from './Service/pdf-generator.service';
import { MatchesService } from './Service/matches.service';
import { LoginService } from './Service/login.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  providers: [LoginService,
    MatchesService,
    PdfGeneratorService,
    TournamentsService,
    UserService]
})
export class AppComponent {
  title = 'frontend';
}
