import { Component, OnInit } from '@angular/core';

export class Report {
    id: Number;
    gameId: Number;
    winner: Number;
    chatLog: String;
    reportDate: Date;
    claimant: Number;
    defendant: Number;
    reportNotes: String;
    flag: Number;

    constructor() {
        this.flag = 0;
        this.claimant = 0;
        this.defendant = 0;
        this.winner = 0;
    }
}
