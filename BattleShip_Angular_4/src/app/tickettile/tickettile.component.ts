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
// import { TestPannelComponent } from '../games/battleship/testpannel/testpannel.component';
import { Report } from '../beans/Report';
import { IntervalObservable } from 'rxjs/observable/IntervalObservable';

@Component({
  selector: 'app-tickettile',
  templateUrl: './tickettile.component.html',
  styleUrls: ['./tickettile.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class TickettileComponent implements OnInit {
  fullimagepath = '/assets/images/battleshipcover.jpg';
  @Input()
  ticket: Report;
  game: Game;
  claimant: User;
  defendant: User;
  alive: boolean;

  constructor(private router: Router, private modalService: NgbModal, private http: Http) {
  }

  ngOnInit() {
    console.log(this.ticket);
    this.alive = true;
    // this.getGame();
    this.getC(this.ticket.claimant);
    this.getD(this.ticket.defendant);
  }

  getGame() {
    let x = 'http://localhost:8080/Battleship/game/load';
    x += '?id=' + this.ticket.gameId;
    this.http.get(x, { withCredentials: true }).subscribe(
      (successResp) => {
        this.game = successResp.json();
      },
      (failResp) => {
      }
    );
  }

  getC(pid) {
    let x = 'http://localhost:8080/Battleship/user/';
    x += pid;
    this.http.get(x, { withCredentials: true }).subscribe(
      (successResp) => {
        this.claimant = successResp.json();
      },
      (failResp) => {
      }
    );
  }

  getD(pid) {
    let x = 'http://localhost:8080/Battleship/user/';
    x += pid;
    this.http.get(x, { withCredentials: true }).subscribe(
      (successResp) => {
        this.defendant = successResp.json();
      },
      (failResp) => {
      }
    );
  }

  getWL(pId) {
    let currWL = new WinLoss();

    let x = 'http://localhost:8080/Battleship/user/getWL';
    x += '?id=' + pId;
    this.http.get(x, { withCredentials: true }).subscribe(
      (successResp) => {
        const wLId = successResp.text();

        x = 'http://localhost:8080/Battleship/winloss/';
        x += wLId;

        this.http.get(x, { withCredentials: true }).subscribe(
          (successResp2) => {
            currWL = successResp2.json();
          },
          (failResp) => {
          }
        );
      },
      (failResp) => {
      }
    );

    return currWL;
  }

  winnerWL(pId) {
    let wWL = new WinLoss();

    let x = 'http://localhost:8080/Battleship/user/getWL';
    x += '?id=' + pId;
    this.http.get(x, { withCredentials: true }).subscribe(
      (successResp) => {
        const wLId = successResp.text();

        x = 'http://localhost:8080/Battleship/winloss/';
        x += wLId;

        this.http.get(x, { withCredentials: true }).subscribe(
          (successResp2) => {
            wWL = successResp2.json();
            wWL.wins += 1;
            wWL.seasonWins += 1;
            this.saveWL(wWL);
          },
          (failResp) => {
          }
        );
      },
      (failResp) => {
      }
    );
  }

  loserWL(pId) {
    let lWL = new WinLoss();

    let x = 'http://localhost:8080/Battleship/user/getWL';
    x += '?id=' + pId;
    this.http.get(x, { withCredentials: true }).subscribe(
      (successResp) => {
        const wLId = successResp.text();

        x = 'http://localhost:8080/Battleship/winloss/';
        x += wLId;

        this.http.get(x, { withCredentials: true }).subscribe(
          (successResp2) => {
            lWL = successResp2.json();
            lWL.losses += 1;
            lWL.seasonLosses += 1;
            this.saveWL(lWL);
          },
          (failResp) => {
          }
        );
      },
      (failResp) => {
      }
    );
  }

  saveWL(inWL) {
    this.http.put('http://localhost:8080/Battleship/winloss/modify', (inWL), { withCredentials: true }).subscribe(
      (successResp) => {
      },
      (failResp) => {
      }
    );
  }

  updateReport() {
    this.http.put('http://localhost:8080/Battleship/report/modify', (this.ticket), { withCredentials: true }).subscribe(
      (successResp) => {
      },
      (failResp) => {
      }
    );
  }

  updateGame() {
    this.http.put('http://localhost:8080/Battleship/game/modify', (this.game), { withCredentials: true }).subscribe(
      (successResp) => {
      },
      (failResp) => {
      }
    );
  }

  showModal(content) {
    this.modalService.open(content);
  }

  routeTo(location) {
    this.router.navigateByUrl(location);
  }

  dismiss() {
    this.ticket.flag = 2;
    this.updateReport();

    this.game.status = 'inprogress';
    this.updateGame();
  }

  deleteGame() {
    let x = 'http://localhost:8080/Battleship/game/';
    x += this.ticket.gameId;

    this.http.delete(x, { withCredentials: true }).subscribe(
      (successResp2) => {
      },
      (failResp) => {
      }
    );

    this.ticket.flag = 2;
    this.updateReport();
  }

  punishClaimant() {
    this.ticket.winner = this.ticket.defendant;
    this.deleteGame();
    this.loserWL(this.ticket.claimant);
    this.winnerWL(this.ticket.defendant);
  }

  punishDefendant() {
    this.ticket.winner = this.ticket.claimant;
    this.deleteGame();
    this.loserWL(this.ticket.defendant);
    this.winnerWL(this.ticket.claimant);
  }
}
