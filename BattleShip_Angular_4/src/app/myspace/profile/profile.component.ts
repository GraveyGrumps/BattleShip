import { Component, OnInit } from '@angular/core';
import { User } from '../../beans/User';
import { Http } from '@angular/http';
import { environment } from '../../../environments/environment';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Clan } from '../../beans/Clan';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  currentUser: User;
  usersClan: Clan = new Clan;

  constructor(private http: Http, private modalService: NgbModal) {
  }

  ngOnInit() {
    this.currentUser = JSON.parse(sessionStorage.getItem('user'));
    this.getClan();
  }

  getClan() {
    this.http.get(environment.context + '/clan/' + this.currentUser.clanId, { withCredentials: true })
      .subscribe((succResp) => {
        this.usersClan = succResp.json();
      });
  }
}
