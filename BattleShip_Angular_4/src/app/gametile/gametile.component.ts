import { Component, OnInit, ViewEncapsulation } from '@angular/core';

import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
@Component({
  selector: 'app-gametile',
  templateUrl: './gametile.component.html',
  styleUrls: ['./gametile.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class GametileComponent implements OnInit {
  fullimagepath = '/assets/images/battleshipcover.jpg';
  constructor(private modalService: NgbModal) { }

  ngOnInit() {
  }
  showModal(content) {
    this.modalService.open(content);
  }
}
