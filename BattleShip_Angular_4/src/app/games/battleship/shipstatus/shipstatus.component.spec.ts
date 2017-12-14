import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShipstatusComponent } from './shipstatus.component';

describe('ShipstatusComponent', () => {
  let component: ShipstatusComponent;
  let fixture: ComponentFixture<ShipstatusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShipstatusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShipstatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
