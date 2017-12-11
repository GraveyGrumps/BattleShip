import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/Rx';
import { User } from '../beans/User';
import { Http } from '@angular/http';
import { environment } from '../../environments/environment';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class UserService {
  subject: Subject<Array<User>> = new Subject();
  private flag = true;
  constructor(private http: Http) { }
  // only update once
  public getSubject() {
    this.updateSubject();
    return this.subject;
  }

  public updateSubject() {
    this.http.get(environment.context + '/user/all', {withCredentials: true}).subscribe(
      (respbody) => {
        if (respbody.text() !== '') {
          this.subject.next(respbody.json());
        }
      }
    );
  }

}
