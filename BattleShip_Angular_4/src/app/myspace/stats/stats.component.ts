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
    this.currentUser = JSON.parse(sessionStorage.getItem('user'));
  }

  ngOnInit() {
    this.getWinLoss();
  }

  getWinLoss() {
    // this.winLoss = this.http.get(environment.context +
    //   '/user/winloss/' + this.currentUser.id)[0];
    // console.log(typeof (this.winLoss));
    // return this.winLoss.wins;
    this.http.get(environment.context + '/winloss/' + this.currentUser.id, { withCredentials: true })
      .subscribe((succResp) => {
        this.winLoss = succResp.json();
      });
  }
}

// this.http.post(environment.context + '/user/login', this.credential, { withCredentials: true })
//   .subscribe((succResp) => {
//     if (succResp.text() !== '') {
//       sessionStorage.setItem('user', succResp.text());
//       console.log(JSON.parse(sessionStorage.getItem('user')));
//       console.log(sessionStorage.length);
//       this.router.navigateByUrl('/users/home');
//     } else {
//       alert('failed to login');
//     }
//   });