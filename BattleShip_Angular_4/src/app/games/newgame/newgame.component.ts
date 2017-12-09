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

export class NewGameComponent {

  newGame = new Game();

  constructor( @Inject(Http) public http: Http) {

  }

  createGame() {
    this.http.post('http://localhost:8080/ERS/users/new', JSON.stringify(this.newGame), { withCredentials: true }).subscribe(
      (successResp) => {
        alert('Creating new game');
      },
      (failResp) => {
        alert('Failed Create new game');
      }
    );
  }
}
