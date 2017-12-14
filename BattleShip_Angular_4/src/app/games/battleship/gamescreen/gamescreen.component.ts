import { Component, OnInit, OnDestroy } from '@angular/core';
import { Game } from '../beans/Game';
import { Boardstate2 } from '../beans/boardstate2';
import { User } from '../../../beans/User';
import { Router } from '@angular/router';
import { Shipstate } from '../beans/Shipstate';
import { IntervalObservable } from 'rxjs/observable/IntervalObservable';
import { Subscription } from 'rxjs/Subscription';
import { GameServiceService } from '../../../services/game-service.service';
import { Report } from '../../../beans/Report';
import { Http } from '@angular/http';
import { environment } from '../../../../environments/environment';

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
  report: Report;
  constructor(private router: Router, private gss: GameServiceService, private http: Http) { }

  ngOnInit() {
    this.user = JSON.parse(sessionStorage.getItem('user'));
    if (this.user === null) {
      this.router.navigateByUrl('/login');
    }
    this.game = JSON.parse(sessionStorage.getItem('game'));
    // console.log(this.game);
    this.shipStates = JSON.parse(this.game.shipState);
    // console.log(this.shipStates);
    // console.log(this.user.id);
    // console.log(this.game.player1Id);
    //  console.log(this.game.player2Id);
    IntervalObservable.create(300)
      .takeWhile(() => this.alive)
      .subscribe(() => {
        this.gss.getSubject().subscribe(
          (games) => {
            if (games.length !== 0) {
              // console.log(this.game);
              const holder = games.filter(i => i.id === this.game.id)[0];
              if (holder !== undefined && JSON.stringify(holder) !== JSON.stringify(this.game)) {
                // console.log('merging with holder');
                // console.log(holder);
                this.game = holder;
              }
            }
          });
        this.http.get(environment.context + '/report/loadbygame/?id=' + this.game.id).subscribe( (report) => {
        if (report.text().length !== 0) {
          if (JSON.stringify(this.report) !== report.text()) {
            this.report = report.json();
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
  isItMyTurn() {
    if (this.user.id === this.game.player1Id && this.game.turn === 0) {
      return 'My Turn';
    } else if (this.user.id === this.game.player2Id && this.game.turn === 1) {
      return 'My Turn';
    } else {
      return `Opponent's Turn`;
    }
  }
}
