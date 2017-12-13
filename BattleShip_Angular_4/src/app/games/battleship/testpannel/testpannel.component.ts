import { Component, OnDestroy, OnInit, Inject } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Game } from '../beans/Game';
import { Shipstate } from '../beans/Shipstate';
import { Report } from '../../../beans/Report';
import { User } from '../../../beans/User';
import { WinLoss } from '../../../beans/WinLoss';
import { IntervalObservable } from 'rxjs/observable/IntervalObservable';

@Component({
  selector: 'app-testpannel',
  templateUrl: './testpannel.component.html',
  styleUrls: ['./testpannel.component.css']
})

export class TestPannelComponent implements OnInit, OnDestroy {

  currUser: User;
  currGame: Game;
  currReport: Report;
  currShipState: Shipstate;
  alive: boolean;
  reportText: String;


  constructor( @Inject(Http) public http: Http) {
    this.alive = true;
  }

  ngOnInit() {
    this.alive = true;
    this.currUser = JSON.parse(sessionStorage.getItem('user'));


    let x = 'http://localhost:8080/Battleship/game/load';
    x += '?id=' + JSON.parse(sessionStorage.getItem('gmID'));
    this.http.get(x, { withCredentials: true }).subscribe(
      (successResp) => {
        this.currGame = successResp.json();
        console.log(this.currGame);
      },
      (failResp) => {
      }
    );

    this.loadReport();

    IntervalObservable.create(3000)
      .takeWhile(() => this.alive) // only fires when component is alive
      .subscribe(() => {
        this.http.get(x, { withCredentials: true }).subscribe(
          (successResp) => {
            this.currGame = successResp.json();
          },
          (failResp) => {
          });
      });
  }

  loadReport() {
    let y = 'http://localhost:8080/Battleship/report/loadbygame';
    y += '?id=' + JSON.parse(sessionStorage.getItem('gmID'));
    this.http.get(y, { withCredentials: true }).subscribe(
      (successResp) => {
        this.currReport = successResp.json();
        console.log(this.currReport);
      },
      (failResp) => {
      }
    );
  }

  reinitalize(gmId) {
    this.currUser = JSON.parse((sessionStorage.getItem('user')));

    let x = 'http://localhost:8080/Battleship/game/load';
    x += '?id=' + gmId;
    this.http.get(x, { withCredentials: true }).subscribe(
      (successResp) => {
        this.currGame = successResp.json();
        console.log(this.currGame);
      },
      (failResp) => {
      }
    );
    let y = 'http://localhost:8080/Battleship/report/loadbygame';
    y += '?id=' + gmId;
    this.http.get(y, { withCredentials: true }).subscribe(
      (successResp) => {
        this.currReport = successResp.json();
        console.log(this.currReport);
      },
      (failResp) => {
      }
    );
  }

  setup() {
    if (this.currGame.turn) {
      this.currGame.status = 'inprogress';
    }
    this.turnSwap();
    this.update();
  }

  forceHit() {
    this.currShipState = JSON.parse(this.currGame.shipState);
    let done = true;
    if (this.currGame.turn) {
      for (let i = 0; i < this.currShipState.details[0].length; i++) {
        if (done && this.currShipState.details[0][i] !== 0) {
          this.currShipState.details[0][i] -= 1;
          if (this.currShipState.details[0][i] === 0) {
          }
        }
      }
      for (let i = 0; i < this.currShipState.details[0].length; i++) {
        if (this.currShipState.details[0][i] !== 0) {
          done = false;
        }
      }
    } else {
      for (let i = 0; i < this.currShipState.details[0].length; i++) {
        if (done && this.currShipState.details[0][i] !== 0) {
          this.currShipState.details[0][i] -= 1;
          if (this.currShipState.details[0][i] === 0) {
          }
        }
      }
      for (let i = 0; i < this.currShipState.details[0].length; i++) {
        if (this.currShipState.details[0][i] !== 0) {
          done = false;
        }
      }
    }

    if (done) {
      this.finish();
    } else {
      this.turnSwap();
    }
  }

