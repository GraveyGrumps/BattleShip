import { Component, OnInit, Input, DoCheck } from '@angular/core';
import { Game } from '../beans/Game';
import { User } from '../../../beans/User';
import { Http } from '@angular/http';
import { Boardstate2 } from '../beans/boardstate2';
import { Shipstate } from '../beans/Shipstate';
import { WinLoss } from '../../../beans/WinLoss';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit, DoCheck {

  @Input()
  game: Game;
  @Input()
  user: User;
  gamestatus: string;
  renderShips: boolean;
  boards;
  holderarray = [];
  shipNames = ['patrolboat', 'destroyer', 'submarine', 'battleship', 'carrier'];
  placed = [false, false, false, false, false];
  ship = [2, 3, 4, 5, 6];
  isplaying = false;
  down = true;
  validplacement: boolean;
  currentship = -1;
  clicklocation = [];
  sameclick: boolean;
  myboard;
  opboard;
  myShipsStatus;
  opShipsStatus;
  whatUserAmI;
  boardstate;
  shipstate;
  canInteract: boolean;
  constructor(private http: Http) { }

  ngOnInit() {
    console.log(this.game);
    this.boardstate = JSON.parse(this.game.boardState);
    this.shipstate = JSON.parse(this.game.shipState);
    if (this.user.id === this.game.player1Id) {
      this.whatUserAmI = 0;
      this.myboard = this.boardstate.p1board;
      this.opboard = this.boardstate.p2board;
      this.myShipsStatus = this.shipstate.p1ships;
      this.opShipsStatus = this.shipstate.p2ships;
    } else {
      this.whatUserAmI = 1;
      this.myboard = this.boardstate.p2board;
      this.opboard = this.boardstate.p1board;
      this.myShipsStatus = this.shipstate.p2ships;
      this.opShipsStatus = this.shipstate.p1ships;
    }
    console.log(this.myboard);
    console.log(this.opboard);
    console.log(this.game);
    if (this.game.turn !== this.whatUserAmI) {
      this.canInteract = false;
      console.log('not your turn!');
    } else {
      console.log('its your turn');
      this.canInteract = true;
      if (this.game.status === 'inprogress') {
        this.isplaying = true;
      }
    }
    // this.board = [[-1,-1,-1,-1,-1,-1,-1-1][2,-1,-1,-1,-1,-1,-1,-1]];
  }
  ngDoCheck() {
    if(this.game.status === 'inprogress') {
      this.isplaying = true;
    } else if (this.game.status !== 'complete') {
      this.isplaying = false;
    }
    if (this.game.turn === this.whatUserAmI) {
      // console.log(this.game.turn);
      // console.log(this.whatUserAmI);
      this.canInteract = true;
      this.loadBoards();
    } else {
      this.canInteract = false;
    }
  }

  fire(board, loc1, loc2) {
    console.log('fire information');
    console.log(board, loc1, loc2);
    console.log(this.isplaying);
    if (!this.canInteract) {
      return;
    }
    if (this.isplaying && board === 0) {
      this.firedAtBefore(board, loc1, loc2);
    } else {
      if (this.clicklocation[0] !== loc1 ||
        this.clicklocation[1] !== loc2) {
        this.sameclick = false;
        this.down = true;
        this.clicklocation[0] = loc1;
        this.clicklocation[1] = loc2;
      } else {
        this.sameclick = true;
        this.down = !this.down;
      }
      console.log(this.holderarray);
      this.clearHolder();
      this.placeShip(loc1, loc2);
    }
  }

  firedAtBefore(board: number, loc1: number, loc2: number) {
    if (document.getElementById(board.toString() + loc1.toString() + loc2.toString()).className === 'boardgridblue') {
      this.checkForShips(loc1, loc2);
    } else {
      window.alert('dont waste shots');
    }
  }

  checkForShips(loc1: number, loc2: number) {
    console.log('checking for ships at: ' + loc1, loc2);
    console.log(this.opboard[loc1][loc2]);
    if (this.opboard[loc1][loc2] === -1) {
      // document.getElementById('0' + loc1.toString() + loc2.toString()).className = 'boardgridwhite';
      this.opboard[loc1][loc2] = 0;
    } else {
      const value = this.opboard[loc1][loc2];
      this.opboard[loc1][loc2] = 1;
      // document.getElementById('0' + loc1.toString() + loc2.toString()).className = 'boardgridred';
      this.damageShip(value);
    }
    this.checkEnd();

  }
  damageShip(value: number) {
    console.log('damaged Ship');
    console.log(value);
    this.opShipsStatus[value - 2] = <number>this.opShipsStatus[value - 2] - 1;
    console.log(this.opShipsStatus);
  }
  placeShip(loc1: number, loc2: number) {
    // if has ship
    if (this.currentship !== -1 && !this.placed[this.currentship]) {
      // check down
      if (this.down) {
        // check for placing down
        if (loc1 + this.myShipsStatus[this.currentship - 2] <= 10 && this.collisionCheck(this.down, loc1, loc2)) {
          console.log('can place down');
          let temp = loc1;
          while (temp < loc1 + this.myShipsStatus[this.currentship - 2]) {
            this.holderarray.push([temp, loc2]);
            this.myboard[temp][loc2] = this.currentship;
            this.setColor(1, temp, loc2, this.currentship);
            temp++;
          }
          this.validplacement = true;
        } else {
          let temp = loc1;
          while (temp < 10 && temp < loc1 + this.myShipsStatus[this.currentship - 2]) {
            this.holderarray.push([temp, loc2]);
            if (this.myboard[temp][loc2] === -1) {
              document.getElementById('1' + temp.toString() + loc2.toString()).className = 'boardgridred';
            }
            temp++;
          }
          this.validplacement = false;
        }
      } else {
        if (loc2 + this.myShipsStatus[this.currentship] <= 10 && this.collisionCheck(this.down, loc1, loc2)) {
          console.log('can place right');
          let temp = loc2;
          while (temp < loc2 + this.myShipsStatus[this.currentship - 2]) {
            this.holderarray.push([loc1, temp]);
            this.myboard[loc1][temp] = this.currentship;
            this.setColor(1, loc1, temp, this.currentship);
            temp++;
          }
          this.validplacement = true;
        } else {
          let temp = loc2;
          while (temp < 10 && temp < loc2 + this.myShipsStatus[this.currentship - 2]) {
            this.holderarray.push([loc1, temp]);
            if (this.myboard[loc1][temp] === -1) {
              document.getElementById('1' + loc1.toString() + temp.toString()).className = 'boardgridred';
            }
            temp++;
          }
          this.validplacement = false;
        }
      }
    }
  }
  clearHolder() {
    console.log(this.myboard);
    console.log(this.holderarray);
    if (this.holderarray.length > 0) {
      for (const elem of this.holderarray) {
        // console.log(elem);
        // console.log(this.board[elem[0]][elem[1]] );

        if (this.validplacement) {
          this.myboard[elem[0]][elem[1]] = -1;
        }
        if (this.myboard[elem[0]][elem[1]] === -1) {
          // console.log('color');
          document.getElementById('1' + elem[0].toString() + elem[1].toString()).className = 'boardgridblue';
        }
      }
    }
    this.holderarray.length = 0;
  }

  collisionCheck(isDown: boolean, loc1: number, loc2: number) {
    console.log('the current ship is: ' + this.myShipsStatus[this.currentship]);
    if (isDown) {
      let temp = loc1;
      while (temp < 10 && temp < loc1 + this.myShipsStatus[this.currentship]) {
        console.log('board');
        console.log(this.myboard[temp][loc2]);
        console.log('location');
        console.log(temp, loc2);
        if (this.myboard[temp][loc2] !== -1) {
          console.log('board');
          console.log(this.myboard[temp][loc2]);
          console.log('location');
          console.log(temp, loc2);
          this.validplacement = false;
          return false;
        }
        temp++;
      }
    } else {
      let temp = loc2;
      while (temp < 10 && temp < loc2 + this.myShipsStatus[this.currentship]) {
        console.log('board');
        console.log(this.myboard[loc1][temp]);
        console.log('location');
        console.log(loc1, temp);
        if (this.myboard[loc1][temp] !== -1) {
          return false;
        }
        temp++;
      }
    }
    return true;
  }
  selected(ship: number) {
    console.log('selected: ' + ship);
    this.clearBoardErrors();
    this.holderarray.length = 0;
    if (this.validplacement) {
      this.placed[this.currentship - 2] = true;
    }
    this.currentship = this.ship[ship];

  }
  clearBoardErrors() {
    console.log(this.myboard);
    for (let i = 0; i < 10; i++) {
      for (let j = 0; j < 10; j++) {
        if (this.myboard[i][j] === -1) {
          document.getElementById('1' + i.toString() + j.toString()).className = 'boardgridblue';
        }
      }
    }
  }
  setColor(boardnum, num1, num2, check) {
    let color = '';
    switch (check) {
      case -1: color = 'boardgridblue';
        break;
      case 0: color = 'boardgridwhite';
        break;
      case 1: color = 'boardgridred';
        break;
      case 2: color = 'boardgridpb';
        break;
      case 3: color = 'boardgridd';
        break;
      case 4: color = 'boardgrids';
        break;
      case 5: color = 'boardgridb';
        break;
      case 6: color = 'boardgridc';
        break;
    }
    document.getElementById(boardnum.toString() + num1.toString() + num2.toString()).className = color;
  }

  loadBoards() {
    this.loadBoardOpp();
    this.loadBoardMe();
  }

  loadBoardOpp() {
    for (let i = 0; i < 10; i++) {
      for (let j = 0; j < 10; j++) {
        if (this.opboard[i][j] > 1) {
          this.setColor(0, i, j, -1);
        } else {
          this.setColor(0, i, j, this.opboard[i][j]);
        }
      }
    }
    // for (let i = 0; i < 10; i++) {
    //   for (let j = 0; j < 10; j++) {
    //     this.setColor(0, i, j, this.opboard[i][j]);
    //   }
    // }
  }
  loadBoardMe() {
    for (let i = 0; i < 10; i++) {
      for (let j = 0; j < 10; j++) {
        this.setColor(1, i, j, this.myboard[i][j]);
      }
    }
  }
  finishedPlacing() {
    if (this.checkAllBoatsPlaced()) {
      this.isplaying = false;
      this.submitMove();
    }
  }
  checkAllBoatsPlaced() {
    console.log(this.placed);
    for (const bool of this.placed) {
      if (!bool) {
        return bool;
      }
    }
    return true;
  }
  submitMove() {
    this.canInteract = false;
    console.log('submitting');
    if (this.whatUserAmI === 0) {
      this.boardstate.p1board = this.myboard;
      this.boardstate.p2board = this.opboard;
      this.shipstate.p1ships = this.myShipsStatus;
      this.shipstate.p2ships = this.opShipsStatus;
      if (this.game.status !== 'inprogress') {
        this.game.status = 'setup2';
      }

    } else {
      this.boardstate.p1board = this.opboard;
      this.boardstate.p2board = this.myboard;
      this.shipstate.p1ships = this.opShipsStatus;
      this.shipstate.p2ships = this.myShipsStatus;
      this.game.status = 'inprogress';
    }
    this.game.boardState = JSON.stringify(this.boardstate);
    this.game.shipState = JSON.stringify(this.shipstate);
    this.game.turn = ((this.game.turn + 1) % 2);
    this.http.put('http://localhost:8080/Battleship/game/modify', (this.game), { withCredentials: true }).subscribe(
      (successResp) => {
        console.log(this.game.turn);
        alert('success');
      },
      (failResp) => {
        alert('Failed Update Game :`(');
      }
    );
  }
  checkEnd() {
    // check p1 ships
    let flag = false;
    for (const ship of this.opShipsStatus) {
      if (ship !== 0) {
        flag = true;
        break;
      }
    }
    if (!flag) {
      let myWin: WinLoss;
      let opWin: WinLoss;
      let opId: number;
      if (this.whatUserAmI === 0) {
        opId = this.game.player2Id;
      } else {
        opId = this.game.player1Id;
      }

      this.http.get('http://localhost:8080/Battleship/winloss/' + this.user.id, { withCredentials: true }).subscribe(
        (resp) => {
          if (resp.text !== null) {
            myWin = resp.json();
            myWin.wins++;
            myWin.seasonWins++;
            this.http.put('http://localhost:8080/Battleship/winloss/modify', (myWin) , { withCredentials: true}).subscribe(
              (successResp) => {
                alert('Congrats you WINNER');
              },
              (failResp) => {
                alert('Failed to give you your WIN');
              }
            );
          }
        },
        (failResp) => {
          alert('Failed Update Game :`(');
        }
      );
    this.http.get('http://localhost:8080/Battleship/user/winloss/' + opId, { withCredentials: true}).subscribe(
      (resp) => {
        if (resp.text !== null) {
          opWin = resp.json();
          opWin.losses++;
          opWin.seasonLosses++;
          this.http.put('http://localhost:8080/Battleship/winloss/modify', (myWin) , { withCredentials: true}).subscribe();
        }
      }
    );
    this.game.status = 'complete';
    this.game.turn = 2;
    this.http.put('http://localhost:8080/Battleship/game/modify', (this.game), { withCredentials: true }).subscribe();
    } else {
      this.submitMove();
    }
  }
}
