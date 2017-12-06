import { Injectable } from '@angular/core';

@Injectable()
export class GlobalChatService {
  private static self = new GlobalChatService();
  private chat =  '';
  private constructor() { }
  public static get Instance() {
    return this.self;
  }

  public addToChat(username, chat) {
    this.chat +=  `<${username}> : ${chat}`;
    if (chat.length > 3000) {
      chat = chat.substring(100);
    }
  }
  public getChat() {
    return this.chat;
  }
}
