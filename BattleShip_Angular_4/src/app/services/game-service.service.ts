import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/Rx';
import { Game } from '../games/battleship/beans/Game';
import { Http } from '@angular/http';
import { environment } from '../../environments/environment';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class GameServiceService {
  subject: BehaviorSubject<Array<Game>> = new BehaviorSubject([]);
  constructor( private http: Http) { }
  public getSubject() {
    this.updateSubject();
    return this.subject;
  }
  public updateSubject() {
    this.http.get(environment.context + '/game/all').subscribe(
      (respbody) => {
        if (respbody.text() !== '') {
          this.subject.next( respbody.json());
        }
      }
    );
  }


}
