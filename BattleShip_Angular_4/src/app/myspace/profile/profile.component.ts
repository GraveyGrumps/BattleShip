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
  officer: string;

  constructor(private http: Http, private modalService: NgbModal) {
  }

  ngOnInit() {
    this.currentUser = JSON.parse(sessionStorage.getItem('user'));
    this.officer = this.getOfficer(this.currentUser.isOfficer);
    this.getClan();
  }

  getOfficer(ofcr) {
    if (ofcr === 1) {
      return '(Clan Officer)';
    } else {
      return '';
    }
  }

  getClan() {
    this.http.get(environment.context + '/clan/' + this.currentUser.clanId, { withCredentials: true })
      .subscribe((succResp) => {
        this.usersClan = succResp.json();
      });
  }
}
