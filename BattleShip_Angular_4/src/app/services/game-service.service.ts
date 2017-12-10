import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { Game } from '../games/battleship/beans/Game';
import { Http } from '@angular/http';
import { environment } from '../../environments/environment';

@Injectable()
export class GameServiceService {
  subject: Subject<Array<Game>> = new Subject();
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
