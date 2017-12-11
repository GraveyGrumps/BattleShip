import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { WinLoss } from '../beans/WinLoss';
import { Http } from '@angular/http';
import { environment } from '../../environments/environment';

@Injectable()
export class WinlossService {
  subject: Subject<Array<WinLoss>> = new Subject();
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
