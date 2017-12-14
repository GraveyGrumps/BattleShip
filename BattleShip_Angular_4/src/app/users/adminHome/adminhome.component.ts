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
import { Report } from '../../beans/Report';

@Component({
  selector: 'app-adminhome',
  templateUrl: './adminhome.component.html',
  styleUrls: ['./adminhome.component.css']
})
export class AdminHomeComponent implements OnInit, OnDestroy {
  global = true;
  games: Array<Game>;
  games2: Array<Game>;
  tickets: Array<Report>;
  currUser: User;
  alive: boolean;
  user: User;
  users: Array<User> = [];
  gameSubscription: Subscription;
  userSubscription: Subscription;

  constructor(private http: Http, private gss: GameServiceService, private router: Router,
    private wls: WinlossService, private us: UserService) { }

  ngOnInit() {
    this.alive = true;
    this.currUser = JSON.parse(sessionStorage.getItem('user'));
    this.user = this.currUser;
    const y = 'http://localhost:8080/Battleship/report/tickets';

    IntervalObservable.create(2000)
    .takeWhile(() => this.alive)
    .subscribe(() => {
      this.gameSubscription = this.gss.getSubject().subscribe(
        (listGames) => {
          if (listGames !== null) {
            if (JSON.stringify(this.games) !== JSON.stringify(listGames)) {
              this.games = listGames;
              this.currentlyRunningGames();
            }
          }
        }
      );
      this.http.get(y, { withCredentials: true }).subscribe(
        (successResp) => {
          if (JSON.stringify(this.tickets) !== JSON.stringify(successResp.json())) {
            this.tickets = successResp.json();
          }
        },
        (failResp) => {
        }
      );
      this.userSubscription = this.us.getSubject().subscribe(
        (userArray) => {
          if (userArray !== null) {
            if (JSON.stringify(this.users) !== JSON.stringify(userArray)) {
              this.users = userArray;
            }
          }
        }
      );
    });
  }

  private currentlyRunningGames() {
    let runningGames = this.games.filter(i => i.status === 'inprogress');
    runningGames = runningGames.filter(i => i.player1Id !== this.currUser.id);
    runningGames = runningGames.filter(i => i.player2Id !== this.currUser.id);

    if (JSON.stringify(this.games2) !== JSON.stringify(runningGames)) {
      this.games2 = runningGames;
    }
  }

  ngOnDestroy() {
    if (this.alive) {
      this.alive = false;
    }
  }
}
