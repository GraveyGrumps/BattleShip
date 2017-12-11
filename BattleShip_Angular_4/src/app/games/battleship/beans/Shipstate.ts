import { Component, OnInit } from '@angular/core';

export class Shipstate {
    details: any[][];

    constructor() {
        this.details = [];

        for (let i = 0; i < 2; i++) {
            this.details[i] = [];
            for (let j = 0; j < 5; j++) {
                this.details[i][j] = [];
            }
        }

        // Player 1
        // Ship 1 - Carrier
        this.details[0][0] = 5;

        // Ship 2 - Battleship
        this.details[0][1] = 4;

        // Ship 3 - Cruiser
        this.details[0][2] = 3;

        // Ship 4 - Submarine
        this.details[0][3] = 3;

        // Ship 5 - Destroyer
        // Peg 1
        this.details[0][4] = 2;

        // Player 2
        // Ship 1 - Carrier
        this.details[1][0] = 5;

        // Ship 2 - Battleship
        this.details[1][1] = 4;

        // Ship 3 - Cruiser
        this.details[1][2] = 3;

        // Ship 4 - Submarine
        this.details[1][3] = 3;

        // Ship 5 - Destroyer
        // Peg 1
        this.details[1][4] = 2;
    }
}
