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
  user = {
    username: '',
    password: '',
    email: '',
    profilePic: '',
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
          console.log(sessionStorage.length);
          this.router.navigateByUrl('/users/home');
        } else {
          alert('failed to login');
        }
      });
  }
  showModal(content) {
    this.modalService.open(content);
  }
  createUser(c) {
    console.log('creating user');
    console.log(this.user);
    if (this.user.username === '' || this.user.password === '' || this.user.email === '' || this.user.profilePic === '') {
      alert('cannot have any empty fields');
      return;
    } else {
      this.http.post(environment.context + '/user/new', this.user, { withCredentials: true })
        .subscribe((succResp) => {
          if (succResp.text() !== '') {
            alert('successfully made user');
            this.user.username = '';
            this.user.email = '';
            this.user.password = '';
            this.user.profilePic = '';
            c('close');
          } else {
            alert('failed to create user');
          }
        });
    }
  }
}
