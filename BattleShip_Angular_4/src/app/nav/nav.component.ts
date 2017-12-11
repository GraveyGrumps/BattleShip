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

  constructor(private router: Router) {}
  ngOnInit() {
    this.user = JSON.parse(sessionStorage.getItem('user'));
    console.log(this.user);
  }
  ngDoCheck () {
    this.user = JSON.parse(sessionStorage.getItem('user'));
  }

  routeTo (location) {
    this.router.navigateByUrl(location);
  }
}
