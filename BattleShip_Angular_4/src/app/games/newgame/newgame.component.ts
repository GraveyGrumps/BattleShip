import { Component, OnInit, Inject } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { User } from '../../beans/User';
import { Game } from '../battleship/beans/Game';


@Component({
  selector: 'app-newgame',
  templateUrl: './newgame.component.html',
  styleUrls: ['./newgame.component.css']
})

export class NewGameComponent implements OnInit {

  newGame = new Game();



  constructor( @Inject(Http) public http: Http) {

  }

  ngOnInit() {
    // let p1 = new User;
    // p1 = JSON.parse((sessionStorage.getItem('user')));
    // this.newGame.player1Id = p1.id;
    // this.newGame.player2Id = p1.id;
    this.newGame.player1Id = 2;
    this.newGame.player2Id = 2;
    this.newGame.turnLength = 10;
  }

  createGame() {
    console.log(JSON.stringify(this.newGame));
    this.http.post('http://localhost:8080/Battleship/game/new', (this.newGame), { withCredentials: true }).subscribe(
      (successResp) => {
        alert('Creating new game');
      },
      (failResp) => {
        alert('Failed Create new game');
      }
    );
  }
}
