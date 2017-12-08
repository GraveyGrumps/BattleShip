import { Component, OnInit } from '@angular/core';
import { Boardstate } from './Boardstate';
import { Shipstate } from './Shipstate';

export class Game {
    id: Number;
    status: String;
    player1Id: Number;
    player2Id: Number;
    turn: Number;
    Board_State: Boardstate;
    boardState: String;
    Ship_State: Shipstate;
    shipState: String;
    postDate: Date;
    turnDeadline: Date;
    turnLength: Number;

    constructor() {
        this.status = 'Pending';
        this.turn = 1;
        this.Board_State = new Boardstate();
        this.Ship_State = new Shipstate();
        this.boardState = JSON.stringify(this.Board_State);
        this.shipState = JSON.stringify(this.Ship_State);
    }
}
