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

  tickets: Array<Report>;
  currUser: User;
  alive: boolean;

  constructor(private http: Http) { }

  ngOnInit() {
    this.alive = true;
    this.currUser = JSON.parse(sessionStorage.getItem('user'));


    const y = 'http://localhost:8080/Battleship/report/tickets';
    this.http.get(y, { withCredentials: true }).subscribe(
      (successResp) => {
        this.tickets = successResp.json();
        console.log(this.tickets);
      },
      (failResp) => {
      }
    );
  }

  ngOnDestroy() {
    if (this.alive) {
      this.alive = false;
    }
  }
}
