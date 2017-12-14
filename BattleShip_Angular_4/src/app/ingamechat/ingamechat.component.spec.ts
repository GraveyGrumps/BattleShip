import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IngamechatComponent } from './ingamechat.component';

describe('IngamechatComponent', () => {
  let component: IngamechatComponent;
  let fixture: ComponentFixture<IngamechatComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IngamechatComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IngamechatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
