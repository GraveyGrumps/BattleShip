import { Component, OnInit } from '@angular/core';
import { Queue } from '@uirouter/angular';
import { Input } from '@angular/core';
import { User } from '../beans/User';
@Component({
  selector: 'app-global-chat',
  templateUrl: './chat-window.component.html',
  styleUrls: ['./chat-window.component.css'],
})
export class ChatWindowComponent implements OnInit {
  private static chat = [];
  private static flag: boolean;
  private static chatBackLog: Queue<String>;
  private user: User;
  ngOnInit() {
    this.user = JSON.parse(sessionStorage.getItem('user'));
    this.addToChat('bill', 'a/s/l');
    this.addToChat('catchy', 'fuck off');
    ChatWindowComponent.flag = false;
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
    if (chat.value.length > 0 && !ChatWindowComponent.flag) {
      ChatWindowComponent.flag = true;
      this.addToChat(this.user.username, chat.value);
      chat.value = '';
      ChatWindowComponent.flag = false;
    }
  }
}
