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
  constructor(private http: Http, private gss: GameServiceService, private router: Router,
     private wls: WinlossService, private us: UserService) { }

  ngOnInit() {
    this.title = 'Top Ten';
    this.user = JSON.parse(sessionStorage.getItem('user'));
    if (this.user === null) {
      this.router.navigateByUrl('login');
    }
    this.gameSubscription = this.gss.getSubject().subscribe(
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
  }
  currentlyRunningGames() {
    this.mygames = this.games.filter(i => i.status === 'inprogress' &&
     ((i.turn === 0 && i.player1Id === this.user.id) || (i.turn === 1 && i.player2Id === this.user.id)));
    if (this.mygames.length > 3) {
      this.mygames = this.mygames.slice(0, 3);
    }
  }
  getPending() {
    this.pendinggames = this.games.filter(i => i.status === 'pending' && i.player1Id !== this.user.id);
    if (this.pendinggames.length > 5) {
      this.pendinggames = this.pendinggames.slice(0, 5);
    }
  }
  private getTopTen() {
    this.winlosses = this.winlosses.filter(i => i.losses > 0);
    this.winlosses.sort(this.compare);

    if (this.winlosses.length > 10) {
      this.winlosses = this.winlosses.slice(0, 10);
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
    for (let i = 0; i < this.winlosses.length; i++) {
      this.range.push(i);
    }
  }
  ngOnDestroy() {
  }
}
