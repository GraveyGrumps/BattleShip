import { Component, OnInit, Inject } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Game } from '../beans/Game';

@Component({
  selector: 'app-testpannel',
  templateUrl: './testpannel.component.html',
  styleUrls: ['./testpannel.component.css']
})

export class TestPannelComponent implements OnInit {

  currGame = new Game();

  constructor(@Inject(Http) public http: Http) {

  }

  ngOnInit() {
    let x = 'http://localhost:8080/Battleship/game/load';
    x += '?id=' + 45;
    this.http.get(x, { withCredentials: true }).subscribe(
      (successResp) => {
        this.currGame = successResp.json();
        console.log(this.currGame);
      },
      (failResp) => {
        alert('Failed to Load Game');
      }
    );
  }

  setup() {
    if (this.currGame.turn) {
      this.currGame.status = 'inprogress';
    }

    this.turnSwap();
  }

  hit() {


    this.turnSwap();
  }

  turnSwap() {
    if (this.currGame.turn) {
      this.currGame.turn = 0;
    } else {
      this.currGame.turn = 1;
    }
  }

}
