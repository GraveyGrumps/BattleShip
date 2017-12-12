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
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.css']
})

export class GridComponent implements OnInit {

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
    // this.alive = true;
    // this.currUser = JSON.parse(sessionStorage.getItem('user'));


    // let x = 'http://localhost:8080/Battleship/game/load';
    // x += '?id=' + JSON.parse(sessionStorage.getItem('gmID'));
    // this.http.get(x, { withCredentials: true }).subscribe(
    //   (successResp) => {
    //     this.currGame = successResp.json();
    //     console.log(this.currGame);
    //   },
    //   (failResp) => {
    //     alert('Failed to Load Game');
    //   }
    // );

    // let y = 'http://localhost:8080/Battleship/report/loadbygame';
    // y += '?id=' + JSON.parse(sessionStorage.getItem('gmID'));
    // this.http.get(y, { withCredentials: true }).subscribe(
    //   (successResp) => {
    //     this.currReport = successResp.json();
    //     console.log(this.currReport);
    //   },
    //   (failResp) => {
    //     alert('Failed to Load Log');
    //   }
    // );

    // IntervalObservable.create(3000)
    //   .takeWhile(() => this.alive) // only fires when component is alive
    //   .subscribe(() => {
    //     this.http.get(x, { withCredentials: true }).subscribe(
    //       (successResp) => {
    //         this.currGame = successResp.json();
    //       },
    //       (failResp) => {
    //         alert('Failed to Load Log');
    //       });
    //   });
  }
}
