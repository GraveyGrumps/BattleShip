import { Component, OnInit } from '@angular/core';
import { User } from '../beans/User';
import { Router } from '@angular/router';
import { GameServiceService } from '../services/game-service.service';
import { Game } from '../games/battleship/beans/Game';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-lobby',
  templateUrl: './lobby.component.html',
  styleUrls: ['./lobby.component.css']
})
export class LobbyComponent implements OnInit {
  user: User;
  games: Array<Game>;
  gameSubscription: Subscription;
  constructor(private router: Router, private gss: GameServiceService) { }

  ngOnInit() {
    this.user = JSON.parse(sessionStorage.getItem('user'));
    if (this.user === null) {
      this.router.navigateByUrl('login');
    }

    this.gameSubscription = this.gss.getSubject().subscribe(
      (games) => {
        if (games !== null) {
          this.games = games;
          this.games = this.games.filter(i => i.status === 'pending');
        }
      }
    );
  }

}
