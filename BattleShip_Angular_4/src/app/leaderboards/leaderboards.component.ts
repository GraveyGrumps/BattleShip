import { Component, OnInit, OnDestroy } from '@angular/core';
import { User } from '../beans/User';
import { Router } from '@angular/router';
import { WinlossService } from '../services/winloss.service';
import { WinLoss } from '../beans/WinLoss';
import { Http } from '@angular/http';
import { UserService } from '../services/user.service';
import { Input } from '@angular/core/';
import { Subscription } from 'rxjs/Subscription';
import { IntervalObservable } from 'rxjs/observable/IntervalObservable';

@Component({
  selector: 'app-leaderboards',
  templateUrl: './leaderboards.component.html',
  styleUrls: ['./leaderboards.component.css']
})
export class LeaderboardsComponent implements OnInit, OnDestroy {

  user: User;
  users: Array<User> = [];
  winlosses: Array<WinLoss>;
  top20GlobalWinLoss: Array<WinLoss>;
  top20SeaonalWinLoss: Array<WinLoss>;
  aroundUserGlobalWinLoss: Array<WinLoss>;
  aroundUserSeasonalWinLoss: Array<WinLoss>;
  globalrange: Array<number> = [];
  seasonalrange: Array<number> = [];
  globalAroundRange: Array<number> = [];
  seasonAroundRange: Array<number> = [];
  global = true;
  alive: boolean;
  private sub: Subscription;
  constructor(private router: Router, private winlossService: WinlossService, private http: Http, private userService: UserService) {}

  ngOnInit() {
    this.user = JSON.parse(sessionStorage.getItem('user'));
    if (this.user === null) {
      this.router.navigateByUrl('login');
    }
    this.alive = true;
    IntervalObservable.create(300)
      .takeWhile(() => this.alive)
      .subscribe(() => {
       this.sub = this.winlossService.getSubject().subscribe(
          (winlossarray) => {
            if (winlossarray !== null) {
              this.winlosses = winlossarray;
              this.loadGlobal20();
              this.loadSeasonal20();
              this.loadAroundRange(this.globalAroundRange, this.aroundUserGlobalWinLoss);
              this.loadAroundRange(this.seasonAroundRange, this.aroundUserSeasonalWinLoss);
            }
          }
        );
        this.userService.getSubject().subscribe(
          (userarray) => {
            if (userarray !== null) {
              this.users = userarray;
            }
          }
        );
      });
  }

  private loadGlobal20() {
    let holder = this.winlosses.filter(i => i.losses > 0);
    holder.sort(this.compareGlobal);
    this.aroundUserGlobalWinLoss = holder;
    if (holder.length > 20) {
      holder = holder.slice(0, 20);
    }
    if (JSON.stringify(holder) !== JSON.stringify(this.top20GlobalWinLoss)) {
      this.top20GlobalWinLoss = holder;
    }
    this.populate(this.globalrange, this.top20GlobalWinLoss.length);
  }

  private loadSeasonal20() {
    let holder = this.winlosses.filter(i => i.seasonLosses > 0);
    holder.sort(this.compareSeasonal);
    this.aroundUserSeasonalWinLoss = holder;
    if (holder.length > 20) {
      holder = holder.slice(0, 20);
    }
    if (JSON.stringify(holder) !== JSON.stringify(this.top20SeaonalWinLoss)) {
      this.top20SeaonalWinLoss = holder;
    }
    this.populate(this.seasonalrange, this.top20SeaonalWinLoss.length);
  }

  private loadAroundRange(range, array) {
    range.length = 0;
    let i;
    for (i = 0; i < array.length; i++) {
      if (array[i].id === this.user.winLossId) {
        break;
      }
    }
    let five = 6;
    let placeholder = i + 1;
    while (i >= 0 && five > 0) {
      range.push(i);
      five--;
      i--;
    }
    range.reverse();
    five = 5;
    while (placeholder < array.length && five > 0) {
      range.push(placeholder++);
      five--;
    }
  }
  private compareGlobal(a, b) {
    if ((a.wins / a.losses) < (b.wins / b.losses)) {
      return 1;
    }
    if ((a.wins / a.losses) > (b.wins / b.losses)) {
      return -1;
    }
    return 0;
  }
  private compareSeasonal(a, b) {
    if ((a.seasonWins / a.seasonLosses) < (b.seasonWins / b.seasonLosses)) {
      return 1;
    }
    if ((a.seasonWins / a.seasonLosses) > (b.seasonWins / b.seasonLosses)) {
      return -1;
    }
    return 0;
  }
  private populate(range, length) {
    range.length = 0;
    for (let i = 0; i < length; i++) {
      range.push(i);
    }
  }
  ngOnDestroy() {
    if (this.alive) {
      this.alive = false;
      this.sub.unsubscribe();
    }
  }
}
