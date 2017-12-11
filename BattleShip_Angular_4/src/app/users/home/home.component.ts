import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { Game } from '../../games/battleship/beans/Game';
import { environment } from '../../../environments/environment';
import { User } from '../../beans/User';
import { GameServiceService } from '../../services/game-service.service';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  games: Array<Game>;
  constructor(private http: Http, private gss: GameServiceService) { }
  mygames: Array<Game>;
  pendinggames: Array<Game>;
  user: User;
  subscription: Subscription;
  ngOnInit() {
    console.log('user is:');
    console.log(JSON.parse(sessionStorage.getItem('user')));
    this.user = JSON.parse(sessionStorage.getItem('user'));
    this.subscription = this.gss.getSubject().subscribe(
      (listGames) => {
        if (listGames !== null) {
          this.games = listGames;
          this.getPending();
          this.http.get(environment.context + '/game/' + this.user.id, { withCredentials: true }).subscribe(
            (respbody) => {
              if (respbody.text() !== '') {
                this.mygames = respbody.json();
                this.currentlyRunningGames();
              }
            });
        }
      }
    );
  }
  currentlyRunningGames() {
    this.mygames = this.games.filter(i => i.status === 'inprogress' && i.turn === this.user.id);
    if (this.mygames.length > 3) {
      this.mygames = this.mygames.slice(0, 3);
    }
  }
  getPending() {
    this.pendinggames = this.games.filter(i => i.status === 'pending');
    if (this.pendinggames.length > 5) {
      this.pendinggames = this.pendinggames.slice(0, 3);
    }
  }

}
