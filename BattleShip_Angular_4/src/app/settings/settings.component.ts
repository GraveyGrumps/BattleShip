import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { environment } from '../../environments/environment';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { User } from '../beans/User';
import { Settings } from '../beans/Settings';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  // styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  currentUser: User;
  settings: Settings = new Settings;
  newUsername = '';
  newPassword = '';
  passwordConfirm = '';
  updatedUser = false;

  constructor(private http: Http, private modalService: NgbModal) {
  }

  ngOnInit() {
    this.currentUser = JSON.parse(sessionStorage.getItem('user'));
    this.getSettings();
  }

  getSettings() {
    this.http.get(environment.context + '/settings/' + this.currentUser.settingsId, { withCredentials: true })
      .subscribe((succResp) => {
        this.settings = succResp.json();
      });
  }

  saveSettings() {
    this.settings.globalChat = this.boolToNum(this.settings.globalChat);
    this.settings.inGameChat = this.boolToNum(this.settings.inGameChat);
    this.settings.acceptFriendship = this.boolToNum(this.settings.acceptFriendship);
    this.settings.allowChallenges = this.boolToNum(this.settings.allowChallenges);
    this.settings.viewable = this.boolToNum(this.settings.viewable);

    if (this.newUsername.length > 0) {
      this.currentUser.username = this.newUsername;
      this.updatedUser = true;
    }

    if (this.newPassword.length > 0 && this.newPassword === this.passwordConfirm) {
      this.currentUser.password = this.newPassword;
      this.updatedUser = true;
    }

    console.log('this.currentUser.username: ' + this.currentUser.username);
    console.log('this.currentUser.password: ' + this.currentUser.password);

    this.http.put(environment.context + '/settings/save/', this.settings)
      .subscribe((succResp) => {
        if (succResp.text() !== '') {
          alert('Your settings have been updated.');
        } else {
          alert('Something went wrong.');
        }
      });

    if (this.updatedUser) {
      this.http.put(environment.context + '/user/modify', this.currentUser)
        .subscribe((succResp) => { });

      this.http.put(environment.context + '/user/login', this.currentUser)
        .subscribe((succResp) => { });
    }
  }

  boolToNum(bool) {
    if (bool) {
      return 1;
    } else {
      return 0;
    }
  }
}

