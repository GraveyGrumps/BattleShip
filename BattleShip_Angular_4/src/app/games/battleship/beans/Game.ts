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
        this.status = '';
        this.turn = 0;
        this.boardState = JSON.stringify(new Boardstate());
        this.shipState = JSON.stringify(new Shipstate());
        this.postDate = new Date();
        this.turnDeadline = new Date();
        this.turnLength = 10;
    }
}
