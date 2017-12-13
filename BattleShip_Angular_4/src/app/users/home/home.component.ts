import { Component, OnInit, OnDestroy } from '@angular/core';
import { Http } from '@angular/http';
import { Game } from '../../games/battleship/beans/Game';
import { environment } from '../../../environments/environment';
import { User } from '../../beans/User';
import { GameServiceService } from '../../services/game-service.service';
import { Subscription } from 'rxjs/Subscription';
import { Router } from '@angular/router';
import { WinlossService } from '../../services/winloss.service';
import { WinLoss } from '../../beans/WinLoss';
import { UserService } from '../../services/user.service';
import { IntervalObservable } from 'rxjs/observable/IntervalObservable';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {
  global = true;
  games: Array<Game>;
  mygames: Array<Game>;
  pendinggames: Array<Game>;
  winlosses: Array<WinLoss>;
  user: User;
  users: Array<User> = [];
  gameSubscription: Subscription;
  winlossSubscription: Subscription;
  userSubscription: Subscription;
  range: Array<number> = [];
  title: String;
  alive: boolean;
  constructor(private http: Http, private gss: GameServiceService, private router: Router,
     private wls: WinlossService, private us: UserService) { }

  ngOnInit() {
    this.title = 'Top Ten';
    this.user = JSON.parse(sessionStorage.getItem('user'));
    if (this.user === null) {
      this.router.navigateByUrl('login');
    }
    this.alive = true;
    IntervalObservable.create(1000)
    .takeWhile(() => this.alive)
    .subscribe(() => {
      this.gameSubscription = this.gss.getSubject().subscribe(
        (listGames) => {
          if (listGames !== null) {
            this.games = listGames;
            this.getPending();
            this.currentlyRunningGames();
            // this.http.get(environment.context + '/game/' + this.user.id, { withCredentials: true }).subscribe(
            //   (respbody) => {
            //     if (respbody.text() !== '') {
            //       this.mygames = respbody.json();
            //       this.currentlyRunningGames();
            //     }
            //   });
          }
        }
      );
      this.winlossSubscription = this.wls.getSubject().subscribe(
        (listWinLosses) => {
          if (listWinLosses !== null) {
            this.winlosses = listWinLosses;
            this.getTopTen();
            this.populateRange();
          }
        }
      );
      this.userSubscription = this.us.getSubject().subscribe(
        (userArray) => {
          if (userArray !== null) {
            this.users = userArray;
          }
        }
      );
    });
  }
  private currentlyRunningGames() {
    let myRunningGames = this.games.filter(i => i.status === 'inprogress' &&
     ((i.turn === 0 && i.player1Id === this.user.id) || (i.turn === 1 && i.player2Id === this.user.id)));
    if (myRunningGames.length > 3) {
      myRunningGames = myRunningGames.slice(0, 3);
    }
    if (JSON.stringify(this.mygames) !== JSON.stringify(myRunningGames)) {
      this.mygames = myRunningGames;
    }
  }
  private getPending() {
    let holder = this.games.filter(i => i.status === 'pending' && i.player1Id !== this.user.id);
    if (holder.length > 5) {
      holder = holder.slice(0, 5);
    }
    if (JSON.stringify(this.pendinggames) !== JSON.stringify(holder)) {
      this.pendinggames = holder;
    }
  }
  private getTopTen() {
    let holder = this.winlosses.filter(i => i.losses > 0);
    holder.sort(this.compare);
    if (holder.length > 10) {
      holder = holder.slice(0, 10);
    }
    if (JSON.stringify(holder) !== JSON.stringify(this.winlosses)) {
      this.winlosses = holder;
    }
  }
  private compare(a, b) {
    if ((a.wins / a.losses) < (b.wins / b.losses)) {
      return 1;
    }
    if ((a.wins / a.losses) > (b.wins / b.losses)) {
      return -1;
    }
    return 0;
  }
  private populateRange() {
    this.range = [];
    for (let i = 0; i < this.winlosses.length; i++) {
      this.range.push(i);
    }
  }
  ngOnDestroy() {
    if (this.alive) {
      this.alive = false;
      this.gameSubscription.unsubscribe();
      this.userSubscription.unsubscribe();
      this.winlossSubscription.unsubscribe();
    }
  }
}
