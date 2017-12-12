import { Component, OnInit, Inject } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Game } from '../beans/Game';
import { Shipstate } from '../beans/Shipstate';
import { Report } from '../../../beans/Report';
import { User } from '../../../beans/User';
import { WinLoss } from '../../../beans/WinLoss';

@Component({
  selector: 'app-testpannel',
  templateUrl: './testpannel.component.html',
  styleUrls: ['./testpannel.component.css']
})

export class TestPannelComponent implements OnInit {

  currUser = new User();
  currGame = new Game();
  currReport = new Report();
  currShipState = new Shipstate();
  currWL = new WinLoss();

  constructor( @Inject(Http) public http: Http) {

  }

  ngOnInit() {
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
  }

  // initalize(gmId) {
  //   this.currUser = JSON.parse((sessionStorage.getItem('user')));

  //   let x = 'http://localhost:8080/Battleship/game/load';
  //   x += '?id=' + gmId;
  //   this.http.get(x, { withCredentials: true }).subscribe(
  //     (successResp) => {
  //       this.currGame = successResp.json();
  //       console.log(this.currGame);
  //     },
  //     (failResp) => {
  //       alert('Failed to Load Game');
  //     }
  //   );
  //   let y = 'http://localhost:8080/Battleship/report/loadbygame';
  //   y += '?id=' + gmId;
  //   this.http.get(y, { withCredentials: true }).subscribe(
  //     (successResp) => {
  //       this.currReport = successResp.json();
  //       console.log(this.currReport);
  //     },
  //     (failResp) => {
  //       alert('Failed to Load Log');
  //     }
  //   );
  // }

  setup() {
    if (this.currGame.turn) {
      this.currGame.status = 'inprogress';
    }
    this.update();

    this.turnSwap();
  }

  hit() {
    this.currShipState = JSON.parse(this.currGame.shipState);
    let done = true;
    if (this.currGame.turn) {
      for (let i = 0; i < this.currShipState.details[0].length; i++) {
        for (let j = 0; j < this.currShipState.details[0][i].length; j++) {
          if (!this.currShipState.details[0][i][j] && done) {
            this.currShipState.details[0][i][j] = true;
            done = false;
            this.currGame.shipState = JSON.stringify(this.currShipState);
          }
        }
      }
    } else {
      for (let i = 0; i < this.currShipState.details[1].length; i++) {
        for (let j = 0; j < this.currShipState.details[1][i].length; j++) {
          if (!this.currShipState.details[1][i][j] && done) {
            this.currShipState.details[1][i][j] = true;
            done = false;
            this.currGame.shipState = JSON.stringify(this.currShipState);
          }
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
      this.getWL(this.currGame.player2Id);
      this.currWL.wins += 1;
      this.currWL.seasonWins += 1;
      this.saveWL();

      // Loser
      this.getWL(this.currGame.player1Id);
      this.currWL.losses += 1;
      this.currWL.seasonLosses += 1;
      this.saveWL();
    } else {
      // Winner
      this.getWL(this.currGame.player1Id);
      this.currWL.wins += 1;
      this.currWL.seasonWins += 1;
      this.saveWL();

      // Loser
      this.getWL(this.currGame.player2Id);
      this.currWL.losses += 1;
      this.currWL.seasonLosses += 1;
      this.saveWL();
    }
  }

  saveWL() {
    this.http.put('http://localhost:8080/Battleship/winloss/modify', (this.currWL), { withCredentials: true }).subscribe(
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
      this.currGame.turn = this.currGame.player1Id;
    }
    this.update();
    this.updateReport();
    this.updateWL();
  }
}
