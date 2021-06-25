import { NgxPaginationModule } from 'ngx-pagination';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { BussinessSignUpFormComponent } from './bussiness-sign-up-form/bussiness-sign-up-form.component';
import { PlayerSignUpFormComponent } from './player-sign-up-form/player-sign-up-form.component';
import { routing } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { PlayerProfileComponent } from './player-profile/player-profile.component';
import { BussinessProfileComponent } from './bussiness-profile/bussiness-profile.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { IndexComponent } from './index/index.component';
import { ErrorsComponent } from './errors/errors.component';
import { PreviousSignUpComponent } from './previous-sign-up/previous-sign-up.component';
import { FriendlyMatchesComponent } from './friendly-matches/friendly-matches.component';
import { DivisionComponent } from './division/division.component';
import { TournamentsComponent } from './tournaments/tournaments.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { Division4Component } from './division4/division4.component';
import { Division5Component } from './division5/division5.component';
import { Division6Component } from './division6/division6.component';
import { JoinFriendlyMatchComponent } from './join-friendly-match/join-friendly-match.component';
import { CreateAFriendlyMatchFormComponent } from './create-afriendly-match-form/create-afriendly-match-form.component';
import { chartProfileComponent } from './charts/chart-profile.component';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { InfoMessageComponent } from './info-message/info-message.component';
import { AcceptDenyTournamentComponent } from './acept-deny-tournament/accept-deny-tournament.component';
import { CreateATournamentFormComponent } from './create-a-tournament-form/create-a-tournament-form.component';
import { TournamentInfoComponent } from './tournament-info/tournament-info.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    BussinessSignUpFormComponent,
    PlayerSignUpFormComponent,
    PlayerProfileComponent,
    BussinessProfileComponent,
    IndexComponent,
    ErrorsComponent,
    PreviousSignUpComponent,
    FriendlyMatchesComponent,
    DivisionComponent,
    TournamentInfoComponent,
    TournamentsComponent,
    AcceptDenyTournamentComponent,
    CreateATournamentFormComponent,
    TournamentInfoComponent,
    AboutUsComponent,
    Division4Component,
    Division5Component,
    Division6Component,
    JoinFriendlyMatchComponent,
    CreateAFriendlyMatchFormComponent,
    chartProfileComponent,
    InfoMessageComponent
  ],
  imports: [
    NgxPaginationModule,
    BrowserModule,
    routing,
    FormsModule,
    HttpClientModule,
    NgbModule,
    NgxChartsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
