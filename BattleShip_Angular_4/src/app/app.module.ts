import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { NavComponent } from './nav/nav.component';

import { appRoutes } from './routes';
import { LoginComponent } from './login/login.component';
import { ChatWindowComponent } from './chat-window/chat-window.component';
import { BattleshipComponent } from './games/battleship/battleship.component';
import { UsersComponent } from './users/users.component';
import { HomeComponent } from './users/home/home.component';
import { NewGameComponent } from './games/newgame/newgame.component';
import { LeaderBoardComponent } from './leaderBoard/leaderBoard.component';
import { GametileComponent } from './gametile/gametile.component';

import { GameServiceService } from './services/game-service.service';

// import { TestPannelComponent } from './games/battleship/testpannel/testpannel.component';
import { WinlossService } from './services/winloss.service';
import { LeaderboardsComponent } from './leaderboards/leaderboards.component';
import { UserService } from './services/user.service';
import { MygamesComponent } from './mygames/mygames.component';
import { LobbyComponent } from './lobby/lobby.component';
import { MyspaceComponent } from './myspace/myspace.component';
import { GridComponent } from './games/battleship/grid/grid.component';
import { ProfileComponent } from './myspace/profile/profile.component';
import { GlobalchatService } from './services/globalchat.service';
import { AdminHomeComponent } from './users/adminHome/adminhome.component';
import { TickettileComponent } from './tickettile/tickettile.component';
import { StatsComponent } from './myspace/stats/stats.component';

import { GameComponent } from './games/battleship/game/game.component';
import { GamescreenComponent } from './games/battleship/gamescreen/gamescreen.component';
import { ShipstatusComponent } from './games/battleship/shipstatus/shipstatus.component';
import { HealthstatusComponent } from './games/battleship/healthstatus/healthstatus.component';

import { ReportbuttonComponent } from './reportbutton/reportbutton.component';
import { IngamechatComponent } from './ingamechat/ingamechat.component';


import { ReportbuttonComponent } from './reportbutton/reportbutton.component';
import { IngamechatComponent } from './ingamechat/ingamechat.component';

import { SettingsComponent } from './settings/settings.component';




@NgModule({
  imports: [
    BrowserModule,
    HttpModule,
    NgbModule.forRoot(),
    RouterModule.forRoot(appRoutes),
    FormsModule
  ],
  declarations: [
    AppComponent,
    NavComponent,
    LoginComponent,
    ChatWindowComponent,
    BattleshipComponent,
    UsersComponent,
    HomeComponent,
    NewGameComponent,
    LeaderBoardComponent,
    GametileComponent,
    // TestPannelComponent,
    LeaderboardsComponent,
    MygamesComponent,
    LobbyComponent,
    MyspaceComponent,
    ProfileComponent,
    GridComponent,
    ProfileComponent,

   AdminHomeComponent,
    TickettileComponent,
    StatsComponent,
    GameComponent,
    GamescreenComponent,
    ShipstatusComponent,
    HealthstatusComponent,
    ReportbuttonComponent,
    SettingsComponent


  ],
  providers: [GameServiceService,
    WinlossService,
    UserService,
    GlobalchatService],
  bootstrap: [AppComponent]
})
export class AppModule { }
