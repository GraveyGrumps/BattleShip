import { Component, OnInit } from '@angular/core';
import { Report } from '../beans/Report';
import { Http } from '@angular/http';
import { Game } from '../games/battleship/beans/Game';
import { IntervalObservable } from 'rxjs/observable/IntervalObservable';
import { User } from '../beans/User';
import { OnDestroy } from '@angular/core/src/metadata/lifecycle_hooks';

@Component({
  selector: 'app-reportbutton',
  templateUrl: './reportbutton.component.html',
  styleUrls: ['./reportbutton.component.css']
})
export class ReportbuttonComponent implements OnInit, OnDestroy {

  currReport: Report;
  currGame: Game;
  alive: boolean;
  reportText: string;
  currUser: User;
  defendant: User;
  claimant: User;

  constructor(private http: Http) { }

  ngOnInit() {
    this.alive = true;
    this.currUser = JSON.parse(sessionStorage.getItem('user'));
    this.currGame = JSON.parse(sessionStorage.getItem('game'));

    let y = 'http://localhost:8080/Battleship/report/loadbygame';
    y += '?id=' + this.currGame.id;
    // this.http.get(y, { withCredentials: true }).subscribe(
    //   (successResp) => {
    //   },
    //   (failResp) => {
    //   }
    // );

    IntervalObservable.create(5000)
      .takeWhile(() => this.alive) // only fires when component is alive
      .subscribe(() => {
        this.http.get(y, { withCredentials: true }).subscribe(
          (successResp) => {
            this.currReport = successResp.json();
          },
          (failResp) => {
          });
      });
  }

  updateReport() {
    this.http.put('http://localhost:8080/Battleship/report/modify', (this.currReport), { withCredentials: true }).subscribe(
      (successResp) => {
      },
      (failResp) => {
      }
    );
  }

  report() {
    this.currReport.flag = 1;
    this.currReport.reportDate = new Date();

    if (this.currGame.turn) {
      this.currReport.claimant = this.currGame.player2Id;
      this.currReport.defendant = this.currGame.player1Id;
    } else {
      this.currReport.claimant = this.currGame.player1Id;
      this.currReport.defendant = this.currGame.player2Id;
    }

    this.updateReport();
  }

  reporttext() {
    this.currReport.reportNotes += this.currUser.username + ': ' + this.reportText + '\n';

    this.reportText = '';
    this.updateReport();
  }

  ngOnDestroy() {
    if (this.alive) {
      this.alive = false;
    }
  }
  checkflag() {
    if (this.currReport === undefined) {
      return false;
    } else {
      return this.currReport.flag;
    }
  }
}
