import { Component, OnInit, ViewEncapsulation } from '@angular/core';

import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Game } from '../games/battleship/beans/Game';
import { Input } from '@angular/core/';
import { User } from '../beans/User';
import { Http } from '@angular/http/';
import { WinLoss } from '../beans/WinLoss';
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
  gamePending: boolean;
  user: User;
  winloss: WinLoss;
  constructor(private modalService: NgbModal, private http: Http) {
  }

  ngOnInit() {
    this.gamePending = (this.game.status === 'pending');
    this.http.get('http://localhost:8080/Battleship/user/' + this.game.player1Id).subscribe(
      (respbody) => {
        if (respbody.text() !== '') {
          this.user = respbody.json();
          this.http.get('http://localhost:8080/Battleship/winloss/' + this.user.winLossId).subscribe(
            (respbody2) => {
              if (respbody2.text() !== '') { this.winloss = respbody2.json(); }
            });
        }
      });
  }
  showModal(content) {
    this.modalService.open(content);
  }
}
