import { Component, OnInit, Inject } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Game } from '../beans/Game';
import { Shipstate } from '../beans/Shipstate';
import { Report } from '../../../beans/Report';

@Component({
  selector: 'app-testpannel',
  templateUrl: './testpannel.component.html',
  styleUrls: ['./testpannel.component.css']
})

export class TestPannelComponent implements OnInit {

  currGame = new Game();
  currReport = new Report();
  currShipState = new Shipstate();

  constructor( @Inject(Http) public http: Http) {

  }

  ngOnInit() {
    let x = 'http://localhost:8080/Battleship/game/load';
    x += '?id=' + 66;
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
    y += '?id=' + 66;
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

  initalize(gmId) {
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

  finish() {
    this.currGame.status = 'complete';
    this.update();
    if (this.currGame.turn) {
      this.currReport.winner = this.currGame.player2Id;
    } else {
      this.currGame.turn = this.currGame.player1Id;
    }
  }
}
