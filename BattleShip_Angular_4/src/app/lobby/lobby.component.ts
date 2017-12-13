import { Component, OnInit, OnDestroy } from '@angular/core';
import { User } from '../beans/User';
import { Router } from '@angular/router';
import { GameServiceService } from '../services/game-service.service';
import { Game } from '../games/battleship/beans/Game';
import { Subscription } from 'rxjs/Subscription';
import { IntervalObservable } from 'rxjs/observable/IntervalObservable';

@Component({
  selector: 'app-lobby',
  templateUrl: './lobby.component.html',
  styleUrls: ['./lobby.component.css']
})
export class LobbyComponent implements OnInit, OnDestroy {
  user: User;
  games: Array<Game>;
  alive = true;
  constructor(private router: Router, private gss: GameServiceService) { }

  ngOnInit() {
    this.user = JSON.parse(sessionStorage.getItem('user'));
    if (this.user === null) {
      this.router.navigateByUrl('login');
    }
    IntervalObservable.create(500)
    .takeWhile(() => this.alive)
    .subscribe(() => {
      this.gss.getSubject().subscribe(
        (games) => {
          if (games !== null) {
            games = games.filter(i => i.status === 'pending');
            //console.log(JSON.stringify(games) === JSON.stringify( this.games));
            if (JSON.stringify(games) !== JSON.stringify( this.games)) {
              this.games = games;
            }
          }
        });
    });
  }
  ngOnDestroy() {
    this.alive = false;
  }

}
