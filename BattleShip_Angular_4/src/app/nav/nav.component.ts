import { Component , OnInit, DoCheck, SimpleChange} from '@angular/core';
import { User } from '../beans/User';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit, DoCheck {
  private user: User;
  private hasUser: boolean;
  constructor(private router: Router) {}
  ngOnInit() {
    this.user = JSON.parse(sessionStorage.getItem('user'));
    this.hasUser = this.user === null;
    console.log(this.hasUser);
  }
  ngDoCheck () {
    this.user = JSON.parse(sessionStorage.getItem('user'));
    this.hasUser = this.user === null;
  }

  routeTo (location) {
    this.router.navigateByUrl(location);
  }
}
