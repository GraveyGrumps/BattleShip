import { Component, OnInit } from '@angular/core';
@Component({
  selector: 'app-global-chat',
  templateUrl: './chat-window.component.html',
  styleUrls: ['./chat-window.component.css'],
})
export class ChatWindowComponent implements OnInit {
  private static chat = [];

  ngOnInit() {
    this.addToChat('bill', 'a/s/l');
    this.addToChat('catchy', 'fuck off');
  }

  public addToChat(username, string) {
    ChatWindowComponent.chat.push(`<${username}>: ${string}`);
    if (ChatWindowComponent.chat.length > 100) {
      ChatWindowComponent.chat.splice(0, 1);
    }
  }
  public getChat() {
    return ChatWindowComponent.chat;
  }
  public onKey(chat: any) {
    if (chat.value.length > 0) {
      this.addToChat('filler', chat.value);
      chat.value = '';
    }
    document.getElementById("chatboxDiv").scroll(0, 0);
  }
}
