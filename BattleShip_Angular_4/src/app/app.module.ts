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
import { GlobalChatService } from './global-chat.service';
import { BattleshipComponent } from './games/battleship/battleship.component';
import { UsersComponent } from './users/users.component';
import { HomeComponent } from './users/home/home.component';
import { NewGameComponent } from './games/newgame/newgame.component';
import { TopTenComponent } from './top-ten/top-ten.component';
import { GametileComponent } from './gametile/gametile.component';

import { GameServiceService } from './services/game-service.service';

import { TestPannelComponent } from './games/battleship/testpannel/testpannel.component';


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
    TopTenComponent,
    GametileComponent,
    TestPannelComponent
  ],
  providers: [GameServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
