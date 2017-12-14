import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HealthstatusComponent } from './healthstatus.component';

describe('HealthstatusComponent', () => {
  let component: HealthstatusComponent;
  let fixture: ComponentFixture<HealthstatusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HealthstatusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HealthstatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
