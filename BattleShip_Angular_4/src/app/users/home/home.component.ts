import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { Game } from '../../games/battleship/beans/Game';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  games: Array<Game>;
  constructor(private http: Http) { }

  ngOnInit() {
    this.http.get('http://localhost:8080/Battleship/game/pending').subscribe(
      (respbody) => {
      if (respbody.text() !== '') {
        this.games = respbody.json();
        if (this.games.length > 5) {
          this.games = this.games.slice(0, 5);
        }
        console.log(this.games); }
      });
  }

}
