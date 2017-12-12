import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { ReplaySubject } from 'rxjs/ReplaySubject';
import { Http } from '@angular/http';
import { environment } from '../../environments/environment';

@Injectable()
export class GlobalchatService {
  private subject: Subject<Array<String>> = new Subject();
  constructor(private http: Http) { }
  public getSubject() {
    this.refreshSubject();
    return this.subject;
  }
  public updateSubject(s: String) {
    this.http.post(
      environment.context + '/websocket/chat',
      s
    ).subscribe( (chat) => {
      this.subject.next(chat.json());
    });
  }
  public refreshSubject() {
    this.http.get(environment.context + '/websocket').subscribe( (chat) => {
      this.subject.next(chat.json());
    });
  }
}
