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
import { UsersComponent } from './users/users.component';
import { HomeComponent } from './users/home/home.component';
import { TopTenComponent } from './top-ten/top-ten.component';
import { GametileComponent } from './gametile/gametile.component';


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
    UsersComponent,
    HomeComponent,
    TopTenComponent,
    GametileComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
