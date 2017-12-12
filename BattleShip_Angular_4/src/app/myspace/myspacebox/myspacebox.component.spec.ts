import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyspaceboxComponent } from './myspacebox.component';

describe('MyspaceboxComponent', () => {
  let component: MyspaceboxComponent;
  let fixture: ComponentFixture<MyspaceboxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyspaceboxComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyspaceboxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
