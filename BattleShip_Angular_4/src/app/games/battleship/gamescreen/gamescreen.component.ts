import { Component, OnInit, OnDestroy } from '@angular/core';
import { Game } from '../beans/Game';
import { Boardstate2 } from '../beans/boardstate2';
import { User } from '../../../beans/User';
import { Router } from '@angular/router';
import { Shipstate } from '../beans/Shipstate';
import { IntervalObservable } from 'rxjs/observable/IntervalObservable';
import { Subscription } from 'rxjs/Subscription';
import { GameServiceService } from '../../../services/game-service.service';

@Component({
  selector: 'app-gamescreen',
  templateUrl: './gamescreen.component.html',
  styleUrls: ['./gamescreen.component.css']
})
export class GamescreenComponent implements OnInit, OnDestroy {
  user: User;
  game: Game;
  gameBoards: Boardstate2;
  shipStates: any;
  renderShips = true;
  alive = true;
  sub: Subscription;
  constructor(private router: Router, private gss: GameServiceService) { }

  ngOnInit() {
    this.user = JSON.parse(sessionStorage.getItem('user'));
    if (this.user === null) {
      this.router.navigateByUrl('/login');
    }
    this.game = JSON.parse(sessionStorage.getItem('game'));
    console.log(this.game);
    this.shipStates = JSON.parse(this.game.shipState);
    console.log(this.shipStates);
    console.log(this.user.id);
    console.log(this.game.player1Id);
    console.log(this.game.player2Id);
    IntervalObservable.create(300)
      .takeWhile(() => this.alive)
      .subscribe(() => {
        this.gss.getSubject().subscribe(
          (games) => {
            if (games.length !== 0) {
              const holder = games.filter(i => i.id === this.game.id)[0];
              if (JSON.stringify(holder) !== JSON.stringify(this.game)) {
                console.log('merging with holder');
                console.log(holder);
                this.game = holder;
              }
            }
          });
      });
  }
  ngOnDestroy() {
    if (this.alive) {
      this.alive = false;
    }

  }
}
