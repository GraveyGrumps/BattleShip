import { Component, OnInit } from '@angular/core';
import { User } from '../../beans/User';
import { Http } from '@angular/http';
import { environment } from '../../../environments/environment';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { WinLoss } from '../../beans/WinLoss';

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.css']
})
export class StatsComponent implements OnInit {

  currentUser: User;
  winLoss: WinLoss = new WinLoss;

  constructor(private http: Http, private modalService: NgbModal) {
  }

  ngOnInit() {
    this.currentUser = JSON.parse(sessionStorage.getItem('user'));
    this.getWinLoss();
  }

  getWinLoss() {
    this.http.get(environment.context + '/winloss/' + this.currentUser.id, { withCredentials: true })
      .subscribe((succResp) => {
        this.winLoss = succResp.json();
      });
  }

}
