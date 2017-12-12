import { Component, OnInit } from '@angular/core';
import { User } from '../beans/User';
import { Router } from '@angular/router';
import { WinlossService } from '../services/winloss.service';
import { WinLoss } from '../beans/WinLoss';
import { Http } from '@angular/http';
import { UserService } from '../services/user.service';
import { Input } from '@angular/core/';

@Component({
  selector: 'app-leaderboards',
  templateUrl: './leaderboards.component.html',
  styleUrls: ['./leaderboards.component.css']
})
export class LeaderboardsComponent implements OnInit {

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
  constructor(private router: Router, private winlossService: WinlossService, private http: Http, private userService: UserService) { }

  ngOnInit() {
    this.user = JSON.parse(sessionStorage.getItem('user'));
    if (this.user === null) {
      this.router.navigateByUrl('login');
    }
    this.winlossService.getSubject().subscribe(
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
  }

  private loadGlobal20() {
    this.top20GlobalWinLoss = this.winlosses.filter(i => i.losses > 0);
    this.top20GlobalWinLoss.sort(this.compareGlobal);
    this.aroundUserGlobalWinLoss = this.top20GlobalWinLoss;
    if (this.top20GlobalWinLoss.length > 20) {
      this.top20GlobalWinLoss = this.top20GlobalWinLoss.slice(0, 20);
    }
    this.populate(this.globalrange, this.top20GlobalWinLoss.length);
  }

  private loadSeasonal20() {
    this.top20SeaonalWinLoss = this.winlosses.filter( i => i.seasonLosses > 0);
    this.top20SeaonalWinLoss.sort(this.compareSeasonal);
    this.aroundUserSeasonalWinLoss = this.top20SeaonalWinLoss;
    if (this.top20SeaonalWinLoss.length > 20) {
      this.top20SeaonalWinLoss = this.top20SeaonalWinLoss.slice(0, 20);
    }
    this.populate(this.seasonalrange, this.top20SeaonalWinLoss.length);
  }

  private loadAroundRange(range, array) {
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
      five --;
      i--;
    }
    range.reverse();
    five = 5;
    while (placeholder < array.length && five > 0) {
      range.push(placeholder++);
      five--;
    }
  }

  private loadSeasonalAround() {
    let i;
    for (i = 0; i < this.aroundUserGlobalWinLoss.length; i++) {
      if (this.aroundUserGlobalWinLoss[i].id === this.user.winLossId) {
        break;
      }
    }
    let five = 6;
    let placeholder = i + 1;
    while (i >= 0 && five > 0) {
      this.globalAroundRange.push(i);
      five --;
      i--;
    }
    this.globalAroundRange.reverse();
    five = 5;
    while (placeholder < this.aroundUserGlobalWinLoss.length && five > 0) {
      this.globalAroundRange.push(placeholder++);
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
    for (let i = 0; i < length; i++) {
      range.push(i);
    }
  }
}
