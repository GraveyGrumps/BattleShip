import { Component, OnInit } from '@angular/core';

export class Settings {
    id: Number;
    globalChat: Number;
    inGameChat: Number;
    acceptFriendship: Number;
    allowChallenges: Number;
    viewable: number;

    constructor() {
        this.globalChat = 1;
        this.inGameChat = 1;
        this.acceptFriendship = 1;
        this.allowChallenges = 1;
        this.viewable = 1;
    }
}
