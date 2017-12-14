import { Component, OnInit, DoCheck, OnDestroy } from '@angular/core';
import { User } from '../beans/User';
import { Subscription } from 'rxjs/Subscription';
import { Report } from '../beans/Report';
import { Http } from '@angular/http';
import { Input } from '@angular/core';
import { environment } from '../../environments/environment';
import { Settings } from '../beans/Settings';

@Component({
  selector: 'app-ingamechat',
  templateUrl: './ingamechat.component.html',
  styleUrls: ['./ingamechat.component.css']
})
export class IngamechatComponent implements OnInit, OnDestroy, DoCheck {

  private static flag: boolean;
  @Input()
  private report: Report;
  private user: User;
  private sub: Subscription;
  private alive = true;
  constructor(private http: Http) {
  }

  settings: Settings = new Settings;

  ngOnInit() {
      this.user = JSON.parse(sessionStorage.getItem('user'));
      if (!this.report.chatLog) {
        this.report.chatLog += (`*`);
      }
    }

  ngDoCheck() {
  }

  public addToChat(username, string) {
    this.report.chatLog += (`<${username}>: ${string}|`);
    this.http.put(environment.context + '/report/modify', this.report, { withCredentials: true })
      .subscribe((resp) => console.log('successfullysent'));
  }

  public onKey(chat: any) {
    if (chat.value.length > 0) {
      this.addToChat(this.user.username, chat.value);
      chat.value = '';
    }
  }

  getSettings() {
    this.http.get(environment.context + '/settings/' + this.user.settingsId, { withCredentials: true })
      .subscribe((succResp) => {
        this.settings = succResp.json();
      });
  }

  ngOnDestroy() {
    this.alive = false;
  }
}
