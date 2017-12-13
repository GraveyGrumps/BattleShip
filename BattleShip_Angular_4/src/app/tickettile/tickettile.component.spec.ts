import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TickettileComponent } from './tickettile.component';

describe('tickettileComponent', () => {
  let component: TickettileComponent;
  let fixture: ComponentFixture<TickettileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TickettileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TickettileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
