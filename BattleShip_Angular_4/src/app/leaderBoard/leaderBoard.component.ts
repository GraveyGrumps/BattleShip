import { Component, DoCheck } from '@angular/core';
import { Http } from '@angular/http';
import { WinLoss } from '../beans/WinLoss';
import { User } from '../beans/User';
import { WinlossService } from '../services/winloss.service';
import { Input } from '@angular/core/';
import { OnInit } from '@angular/core';

@Component({
  selector: 'app-leader-board',
  templateUrl: './leaderBoard.component.html',
  styleUrls: ['./leaderBoard.component.css']
})
export class LeaderBoardComponent implements OnInit {
  constructor(private http: Http, private wls: WinlossService) { }
  @Input()
  users: Array<User> = [];
  @Input()
  range: Array<Number> = [];
  @Input()
  winlosses: Array<WinLoss>;
  @Input()
  title: String;
  @Input()
  global;
  ngOnInit() {
  }
  public convertToUsername(winloss) {
    try {
      return this.users.filter(i => i.winLossId === winloss.id)[0].username;
    } catch (TypeError) {
      return null;
    }
  }
}
