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

export class TestPannelComponent implements OnInit {

  currUser: User;
  currGame: Game;
  currReport: Report;
  currShipState: Shipstate;
  currWL: WinLoss;
  wWL: WinLoss;
  lWL: WinLoss;
  alive: boolean;


  constructor( @Inject(Http) public http: Http) {
    this.alive = true;
  }

  ngOnDestroy() {
    this.alive = false;
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
        alert('Failed to Load Game');
      }
    );

    let y = 'http://localhost:8080/Battleship/report/loadbygame';
    y += '?id=' + JSON.parse(sessionStorage.getItem('gmID'));
    this.http.get(y, { withCredentials: true }).subscribe(
      (successResp) => {
        this.currReport = successResp.json();
        console.log(this.currReport);
      },
      (failResp) => {
        alert('Failed to Load Log');
      }
    );

    IntervalObservable.create(3000)
      .takeWhile(() => this.alive) // only fires when component is alive
      .subscribe(() => {
        this.http.get(x, { withCredentials: true }).subscribe(
          (successResp) => {
            this.currGame = successResp.json();
          },
          (failResp) => {
            alert('Failed to Load Log');
          });
      });
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
        alert('Failed to Load Game');
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
        alert('Failed to Load Log');
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
            alert('You sunk a ship!');
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
            alert('You sunk a ship!');
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
        alert('Failed Update Game :`(');
      }
    );
  }

  getWL(pId) {
    let x = 'http://localhost:8080/Battleship/user/getWL';
    x += '?id=' + pId;
    this.http.get(x, { withCredentials: true }).subscribe(
      (successResp) => {
        const wLId = successResp.text();

        x = 'http://localhost:8080/Battleship/winloss/';
        x += wLId;

        this.http.get(x, { withCredentials: true }).subscribe(
          (successResp2) => {
            this.currWL = successResp2.json();
            console.log(successResp2.json());
            console.log(this.currWL);
          },
          (failResp) => {
            alert('Failed to W/L');
          }
        );
      },
      (failResp) => {
        alert('Failed to W/L');
      }
    );
  }

  winnerWL(pId) {
    let x = 'http://localhost:8080/Battleship/user/getWL';
    x += '?id=' + pId;
    this.http.get(x, { withCredentials: true }).subscribe(
      (successResp) => {
        const wLId = successResp.text();

        x = 'http://localhost:8080/Battleship/winloss/';
        x += wLId;

        this.http.get(x, { withCredentials: true }).subscribe(
          (successResp2) => {
            this.wWL = successResp2.json();
            this.wWL.wins += 1;
            this.wWL.seasonWins += 1;
            this.saveWL(this.wWL);
          },
          (failResp) => {
            alert('Failed to W/L');
          }
        );
      },
      (failResp) => {
        alert('Failed to W/L');
      }
    );
  }

  loserWL(pId) {
    let x = 'http://localhost:8080/Battleship/user/getWL';
    x += '?id=' + pId;
    this.http.get(x, { withCredentials: true }).subscribe(
      (successResp) => {
        const wLId = successResp.text();

        x = 'http://localhost:8080/Battleship/winloss/';
        x += wLId;

        this.http.get(x, { withCredentials: true }).subscribe(
          (successResp2) => {
            this.lWL = successResp2.json();
            this.lWL.losses += 1;
            this.lWL.seasonLosses += 1;
            this.saveWL(this.lWL);
          },
          (failResp) => {
            alert('Failed to W/L');
          }
        );
      },
      (failResp) => {
        alert('Failed to W/L');
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
        alert('Failed Update W/L :`(');
      }
    );
  }

  updateReport() {
    this.http.put('http://localhost:8080/Battleship/report/modify', (this.currReport), { withCredentials: true }).subscribe(
      (successResp) => {
      },
      (failResp) => {
        alert('Failed Update Report :`(');
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
}
