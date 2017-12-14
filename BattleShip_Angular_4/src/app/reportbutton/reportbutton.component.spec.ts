import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportbuttonComponent } from './reportbutton.component';

describe('ReportbuttonComponent', () => {
  let component: ReportbuttonComponent;
  let fixture: ComponentFixture<ReportbuttonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReportbuttonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportbuttonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
