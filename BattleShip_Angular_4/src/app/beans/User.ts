import { Component, OnInit } from '@angular/core';

export class User {
    id: Number;
    username: String;
    password: String;
    email: String;
    admin: Number;
    settingsId: Number;
    winLossId: Number;
    profilePic: String;
    clanId: Number;
    isOfficer: Number;
    adminNotes: String;
    hash: String;
    verified: Number;

    constructor() {
        this.profilePic = 'https://cdn.shopify.com/s/files/1/1061/1924/products/Poop_Emoji_7b204f05-eec6-4496-91b1-351acc03d2c7_large.png?v=1480481059';
        this.admin = 0;
        this.isOfficer = 0;
        this.verified = 0;
    }
}
