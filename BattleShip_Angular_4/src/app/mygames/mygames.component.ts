import { Component, OnInit } from '@angular/core';
import { Game } from '../games/battleship/beans/Game';
import { User } from '../beans/User';
import { Subscriber } from 'rxjs/Subscriber';
import { GameServiceService } from '../services/game-service.service';
import { UserService } from '../services/user.service';
import { environment } from '../../environments/environment';
import { Http } from '@angular/http';
import { Router } from '@angular/router/';
import { Subscription } from 'rxjs/Subscription';
import { OnDestroy } from '@angular/core/src/metadata/lifecycle_hooks';

@Component({
  selector: 'app-mygames',
  templateUrl: './mygames.component.html',
  styleUrls: ['./mygames.component.css']
})
export class MygamesComponent implements OnInit, OnDestroy {
  pendingGames: Array<Game>;
  games: Array<Game>;
  users: Array<User>;
  user: User;
  sub: Subscription;
  constructor(private gs: GameServiceService, private us: UserService, private http: Http, private router: Router) { }

  ngOnInit() {
    this.user = JSON.parse(sessionStorage.getItem('user'));
    if (this.user === null) {
      this.router.navigateByUrl('/login');
    }
    this.sub = this.http.get(environment.context + '/game/' + this.user.id).subscribe(
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
    console.log(typeof(this.games[0].status));
    for (let g of this.games) {
      console.log(g.status === 'pending');
      console.log(g.status);
    }

    this.pendingGames = this.games.filter(i => i.status === 'pending');
    console.log('pendinggames');
    console.log(this.pendingGames);

    this.games = this.games.filter( i => i.status !== 'pending');
    console.log('nonpending games');
    console.log(this.games);
  }
  ngOnDestroy() {
    this.sub.unsubscribe();
  }
}
