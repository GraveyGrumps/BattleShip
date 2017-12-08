package com.revature.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

@Component
@Entity
// @Table(name = "Settings")
public class Settings {

	@Id
	@Column(name = "S_ID")
	@SequenceGenerator(name = "S_seq", sequenceName = "S_seq")
	@GeneratedValue(generator = "S_seq", strategy = GenerationType.AUTO)
	private int id;

	// Allow global chat?
	@Column(name = "Global_Chat")
	private int globalChat;

	// Allow in-game chat?
	@Column(name = "In_Game_Chat")
	private int inGameChat;

	// what does this field signify? A: wither or not they can receive friend requests
	@Column(name = "Accept_Friendship")
	private int acceptFriendship;

	// Allow global chat?
	@Column(name = "Allow_Challenges")
	private int allowChallenges;

	// Is profile visible to others?
	@Column(name = "Viewable")
	private int viewable;

	public Settings(int id, int globalChat, int inGameChat, int acceptFriendship, int allowChallenges, int viewable) {
		super();
		this.id = id;
		this.globalChat = globalChat;
		this.inGameChat = inGameChat;
		this.acceptFriendship = acceptFriendship;
		this.allowChallenges = allowChallenges;
		this.viewable = viewable;
	}

	public Settings() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGlobalChat() {
		return globalChat;
	}

	public void setGlobalChat(int globalChat) {
		this.globalChat = globalChat;
	}

	public int getInGameChat() {
		return inGameChat;
	}

	public void setInGameChat(int inGameChat) {
		this.inGameChat = inGameChat;
	}

	public int getAcceptFriendship() {
		return acceptFriendship;
	}

	public void setAcceptFriendship(int acceptFriendship) {
		this.acceptFriendship = acceptFriendship;
	}

	public int getAllowChallenges() {
		return allowChallenges;
	}

	public void setAllowChallenges(int allowChallenges) {
		this.allowChallenges = allowChallenges;
	}

	public int getViewable() {
		return viewable;
	}

	public void setViewable(int viewable) {
		this.viewable = viewable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + acceptFriendship;
		result = prime * result + allowChallenges;
		result = prime * result + globalChat;
		result = prime * result + id;
		result = prime * result + inGameChat;
		result = prime * result + viewable;
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
		Settings other = (Settings) obj;
		if (acceptFriendship != other.acceptFriendship)
			return false;
		if (allowChallenges != other.allowChallenges)
			return false;
		if (globalChat != other.globalChat)
			return false;
		if (id != other.id)
			return false;
		if (inGameChat != other.inGameChat)
			return false;
		if (viewable != other.viewable)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Settings [id=" + id + ", globalChat=" + globalChat + ", inGameChat=" + inGameChat
				+ ", acceptFriendship=" + acceptFriendship + ", allowChallenges=" + allowChallenges + ", viewable="
				+ viewable + "]";
	}
	
	
}
