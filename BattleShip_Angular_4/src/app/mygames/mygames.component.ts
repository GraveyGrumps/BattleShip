import { Component, OnInit } from '@angular/core';
import { Game } from '../games/battleship/beans/Game';
import { User } from '../beans/User';
import { Subscriber } from 'rxjs/Subscriber';
import { GameServiceService } from '../services/game-service.service';
import { UserService } from '../services/user.service';
import { environment } from '../../environments/environment';
import { Http } from '@angular/http';

@Component({
  selector: 'app-mygames',
  templateUrl: './mygames.component.html',
  styleUrls: ['./mygames.component.css']
})
export class MygamesComponent implements OnInit {
  pendingGames: Array<Game>;
  games: Array<Game>;
  users: Array<User>;
  user: User;
  constructor(private gs: GameServiceService, private us: UserService, private http: Http) { }

  ngOnInit() {
    this.user = JSON.parse(sessionStorage.getItem('user'));
    this.http.get(environment.context + '/game/' + this.user.id).subscribe(
      (games) => {
        if (games.text() !== '') {
          console.log(games.json());
          this.games = games.json();
          this.sortgames();
        }
      });
    this.us.getSubject().subscribe(
      (users) => {
        if (users !== null) {
          this.users = users;
        }
      });
  }
  sortgames() {
    this.pendingGames = this.games.filter(i => i.status === 'pending');
    console.log('pendinggames');
    console.log(this.pendingGames);

    this.games = this.games.filter( i => i.status !== 'pending');
  }

}