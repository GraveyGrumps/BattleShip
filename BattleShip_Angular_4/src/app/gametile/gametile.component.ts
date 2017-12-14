import { Component, OnInit, ViewEncapsulation } from '@angular/core';

import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Game } from '../games/battleship/beans/Game';
import { Input } from '@angular/core/';
import { User } from '../beans/User';
import { Http } from '@angular/http/';
import { WinLoss } from '../beans/WinLoss';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap/modal/modal-ref';
import { environment } from '../../environments/environment';
import { Router } from '@angular/router';
import { GameServiceService } from '../services/game-service.service';
import { Report } from '../beans/Report';
// import { TestPannelComponent } from '../games/battleship/testpannel/testpannel.component';
@Component({
  selector: 'app-gametile',
  templateUrl: './gametile.component.html',
  styleUrls: ['./gametile.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class GametileComponent implements OnInit {
  fullimagepath = '/assets/images/battleshipcover.jpg';
  @Input()
  game: Game;
  gameRunning: boolean;
  gamePending: boolean;
  myGame: boolean;
  @Input()
  user: User;
  gameUser1: User;
  gameUser2: User;
  winloss: WinLoss;
  usernames: Array<String> = [];
  @Input()
  ticket: Report;

  constructor(private router: Router, private modalService: NgbModal, private http: Http, private gss: GameServiceService) {
  }

  ngOnInit() {
    this.gameRunning = (this.game.status === 'inprogress');
    this.gamePending = (this.game.status === 'pending');
    this.myGame = (this.game.player1Id === this.user.id);
    this.http.get(environment.context + '/user/' + this.game.player1Id).subscribe(
      (respbody) => {
        if (respbody.text() !== '') {
          this.gameUser1 = respbody.json();
        }
        this.http.get(environment.context + '/user/' + this.game.player2Id).subscribe(
          (respbody2) => {
            if (respbody2.text() !== '') {
              this.gameUser2 = respbody2.json();
              if (this.game.player1Id === this.user.id) {
                this.usernames.push(this.gameUser2.username);
                this.usernames.push(this.gameUser1.username);
              } else {
                this.usernames.push(this.gameUser1.username);
                this.usernames.push(this.gameUser2.username);
              }
              this.http.get(environment.context + '/winloss/' + this.gameUser1.winLossId).subscribe(
                (respbody3) => {
                  if (respbody3.text() !== '') { this.winloss = respbody3.json(); }
                });
            }
          });
      });
  }
  showModal(content) {
    this.modalService.open(content);
  }

  startGame(c) {
    if (this.game.player1Id === this.user.id) {
      c('Close click');
      alert('cannot start a game with yourself');
      return;
    }
    this.game.player2Id = this.user.id;
    this.game.status = 'setup1';
    this.http.put(environment.context + '/game/modify', this.game).subscribe(
      (respbody) => {
        if (respbody.text() !== '') {
          c('Close click');
          this.gss.updateSubject();
          this.initGame();
          this.routeTo('gamer');
        }
      }
    );
  }
  getUsername(num) {
    return this.usernames[num];
  }

  initGame() {
    sessionStorage.setItem('game', JSON.stringify(this.game));
  }

  routeTo(location) {
    this.router.navigateByUrl(location);
  }
}
