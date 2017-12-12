import { TestBed, inject } from '@angular/core/testing';

import { GlobalchatService } from './globalchat.service';

describe('GlobalchatService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GlobalchatService]
    });
  });

  it('should be created', inject([GlobalchatService], (service: GlobalchatService) => {
    expect(service).toBeTruthy();
  }));
});
