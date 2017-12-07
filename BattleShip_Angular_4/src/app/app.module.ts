import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import {AppComponent} from './app.component';
import {NavComponent} from './nav/nav.component';

import { appRoutes } from './routes';
<<<<<<< HEAD
import { LoginComponent } from './login/login.component';
=======
import { ChatWindowComponent } from './chat-window/chat-window.component';
import { GlobalChatService } from './global-chat.service';
>>>>>>> bad8b12ebcd214d37819094d9726cd3568253a28

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
<<<<<<< HEAD
    LoginComponent,
=======
    ChatWindowComponent,
>>>>>>> bad8b12ebcd214d37819094d9726cd3568253a28
   ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
