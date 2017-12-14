import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-healthstatus',
  templateUrl: './healthstatus.component.html',
  styleUrls: ['./healthstatus.component.css']
})
export class HealthstatusComponent implements OnInit {
  @Input()
  healthstatus: Array<boolean>;
  constructor() { }

  ngOnInit() {
  }

}
