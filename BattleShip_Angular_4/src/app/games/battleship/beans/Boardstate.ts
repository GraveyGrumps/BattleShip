import { Component, OnInit } from '@angular/core';
import { Tile } from './Tile';


export class Boardstate {
    tiles: any[];

    constructor() {
        this.tiles = [];

        for (let i = 0; i < 2; i++) {
             this.tiles[i] = [];
            for (let j = 0; j < 10; j++) {
                this.tiles[i][j] = [];
                for (let k = 0; k < 10; k++) {
                    this.tiles[i][j][k] = new Tile;
                }
            }
        }
    }
}
