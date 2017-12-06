import { TestBed, inject } from '@angular/core/testing';

import { GlobalChatService } from './global-chat.service';

describe('GlobalChatService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GlobalChatService]
    });
  });

  it('should be created', inject([GlobalChatService], (service: GlobalChatService) => {
    expect(service).toBeTruthy();
  }));
});
