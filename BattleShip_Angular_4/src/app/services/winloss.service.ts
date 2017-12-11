import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/Rx';
import { WinLoss } from '../beans/WinLoss';
import { Http } from '@angular/http';
import { environment } from '../../environments/environment';

@Injectable()
export class WinlossService {
  subject: BehaviorSubject<Array<WinLoss>> = new BehaviorSubject([]);
  constructor(private http: Http) { }
  public getSubject() {
    this.updateSubject();
    return this.subject;
  }
  public updateSubject() {
    this.http.get(environment.context + '/winloss').subscribe(
      (respbody) => {
        if (respbody.text() !== '') {
          this.subject.next(respbody.json());
        }
      }
    );
  }
}
