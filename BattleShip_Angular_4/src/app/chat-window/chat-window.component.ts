import { Component, OnInit, OnChanges, DoCheck, OnDestroy } from '@angular/core';
import { Queue } from '@uirouter/angular';
import { Input } from '@angular/core';
import { User } from '../beans/User';
import { GlobalchatService } from '../services/globalchat.service';
import { SubjectSubscriber } from 'rxjs/Subject';
import { Subscription } from 'rxjs/Subscription';
import { Subscriber } from 'rxjs/Subscriber';
import { Observer } from 'rxjs/Observer';
import { IntervalObservable } from 'rxjs/observable/IntervalObservable';
import { Http } from '@angular/http';
import { environment } from '../../environments/environment';
import { Settings } from '../beans/Settings';

@Component({
  selector: 'app-global-chat',
  templateUrl: './chat-window.component.html',
  styleUrls: ['./chat-window.component.css'],
})
export class ChatWindowComponent implements OnInit, OnDestroy {
  private static flag: boolean;
  private static chatBackLog: Queue<String>;
  public globalchat: Array<String> = [];
  private user: User;
  private sub: Subscription;
  private alive = true;

  constructor(private gcs: GlobalchatService, private http: Http) {
  }

  currentUser: User;
  settings: Settings = new Settings;

  ngOnInit() {
    IntervalObservable.create(500)
      .takeWhile(() => this.alive)
      .subscribe(() => {
        this.gcs.getSubject().subscribe((chat) => this.globalchat = chat);
      });
    this.user = JSON.parse(sessionStorage.getItem('user'));
    ChatWindowComponent.flag = false;

    this.currentUser = JSON.parse(sessionStorage.getItem('user'));
    this.getSettings();
  }

  getSettings() {
    this.http.get(environment.context + '/settings/' + this.currentUser.settingsId, { withCredentials: true })
      .subscribe((succResp) => {
        this.settings = succResp.json();
      });
  }

  public addToChat(username, string) {
    this.gcs.updateSubject(`<${username}>: ${string}`);
  }
  public onKey(chat: any) {
    if (chat.value.length > 0 && !ChatWindowComponent.flag) {
      ChatWindowComponent.flag = true;
      this.addToChat(this.user.username, chat.value);
      chat.value = '';
      ChatWindowComponent.flag = false;
    }
  }
  ngOnDestroy() {
    this.alive = false;
  }
}
