import { Component, OnInit } from '@angular/core';
import { User } from '../../beans/User';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  currentUser: User;

  constructor() {
    this.currentUser = JSON.parse(sessionStorage.getItem('user'));
  }

  ngOnInit() {
  }
}
