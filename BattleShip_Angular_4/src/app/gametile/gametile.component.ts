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
  @Input()
  user: User;
  gameUser: User;
  winloss: WinLoss;
  constructor(private router: Router, private modalService: NgbModal, private http: Http, private gss: GameServiceService) {
  }

  ngOnInit() {
    this.gameRunning = (this.game.status === 'inprogress');
    this.gamePending = (this.game.status === 'pending');
    this.http.get(environment.context + '/user/' + this.game.player1Id).subscribe(
      (respbody) => {
        if (respbody.text() !== '') {
          this.gameUser = respbody.json();
          this.http.get(environment.context + '/winloss/' + this.gameUser.winLossId).subscribe(
            (respbody2) => {
              if (respbody2.text() !== '') { this.winloss = respbody2.json(); }
            });
        }
      });
  }
  showModal(content) {
    this.modalService.open(content);
  }

  startGame(c) {
    console.log('Starting game');
    console.log('user');
    console.log(this.user);
    if (this.game.player1Id === this.user.id) {
      console.log('Player is the same as started player');
      c('Close click');
    }
    this.game.player2Id = this.user.id;
    this.game.status = 'setup1';
    this.http.put(environment.context + '/game/modify', this.game).subscribe(
      (respbody) => {
        if (respbody.text() !== '') {
          c('Close click');
          this.gss.updateSubject();
          console.log(respbody.json());
        }
      }
    );
  }
}
