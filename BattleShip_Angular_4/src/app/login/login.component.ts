import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Http } from '@angular/http';
import { environment } from '../../environments/environment';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  closeResult: string;
  credential = {
    username: '',
    password: '',
  };

  constructor(private router: Router, private http: Http, private modalService: NgbModal) { }

  ngOnInit() {
    sessionStorage.removeItem('user');
  }

  submit() {

    this.http.post(environment.context + '/user/login', this.credential, { withCredentials: true })
      .subscribe((succResp) => {
        if (succResp.text() !== '') {
          sessionStorage.setItem('user', succResp.text());
          console.log(JSON.parse(sessionStorage.getItem('user')));
          this.router.navigateByUrl('/users/home');
        } else {
          alert('failed to login');
        }
      });
  }
  showModal(content) {
    this.modalService.open(content);
  }
}
