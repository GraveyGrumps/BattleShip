import { Component, OnInit } from '@angular/core';

export class Settings {
    id: number;
    globalChat: number;
    inGameChat: number;
    acceptFriendship: number;
    allowChallenges: number;
    viewable: number;

    constructor() {
        this.globalChat = 1;
        this.inGameChat = 1;
        this.acceptFriendship = 1;
        this.allowChallenges = 1;
        this.viewable = 1;
    }
}
