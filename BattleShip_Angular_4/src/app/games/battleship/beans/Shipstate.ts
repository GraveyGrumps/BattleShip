import { Component, OnInit } from '@angular/core';

export class Shipstate {
    details: any[][][];

    constructor() {
        this.details = [];

        for (let i: 0; i < 2; i++) {
            this.details[i] = [];
            for (let j: 0; j < 5; j++) {
                this.details[i][j] = [];
            }
        }

        // Player 1
        // Ship 1 - Carrier
        // Peg 1
        this.details[0][0][0] = false;
        // Peg 2
        this.details[0][0][1] = false;
        // Peg 3
        this.details[0][0][2] = false;
        // Peg 4
        this.details[0][0][3] = false;
        // Peg 5
        this.details[0][0][4] = false;

        // Ship 2 - Battleship
        // Peg 1
        this.details[0][1][0] = false;
        // Peg 2
        this.details[0][1][1] = false;
        // Peg 3
        this.details[0][1][2] = false;
        // Peg 4
        this.details[0][1][3] = false;

        // Ship 3 - Cruiser
        // Peg 1
        this.details[0][2][0] = false;
        // Peg 2
        this.details[0][2][1] = false;
        // Peg 3
        this.details[0][2][2] = false;

        // Ship 4 - Submarine
        // Peg 1
        this.details[0][3][0] = false;
        // Peg 2
        this.details[0][3][1] = false;
        // Peg 3
        this.details[0][3][2] = false;

        // Ship 5 - Destroyer
        // Peg 1
        this.details[0][4][0] = false;
        // Peg 2
        this.details[0][4][1] = false;

        // Player 2
        // Ship 1 - Carrier
        // Peg 1
        this.details[1][0][0] = false;
        // Peg 2
        this.details[1][0][1] = false;
        // Peg 3
        this.details[1][0][2] = false;
        // Peg 4
        this.details[1][0][3] = false;
        // Peg 5
        this.details[1][0][4] = false;

        // Ship 2 - Battleship
        // Peg 1
        this.details[1][1][0] = false;
        // Peg 2
        this.details[1][1][1] = false;
        // Peg 3
        this.details[1][1][2] = false;
        // Peg 4
        this.details[1][1][3] = false;

        // Ship 3 - Cruiser
        // Peg 1
        this.details[1][2][0] = false;
        // Peg 2
        this.details[1][2][1] = false;
        // Peg 3
        this.details[1][2][2] = false;

        // Ship 4 - Submarine
        // Peg 1
        this.details[1][3][0] = false;
        // Peg 2
        this.details[1][3][1] = false;
        // Peg 3
        this.details[1][3][2] = false;

        // Ship 5 - Destroyer
        // Peg 1
        this.details[1][4][0] = false;
        // Peg 2
        this.details[1][4][1] = false;
    }
}
