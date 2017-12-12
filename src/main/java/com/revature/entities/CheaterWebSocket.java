package com.revature.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CheaterWebSocket {
	private List<String> chat;

	public CheaterWebSocket() {
		super();
		this.chat = new ArrayList<>();
	}

	public CheaterWebSocket(List<String> chat) {
		super();
		this.chat = chat;
	}

	public List<String> getChat() {
		return chat;
	}

	public void setChat(List<String> chat) {
		this.chat = chat;
	}
	
	public void addToChat(String chat) {
		this.chat.add(chat);
		if(this.chat.size() > 100) {
			this.chat.remove(0);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chat == null) ? 0 : chat.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CheaterWebSocket other = (CheaterWebSocket) obj;
		if (chat == null) {
			if (other.chat != null)
				return false;
		} else if (!chat.equals(other.chat))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CheaterWebSocket [chat=" + chat + "]";
	}
	
	
}
