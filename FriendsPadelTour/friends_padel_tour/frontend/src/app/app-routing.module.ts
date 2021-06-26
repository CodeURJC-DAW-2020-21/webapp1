import { TournamentInfoComponent } from './tournament-info/tournament-info.component';
import { CreateATournamentFormComponent } from './create-a-tournament-form/create-a-tournament-form.component';
import { InfoMessageComponent } from './info-message/info-message.component';
import { CreateAFriendlyMatchFormComponent } from './create-afriendly-match-form/create-afriendly-match-form.component';
import { JoinFriendlyMatchComponent } from './join-friendly-match/join-friendly-match.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { TournamentsComponent } from './tournaments/tournaments.component';
import { DivisionComponent } from './division/division.component';
import { FriendlyMatchesComponent } from './friendly-matches/friendly-matches.component';
import { PreviousSignUpComponent } from './previous-sign-up/previous-sign-up.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BussinessProfileComponent } from './bussiness-profile/bussiness-profile.component';
import { BussinessSignUpFormComponent } from './bussiness-sign-up-form/bussiness-sign-up-form.component';
import { ErrorsComponent } from './errors/errors.component';
import { IndexComponent } from './index/index.component';
import { PlayerProfileComponent } from './player-profile/player-profile.component';
import { PlayerSignUpFormComponent } from './player-sign-up-form/player-sign-up-form.component';
import { Division4Component } from './division4/division4.component';
import { Division5Component } from './division5/division5.component';
import { Division6Component } from './division6/division6.component';
import { Division2Component } from './division2/division2.component';
import { Division3Component } from './division3/division3.component';
import { AcceptDenyTournamentComponent } from './acept-deny-tournament/accept-deny-tournament.component';


const routes: Routes = [
 { path: '', component: IndexComponent },
 { path: 'error', component: ErrorsComponent },
 { path: 'player/signUp', component: PlayerSignUpFormComponent },
 { path: 'bussiness/signUp', component: BussinessSignUpFormComponent },
 { path: 'player/:userName', component: PlayerProfileComponent },
 { path: 'bussiness/:userName', component: BussinessProfileComponent },
 { path: 'previousSignUp', component: PreviousSignUpComponent },
 { path: 'bussinessSignUpForm', component: BussinessSignUpFormComponent },
 { path: 'playerSignUpForm', component: PlayerSignUpFormComponent},
 { path: 'friendlyMatches', component: FriendlyMatchesComponent },
 { path: 'division/1', component: DivisionComponent },
 { path: 'division/2', component: Division2Component },
 { path: 'division/3', component: Division3Component },
 { path: 'division/4', component: Division4Component },
 { path: 'division/5', component: Division5Component },
 { path: 'division/6', component: Division6Component },
 { path: 'tournamentRequest', component: CreateATournamentFormComponent},
 { path: 'tournamentInfo/:id', component: TournamentInfoComponent},
 { path: 'tournaments', component: TournamentsComponent},
 { path: 'aboutUs', component: AboutUsComponent },
 { path: 'joinFriendlyMatch/:id', component: JoinFriendlyMatchComponent },
 { path: 'createAFriendlyMatch/:division', component: CreateAFriendlyMatchFormComponent },
 { path: 'tournamentManagment', component: AcceptDenyTournamentComponent},
 { path: '**', component: ErrorsComponent },
 { path: 'success/:message', component: InfoMessageComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routing = RouterModule.forRoot(routes);
