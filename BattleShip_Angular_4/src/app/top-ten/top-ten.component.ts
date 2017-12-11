import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { WinLoss } from '../beans/WinLoss';
import { User } from '../beans/User';

@Component({
  selector: 'app-top-ten',
  templateUrl: './top-ten.component.html',
  styleUrls: ['./top-ten.component.css']
})
export class TopTenComponent implements OnInit {
  winlosses: Array<WinLoss>;
  constructor(private http: Http) { }
  users: Array<User> = [];
  range: Array<Number> = [];
  ngOnInit() {
    this.http.get('http://localhost:8080/Battleship/winloss').subscribe(
      (respbody) => {
        if (respbody.text() !== '') {
          this.winlosses = respbody.json();
          this.getTopTen();
          this.populateRange();
          for (let wl of this.winlosses) {
            console.log(wl);
            this.http.get('http://localhost:8080/Battleship/user/winloss/' + wl.id).subscribe(
              (respbody2) => {
                if (respbody2.text() !== '') {
                  this.users.push(respbody2.json());
                }
              });
          }
          console.log(this.users);
        }
      });
  }
  private getTopTen() {
    this.winlosses.sort(this.compare);
    this.winlosses = this.winlosses.filter( i => i.wins + i.losses > 10);
    if (this.winlosses.length > 10) {
      this.winlosses = this.winlosses.slice(0, 10);
    }
  }
  private compare(a, b) {
    if ((a.wins / a.losses) < (b.wins / b.losses)) {
      return 1;
    }
    if ((a.wins / a.losses) > (b.wins / b.losses)) {
      return -1;
    }
    return 0;
  }
  private populateRange() {
    for (let i = 0; i < this.winlosses.length; i++) {
      this.range.push(i);
    }
  }
  public convertToUsername(winloss) {
    try {
      return this.users.filter(i => i.winLossId === winloss.id)[0].username;
    } catch (TypeError) {
      return null;
    }
  }
}
