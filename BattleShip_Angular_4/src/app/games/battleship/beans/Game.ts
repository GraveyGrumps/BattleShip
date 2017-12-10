import { Component, OnInit } from '@angular/core';
import { Boardstate } from './Boardstate';
import { Shipstate } from './Shipstate';

export class Game {
    id: number;
    status: String;
    player1Id: number;
    player2Id: number;
    turn: number;
    Board_State: Boardstate;
    boardState: String;
    Ship_State: Shipstate;
    shipState: String;
    postDate: Date;
    turnDeadline: Date;
    turnLength: number;

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
