import { Component, OnInit } from '@angular/core';

export class Report {
    id: number;
    gameId: number;
    winner: number;
    chatLog: String;
    reportDate: Date;
    claimant: number;
    defendant: number;
    reportNotes: String;
    flag: number;

    constructor() {
        this.flag = 0;
        this.claimant = 0;
        this.defendant = 0;
        this.winner = 0;
    }
}
