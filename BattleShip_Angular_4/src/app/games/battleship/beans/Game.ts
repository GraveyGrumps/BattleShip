import { Component, OnInit } from '@angular/core';
import { Boardstate } from './Boardstate';
import { Shipstate } from './Shipstate';
import { Boardstate2 } from './Boardstate2';

export class Game {
    id: number;
    status: string;
    player1Id: number;
    player2Id: number;
    turn: number;
    Board_State: Boardstate2;
    boardState: string;
    Ship_State: Shipstate;
    shipState: string;
    postDate: Date;
    turnDeadline: Date;
    turnLength: number;

    constructor() {
        this.status = '';
        this.turn = 0;
        this.boardState = JSON.stringify(new Boardstate2());
        this.shipState = JSON.stringify(new Shipstate());
        this.postDate = new Date();
        this.turnDeadline = new Date();
        this.turnLength = 10;
    }
}