  report() {
    this.currGame.status = 'setup2';
    this.currGame.turnDeadline.setFullYear(this.currGame.turnDeadline.getFullYear() + 100);
    this.update();
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
    this.loadReport();
    this.currReport.reportNotes.replace('null', '');
    this.currReport.reportNotes += 'User ' + this.currUser.id + ': ' + this.reportText + '\n';
    this.reportText = '';

    console.log(this.currReport);
    this.updateReport();
    this.loadReport();
  }

  turnSwap() {
    if (this.currGame.turn) {
      this.currGame.turn = 0;
    } else {
      this.currGame.turn = 1;
    }
    this.update();
  }

  concede() {
    this.turnSwap();
    this.finish();
  }

  update() {
    this.http.put('http://localhost:8080/Battleship/game/modify', (this.currGame), { withCredentials: true }).subscribe(
      (successResp) => {
      },
      (failResp) => {
      }
    );
  }

  getWL(pId) {
    let currWL = new WinLoss();

    let x = 'http://localhost:8080/Battleship/user/getWL';
    x += '?id=' + pId;
    this.http.get(x, { withCredentials: true }).subscribe(
      (successResp) => {
        const wLId = successResp.text();

        x = 'http://localhost:8080/Battleship/winloss/';
        x += wLId;

        this.http.get(x, { withCredentials: true }).subscribe(
          (successResp2) => {
            currWL = successResp2.json();
          },
          (failResp) => {
          }
        );
      },
      (failResp) => {
      }
    );

    return currWL;
  }

  winnerWL(pId) {
    let wWL = new WinLoss();

    let x = 'http://localhost:8080/Battleship/user/getWL';
    x += '?id=' + pId;
    this.http.get(x, { withCredentials: true }).subscribe(
      (successResp) => {
        const wLId = successResp.text();

        x = 'http://localhost:8080/Battleship/winloss/';
        x += wLId;

        this.http.get(x, { withCredentials: true }).subscribe(
          (successResp2) => {
            wWL = successResp2.json();
            wWL.wins += 1;
            wWL.seasonWins += 1;
            this.saveWL(wWL);
          },
          (failResp) => {
          }
        );
      },
      (failResp) => {
      }
    );
  }

  loserWL(pId) {
    let lWL = new WinLoss();

    let x = 'http://localhost:8080/Battleship/user/getWL';
    x += '?id=' + pId;
    this.http.get(x, { withCredentials: true }).subscribe(
      (successResp) => {
        const wLId = successResp.text();

        x = 'http://localhost:8080/Battleship/winloss/';
        x += wLId;

        this.http.get(x, { withCredentials: true }).subscribe(
          (successResp2) => {
            lWL = successResp2.json();
            lWL.losses += 1;
            lWL.seasonLosses += 1;
            this.saveWL(lWL);
          },
          (failResp) => {
          }
        );
      },
      (failResp) => {
      }
    );
  }

  updateWL() {

    if (this.currGame.turn) {
      // Winner
      this.winnerWL(this.currGame.player2Id);
      // Loser
      this.loserWL(this.currGame.player1Id);

    } else {
      // Winner
      this.winnerWL(this.currGame.player1Id);
      // Loser
      this.loserWL(this.currGame.player2Id);
    }
  }

  saveWL(inWL) {
    this.http.put('http://localhost:8080/Battleship/winloss/modify', (inWL), { withCredentials: true }).subscribe(
      (successResp) => {
      },
      (failResp) => {
      }
    );
  }

  updateReport() {
    this.http.put('http://localhost:8080/Battleship/report/modify', (this.currReport), { withCredentials: true }).subscribe(
      (successResp) => {
      },
      (failResp) => {
      }
    );
  }

  finish() {
    this.currGame.status = 'complete';
    if (this.currGame.turn) {
      this.currReport.winner = this.currGame.player2Id;
    } else {
      this.currReport.winner = this.currGame.player1Id;
    }
    this.alive = false;
    this.update();
    this.updateReport();
    this.updateWL();
  }

  ngOnDestroy() {
    this.alive = false;
  }
}
